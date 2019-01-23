package com.hyzs.cidyth.module.demand.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hyzs.cidyth.common.enums.DemandFlowStepStatus;
import com.hyzs.cidyth.common.enums.DemandProcessAction;
import com.hyzs.cidyth.common.enums.DemandStatus;
import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.common.utils.Constant;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.common.utils.MessageUtil;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.attachment.service.AttachmentService;
import com.hyzs.cidyth.module.base.dao.CasesPartnerMapper;
import com.hyzs.cidyth.module.base.entity.CasesPartner;
import com.hyzs.cidyth.module.base.service.CasesService;
import com.hyzs.cidyth.module.demand.dao.DemandFlowHisMapper;
import com.hyzs.cidyth.module.demand.dao.DemandFlowRoleMapper;
import com.hyzs.cidyth.module.demand.entity.Demand;
import com.hyzs.cidyth.module.demand.entity.DemandFlowHis;
import com.hyzs.cidyth.module.demand.service.DemandFlowService;
import com.hyzs.cidyth.module.demand.service.DemandService;
import com.hyzs.cidyth.module.demand.vo.DemandFlowHisVO;
import com.hyzs.cidyth.module.demand.vo.DemandIndexVO;
import com.hyzs.cidyth.module.interaction.vo.DemandDetail;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.Premission;
import com.hyzs.cidyth.module.uc.vo.User;
import com.hyzs.cidyth.module.websocket.event.BussinessDataEvent;
import com.hyzs.cidyth.module.websocket.event.impl.BussinessDataEventPublisher;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 需求流程相关的服务
 * 
 * @author
 *
 */
@Service("demandFlowService")
public class DemandFlowServiceImpl implements DemandFlowService {
	private static final Logger logger = LoggerFactory.getLogger(DemandFlowServiceImpl.class);
	@Autowired
	private DemandFlowHisMapper demandFlowHisMapper;
	@Autowired
	@Qualifier("localAttachmentService")
	private AttachmentService attachmentService;
	@Autowired
	private DemandFlowRoleMapper demandFlowRoleMapper;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private DemandService demandService;
	@Autowired
	private CasesPartnerMapper casesPartnerMapper;// 合成作战小组
	@Autowired
	private CasesService casesService;

	@Override
	public boolean isAllocateRole(Constant.SystemInfo sysInfo) {
		User loginUser = UserUtil.getUser();
		if (loginUser == null) {
			throw new ServiceException("无法获取当前登录用户!");
		}
		Set<Premission> premession = loginUser.getPermissions(sysInfo);
		if (CollectionUtils.isNotEmpty(premession)) {
			for (Premission prem : premession) {
				if (prem.getCode().equals(DemandProcessAction.ALLOCATE.getPremissionCode())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	@Transactional(value = "masterTransactionManager", rollbackFor = { RuntimeException.class, Exception.class })
	public void insert(DemandFlowHisVO demandFlowHisVO) {
		if (casesService.isFinish(demandFlowHisVO.getAjbh())) {
			throw new ServiceException("该案件已侦结!");
		}
		if (demandFlowHisVO.getXqid() == null) {
			throw new ServiceException("获取需求ID失败!");
		}
		// 指派时，修改这条需求的紧急程度
		demandService.updateJjcd(demandFlowHisVO.getXqid(), demandFlowHisVO.getJjcd(), demandFlowHisVO.getAjbh());

		Demand demand = new Demand();
		demand.setId(demandFlowHisVO.getXqid());
		// 获取需要指派的需求
		demand = demandService.selectOne(demand);
		// 当前指派人是否为指派领导
		boolean isLeader = false;
		// 获取用户
		User loginUser = UserUtil.getUser();
		;
		if (loginUser == null) {
			throw new ServiceException("无法获取登录用户!");
		}
		if (loginUser.getDepartment() == null) {
			throw new ServiceException("无法获取登录用户的机构!");
		}
		if (StringUtils.isEmpty(demand.getZpld())) {
			throw new ServiceException("该需求没有指派领导!");
		} else {
			for (String item : demand.getZpld().split(",")) {
				if (item.equals(loginUser.getAccount())) {
					isLeader = true;
				}
			}
		}
		if (!isLeader) {
			throw new ServiceException("当前操作没有指派权限!");
		}

		// 如果需求没有被指派，则可以指派，否则其他领导不能再次指派
		if (demand.getQszt().equals(DemandStatus.INIT.name())) {
			/**
			 * 合成作战组员
			 */
			List<CasesPartner> partners = Lists.<CasesPartner>newArrayList();
			// 拆分多个警号
			String[] userNames = demandFlowHisVO.getJsrybh().split(",");
			// 计算签收时间为1天内
			Date qssjjz = DateUtil.getDateAfter(new Date(), 1);
			// 计算反馈截止时间为反馈天数（只算工作日）
			Date fksjjz = DateUtil.getWorkDateAfter(new Date(), demand.getFkts());

			// 领导指派多个同部门的人员
			for (String userName : userNames) {
				DemandFlowHis demandFlowHis = new DemandFlowHis();
				demandFlowHis.setXqid(demandFlowHisVO.getXqid());
				User user = null;
				try {
					user = userCenterService.getUserByUserName(userName);
					if (user == null) {
						throw new ServiceException("用户中心获取用户失败!");
					}
				} catch (Exception e) {
					throw new ServiceException("用户中心获取用户失败!");
				}

				demandFlowHis.setZprybh(loginUser.getAccount());
				demandFlowHis.setZprymc(loginUser.getName());
				demandFlowHis.setZpryjgbh(loginUser.getDeptid());
				demandFlowHis
						.setZpryjgmc(loginUser.getDepartment() == null ? "" : loginUser.getDepartment().getFullname());
				demandFlowHis.setJsrybh(user.getAccount());
				demandFlowHis.setJsrymc(user.getName());
				demandFlowHis.setJsryjgbh(user.getDeptid());
				demandFlowHis.setJsryjgmc(user.getDepartment() == null ? "" : user.getDepartment().getFullname());
				demandFlowHis.setQszt(DemandFlowStepStatus.WAIT_FOR_SIGN.name());
				demandFlowHis.setQssjjz(qssjjz);
				demandFlowHis.setFktsjz(demand.getFkts());
				demandFlowHis.setFksjjz(fksjjz);

				demandFlowHisMapper.insertSelective(demandFlowHis);

				if (casesPartnerMapper.isExist(demand.getAjbh(), demandFlowHis.getJsrybh()) == 0) {
					CasesPartner partner = new CasesPartner();
					partner.setAjbh(demand.getAjbh());
					partner.setJgdm(demandFlowHis.getJsryjgbh());
					partner.setJgmc(demandFlowHis.getJsryjgmc());
					partner.setJybh(demandFlowHis.getJsrybh());
					partner.setJyxm(demandFlowHis.getJsrymc());
					partner.setSjhm(user.getPhone());
					if (demand.getQqry() != null && demand.getQqry().trim().length() > 0) {
						try {
							User demandPubUser = userCenterService.getUserByUserName(demand.getQqry());
							if (demandPubUser != null) {
								partner.setLrryjgdm(demandPubUser.getDepartment() == null ? null
										: demandPubUser.getDepartment().getCode());
								partner.setLrryjgmc(demandPubUser.getDepartment() == null ? null
										: demandPubUser.getDepartment().getFullname());
								partner.setLrrybh(demandPubUser.getAccount());
								partner.setLrrymc(demandPubUser.getName());
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					partners.add(partner);
				}
				if (demandFlowHisVO.isSendMessage()) {
					// 发送短信：指派到具体的人时
					MessageUtil.sendSMS(user.getPhone(), loginUser.getDepartment().getFullname(), loginUser.getName(),
							demand.getXqmc(), DemandStatus.ALLOCATED);
				}
			}

			// 修改需求为已指派状态
			demandService.updateQszt(demandFlowHisVO.getXqid(), DemandStatus.ALLOCATED.name(),
					demandFlowHisVO.getAjbh());
			// 插入合成作战成员
			if (CollectionUtils.isNotEmpty(partners)) {
				int count = casesPartnerMapper.batchInsertCasePartners(partners);
				logger.info("saved {} partner(s) for case {} ", count, demand.getAjbh());
			}
			List<Map<String, Object>> attachments = attachmentService.loadAttachment("" + demand.getId(),
					TimeNodeEnum.DEMAND);
			BussinessDataEventPublisher.publishEvent(BussinessDataEvent.create(this, ((DemandDetail) new DemandDetail()
					.setDemand(demand).setDemandReceiver(partners).setAttacments(attachments))));
		} else {
			throw new ServiceException("需求已经被指派!");
		}

	}

	@Override
	public void sign(DemandFlowHisVO demandFlowHisVO) {
		Integer xqid = demandFlowHisVO.getXqid();
		if (casesService.isFinish(demandFlowHisVO.getAjbh())) {
			throw new ServiceException("该案件已侦结!");
		}
		if (xqid == null) {
			throw new ServiceException("获取需求ID失败!");
		}
		User loginUser = UserUtil.getUser();
		;
		if (loginUser == null) {
			throw new ServiceException("无法获取登录用户!");
		}

		DemandFlowHis myDemandHis = new DemandFlowHis();
		myDemandHis.setXqid(xqid);
		myDemandHis.setJsrybh(loginUser.getAccount());
		myDemandHis = demandFlowHisMapper.selectOne(myDemandHis);
		if (myDemandHis != null && myDemandHis.getLrsj() != null && myDemandHis.getQssjjz() != null) {
			// 签收截止时间小于当前时间为未超时签收，否则为超时签收
			myDemandHis.setQscs(new Date().compareTo(myDemandHis.getQssjjz()) > 0 ? 1 : 0);
			myDemandHis.setQsbz(1);
			myDemandHis.setQszt(DemandFlowStepStatus.SIGNED.name());
			myDemandHis.setFkcs(
					(myDemandHis.getFksjjz() != null && new Date().compareTo(myDemandHis.getFksjjz()) > 0) ? 1 : 0);
			demandFlowHisMapper.updateByPrimaryKeySelective(myDemandHis);
			// TODO
			// 因为一个需求被指派给多个人，各自人员有自己的签收、反馈状态，所以这里不需要修改需求的状态了，需求的状态使用需求节点的状态。
			// 如果需求当前为【已指派】则修改为【已签收】，否则不修改
			/*
			 * if(demand != null &&
			 * demand.getQszt().equals(DemandStatus.ALLOCATED.name())){
			 * demandService.updateQszt(xqid, DemandStatus.SIGNED.name()); }
			 */
		}
	}

	@Override
	public void retreat(DemandFlowHisVO demandFlowHisVO) {
		if (casesService.isFinish(demandFlowHisVO.getAjbh())) {
			throw new ServiceException("该案件已侦结!");
		}
		if (demandFlowHisVO.getXqid() == null) {
			throw new ServiceException("获取需求ID失败!");
		}
		User loginUser = UserUtil.getUser();
		;
		if (loginUser == null) {
			throw new ServiceException("无法获取登录用户!");
		}
		Demand demand = new Demand();
		demand.setId(demandFlowHisVO.getXqid());
		demand = demandService.selectOne(demand);
		if (!demand.getQszt().equals(DemandStatus.ALLOCATED.name())) {
			throw new ServiceException("当前节点无法退回!");
		}
		DemandFlowHis myDemandHis = new DemandFlowHis();
		myDemandHis.setXqid(demandFlowHisVO.getXqid());
		myDemandHis.setJsrybh(loginUser.getAccount());
		myDemandHis = demandFlowHisMapper.selectOne(myDemandHis);
		if (myDemandHis != null) {
			myDemandHis.setQszt(DemandFlowStepStatus.RETREAT.name());
			myDemandHis.setQsztms(demandFlowHisVO.getQsztms());
			demandFlowHisMapper.updateByPrimaryKeySelective(myDemandHis);
		}
	}

	@Override
	public void feedback(Integer xqid, String ajbh) {
		if (casesService.isFinish(ajbh)) {
			throw new ServiceException("该案件已侦结!");
		}
		if (xqid == null) {
			throw new ServiceException("获取需求ID失败!");
		}
		/*Demand demand = new Demand();
		demand.setId(xqid);
		demand = demandService.selectOne(demand);*/
		// 获取用户
		User loginUser = UserUtil.getUser();
		if (loginUser == null) {
			throw new ServiceException("无法获取登录用户!");
		}
		/**
		 * 1、修改需求节点表的qszt为反馈状态、修改qssj为当前操作时间，只有第一次反馈才执行这个qssj更新
		 */
		DemandFlowHis myDemandHis = new DemandFlowHis();
		myDemandHis.setXqid(xqid);
		myDemandHis.setJsrybh(loginUser.getAccount());

		DemandFlowHis returnDemandFlowHis = demandFlowHisMapper.selectOne(myDemandHis);

		//当前需求节点的状态不是反馈状态则修改为反馈状态，且qssj为当前时间
		if(returnDemandFlowHis != null && !returnDemandFlowHis.getQszt().equals(DemandFlowStepStatus.FEEDBACKED.name())) {
			myDemandHis.setQssj(new Date());
			myDemandHis.setQszt(DemandFlowStepStatus.FEEDBACKED.name());
			myDemandHis.setId(returnDemandFlowHis.getId());
			demandFlowHisMapper.updateByPrimaryKeySelective(myDemandHis);
		}


		// 如果需求当前为【已签收】则修改为【已反馈】，否则不修改
		/*if (demand != null && demand.getQszt().equals(DemandStatus.SIGNED.name())) {
			demandService.updateQszt(xqid, DemandStatus.FEEDBACKED.name(), ajbh);
		}*/
	}

	@Override
	public Integer countByAllocateSelf(Integer xqid, String jsrybh) {
		DemandFlowHis demandFlowHis = new DemandFlowHis();
		demandFlowHis.setXqid(xqid);
		demandFlowHis.setJsrybh(jsrybh);
		return demandFlowHisMapper.selectCount(demandFlowHis);
	}

	@Override
	public List<DemandFlowHisVO> listMyAllocatedFlow(Integer xqid) {
		List<DemandFlowHis> lsDemandFlowHis = demandFlowHisMapper.listMyAllocatedFlow(xqid);
		List<DemandFlowHisVO> lsDemandFlowVO = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(lsDemandFlowHis)) {
			for (DemandFlowHis flow : lsDemandFlowHis) {
				DemandFlowHisVO demandFlowHisVO = new DemandFlowHisVO();
				BeanUtils.copyProperties(flow, demandFlowHisVO);
				if (demandFlowHisVO.getQszt().equals(DemandFlowStepStatus.WAIT_FOR_SIGN.name())) {
					demandFlowHisVO.setQsztCn(DemandFlowStepStatus.WAIT_FOR_SIGN.descp());
				} else if (demandFlowHisVO.getQszt().equals(DemandFlowStepStatus.RETREAT.name())) {
					demandFlowHisVO.setQsztCn(DemandFlowStepStatus.RETREAT.descp());
				} else if (demandFlowHisVO.getQszt().equals(DemandStatus.SIGNED.name())) {
					demandFlowHisVO.setQsztCn(DemandStatus.SIGNED.descp());
				} else if (demandFlowHisVO.getQszt().equals(DemandStatus.FEEDBACKED.name())) {
					demandFlowHisVO.setQsztCn(DemandStatus.FEEDBACKED.descp());
				} else {
					demandFlowHisVO.setQsztCn("");
				}
				lsDemandFlowVO.add(demandFlowHisVO);
			}
		}
		return lsDemandFlowVO;

	}

	@Override
	public DemandFlowHis getDemandFlowHis(Integer xqid, String jsrybh) {
		if (xqid == null) {
			throw new ServiceException("获取需求ID失败!");
		}
		if (jsrybh == null) {
			throw new ServiceException("无法获取接收人员编号!");
		}
		DemandFlowHis demandFlowHis = new DemandFlowHis();
		demandFlowHis.setXqid(xqid);
		demandFlowHis.setJsrybh(jsrybh);
		return demandFlowHisMapper.selectOne(demandFlowHis);
	}

	@Override
	public void delayApply(DemandFlowHisVO demandFlowHisVO) {
		if (casesService.isFinish(demandFlowHisVO.getAjbh())) {
			throw new ServiceException("该案件已侦结!");
		}
		if (demandFlowHisVO.getXqid() == null) {
			throw new ServiceException("获取需求ID失败!");
		}
		User loginUser = UserUtil.getUser();
		if (loginUser == null) {
			throw new ServiceException("无法获取登录用户!");
		}
		DemandFlowHis myDemandHis = new DemandFlowHis();
		myDemandHis.setXqid(demandFlowHisVO.getXqid());
		myDemandHis.setJsrybh(loginUser.getAccount());
		myDemandHis = demandFlowHisMapper.selectOne(myDemandHis);
		if (myDemandHis != null) {
			myDemandHis.setFkcs(1);
			myDemandHis.setFksqyq(1);
			myDemandHis.setFksqyy(demandFlowHisVO.getFksqyy());
			demandFlowHisMapper.updateByPrimaryKeySelective(myDemandHis);
		}
	}

	@Override
	public List<DemandFlowHis> getDemandFlowHis(Integer xqid, DemandFlowStepStatus... status) {
		if (xqid == null)
			throw new ServiceException("需求ID不能为空!");
		if (status == null || status.length == 0)
			throw new ServiceException("需求流转状态不能为空!");
		List<DemandFlowHis> result = demandFlowHisMapper.selectByStatus$xqid(xqid,Lists.transform(Lists.newArrayList(status), new Function<DemandFlowStepStatus,String>(){
			@Override
			public String apply(DemandFlowStepStatus param) {
				return param.name();
			}
		}));
		return result;
	}

	@Override
	public List<Map<String, Object>> overdueFeedback() {
		List<Dept> lsDept = null;
		try {
			lsDept = userCenterService.getBranchDept();
			if (CollectionUtils.isEmpty(lsDept)) {
				return null;
			}
		} catch (Exception e) {
			throw new ServiceException("无法获取机构数据");
		}
		List<Map<String, Object>> lsGroup = demandFlowHisMapper.overdueFeedback();
		Map<String, String> deptMap = Maps.newHashMap();
		for (Dept dept : lsDept) {
			if (org.apache.commons.lang3.StringUtils.isNotEmpty(dept.getCode())) {
				deptMap.put(dept.getCode().substring(0, 6), dept.getFullname());
			}
		}
		List<Map<String, Object>> lsResult = Lists.newArrayList();
		String deptCode = "";
		String deptName = "";
		for (Map.Entry<String, String> deptMapEntry : deptMap.entrySet()) {
			//判断数据库返回的是否存在该机构的结果
			boolean isExists = false;
			deptCode = deptMapEntry.getKey();
			deptName = deptMapEntry.getValue();
			for (Map<String, Object> resultMap : lsGroup) {
				if (deptCode.equals(resultMap.get("prefix_code"))) {
					resultMap.put("name", deptName);
					lsResult.add(resultMap);
					isExists = true;
					break;
				}
			}
			//不存在，则添加一个对象
			if (!isExists) {
				Map<String, Object> insertMap = Maps.newHashMap();
				insertMap.put("prefix_code", deptCode);
				insertMap.put("name", deptName);
				insertMap.put("value", 0);
				lsResult.add(insertMap);
			}
		}
		return lsResult;
	}

	@Override
	public PageInfo<Map<String, Object>> overdueFeedbackDetail(String prefixCode, Page page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		PageInfo<Map<String, Object>> pageResult = new PageInfo<>(demandFlowHisMapper.overdueFeedbackDetail(prefixCode));
		pageResult.setPageNum(page.getPageNum());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public boolean isDelayProcess(Integer xqid, String jsrybh) {
		// 所有待办的状态
		List<String> lsWaitHandleStatus = Lists.newArrayList(
				DemandFlowStepStatus.ALLOCATED.name(),
				DemandFlowStepStatus.WAIT_FOR_SIGN.name(),
				DemandFlowStepStatus.SIGNED.name());
		boolean isDelay = false;
		DemandFlowHis myDemandFlow = getDemandFlowHis(xqid, jsrybh);
		if(myDemandFlow == null){
			return true;
		}
		//超期时间
		Date delayDate = null;
		if(myDemandFlow.getFksjjz() != null){
			//delayDate = DateUtil.getWorkDateAfter(myDemandFlow.getFksjjz(), myDemandFlow.getFktsjz());
			delayDate = myDemandFlow.getFksjjz();
			/**
			 * 同时满足一下3点则代表超期
			 * 1、反馈截止时间小于当前时间
			 * 2、没有申请延期
			 * 3、待签收、未反馈状态
			 */
			isDelay = delayDate.compareTo(new Date()) < 0 && myDemandFlow.getFksqyq() == 0 && lsWaitHandleStatus.contains(myDemandFlow.getQszt());
		}
		return isDelay;
	}

	@Override
	public int delayCount(Integer xqid, String jsrybh) {
		DemandFlowHis myDemandFlow = getDemandFlowHis(xqid, jsrybh);
		return DateUtil.subWorkDays(myDemandFlow.getFksjjz(), new Date());
	}

	@Override
	public PageInfo<DemandIndexVO> waitSign(Page page) {
		return waitHandler(DemandFlowStepStatus.WAIT_FOR_SIGN.name(), page);
	}

	@Override
	public PageInfo<DemandIndexVO> waitFeedBack(Page page) {
		return waitHandler(DemandFlowStepStatus.SIGNED.name(), page);
	}

	/**
	 * 首页：通用待签收、待反馈
	 * @param qszt
	 * @param page
	 * @return
	 */
	public PageInfo<DemandIndexVO> waitHandler(String qszt, Page page) {
		User loginUser = UserUtil.getUser();
		if (loginUser == null) {
			throw new ServiceException("无法获取登录用户!");
		}
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(demandFlowHisMapper.waitHandlerDetail(qszt, loginUser.getAccount()));
		List<DemandIndexVO> lsVO = Lists.newArrayList();
		for (Map<String, Object> map : pageInfo.getList()) {
			DemandIndexVO vo = new DemandIndexVO();
			vo.setXqmc(map.get("xqmc").toString());
			vo.setQqdw(map.get("qqdw").toString());
			vo.setQqsj((Date) map.get("qqsj"));
			vo.setLrrymc(map.get("lrrymc").toString());
			vo.setJsdw(map.get("jsdw").toString());
			vo.setJsrymc(map.get("jsrymc").toString());
			lsVO.add(vo);
		}
		PageInfo<DemandIndexVO> pageResult = new PageInfo<>();
		pageResult.setList(lsVO);
		pageResult.setTotal(pageInfo.getTotal());
		pageResult.setPageNum(page.getPageNum());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}


}

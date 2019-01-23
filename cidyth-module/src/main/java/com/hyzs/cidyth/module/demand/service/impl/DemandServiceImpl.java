package com.hyzs.cidyth.module.demand.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hyzs.cidyth.common.enums.*;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.common.utils.MessageUtil;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.attachment.service.AttachmentService;
import com.hyzs.cidyth.module.base.dao.CasesPartnerMapper;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.base.entity.CasesGroup;
import com.hyzs.cidyth.module.base.entity.CasesPartner;
import com.hyzs.cidyth.module.base.service.CasesGroupService;
import com.hyzs.cidyth.module.base.service.CasesService;
import com.hyzs.cidyth.module.clue.dao.ClueMapper;
import com.hyzs.cidyth.module.clue.dao.DemandClueMapper;
import com.hyzs.cidyth.module.clue.entity.DemandClue;
import com.hyzs.cidyth.module.clue.service.ClueService;
import com.hyzs.cidyth.module.demand.dao.DemandFlowHisMapper;
import com.hyzs.cidyth.module.demand.dao.DemandMapper;
import com.hyzs.cidyth.module.demand.entity.Demand;
import com.hyzs.cidyth.module.demand.entity.DemandFlowHis;
import com.hyzs.cidyth.module.demand.service.DemandFlowService;
import com.hyzs.cidyth.module.demand.service.DemandService;
import com.hyzs.cidyth.module.demand.vo.DemandBatchVO;
import com.hyzs.cidyth.module.demand.vo.DemandIndexVO;
import com.hyzs.cidyth.module.demand.vo.DemandVO;
import com.hyzs.cidyth.module.dic.service.DicService;
import com.hyzs.cidyth.module.integral.service.IntegralConfigService;
import com.hyzs.cidyth.module.integral.service.IntegralHisService;
import com.hyzs.cidyth.module.interaction.vo.DemandDetail;
import com.hyzs.cidyth.module.mind.service.MindService;
import com.hyzs.cidyth.module.time.service.TimeNodeService;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;
import com.hyzs.cidyth.module.websocket.event.BussinessDataEvent;
import com.hyzs.cidyth.module.websocket.event.impl.BussinessDataEventPublisher;
import com.hyzs.psd.gafa.daf.mybatis.tk.entity.Example;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("DemandService")
public class DemandServiceImpl implements DemandService {

	private static final Logger logger = LoggerFactory.getLogger(DemandServiceImpl.class);

	@Autowired
	private DemandMapper demandMapper;
	@Autowired
	private CasesPartnerMapper casesPartnerMapper;
	@Autowired
	private CasesGroupService casesGroupService;//合成作战小组
	@Autowired
	private DemandClueMapper demandClueMapper;
	@Autowired
	@Qualifier("localAttachmentService")
	private AttachmentService attachmentService;
	@Autowired
	private CasesService casesService;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private DemandFlowService demandFlowService;
	@Autowired
	private DemandFlowHisMapper demandFlowHisMapper;
	@Autowired
	private MindService mindService;
	@Autowired
	private DicService dicService;
	@Autowired
	private TimeNodeService timeNodeService;
	@Autowired
	private IntegralHisService integralHisService;
	@Autowired
	private IntegralConfigService integralConfigService;
	@Autowired
	private ClueMapper clueMapper;
	@Autowired
	private ClueService clueService;

	@Override
	public PageInfo<DemandVO> list(DemandVO demandParam, Page page) {
		if(demandParam==null){
			demandParam = new DemandVO();
		}
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取当前登录用户!");
		}else{
			demandParam.setLrry(loginUser.getAccount());
		}

		// 如果时间段不为空
		if (!StringUtils.isBlank(demandParam.getTimeSection())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if (demandParam.getTimeSection().equals(DemandTimeEnum.THREE_DAY)) {
				// 时间为3天内
				Date lastDay = DateUtil.getDateBefore(new Date(), 2);
				demandParam.setBeginCreateTime(sdf.format(lastDay) + " 00:00:00");
				demandParam.setEndCreateTime(sdf.format(new Date()) + " 23:59:59");
			}
			if (demandParam.getTimeSection().equals(DemandTimeEnum.THIRTY_DAY)) {
				// 时间为30天内
				Date lastDay = DateUtil.getDateBefore(new Date(), 30);
				demandParam.setBeginCreateTime(sdf.format(lastDay) + " 00:00:00");
				demandParam.setEndCreateTime(sdf.format(new Date()) + " 23:59:59");
			}
		}

		Demand queryDemand = new Demand();
		BeanUtils.copyProperties(demandParam, queryDemand);

		PageHelper.startPage(page.getPageNum(), page.getPageSize(), "id desc");
		PageInfo<Demand> pageDemand = new PageInfo<Demand>(demandMapper.select(queryDemand));

		List<DemandVO> lsDemandVO = new ArrayList<DemandVO>();
		List<Demand> lsDemand = pageDemand.getList();
		DemandClue demandClueParam = new DemandClue();

		for (Demand demand : lsDemand) {
			DemandVO demandVO = new DemandVO();
			BeanUtils.copyProperties(demand, demandVO);
			demandClueParam.setDemandId(demand.getId());
			demandClueParam.setClueType(Integer.valueOf(ClueTypeEnum.RETURN.code()));
			demandVO.setXqlxCn(dicService.getValueByKey("xqlx", demandVO.getXqlx()));
			demandVO.setClueCount(demandClueMapper.selectCount(demandClueParam));
			CaseStateEnum caseStateEnum = CaseStateEnum.ofCode(casesService.getCaseByAjbh(demand.getAjbh()).getBdajstate());
			demandVO.setBdajstate(caseStateEnum == null ? "" : caseStateEnum.name());
			// 获取需求的上传附件
			List<Map<String, Object>> lsAttachment = attachmentService.loadAttachment(String.valueOf(demand.getId()), TimeNodeEnum.DEMAND);
			demandVO.setLsAttachment(lsAttachment);

			// 加载需求下的线索
			demandVO.setLsClueVO(clueService.listClueByDemandId(demand.getId()));

			// 加载需求下的接收人员的反馈情况
			demandVO.setLsDemandFlowHisVO(demandFlowService.listMyAllocatedFlow(demand.getId()));

			//设置当前需求状态
			demandVO.setQsztCn(DemandFlowStepStatus.ofName(demandVO.getQszt()).descp());

			lsDemandVO.add(demandVO);
		}

		PageInfo<DemandVO> pageDemandVO = new PageInfo<DemandVO>(lsDemandVO);
		pageDemandVO.setTotal(pageDemand.getTotal());
		pageDemandVO.setPageNum(page.getPageNum());
		pageDemandVO.setPageSize(page.getPageSize());

		return pageDemandVO;
	}

	@Override
	public PageInfo<DemandVO> waitHandle(DemandVO demandParam, Page page) {
		if(demandParam==null){
			demandParam = new DemandVO();
		}
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取当前登录用户!");
		}else{
			demandParam.setLrry(loginUser.getAccount());
		}

		PageHelper.startPage(page.getPageNum(), page.getPageSize(), "id desc");
		PageInfo<Demand> pageDemand = new PageInfo<Demand>(demandMapper.selectByPage(demandParam));

		List<DemandVO> lsDemandVO = new ArrayList<DemandVO>();
		List<Demand> lsDemand = pageDemand.getList();
		DemandClue demandClueParam = new DemandClue();

		for (Demand demand : lsDemand) {
			DemandVO demandVO = new DemandVO();
			BeanUtils.copyProperties(demand, demandVO);
			demandClueParam.setDemandId(demand.getId());
			demandClueParam.setClueType(Integer.valueOf(ClueTypeEnum.RETURN.code()));
			demandVO.setXqlxCn(dicService.getValueByKey("xqlx", demandVO.getXqlx()));
			demandVO.setClueCount(demandClueMapper.selectCount(demandClueParam));
			CaseStateEnum caseStateEnum = CaseStateEnum.ofCode(casesService.getCaseByAjbh(demand.getAjbh()).getBdajstate());
			demandVO.setBdajstate(caseStateEnum == null ? "" : caseStateEnum.name());
			// 获取需求的上传附件
			List<Map<String, Object>> lsAttachment = attachmentService.loadAttachment(String.valueOf(demand.getId()), TimeNodeEnum.DEMAND);
			demandVO.setLsAttachment(lsAttachment);

			// 指派领导是我
			boolean myAllocated = arrayContainer(demand.getZpld(), loginUser.getAccount());
			// 接收指派需求是我
			boolean myReceive = demandFlowService.countByAllocateSelf(demandVO.getId(), loginUser.getAccount()) > 0 ? true : false;
			// 是否超期
			boolean isDelay = false;
			// 超期天数
			int delayCount = 0;
			// 处于需要待办的状态


			// 加载所有接收人员的反馈情况
			demandVO.setLsDemandFlowHisVO(demandFlowService.listMyAllocatedFlow(demand.getId()));

			// 接收需求是我则计算是否超期
			if(myReceive) {
				isDelay = demandFlowService.isDelayProcess(demandVO.getId(), loginUser.getAccount());
				delayCount = demandFlowService.delayCount(demandVO.getId(), loginUser.getAccount());
				/*myDemandFlow = demandFlowService.getDemandFlowHis(demandVO.getId(), loginUser.getAccount());
				//超期时间
				Date delayDate = null;
				if(myDemandFlow.getFksjjz() != null){
					delayDate = DateUtil.getWorkDateAfter(myDemandFlow.getFksjjz(), myDemandFlow.getFktsjz());
					//超期时间小于当前时间（已超期）且未申请延期
					isDelay = delayDate.compareTo(new Date()) < 0 && myDemandFlow.getFksqyq() == 0;
					delayCount = DateUtil.subWorkDays(myDemandFlow.getFksjjz(), new Date());
				}*/
			}

			//设置当前需求状态
			demandVO.setQsztCn(DemandFlowStepStatus.ofName(demandVO.getQszt()).descp());

			//指派领导是我，接收需求也是我
			if(myAllocated && myReceive){
				if(demandVO.getQszt().equals(DemandFlowStepStatus.INIT.name())){
					// 如果是【待指派】,则标记该需求显示【指派按钮】
					initProcess(demandVO);
				}else if(isDelay){
					//处于待办状态并且已超期则记该需求状态为DELAY
					demandVO.setOperationStatus(DemandFlowStepStatus.DELAY.name());
					demandVO.setOperationStatusTip("已经超过" + delayCount + "个工作日未反馈，请申请延期处理");
				}else{
					//否则根据状态判断操作状态
					normalProcess(demandVO);
				}
			}else if(myAllocated){// 指派领导是我
				if(demandVO.getQszt().equals(DemandStatus.INIT.name())){// 如果是【待指派】,则标记该需求显示【指派按钮】
					initProcess(demandVO);
				}else{
					demandVO.setQsztCn(DemandStatus.ALLOCATED.descp());
				}
			}else if(myReceive){// 接收需求是我
				//否则根据状态判断操作状态
				normalProcess(demandVO);
			}

			lsDemandVO.add(demandVO);
		}

		PageInfo<DemandVO> pageDemandVO = new PageInfo<DemandVO>(lsDemandVO);
		pageDemandVO.setTotal(pageDemand.getTotal());
		pageDemandVO.setPageNum(page.getPageNum());
		pageDemandVO.setPageSize(page.getPageSize());

		return pageDemandVO;
	}

	/**
	 * 待指派流程处理
	 * @param demandVO
	 */
	private void initProcess(DemandVO demandVO) {
		demandVO.setQsztCn(DemandStatus.INIT.descp());
		demandVO.setOperationStatus(DemandStatus.INIT.name());
		demandVO.setOperationStatusTip("请在12小时内指派");
	}

	/**
	 * 正常流程处理
	 * @param demandVO
	 */
	private void normalProcess(DemandVO demandVO) {
		//否则根据状态判断操作状态
		if(demandVO.getQszt().equals(DemandFlowStepStatus.ALLOCATED.name()) || demandVO.getQszt().equals(DemandFlowStepStatus.WAIT_FOR_SIGN.name())){
			// 如果是【已指派/待签收】,则标记该需求状态为WAIT_FOR_SIGN
			demandVO.setQsztCn(DemandFlowStepStatus.WAIT_FOR_SIGN.descp());
			demandVO.setOperationStatus(DemandFlowStepStatus.WAIT_FOR_SIGN.name());
			demandVO.setOperationStatusTip("请在24小时内签收");
		}else if(demandVO.getQszt().equals(DemandFlowStepStatus.SIGNED.name())){
			// 如果是【已签收】,则标记该需求状态为SIGNED
			demandVO.setQsztCn(DemandStatus.SIGNED.descp());
			demandVO.setOperationStatus(DemandStatus.SIGNED.name());
			demandVO.setOperationStatusTip("请在" + demandVO.getFkts() + "个工作日内反馈");
		}else if(demandVO.getQszt().equals(DemandFlowStepStatus.RETREAT.name())){
			// 如果是【已退回】,则标记该需求状态为RETREAT
			demandVO.setQsztCn(DemandFlowStepStatus.RETREAT.descp());
			demandVO.setOperationStatus(DemandFlowStepStatus.RETREAT.name());
			demandVO.setOperationStatusTip(DemandFlowStepStatus.RETREAT.descp());
		}else{
			// 如果是【已反馈】,则标记该需求状态为FEEDBACKED
			demandVO.setQsztCn(DemandFlowStepStatus.FEEDBACKED.descp());
			demandVO.setOperationStatus(DemandFlowStepStatus.FEEDBACKED.name());
			demandVO.setOperationStatusTip(DemandFlowStepStatus.FEEDBACKED.descp());
		}
	}

	@Override
	public int getCountByAjbh(String ajbh) {
		Demand demand = new Demand();
		demand.setAjbh(ajbh);
		return demandMapper.selectCount(demand);
	}

	@Transactional(value = "masterTransactionManager", rollbackFor = { RuntimeException.class, Exception.class })
	public Map<String, Object> insert(DemandVO demandVO){
		List<DemandVO> lsDemandVO = Lists.newArrayList();
		lsDemandVO.add(demandVO);

		DemandBatchVO demandBatchVO = new DemandBatchVO();
		demandBatchVO.setLsDemandVO(lsDemandVO);
		return insert(demandBatchVO);
	}

	@Transactional(value = "masterTransactionManager", rollbackFor = { RuntimeException.class, Exception.class })
	public Map<String, Object> insert(DemandBatchVO demandBatchVO) {
		Map<String, Object> mapResult = Maps.newHashMap();
		// 积分次数累计
		int scoreNum = 0;
		// 获取用户
		User loginUser = UserUtil.getUser();
		Dept loginUserDept = loginUser.getDepartment();
		if (loginUser == null) {
			throw new ServiceException("无法获取当前登录用户!");
		}
		if (loginUserDept == null) {
			throw new ServiceException("无法获取当前登录用户的机构!");
		}
		// 警种类别
		String jzlb = PoliceTypeEnum.ofCode(String.valueOf(loginUserDept.getPoliceType())) == null ? "" : PoliceTypeEnum.ofCode(String.valueOf(loginUserDept.getPoliceType())).name();
		// 循环处理批量需求
		for(DemandVO demandVO : demandBatchVO.getLsDemandVO()){
			if(casesService.isFinish(demandVO.getAjbh())){
				throw new ServiceException("该案件已侦结!");
			}
			//接收单位编号
			String jsdwbh = demandVO.getJsdwbh();
			//反馈天数
			Integer fkts = demandVO.getFkts();
			/**
			 * 合成作战组员
			 */
			List<CasesPartner> partners = Lists.<CasesPartner>newArrayList();
			List<CasesGroup> lsCasesGroups = Lists.newArrayList();

			Demand demand = new Demand();
			BeanUtils.copyProperties(demandVO, demand);
			demand.setQszt(DemandStatus.INIT.name());
			demand.setQqry(loginUser.getAccount());
			demand.setLrry(loginUser.getAccount());
			demand.setLrrymc(loginUser.getName());
			loginUserDept = loginUser.getDepartment();
			demand.setQqdw(loginUserDept.getFullname());
			demand.setQqdwbh(loginUserDept.getCode());
			demand.setJzlb(jzlb);

			List<Demand> lsExecuteDemand = listExecuteDemand(demand.getJsdwbh(), demand.getAjbh());
			// 判断该案件该接收单位是否接收到1条以上的需求且状态是已经签收之后的状态，如果是，则后续需求不需要指派和签收
			// （原来的流程不变，只是判断如果有过1次处理过该案件的需求，则在insert需求的时候设置状态为【已签收】，需求的his数据和上一次的状态一致）
			/*if(CollectionUtils.isNotEmpty(lsExecuteDemand)){
				// 曾经处理过的需求对象
				Demand ExecutedDemand = lsExecuteDemand.get(0);
				// 设置需求状态为已签收
				demand.setQszt(DemandStatus.SIGNED.name());
				// 设置为曾经指派过的领导
				demand.setZpld(ExecutedDemand.getZpld());
			}else{*/
			if(casesPartnerMapper.isExist(demand.getAjbh(), loginUser.getAccount())==0) {
				CasesPartner partnerAsLoginUser = new CasesPartner();
				partnerAsLoginUser.setAjbh(demand.getAjbh());
				Dept dept = loginUser.getDepartment();
				partnerAsLoginUser.setJgdm(loginUser.getDepartment() == null ? null : loginUser.getDepartment().getCode());
				partnerAsLoginUser.setJgmc(loginUser.getDepartment() == null ? null : loginUser.getDepartment().getFullname());
				partnerAsLoginUser.setJybh(loginUser.getAccount());
				partnerAsLoginUser.setJyxm(loginUser.getName());
				partnerAsLoginUser.setSjhm(loginUser.getPhone());
				partnerAsLoginUser.setLrryjgdm(loginUser.getDepartment() == null ? null : loginUser.getDepartment().getCode());
				partnerAsLoginUser.setLrryjgmc(loginUser.getDepartment() == null ? null : loginUser.getDepartment().getFullname());
				partnerAsLoginUser.setLrrybh(loginUser.getAccount());
				partnerAsLoginUser.setLrrymc(loginUser.getName());
				casesPartnerMapper.insertSelective(partnerAsLoginUser);
			}
			casesGroupService.insert(demand.getAjbh(), loginUser);
			try {
				// 指派领导账号
				String allocateUserAccount = "";
				// 根据接收单位编号获取该单位的所有用户有指派权限的用户
				List<User> lsAllocateUsers = userCenterService.getUsersByDepartmentCodeAndPremission(jsdwbh,Lists.newArrayList(DemandProcessAction.ALLOCATE.getPremissionCode()));
				if(CollectionUtils.isNotEmpty(lsAllocateUsers)){
					for(User allocateUser : lsAllocateUsers){
						if(allocateUser != null){
							if(StringUtils.isNotBlank(demand.getAjbh())&&StringUtils.isNotBlank(allocateUser.getAccount())){
								if(casesPartnerMapper.isExist(demand.getAjbh(), allocateUser.getAccount())==0){
									CasesPartner partnerAsAllocator = new CasesPartner();
									partnerAsAllocator.setAjbh(demand.getAjbh());
									partnerAsAllocator.setJgdm(allocateUser.getDepartment()==null?null:allocateUser.getDepartment().getCode());
									partnerAsAllocator.setJgmc(allocateUser.getDepartment()==null?null:allocateUser.getDepartment().getFullname());
									partnerAsAllocator.setJybh(allocateUser.getAccount());
									partnerAsAllocator.setJyxm(allocateUser.getName());
									partnerAsAllocator.setSjhm(allocateUser.getPhone());
									partnerAsAllocator.setLrryjgdm(loginUser.getDepartment()==null?null:loginUser.getDepartment().getCode());
									partnerAsAllocator.setLrryjgmc(loginUser.getDepartment()==null?null:loginUser.getDepartment().getFullname());
									partnerAsAllocator.setLrrybh(loginUser.getAccount());
									partnerAsAllocator.setLrrymc(loginUser.getName());
									partners.add(partnerAsAllocator);//指派领导作为合成作战组员
								}
								if(!casesGroupService.isExist(demand.getAjbh(), allocateUser.getAccount())){
									CasesGroup groupAsAllocator = new CasesGroup();
									groupAsAllocator.setAjbh(demand.getAjbh());
									groupAsAllocator.setJgdm(allocateUser.getDepartment()==null?null:allocateUser.getDepartment().getCode());
									groupAsAllocator.setJgmc(allocateUser.getDepartment()==null?null:allocateUser.getDepartment().getFullname());
									groupAsAllocator.setJybh(allocateUser.getAccount());
									groupAsAllocator.setJyxm(allocateUser.getName());
									groupAsAllocator.setSjhm(allocateUser.getPhone());
									groupAsAllocator.setLrryjgdm(loginUser.getDepartment()==null?null:loginUser.getDepartment().getCode());
									groupAsAllocator.setLrryjgmc(loginUser.getDepartment()==null?null:loginUser.getDepartment().getFullname());
									groupAsAllocator.setLrrybh(loginUser.getAccount());
									groupAsAllocator.setLrrymc(loginUser.getName());
									lsCasesGroups.add(groupAsAllocator);//指派领导作为合成作战组员
								}
							}
							allocateUserAccount += allocateUser.getAccount() + ",";
							if(demandVO.isSendMessage()){
								// 发送短信：创建需求让领导收到
								MessageUtil.sendSMS(allocateUser.getPhone(), loginUserDept.getFullname(), loginUser.getName(), demand.getXqmc(), DemandStatus.INIT);
							}
						}
					}
				}
				demand.setZpld(allocateUserAccount.length() > 0 ? allocateUserAccount.substring(0, allocateUserAccount.length() - 1) : allocateUserAccount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				// 接收单位对象
				Dept deptJsdw = userCenterService.getDeptByCode(jsdwbh);
				if(deptJsdw != null){
					demand.setJsdw(deptJsdw.getFullname());
					demand.setJsdwbh(deptJsdw.getCode());
				}
			} catch (Exception e) {
				throw new ServiceException("无法获取需求接收单位!");
			}
			//}

			// 插入需求表
			demandMapper.insertSelective(demand);

			//保存合成作战组员
			if(CollectionUtils.isNotEmpty(partners)){
				int count = casesPartnerMapper.batchInsertCasePartners(partners);
				logger.info("saved {} partner(s) for case {} ",count,demand.getAjbh());
			}

			// 获取案件对象
			Cases cases = casesService.getCaseByAjbh(demand.getAjbh());
			List<Map<String,Object>> attachments = null;
			if(CollectionUtils.isNotEmpty(demandVO.getFiles())){
				// 保存附件
				attachments = attachmentService.insert(demandVO.getFiles(), demandVO.getFileComment(), String.valueOf(demand.getId()), TimeNodeEnum.DEMAND.name());
			}
			// 保存时间轴
			timeNodeService.insert(demand.getAjbh(), demand.getId(), TableTypeEnum.DEMAND.name(), demand.getXqmc(), demand.getXqnr(), demand.getJsdwbh(), demand.getQqsj());
			// 案件下的需求发送给某部门不超过5次则保存积分
			if(integralConfigService.getAwardLimit(RuleTypeEnum.DEMAND.name()) < 6){

			}
			if(saveIntegralRecord(demand.getAjbh(), demand.getJsdwbh()) > 0){
				scoreNum++;
			}
			// 保存思维导图
			mindService.insert(demand.getAjbh(), demand.getId(), cases.getId(), TimeNodeEnum.DEMAND.name(), TimeNodeEnum.CASE.name());
			BussinessDataEventPublisher.publishEvent(BussinessDataEvent.create(this, ((DemandDetail) new DemandDetail().setDemand(demand).setAttacments(attachments))));

		}
		// 获取创建需求的得分分数
		Float score = integralConfigService.getScoreByRuleType(RuleTypeEnum.DEMAND.name());
		mapResult.put("score", scoreNum * score);
		return mapResult;
	}

	@Override
	public DemandVO detail(Integer id) {
		Demand demand = demandMapper.selectByPrimaryKey(id);
		DemandVO demandVO = new DemandVO();
		BeanUtils.copyProperties(demand, demandVO);
		demandVO.setQsztCn(DemandStatus.ofName(demandVO.getQszt()).descp());
		return demandVO;
	}

	@Override
	public void updateQszt(Integer id, String qszt, String ajbh) {
		if(casesService.isFinish(ajbh)){
			throw new ServiceException("该案件已侦结!");
		}
		demandMapper.updateQszt(id, qszt);
	}

	@Override
	public Demand selectOne(Demand demand) {
		return demandMapper.selectOne(demand);
	}

	public boolean arrayContainer(String arrStr, String target){
		if(StringUtils.isEmpty(arrStr)){
			return false;
		}
		String[] arr = arrStr.split(",");
		for(String str: arr){
			if(str.equals(target))
				return true;
		}
		return false;
	}

	// 指派时：修改紧急程度状态
	public void updateJjcd(Integer id, Integer jjcd, String ajbh){
		if(casesService.isFinish(ajbh)){
			throw new ServiceException("该案件已侦结!");
		}
		Demand demand = new Demand();
		demand.setId(id);
		demand.setJjcd(jjcd);
		demandMapper.updateByPrimaryKeySelective(demand);
	}

	@Override
	public List<Demand> listExecuteDemand(String ajbh, String jsdwbh) {
		return demandMapper.listExecuteDemand(ajbh, jsdwbh);
	}

	@Override
	public List<Demand> checkDemandInfoExists(List<Demand> lsDemand) {
		List<Demand> lsResult = Lists.newArrayList();
		for(Demand demand : lsDemand){
			if(StringUtils.isBlank(demand.getAjbh())){
				throw new ServiceException("无法获取案件编号!");
			}
			if(StringUtils.isBlank(demand.getXqlx())){
				throw new ServiceException("无法获取需求类型!");
			}
			if(StringUtils.isBlank(demand.getXqnr())){
				throw new ServiceException("无法获取需求内容!");
			}
			List<Demand> lsExists = demandMapper.checkDemandInfoExists(demand);
			if(CollectionUtils.isNotEmpty(lsExists)) {
				lsResult.addAll(lsExists);
			}
		}
		return lsResult;
	}

	@Override
	public Map<String, Object> waitHandler() {
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		/**
		 * 1、统计我待指派的数量
		 */
		int initCount = demandMapper.countByDemandInit(loginUser.getAccount());

		/**
		 * 2、统计我待签收的数量
		 */
		DemandFlowHis paramWaitForSign = new DemandFlowHis();
		paramWaitForSign.setQszt(DemandFlowStepStatus.WAIT_FOR_SIGN.name());
		paramWaitForSign.setJsrybh(loginUser.getAccount());
		int waitForSignCount = demandFlowHisMapper.selectCount(paramWaitForSign);

		/**
		 * 3、统计我待反馈的数量
		 */
		DemandFlowHis paramWaitFeedBack = new DemandFlowHis();
		paramWaitFeedBack.setQszt(DemandFlowStepStatus.SIGNED.name());
		paramWaitFeedBack.setJsrybh(loginUser.getAccount());
		int signedCount = demandFlowHisMapper.selectCount(paramWaitFeedBack);

		Map<String, Object> mapResult = Maps.newHashMap();
		mapResult.put(DemandStatus.INIT.name(), initCount);
		mapResult.put(DemandFlowStepStatus.WAIT_FOR_SIGN.name(), waitForSignCount);
		mapResult.put(DemandFlowStepStatus.SIGNED.name(), signedCount);

		return mapResult;
	}

	@Override
	public PageInfo<DemandIndexVO> waitAllocate(Page page) {
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		PageInfo<Demand> pageInfo = new PageInfo<>(demandMapper.myAllocate(loginUser.getAccount()));
		List<DemandIndexVO> lsVO = Lists.newArrayList();
		for(Demand demand : pageInfo.getList()){
			DemandIndexVO vo = new DemandIndexVO();
			vo.setXqmc(demand.getXqmc());
			vo.setQqdw(demand.getQqdw());
			vo.setQqsj(demand.getQqsj());
			vo.setLrrymc(demand.getLrrymc());
			vo.setJsdw(demand.getJsdw());
			vo.setJsrymc(loginUser.getName());
			lsVO.add(vo);
		}
		PageInfo<DemandIndexVO> pageResult = new PageInfo<>();
		pageResult.setList(lsVO);
		pageResult.setTotal(pageInfo.getTotal());
		pageResult.setPageNum(page.getPageNum());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public Map<String, Object> demandClue(String kssj, String jssj) {

		Map<String, Object> mapResult = Maps.newHashMap();

		for (String enumName : PoliceTypeEnum.names()) {
			Map<String, Object> mapItem = Maps.newHashMap();

			Example exampleDemand = new Example(Demand.class);
			exampleDemand.createCriteria()
					.andBetween("lrsj", kssj, jssj)
					.andEqualTo("jzlb", enumName);
			mapItem.put("demand", demandMapper.selectCountByExample(exampleDemand));

			Example exampleClue = new Example(Demand.class);
			exampleClue.createCriteria()
					.andBetween("lrsj", kssj, jssj)
					.andEqualTo("jzlb", enumName);
			mapItem.put("clue", clueMapper.selectCountByExample(exampleClue));

			mapItem.put("name", PoliceTypeEnum.ofName(enumName).descp());

			if(enumName.equals("NETWORK_INVESTIGATE")){
				mapItem.put("type", Lists.newArrayList("WIFI"));
			}
			if(enumName.equals("TECH_INVESTIGATE")){
				mapItem.put("type", Lists.newArrayList("基站"));
			}
			if(enumName.equals("VIDEO_INVESTIGATE")){
				mapItem.put("type", Lists.newArrayList("视频"));
			}
			if(enumName.equals("INFO_INVESTIGATE")){
				mapItem.put("type", Lists.newArrayList("轨迹"));
			}
			if(enumName.equals("CRIMINAL_INVESTIGATE")){
				mapItem.put("type", Lists.newArrayList("笔录"));
			}
			if(enumName.equals("CRIMINAL_TECH_INVESTIGATE")){
				mapItem.put("type", Lists.newArrayList("电子物证", "指纹", "涉案物品"));
			}
			if(enumName.equals("POLICE_INVESTIGATE")){
				mapItem.put("type", Lists.newArrayList("DNA"));
			}
			if(enumName.equals("LAB_INVESTIGATE")){
				mapItem.put("type", Lists.newArrayList("大数据"));
			}
			mapResult.put(enumName, mapItem);
		}
		return mapResult;
	}

	@Override
	public PageInfo<DemandVO> demandClueDetail(String kssj, String jssj, String policeType, Page page) {
		Demand param = new Demand();
		param.setJzlb(policeType);
		Example exampleDemand = new Example(Demand.class);
		exampleDemand.createCriteria()
				.andBetween("lrsj", kssj, jssj)
				.andEqualTo("jzlb", policeType);
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		PageInfo<Demand> pageInfo = new PageInfo<>(demandMapper.selectByExample(exampleDemand));
		List<DemandVO> lsDemandVO = Lists.newArrayList();
		for(Demand itemDemand : pageInfo.getList()){
			DemandVO vo = new DemandVO();
			BeanUtils.copyProperties(itemDemand, vo);
			lsDemandVO.add(vo);
		}
		PageInfo<DemandVO> pageResult = new PageInfo<>(lsDemandVO);
		pageResult.setTotal(pageInfo.getTotal());
		pageResult.setPageNum(page.getPageNum());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	/**
	 * 是否超过最大积分奖励次数
	 * @param ajbh 案件编号
	 * @param jsdwbh 需求接收单位编号
	 * @param awardLimit 需求规则奖励次数
	 * @return
	 */
	private int saveIntegralRecord(String ajbh, String jsdwbh) {
		User loginUser = UserUtil.getUser();
		// 需求类型的奖励次数
		int awardLimit = integralConfigService.getAwardLimit(RuleTypeEnum.DEMAND.name());
		if(awardLimit == 0){
			return integralHisService.insert(ajbh, loginUser, RuleTypeEnum.DEMAND.name());
		}else{
			Demand param = new Demand();
			param.setAjbh(ajbh);
			param.setLrry(loginUser.getAccount());
			param.setJsdwbh(jsdwbh);
			if(demandMapper.selectCount(param) < awardLimit){
				return integralHisService.insert(ajbh, loginUser, RuleTypeEnum.DEMAND.name());
			}
			return 0;
		}
	}

}

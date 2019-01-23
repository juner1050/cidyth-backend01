package com.hyzs.cidyth.module.interaction.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.enums.ApproveTypeEnum;
import com.hyzs.cidyth.common.enums.ClueTypeEnum;
import com.hyzs.cidyth.common.enums.ReplyTypeEnum;
import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import com.hyzs.cidyth.common.utils.Constant;
import com.hyzs.cidyth.common.utils.RegExpUtil;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.appraise.service.AppraiseService;
import com.hyzs.cidyth.module.appraise.vo.Appraise;
import com.hyzs.cidyth.module.approve.service.ApproveService;
import com.hyzs.cidyth.module.attachment.service.AttachmentService;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.base.service.CasesService;
import com.hyzs.cidyth.module.clue.dao.DemandClueMapper;
import com.hyzs.cidyth.module.clue.entity.Clue;
import com.hyzs.cidyth.module.demand.dao.DemandMapper;
import com.hyzs.cidyth.module.demand.entity.Demand;
import com.hyzs.cidyth.module.demand.service.DemandFlowService;
import com.hyzs.cidyth.module.dic.service.DicService;
import com.hyzs.cidyth.module.interaction.service.InteractionService;
import com.hyzs.cidyth.module.interaction.vo.AbstractDemandDetail;
import com.hyzs.cidyth.module.interaction.vo.AbstractDemandDetail.CaseParameter;
import com.hyzs.cidyth.module.interaction.vo.DemandDetail;
import com.hyzs.cidyth.module.interaction.vo.InfoDetail;
import com.hyzs.cidyth.module.msg.dao.InfoMapper;
import com.hyzs.cidyth.module.msg.entity.Info;
import com.hyzs.cidyth.module.reply.dao.ReplyMapper;
import com.hyzs.cidyth.module.reply.entity.Reply;
import com.hyzs.cidyth.module.reply.vo.DemandInteractionContent;
import com.hyzs.cidyth.module.reply.vo.InfoInteractionContent;
import com.hyzs.cidyth.module.reply.vo.InteractionContent;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;
import com.hyzs.psd.gafa.utils.bean.GenericBeanUtils;
import com.hyzs.psd.gafa.utils.bean.GenericBeanUtils.ConvertedBeanPropertyValueResolver;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service("interactionService")
public class InteractionServiceImpl implements InteractionService {
	private static final Logger logger = LoggerFactory.getLogger(InteractionServiceImpl.class);
	@Autowired
	private DemandClueMapper demandClueMapper;
	@Autowired
	private ReplyMapper replyMapper;

	@Autowired
	private DemandMapper demandMapper;
	@Autowired
	private InfoMapper infoMapper;

	@Autowired
	private CasesService casesService;
	@Autowired
	@Qualifier("localAttachmentService")
	private AttachmentService attachmentService;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private DemandFlowService demandFlowService;
	@Autowired
	private AppraiseService clueAppraiseService;
	@Autowired
	private DicService dicService;
	@Autowired
	private ApproveService approveService;

	@Override
	public InteractionContent loadInteractionBodyForDemand(Collection<String> referenceIdList) {
		DemandInteractionContent content = new DemandInteractionContent();
		List<ImmutablePair<Clue, List<Map<String, Object>>>> clueAttach = Lists.newArrayList();
		List<ImmutablePair<Clue, Appraise>> clueAppraise = Lists.newArrayList();//线索的评价 
		Collection<ImmutablePair<Reply, List<Map<String, Object>>>> replyAttach = Lists.newArrayList();
		
		List<Reply> replyList = Lists.newArrayList();// 所有的回复(包含针对需求的回复、针对线索的回复、针对回复的回复)
		List<Clue> clueList = demandClueMapper.selectClueByDemandIdList(referenceIdList,
				Integer.parseInt(ClueTypeEnum.RETURN.code()));// 线索
		if (CollectionUtils.isNotEmpty(clueList)) {
			for (Clue clue : clueList) {
				clue.setApproveCount(approveService.count(clue.getId(), ApproveTypeEnum.CLUE.name()));
				ImmutablePair catt = ImmutablePair.of(clue,
						attachmentService.loadAttachment(clue.getId().toString(), TimeNodeEnum.CLUE));
				clueAttach.add(catt);
				
				Appraise appraise=null;
				List<Appraise> appraiseList = clueAppraiseService.loadAppraise(null, clue.getId()+"");
				if(CollectionUtils.isNotEmpty(appraiseList)){
					appraise = appraiseList.get(0);
					ImmutablePair capp = ImmutablePair.of(clue,appraise);
					clueAppraise.add(capp);
				}
				List<Reply> clueReplyList = getReply(Lists.newArrayList(clue.getId().toString()), ReplyTypeEnum.CLUE);// 针对线索的回复
				if (CollectionUtils.isNotEmpty(clueReplyList)) {
					recursionResponseOfReply(clueReplyList, replyList);
				}
			}
		}
		content.setClueList(clueAttach);
		content.setClueAppraiseList(clueAppraise);
		
		List<Reply> demandReplyList = getReply(referenceIdList, ReplyTypeEnum.DEMAND);// 针对需求的回复
		if (CollectionUtils.isNotEmpty(demandReplyList)) {
			recursionResponseOfReply(demandReplyList, replyList);
		}
		if (CollectionUtils.isNotEmpty(replyList)) {// 所有回复相关联的附件
			for (Reply rep : replyList) {
				ImmutablePair pair = ImmutablePair.of(rep,
						attachmentService.loadAttachment("" + rep.getId(), TimeNodeEnum.REPLY));
				replyAttach.add(pair);
			}
		}
		content.setReplyList(replyAttach);
		return content;
	}

	private void recursionResponseOfReply(String replyId, Collection<Reply> resultContainer) {
		List<Reply> replyReplyList = getReply(Lists.newArrayList(replyId), ReplyTypeEnum.RESP);// 针对回复的回复
		recursionResponseOfReply(replyReplyList, resultContainer);
	}

	private void recursionResponseOfReply(List<Reply> replyList, Collection<Reply> resultContainer) {
		if (CollectionUtils.isEmpty(replyList)) {
			return;
		} else {
			resultContainer.addAll(replyList);
			for (Reply reply : replyList) {
				recursionResponseOfReply(reply.getId().toString(), resultContainer);
			}
		}
	}

	private List<Reply> getReply(Collection<String> referenceIdList, ReplyTypeEnum... replyTypes) {
		if (CollectionUtils.isEmpty(referenceIdList) || replyTypes == null || replyTypes.length == 0) {
			throw new IllegalArgumentException("参数不能同时为空.");
		}
		List<String> types = Lists.transform(Lists.newArrayList(replyTypes), new Function<ReplyTypeEnum, String>() {
			@Override
			public String apply(ReplyTypeEnum input) {
				return input.name();
			}
		});
		List<Reply> list = replyMapper.selectByReferenceId$ReplyTypes(referenceIdList, types);
		return list;
	}

	@Override
	public InteractionContent loadInteractionBodyForInfo(Collection<String> infoIdList) {
		InfoInteractionContent content = new InfoInteractionContent();
		Collection<ImmutablePair<Reply, List<Map<String, Object>>>> replyAttach = Lists.newArrayList();
		List<Reply> replyList = Lists.newArrayList();// 所有的回复(包含针对信息的回复、针对回复的回复)

		List<Reply> InfoReplyList = getReply(infoIdList, ReplyTypeEnum.INFO);// 针对信息的回复
		if (CollectionUtils.isNotEmpty(InfoReplyList)) {
			recursionResponseOfReply(InfoReplyList, replyList);
		}

		if (CollectionUtils.isNotEmpty(replyList)) {
			for (Reply rep : replyList) {
				ImmutablePair pair = ImmutablePair.of(rep,
						attachmentService.loadAttachment("" + rep.getId(), TimeNodeEnum.REPLY));
				replyAttach.add(pair);
			}
		}
		content.setReplyList(replyAttach);
		return content;
	}

	/**
	 * 查询并验证案件
	 * 
	 * @param caseNo
	 * @return
	 */
	private Cases getCase(String caseNo) {
		if (StringUtils.isBlank(caseNo)) {
			throw new IllegalArgumentException("案件编号不能为空!");
		}
		Cases cases = casesService.getCaseByAjbh(caseNo);
		if (cases == null) {
			throw new ServiceException("案件" + caseNo + "不存在!");
		}
		return cases;
	}

	/**
	 * 查询需求
	 * 
	 * @param cases
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	private ImmutablePair<Cases, List<Demand>> doGetDemandDetailList(Cases cases, Integer pageNum, Integer pageSize) {
		List<Demand> data = null;
		User loginUser = UserUtil.getUser();
		;
		if (loginUser == null) {
			throw new ServiceException("无法获取当前登录用户!");
		}
		String deptId = loginUser.getDepartment().getCode();
		String pDeptId = loginUser.getDepartment().getPcode();
		if (StringUtils.isBlank(pDeptId) || pDeptId.equals(deptId)
				|| (RegExpUtil.isNumber(pDeptId) && "0".equals(pDeptId))) {
			// 当前用户是最顶级机构的用户
			if (pageNum != null && pageSize != null) {
				PageHelper.startPage(pageNum, pageSize);
			}
			data = demandMapper.selectBySelective(Lists.newArrayList(cases.getAjbh()), null, null, null);
		} else {
			if (demandFlowService.isAllocateRole(Constant.SystemInfo.WEB_PORTAL)) {
				// 当前用户有指派需求的权限,查询我发起的和指派到本单位的需求
				if (pageNum != null && pageSize != null) {
					PageHelper.startPage(pageNum, pageSize);
				}
				data = demandMapper.selectBySelective(Lists.newArrayList(cases.getAjbh()), loginUser.getAccount(),
						Lists.newArrayList(deptId), null);
			} else {
				// 普通民警,查询我发起的和被指派给我的需求
				if (pageNum != null && pageSize != null) {
					PageHelper.startPage(pageNum, pageSize);
				}
				data = demandMapper.selectBySelective(Lists.newArrayList(cases.getAjbh()), loginUser.getAccount(), null,
						Lists.newArrayList(loginUser.getAccount()));
			}
		}
		return ImmutablePair.of(cases, data);
	}

	/**
	 * 查询消息
	 * 
	 * @param cases
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	private ImmutablePair<Cases, List<Info>> doGetInfos(Cases cases, Integer pageNum, Integer pageSize) {
		List<Info> data = null;
		User loginUser = UserUtil.getUser();
		if (loginUser == null) {
			throw new ServiceException("无法获取当前登录用户!");
		}
		String deptId = loginUser.getDepartment().getCode();
		if (pageNum != null && pageSize != null) {
			PageHelper.startPage(pageNum, pageSize);
		}
		data = infoMapper.selectBySelective(Lists.newArrayList(cases.getAjbh()), loginUser.getAccount(), null,
				Lists.newArrayList(loginUser.getAccount()));
		return ImmutablePair.of(cases, data);
	}

	@Override
	public List<AbstractDemandDetail> loadInteractionContentList(String caseNo, boolean loadReplies) {
		User loginUser = UserUtil.getUser();
		if (loginUser == null) {
			throw new ServiceException("无法获取当前登录用户!");
		}
		List<AbstractDemandDetail> result = Lists.newArrayList();
		ImmutablePair<Cases, List<Demand>> demandPair = this.doGetDemandDetailList(this.getCase(caseNo), null, null);
		Cases cases = demandPair.getLeft();
		List<Demand> demands = demandPair.getRight();
		if (CollectionUtils.isNotEmpty(demands)) {
			for (Demand dem : demands) {
				//是否超期
				boolean isDelay = demandFlowService.isDelayProcess(dem.getId(), loginUser.getAccount());
				DemandDetail detail = new DemandDetail();
				detail.setCases(cases);
				detail.setDemand(dem);
				detail.setShowClueBtn(!isDelay);
				if (loadReplies) {
					detail.setInteractionContent(loadInteractionBodyForDemand(Lists.newArrayList(dem.getId() + "")));
				}
				detail.setAttacments(attachmentService.loadAttachment(dem.getId() + "", TimeNodeEnum.DEMAND));
				this.fillCaseInfoOfAbstractDemandDetail(cases, detail.parameter());
				result.add(detail);
			}
		}
		ImmutablePair<Cases, List<Info>> infoPair = this.doGetInfos(cases, null, null);
		List<Info> infos = infoPair.getRight();
		if (CollectionUtils.isNotEmpty(infos)) {
			for (Info info : infos) {
				InfoDetail detail = new InfoDetail();
				detail.setCases(cases);
				detail.setInfo(info);
				if (loadReplies) {
					detail.setInteractionContent(loadInteractionBodyForInfo(Lists.newArrayList(info.getId() + "")));
				}
				detail.setApproveCount(approveService.count(info.getId(), ApproveTypeEnum.INFO.name()));
				detail.setAttacments(attachmentService.loadAttachment(info.getId() + "", TimeNodeEnum.INFO));
				this.fillCaseInfoOfAbstractDemandDetail(cases, detail.parameter());
				result.add(detail);
			}
		}
		return result;
	}

	private void fillCaseInfoOfAbstractDemandDetail(Cases cases, CaseParameter parameter) {
		String[] caseProperties = new String[] { "ab", "sdtd" };
		GenericBeanUtils.convertSimpleBean2Map(cases, false, new ConvertedBeanPropertyValueResolver() {
			@Override
			public Object resolvePropertyValue(String propertyName, Object propertyValue) {
				if (StringUtils.equalsAny(propertyName, caseProperties)) {
					parameter.put(propertyName + "Cn", propertyValue == null ? ""
							: dicService.getValueByKey(propertyName, propertyValue.toString()));
				} else if ("zbdw".equals(propertyName) && StringUtils.isNotEmpty(cases.getZbdw())) {// 主办单位不为空，从用户中心获取
					Dept deptZbdw = null;
					try {
						deptZbdw = userCenterService.getDeptByCode(cases.getZbdw());
					} catch (Exception e) {
						e.printStackTrace();
					}
					parameter.setZbdwCn(deptZbdw == null ? "" : deptZbdw.getFullname());
				} else if ("ajzbry".equals(propertyName) && StringUtils.isNotEmpty(cases.getAjzbry())) {// 主办人员不为空，从用户中心获取
					User userAjzbry = null;
					try {
						userAjzbry = userCenterService.getUserByUserName(cases.getAjzbry());
					} catch (Exception e) {
						e.printStackTrace();
					}
					parameter.setAjzbryCn(userAjzbry == null ? "" : userAjzbry.getName());
				}
				return propertyValue;
			}
		});
	}

	@Override
	public List<AbstractDemandDetail> loadInteractionContentList(String caseNo) {
		return this.loadInteractionContentList(caseNo, false);
	}

	@Override
	public AbstractDemandDetail loadInteractionContentItem(String referenceId, String type) {
		if (StringUtils.isBlank(referenceId) || !StringUtils.isNumeric(referenceId)) {
			throw new ServiceException("目标id参数为空或者不是数字！");
		}
		AbstractDemandDetail item = null;
		if (ReplyTypeEnum.DEMAND.name().equals(type)) {
			Demand demand = demandMapper.selectByPrimaryKey(Integer.parseInt(referenceId));
			if (demand != null) {
				item = new DemandDetail().setDemand(demand)
						.setInteractionContent(this.loadInteractionBodyForDemand(Lists.newArrayList(referenceId)))
						.setAttacments(attachmentService.loadAttachment(referenceId, TimeNodeEnum.DEMAND));

			}
		}
		if (ReplyTypeEnum.INFO.name().equals(type)) {
			Info info = infoMapper.selectByPrimaryKey(Integer.parseInt(referenceId));
			if (info != null) {
				item = new InfoDetail().setInfo(info)
						.setInteractionContent(this.loadInteractionBodyForInfo(Lists.newArrayList(referenceId)))
						.setAttacments(attachmentService.loadAttachment(referenceId, TimeNodeEnum.INFO));
			}
		}
		return item;
	}
}

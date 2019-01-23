package com.hyzs.cidyth.module.clue.service.impl;

import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.enums.*;
import com.hyzs.cidyth.common.utils.MessageUtil;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.appraise.dao.ClueAppraiseMapper;
import com.hyzs.cidyth.module.appraise.entity.ClueAppraise;
import com.hyzs.cidyth.module.appraise.service.AppraiseService;
import com.hyzs.cidyth.module.attachment.service.AttachmentService;
import com.hyzs.cidyth.module.base.service.CasesService;
import com.hyzs.cidyth.module.clue.dao.ClueMapper;
import com.hyzs.cidyth.module.clue.dao.DemandClueMapper;
import com.hyzs.cidyth.module.clue.entity.Clue;
import com.hyzs.cidyth.module.clue.entity.DemandClue;
import com.hyzs.cidyth.module.clue.service.ClueService;
import com.hyzs.cidyth.module.clue.vo.*;
import com.hyzs.cidyth.module.demand.dao.DemandMapper;
import com.hyzs.cidyth.module.demand.entity.Demand;
import com.hyzs.cidyth.module.demand.entity.DemandFlowHis;
import com.hyzs.cidyth.module.demand.service.DemandFlowService;
import com.hyzs.cidyth.module.integral.service.IntegralConfigService;
import com.hyzs.cidyth.module.integral.service.IntegralHisService;
import com.hyzs.cidyth.module.integral.service.IntegralRuleService;
import com.hyzs.cidyth.module.integral.vo.IntegralRuleOption;
import com.hyzs.cidyth.module.mind.service.MindService;
import com.hyzs.cidyth.module.time.service.TimeNodeService;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;
import com.hyzs.cidyth.module.websocket.event.BussinessDataEvent;
import com.hyzs.cidyth.module.websocket.event.impl.BussinessDataEventPublisher;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("ClueService")
public class ClueServiceImpl implements ClueService {
	
	private static final Logger logger = LoggerFactory.getLogger(ClueServiceImpl.class);

    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private DemandClueMapper demandClueMapper;
    @Autowired
    private DemandMapper demandMapper;
    @Autowired
    private MindService mindService;
    @Autowired
    @Qualifier("localAttachmentService")
    private AttachmentService attachmentService;
	@Autowired
	private TimeNodeService timeNodeService;
	@Autowired
	private DemandFlowService demandFlowService;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private IntegralRuleService integralRuleService;
	@Autowired
	private IntegralHisService integralHisService;
	@Autowired
	private IntegralConfigService integralConfigService;
	@Autowired
	private CasesService casesService;
	@Autowired
	private AppraiseService appraiseService;

	@Override
	public int getCountByAjbh(String ajbh) {
		Clue clue = new Clue();
		clue.setAjbh(ajbh);
		return clueMapper.selectCount(clue);
	}
	
	@Override
	@Transactional(value="masterTransactionManager",rollbackFor={RuntimeException.class,Exception.class})
	public void insertDemandClue(List<Integer> lsClueId, Demand demand) {
		if(lsClueId != null && lsClueId.size() > 0){
			for(int clueId : lsClueId){
				
		 		DemandClue demandClue = new DemandClue();
				demandClue.setDemandId(demand.getId());
				demandClue.setClueId(clueId);
				demandClue.setClueType(Integer.valueOf(ClueTypeEnum.UPLOAD.code()));
				demandClueMapper.insertSelective(demandClue);
				
				//保存思维导图
				mindService.insert(demand.getAjbh(), clueId, demand.getId(), TableTypeEnum.DEMAND.name(), TableTypeEnum.UPLOAD_CLUE.name());
			}
		}
	}

	@Override
	@Transactional(value="masterTransactionManager",rollbackFor={RuntimeException.class,Exception.class})
	public Map<String, Object> insert(ClueVO clueVO) {
		Map<String, Object> mapResult = new HashMap<String, Object>();
		// 积分次数
		int scoreNum = 0;
		if(casesService.isFinish(clueVO.getAjbh())){
			throw new ServiceException("该案件已侦结!");
		}
		//获取需求id
		Integer demandId = clueVO.getDemandId();
		if(demandId == null){
			throw new ServiceException("获取需求失败!");
		}
		//获取用户
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("获取当前登录用户失败!");
		}
		Dept dept = loginUser.getDepartment();
		if(dept == null){
			throw new ServiceException("获取当前登录用户机构失败!");
		}
		// 警种类别
		String jzlb = PoliceTypeEnum.ofCode(String.valueOf(dept.getPoliceType())) == null ? "" : PoliceTypeEnum.ofCode(String.valueOf(dept.getPoliceType())).name();

		Clue clue = new Clue();
		BeanUtils.copyProperties(clueVO, clue);
		clue.setXqid(demandId);
		clue.setFkry(loginUser.getAccount());
		clue.setLrry(loginUser.getAccount());
		clue.setLrrymc(loginUser.getName());
		clue.setJzlb(jzlb);
		if(dept!=null){
			clue.setFkdw(dept.getFullname());
			clue.setFkdwbh(dept.getCode());
		}
		clue.setXslx(Integer.parseInt(ClueTypeEnum.RETURN.code()));
		// 保存线索
		clueMapper.insertSelective(clue);
		List<Map<String,Object>> attachments = null;
		if(CollectionUtils.isNotEmpty(clueVO.getFiles())){
			// 保存附件
			attachments =attachmentService.insert(clueVO.getFiles(), clueVO.getFileComment(), String.valueOf(clue.getId()), TimeNodeEnum.CLUE.name());
		}
		// 保存积分历史（反馈线索）
		if(saveIntegralRecord(clue.getAjbh(), demandId) > 0){
			scoreNum++;
		}

		// 插入需求线索表
		DemandClue demandClue = new DemandClue();
		demandClue.setClueId(clue.getId());
		demandClue.setClueType(Integer.valueOf(ClueTypeEnum.RETURN.code()));
		demandClue.setDemandId(demandId);
		demandClueMapper.insertSelective(demandClue);

		// 插入思维导图表
		mindService.insert(clue.getAjbh(), clue.getId(), demandId, TimeNodeEnum.CLUE.name(), TimeNodeEnum.DEMAND.name());

		// 1、修改需求操作(流转)记录表的qszt为反馈状态
		//

		if(loginUser != null){
			DemandFlowHis demandFlowHis = new DemandFlowHis();
			demandFlowHis.setXqid(demandId);
			demandFlowHis.setJsrybh(loginUser.getAccount());
			demandFlowService.feedback(demandId, clueVO.getAjbh());
		}
		// 获取反馈线索所属的需求
		Demand demand = demandMapper.selectByPrimaryKey(demandId);
		// 时间轴新增
		timeNodeService.insert(clue.getAjbh(), clue.getId(), TimeNodeEnum.CLUE.name(), clue.getTheme(), clue.getXsnr(),
				demand.getQqdwbh());
		BussinessDataEventPublisher.publishEvent(BussinessDataEvent.create(this, new Clues(clue).setDemandId(demandId).setDemandCreateUserId(demand.getLrry()).setAttachments(attachments)));

		// 发送短信：指派到具体的人时
		String demandUserAccount = demand.getLrry();
		if(StringUtils.isNotEmpty(demandUserAccount)){
			User demandInputUser = null;
			try {
				demandInputUser = userCenterService.getUserByUserName(demandUserAccount);
				if(demandInputUser == null){
					throw new ServiceException("获取需求用户失败!");
				}else{
					if(clueVO.isSendMessage()){
						MessageUtil.sendSMS(demandInputUser.getPhone(), loginUser.getDepartment().getFullname(), loginUser.getName(), clue.getXsnr(), DemandStatus.FEEDBACKED);
					}
				}
			} catch (Exception e) {
				throw new ServiceException("获取需求用户失败!");
			}
		}
		Float score = integralConfigService.getScoreByRuleType(RuleTypeEnum.RETURN_CLUE.name());
		mapResult.put("id", clue.getId());
		mapResult.put("score", scoreNum * score);
		// 返回反馈线索的主键
		return mapResult;
	}

	@Override
	public ClueVO detail(Integer id) {
		Clue clue = clueMapper.selectByPrimaryKey(id);
		ClueVO clueVO = new ClueVO();
		BeanUtils.copyProperties(clue, clueVO);

		return clueVO;
	}

	@Override
	public void uploadClue(ClueVO clueVO) {
		clueVO.setXslx(Integer.parseInt(ClueTypeEnum.UPLOAD.code()));
		Clue clue = new Clue();
		BeanUtils.copyProperties(clueVO, clue);
		clueMapper.insert(clue);
	}

	//	@Override
//	public int evaluate(ClueVO clueVO) {
//		Clue clueResult = clueMapper.selectByPrimaryKey(clueVO.getId()) ;
//		if(clueResult.getSfpf() == null || clueResult.getSfpf().equals(Integer.valueOf(ClueEvaluateEnum.NOT_EVALUATE.code()))){
//			Clue clue = new Clue();
//			clue.setId(clueVO.getId());
//			clue.setPffz(clueVO.getPffz());
//			clue.setPfry(clueVO.getPfry());
//			clue.setPfnr(clueVO.getPfnr());
//			User user = null;
//			try {
//				user = userCenterService.getUserByUserName(clue.getPfry());
//			} catch (Exception e) {
//				throw new ServiceException("用户中心获取用户失败!");
//			}
//			if(user != null){
//				clue.setPfrymc(user.getName());
//			}
//			clueMapper.updateByPrimaryKeySelective(clue);
//			return clue.getId();
//		}else{
//			throw new ServiceException("线索已评价!");
//		}
//	}


	@Override
	public ClueEvaluation listClueEvaluateByCases(String ajbh) {
		ClueEvaluation clueEvaluation = new ClueEvaluation();

		Clue clue = new Clue();
		clue.setAjbh(ajbh);
		List<Clue> lsClue = clueMapper.select(clue);
		List<ClueMain> lsClueMain = Lists.newArrayList();

		if(CollectionUtils.isNotEmpty(lsClue)){
			for (Clue item : lsClue) {
				ClueMain clueMain = new ClueMain();
				BeanUtils.copyProperties(item, clueMain);
				lsClueMain.add(clueMain);
			}
		}
		// 获取关键线索下的所有子集规则和分数
		List<IntegralRuleOption> lsIntegralRuleOption = integralRuleService.listIntegralRuleOptionByRuleType(RuleTypeEnum.MAIN_CLUE.name());
		clueEvaluation.setLsClueMain(lsClueMain);
		clueEvaluation.setLsIntegralRuleOption(lsIntegralRuleOption);

		return clueEvaluation;
	}

	@Override
	public void saveClueEvaluate(String ajbh, List<ClueAppraiseSave> lsClueAppraiseSave) {
		if (CollectionUtils.isNotEmpty(lsClueAppraiseSave)){
			for(ClueAppraiseSave clueAppraiseSave : lsClueAppraiseSave){
				Clue clue = clueMapper.selectByPrimaryKey(clueAppraiseSave.getClueId());
				User clueProvider = null;
				try {
					clueProvider = userCenterService.getUserByUserName(clue.getLrry());
				} catch (Exception e) {
					throw new ServiceException("无法获取线索提供人!");
				}
				String ruleType = clueAppraiseSave.getRuleType();
				integralHisService.insert(ajbh, clueProvider, ruleType);
			}
		}
	}

	@Override
	public List<ClueVO> listClueByDemandId(Integer xqid) {
		List<ClueVO> lsClueVO = Lists.newArrayList();
		Clue queryClue = new Clue();
		queryClue.setXqid(xqid);
		clueMapper.select(queryClue).stream().forEach(i -> {
			ClueVO vo = new ClueVO();
			BeanUtils.copyProperties(i, vo);
			vo.setPffz(appraiseService.getScoreByClueId(i.getId()));
			vo.setDemandId(xqid);
			vo.setLsAttachment(attachmentService.loadAttachment(String.valueOf(i.getId()), TimeNodeEnum.CLUE));
			vo.setFkry(i.getLrry());
			vo.setFkrymc(i.getLrrymc());
			lsClueVO.add(vo);
		});
		return lsClueVO;
	}


	/**
	 * 是否超过最大积分奖励次数
	 * @param ajbh 案件编号
	 * @param jsdwbh 需求接收单位编号
	 * @param awardLimit 需求规则奖励次数
	 * @return
	 */
	private int saveIntegralRecord(String ajbh, Integer demandId) {
		User loginUser = UserUtil.getUser();
		// 需求类型的奖励次数
		int awardLimit = integralConfigService.getAwardLimit(RuleTypeEnum.RETURN_CLUE.name());
		if(awardLimit == 0){
			return integralHisService.insert(ajbh, loginUser, RuleTypeEnum.RETURN_CLUE.name());
		}else{
			Clue param = new Clue();
			param.setXqid(demandId);
			param.setLrry(loginUser.getAccount());
			if(clueMapper.selectCount(param) < awardLimit){
				return integralHisService.insert(ajbh, loginUser, RuleTypeEnum.RETURN_CLUE.name());
			}
			return 0;
		}
	}
}

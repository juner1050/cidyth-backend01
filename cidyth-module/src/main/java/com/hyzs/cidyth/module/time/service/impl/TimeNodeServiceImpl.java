package com.hyzs.cidyth.module.time.service.impl;

import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.config.MultiThreadTaskConfig;
import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.attachment.service.AttachmentService;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.base.service.CasesService;
import com.hyzs.cidyth.module.clue.dao.DemandClueMapper;
import com.hyzs.cidyth.module.clue.entity.DemandClue;
import com.hyzs.cidyth.module.reply.service.MessageAnchroRule;
import com.hyzs.cidyth.module.time.dao.TimeNodeMapper;
import com.hyzs.cidyth.module.time.entity.TimeNode;
import com.hyzs.cidyth.module.time.service.TimeNodeService;
import com.hyzs.cidyth.module.time.vo.TimeNodeVO;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;
import com.hyzs.psd.gafa.daf.mybatis.tk.entity.Example;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("TimeNodeService")
public class TimeNodeServiceImpl implements TimeNodeService {

	private static final Logger logger = LoggerFactory.getLogger(TimeNodeServiceImpl.class);

	@Autowired
	private TimeNodeMapper timeNodeMapper;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private DemandClueMapper demandClueMapper;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	@Lazy
	private CasesService casesService;

	@Override
	public List<TimeNodeVO> listByAjbh(String ajbh) {

		Example dynamicQuery = new Example(TimeNode.class);
		dynamicQuery.createCriteria().andEqualTo("ajbh", ajbh);
		dynamicQuery.setOrderByClause("sort");

		List<TimeNode> lsTimeNode = timeNodeMapper.selectByExample(dynamicQuery);
		if(CollectionUtils.isNotEmpty(lsTimeNode)){
			List<TimeNodeVO> lsTimeNodeVO = Lists.newArrayList();
			// 转换到VO对象
			for(TimeNode objTimeNode1 : lsTimeNode){
				TimeNodeVO timeNodeVO = new TimeNodeVO();
				BeanUtils.copyProperties(objTimeNode1, timeNodeVO);
				Cases cases = casesService.getCaseByAjbh(objTimeNode1.getAjbh());
				if(cases != null){
					timeNodeVO.setAjmc(cases.getAjmc());
					timeNodeVO.setZyaq(cases.getZyaq());
				}
				String enumName = TimeNodeEnum.ofName(objTimeNode1.getReferenceType()) == null ? "" : TimeNodeEnum.ofName(objTimeNode1.getReferenceType()).descp();
				timeNodeVO.setReferenceTypeCn(enumName);
				// 建造contentType格式：用于前端时间轴点击节点跳转
				if(objTimeNode1.getReferenceType().equals(TimeNodeEnum.DEMAND.name())){
					timeNodeVO.setContentType(MessageAnchroRule.buildForDemand(objTimeNode1.getAjbh(), String.valueOf(objTimeNode1.getReferenceId())));
					timeNodeVO.setLsAttachment(attachmentService.loadAttachment(String.valueOf(objTimeNode1.getReferenceId()), TimeNodeEnum.DEMAND));
				}
				if(objTimeNode1.getReferenceType().equals(TimeNodeEnum.CLUE.name())){
					// 获取反馈线索id
					Integer clueId = objTimeNode1.getReferenceId();
					if(clueId != null){
						// 获取反馈线索的所属需求
						DemandClue demandClue = new DemandClue();
						demandClue.setClueId(clueId);
						demandClue = demandClueMapper.selectOne(demandClue);
						if(demandClue != null && demandClue.getDemandId() != null){
							timeNodeVO.setContentType(MessageAnchroRule.buildForClue(objTimeNode1.getAjbh(), String.valueOf(demandClue.getDemandId()), String.valueOf(objTimeNode1.getReferenceId())));
						}
					}
					timeNodeVO.setLsAttachment(attachmentService.loadAttachment(String.valueOf(objTimeNode1.getReferenceId()), TimeNodeEnum.CLUE));
				}
				if(objTimeNode1.getReferenceType().equals(TimeNodeEnum.INFO.name())){
					timeNodeVO.setContentType(MessageAnchroRule.buildForInfo(objTimeNode1.getAjbh(), String.valueOf(objTimeNode1.getReferenceId())));
					timeNodeVO.setLsAttachment(attachmentService.loadAttachment(String.valueOf(objTimeNode1.getReferenceId()), TimeNodeEnum.INFO));
				}
				lsTimeNodeVO.add(timeNodeVO);
			}

			return lsTimeNodeVO;
		}else{
			return null;
		}
	}
	
	@Override
	public void insert(String ajbh, Integer referenceId, String referenceType, String title, String content,
			String receiveOrgCode) {
		insert(ajbh, referenceId, referenceType, title, content, receiveOrgCode, null);
	}

	@Override
	public void insert(String ajbh, Integer referenceId, String referenceType, String title, String content, String receiveOrgCode, Date lrsj) {
		User loginUser = UserUtil.getUser();

		if(loginUser == null){
			throw new ServiceException("无法获取当前登录用户!");
		}
		Dept dept = loginUser.getDepartment();
		if(dept == null){
			throw new ServiceException("无法获取登录用户机构!");
		}

		TimeNode timeNode = new TimeNode();
		timeNode.setAjbh(ajbh);
		timeNode.setReferenceId(referenceId);
		timeNode.setReferenceType(referenceType);
		timeNode.setTitle(title);
		timeNode.setContent(content);
		timeNode.setLrry(loginUser.getAccount());
		timeNode.setLrrymc(loginUser.getName());
		timeNode.setSendOrgCode(dept.getCode());
		timeNode.setSendOrgName(dept.getFullname());
		timeNode.setLrsj(lrsj == null ? new Date() : lrsj);

		if(referenceType.equals(TimeNodeEnum.DEMAND.name())){
			if(StringUtils.isNoneEmpty(receiveOrgCode)){
				Dept receiveDept;
				try {
					receiveDept = userCenterService.getDeptByCode(receiveOrgCode);
				} catch (Exception e) {
					throw new ServiceException("无法获取接收机构!");
				}
				if(receiveDept != null){
					timeNode.setReceiveOrgCode(receiveOrgCode);
					timeNode.setReceiveOrgName(receiveDept.getFullname());
				}
			}
		}else if(referenceType.equals(TimeNodeEnum.INFO.name())){
			if(StringUtils.isNoneEmpty(receiveOrgCode)){
				String[] orgCodes = receiveOrgCode.split(",");
				String receiveOrgName = "";
				for(String orgCode : orgCodes){
					Dept receiveDept;
					try {
						receiveDept = userCenterService.getDeptByCode(orgCode);
					} catch (Exception e) {
						throw new ServiceException("无法获取接收机构!");
					}
					if(receiveDept != null){
						receiveOrgName += receiveDept.getFullname() + ",";
					}
				}
				receiveOrgName = receiveOrgName.substring(0, receiveOrgName.length() - 1);
				timeNode.setReceiveOrgCode(receiveOrgCode);
				timeNode.setReceiveOrgName(receiveOrgName);
			}
		}else if(referenceType.equals(TimeNodeEnum.CLUE.name())){
			if(StringUtils.isNoneEmpty(receiveOrgCode)){
				Dept receiveDept;
				try {
					receiveDept = userCenterService.getDeptByCode(receiveOrgCode);
				} catch (Exception e) {
					throw new ServiceException("无法获取接收机构!");
				}
				if(receiveDept != null){
					timeNode.setReceiveOrgCode(receiveOrgCode);
					timeNode.setReceiveOrgName(receiveDept.getFullname());
				}
			}
		}else {

		}
		// 获取枚举的code作为排序插入数据库
		TimeNodeEnum timeNodeEnum = TimeNodeEnum.ofName(referenceType);
		timeNode.setSort(timeNodeEnum == null ? null : Integer.valueOf(timeNodeEnum.code()));
		timeNodeMapper.insertSelective(timeNode);
	}

	@Override
	public void insert(String ajbh, Integer referenceId, String referenceType, String title, String content) {
		insert(ajbh, referenceId, referenceType, title, content, null);
	}

	@Override
	public int update(TimeNode timeNode) {
		if(timeNode.getId() == null){
			throw new ServiceException("对象id为空!");
		}
		return timeNodeMapper.updateByPrimaryKeySelective(timeNode);
	}

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void autoInsertBySyncData(String ajbh, TimeNodeEnum timeNodeEnum, String content, String sendOrgCode, String sendOrgName, String receiveOrgCode, String receiveOrgName) {
		if(!isExist(ajbh, timeNodeEnum.name())){
			TimeNode timeNode = new TimeNode();
			timeNode.setAjbh(ajbh);
			timeNode.setReferenceType(timeNodeEnum.name());
			timeNode.setSendOrgCode(sendOrgCode);
			timeNode.setSendOrgName(sendOrgCode.contains("44030019") ? "刑侦局" : sendOrgName);
			timeNode.setTitle(timeNodeEnum.descp());
			timeNode.setContent(content);
			timeNode.setSort(Integer.valueOf(timeNodeEnum.code()));
			timeNode.setReceiveOrgCode(receiveOrgCode);
			timeNode.setReceiveOrgName(receiveOrgCode.contains("44030019") ? "刑侦局" : receiveOrgName);
			timeNodeMapper.insertSelective(timeNode);
		}
	}

	private boolean isExist(String ajbh, String reference_type) {
		TimeNode param = new TimeNode();
		param.setAjbh(ajbh);
		param.setReferenceType(reference_type);
		return timeNodeMapper.selectCount(param) > 0 ? true : false;
	}
}

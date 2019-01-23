package com.hyzs.cidyth.module.analysis.service.impl;

import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.enums.AnalysisNodeEnum;
import com.hyzs.cidyth.common.enums.SystemExceptionEnum;
import com.hyzs.cidyth.common.enums.YesNoEnum;
import com.hyzs.cidyth.common.utils.MessageUtil;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.analysis.dao.AnalysisNodeMapper;
import com.hyzs.cidyth.module.analysis.entity.AnalysisNode;
import com.hyzs.cidyth.module.analysis.service.AnalysisNodeService;
import com.hyzs.cidyth.module.base.entity.CasesGroup;
import com.hyzs.cidyth.module.base.service.CasesGroupService;
import com.hyzs.cidyth.module.cases.service.PcCasesService;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;
import com.hyzs.psd.gafa.daf.mybatis.tk.entity.Example;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 研判线索节点
 */
@Service("analysisNodeService")
public class AnalysisNodeServiceImpl implements AnalysisNodeService {

	private static final Logger logger = LoggerFactory.getLogger(AnalysisNodeServiceImpl.class);

	@Autowired
	private AnalysisNodeMapper analysisNodeMapper;
	@Autowired
	private AnalysisNodeService analysisNodeService;
	@Autowired
	private CasesGroupService casesGroupService;
	@Autowired
	private PcCasesService pcCasesService;
	@Autowired
	private UserCenterService userCenterService;

	/**
	 * 获取我的待办节点
	 *
	 * @param account
	 * @param xsbh
	 * @return
	 */
	@Override
	public AnalysisNode getMyHandleNode(String account, String xsbh) {
		AnalysisNode param = new AnalysisNode();
		param.setXsbh(xsbh);
		param.setJsrybh(account);
		param.setSfjs(YesNoEnum.NO.name());
		return analysisNodeMapper.selectOne(param);
	}

	/**
	 * 获取我已完成的最后一个节点
	 *
	 * @param account
	 * @param xsbh
	 * @return
	 */
	@Override
	public AnalysisNode getMyLastNode(String account, String xsbh) {
		AnalysisNode param = new AnalysisNode();
		param.setXsbh(xsbh);
		param.setJsrybh(account);
		param.setSfjs(YesNoEnum.NO.name());
		Example condition = new Example(AnalysisNode.class);
		condition.createCriteria().andEqualTo("xsbh", xsbh)
				.andEqualTo("jsrybh", account);
		condition.setOrderByClause("id desc");
		List<AnalysisNode> lsAnalysisNode = analysisNodeMapper.selectByExample(condition);
		return lsAnalysisNode.get(0);
	}

	/**
	 * 批量插入
	 *
	 * @param lsAnalysisNode
	 */
	@Override
	public void insertList(List<AnalysisNode> lsAnalysisNode) {
		if(CollectionUtils.isNotEmpty(lsAnalysisNode)){
			analysisNodeMapper.insertList(lsAnalysisNode);
		}
	}

	/**
	 * 结束指派状态的数据（修改是否结束标记为结束状态）
	 *
	 * @param xsbh
	 * @return
	 */
	@Override
	public void finishAllocate(String xsbh) {
		AnalysisNode updateObject = new AnalysisNode();
		updateObject.setSfjs(YesNoEnum.YES.name());

		Example condition = new Example(AnalysisNode.class);
		condition.createCriteria()
				.andEqualTo("xsbh", xsbh)
				.andEqualTo("jdzt", AnalysisNodeEnum.WAIT_ALLOCATE.name())
				.andEqualTo("sfjs", YesNoEnum.NO.name());
		analysisNodeMapper.updateByExampleSelective(updateObject, condition);
	}

	/**
	 * 是否下发研判产品线索
	 *
	 * @param xsbh
	 * @return
	 */
	@Override
	public boolean isSend(String xsbh) {
		AnalysisNode queryParam = new AnalysisNode();
		queryParam.setXsbh(xsbh);
		return analysisNodeMapper.selectCount(queryParam) > 0 ? true : false;
	}

	/**
	 * 创建领导用户数据节点
	 *
	 * @param deptUsers
	 */
	@Override
	public void saveAllocateLeader(String xsbh, User loginUser, List<User> deptUsers) {
		List<AnalysisNode> analysisNodeBatchList = Lists.newArrayList();
		List<CasesGroup> casesGroupBatchList = Lists.newArrayList();
		List<String> lsCaseCode = pcCasesService.listMergeCaseCodeByXsbh(xsbh);
		deptUsers.stream().forEach(i -> {
			Dept receiveUserDept = i.getDepartment();
			AnalysisNode analysisNode = new AnalysisNode();
			analysisNode.setXsbh(xsbh);
			analysisNode.setFsrybh(loginUser.getAccount());
			analysisNode.setFsrymc(loginUser.getName());
			analysisNode.setFsryjgbh(loginUser.getDepartment() == null ? "" : loginUser.getDepartment().getCode());
			analysisNode.setFsryjgmc(loginUser.getDepartment() == null ? "" : loginUser.getDepartment().getFullname());
			analysisNode.setJsrybh(i.getAccount());
			analysisNode.setJsrymc(i.getName());
			analysisNode.setJsryjgbh(receiveUserDept == null ? "" : receiveUserDept.getCode());
			analysisNode.setJsryjgmc(receiveUserDept == null ? "" : receiveUserDept.getFullname());
			analysisNode.setJdzt(AnalysisNodeEnum.WAIT_ALLOCATE.name());
			analysisNode.setSfjs(YesNoEnum.NO.name());
			analysisNode.setLrry(loginUser.getAccount());
			analysisNode.setLrrymc(loginUser.getName());
			analysisNode.setLrsj(new Date());
			analysisNodeBatchList.add(analysisNode);
			lsCaseCode.stream().forEach(ajbh -> {
				// 不存在则加入到集合里
				if(!casesGroupService.isExist(ajbh, i.getAccount())){
					CasesGroup casesGroup = new CasesGroup();
					casesGroup.setAjbh(ajbh);
					casesGroup.setJgdm(receiveUserDept.getCode());
					casesGroup.setJgmc(receiveUserDept.getFullname());
					casesGroup.setJybh(i.getAccount());
					casesGroup.setJyxm(i.getName());
					casesGroup.setSjhm(i.getPhone());
					casesGroup.setLrrybh(loginUser.getAccount());
					casesGroup.setLrrymc(loginUser.getName());
					casesGroup.setLrryjgdm(loginUser.getDepartment() == null ? "" : loginUser.getDepartment().getCode());
					casesGroup.setLrryjgmc(loginUser.getDepartment() == null ? "" : loginUser.getDepartment().getFullname());
					casesGroup.setSfyx("1");
					casesGroupBatchList.add(casesGroup);
				}
			});
		});
		analysisNodeService.insertList(analysisNodeBatchList);
		casesGroupService.insertList(casesGroupBatchList);
		deptUsers.forEach(i -> MessageUtil.sendSMS(i.getPhone(),
				loginUser.getDepartment() == null ? "" : loginUser.getDepartment().getFullname(),
				loginUser.getName(), xsbh, AnalysisNodeEnum.WAIT_ALLOCATE));

	}

	/**
	 * 创建签收人员数据节点
	 *
	 * @param persons
	 */
	@Override
	public void saveSignPerson(String xsbh, User loginUser, List<String> persons) {
		List<AnalysisNode> analysisNodeBatchList = Lists.newArrayList();
		List<CasesGroup> casesGroupBatchList = Lists.newArrayList();
		List<String> lsCaseCode = pcCasesService.listMergeCaseCodeByXsbh(xsbh);
		for (String receiveAccount : persons) {
			// 接收人员
			User receiveUser = null;
			try {
				receiveUser = userCenterService.getUserLazyByUserName(receiveAccount);
			} catch (Exception e) {
				throw new ServiceException(SystemExceptionEnum.REQUEST_FAILED.code(), SystemExceptionEnum.REQUEST_FAILED.descp());
			}
			if(receiveUser != null){
				MessageUtil.sendSMS(receiveUser.getPhone(),
						loginUser.getDepartment() == null ? "" : loginUser.getDepartment().getFullname(),
						loginUser.getName(), xsbh, AnalysisNodeEnum.WAIT_SIGN);
				AnalysisNode analysisNode = new AnalysisNode();
				analysisNode.setXsbh(xsbh);
				analysisNode.setFsrybh(loginUser.getAccount());
				analysisNode.setFsrymc(loginUser.getName());
				analysisNode.setFsryjgbh(loginUser.getDepartment() == null ? "" : loginUser.getDepartment().getCode());
				analysisNode.setFsryjgmc(loginUser.getDepartment() == null ? "" : loginUser.getDepartment().getFullname());
				analysisNode.setJsrybh(receiveUser.getAccount());
				analysisNode.setJsrymc(receiveUser.getName());
				analysisNode.setJsryjgbh(receiveUser.getDepartment() == null ? "" : receiveUser.getDepartment().getCode());
				analysisNode.setJsryjgmc(receiveUser.getDepartment() == null ? "" : receiveUser.getDepartment().getFullname());
				analysisNode.setJdzt(AnalysisNodeEnum.WAIT_SIGN.name());
				analysisNode.setSfjs(YesNoEnum.NO.name());
				analysisNode.setLrry(loginUser.getAccount());
				analysisNode.setLrrymc(loginUser.getName());
				analysisNode.setLrsj(new Date());
				analysisNodeBatchList.add(analysisNode);
				for (String ajbh : lsCaseCode) {
					// 不存在则加入到集合里
					if(!casesGroupService.isExist(ajbh, receiveUser.getAccount())){
						CasesGroup casesGroup = new CasesGroup();
						casesGroup.setAjbh(ajbh);
						casesGroup.setJgdm(receiveUser.getDepartment() == null ? "" : receiveUser.getDepartment().getCode());
						casesGroup.setJgmc(receiveUser.getDepartment() == null ? "" : receiveUser.getDepartment().getFullname());
						casesGroup.setJybh(receiveUser.getAccount());
						casesGroup.setJyxm(receiveUser.getName());
						casesGroup.setSjhm(receiveUser.getPhone());
						casesGroup.setLrrybh(loginUser.getAccount());
						casesGroup.setLrrymc(loginUser.getName());
						casesGroup.setLrryjgdm(loginUser.getDepartment() == null ? "" : loginUser.getDepartment().getCode());
						casesGroup.setLrryjgmc(loginUser.getDepartment() == null ? "" : loginUser.getDepartment().getFullname());
						casesGroup.setSfyx("1");
						casesGroupBatchList.add(casesGroup);
					}
				}
			}
		}
		analysisNodeService.insertList(analysisNodeBatchList);
		casesGroupService.insertList(casesGroupBatchList);
	}

	/**
	 * 签收节点数据
	 */
	@Override
	public void sign(String xsbh) {
		User loginUser = UserUtil.getUser();
		AnalysisNode queryParam = new AnalysisNode();
		queryParam.setXsbh(xsbh);
		queryParam.setJsrybh(loginUser.getAccount());
		queryParam.setJdzt(AnalysisNodeEnum.WAIT_SIGN.name());
		queryParam = analysisNodeMapper.selectOne(queryParam);
		if(queryParam != null){
			AnalysisNode updateObject = new AnalysisNode();
			updateObject.setId(queryParam.getId());
			updateObject.setJdzt(AnalysisNodeEnum.SIGNED.name());
			updateObject.setSfjs(YesNoEnum.YES.name());
			analysisNodeMapper.updateByPrimaryKeySelective(updateObject);
		}
	}

	/**
	 * 退回节点数据
	 *
	 * @param xsbh
	 */
	@Override
	public void retreat(String xsbh) {
		User loginUser = UserUtil.getUser();
		AnalysisNode queryParam = new AnalysisNode();
		queryParam.setXsbh(xsbh);
		queryParam.setJsrybh(loginUser.getAccount());
		queryParam.setJdzt(AnalysisNodeEnum.WAIT_SIGN.name());
		queryParam = analysisNodeMapper.selectOne(queryParam);
		if(queryParam != null){
			AnalysisNode updateObject = new AnalysisNode();
			updateObject.setId(queryParam.getId());
			updateObject.setJdzt(AnalysisNodeEnum.RETREAT.name());
			updateObject.setSfjs(YesNoEnum.YES.name());
			analysisNodeMapper.updateByPrimaryKeySelective(updateObject);
		}
	}
}

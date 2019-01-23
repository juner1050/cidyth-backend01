package com.hyzs.cidyth.module.investigatehis.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import com.hyzs.cidyth.module.uc.common.UserUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyzs.cidyth.common.enums.SuspectsInformationCategory;
import com.hyzs.cidyth.common.helper.ContextUserHelper;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.investigatehis.dao.InvestigateHisDetailMapper;
import com.hyzs.cidyth.module.investigatehis.dao.InvestigateHisMapper;
import com.hyzs.cidyth.module.investigatehis.entity.InvestigateHis;
import com.hyzs.cidyth.module.investigatehis.entity.InvestigateHisDetail;
import com.hyzs.cidyth.module.investigatehis.service.InvestigateHisService;
import com.hyzs.cidyth.module.investigatehis.vo.InvestigateHistory;
import com.hyzs.cidyth.module.uc.vo.User;

@Service
public class InvestigateHisServiceImpl implements InvestigateHisService {
	private static final Logger logger = LoggerFactory.getLogger(InvestigateHisServiceImpl.class);
	@Autowired
	private InvestigateHisMapper investigateHisMapper;
	@Autowired
	private InvestigateHisDetailMapper investigateHisDetailMapper;

	@Transactional
	@Override
	public int saveInvestigateHistory(InvestigateHistory history) {
		int rs = 0;
		if (history != null) {
			User loginUser = UserUtil.getUser();
			if (loginUser == null) {
				throw new ServiceException("无法获取当前登录用户!");
			}
			if (StringUtils.isBlank(history.getCaseNo())) {
				throw new ServiceException("案件编号不能为空!");
			}
			if (StringUtils.isBlank(history.getSuspectId())) {
				throw new ServiceException("嫌疑人身份证号码不能为空!");
			}
			if (StringUtils.isBlank(history.getSuspectInfoType())
					|| !SuspectsInformationCategory.codes().contains(history.getSuspectInfoType())) {
				throw new ServiceException("嫌疑人信息分类不能为空或者不是指定的取值范围" + SuspectsInformationCategory.codes());
			}
			InvestigateHis his = new InvestigateHis();
			his.setCaseNo(history.getCaseNo());
			his.setSuspectId(history.getSuspectId());
			his.setSuspectInfoType(history.getSuspectInfoType());
			his.setCreatedById(loginUser.getAccount());
			List<InvestigateHis> result = investigateHisMapper.select(his);
			Long recordId = null;
			if (CollectionUtils.isEmpty(result)) {
				his.setCreatedByName(loginUser.getName());
				investigateHisMapper.insert(his);
				if (his.getId() != null) {
					recordId = his.getId();
				}
			} else {
				recordId = result.get(0).getId();
			}
			if (recordId != null && StringUtils.isNotBlank(history.getDetailContent())) {
				InvestigateHisDetail detail = new InvestigateHisDetail();
				detail.setDetailContent(history.getDetailContent().length() > 4000
						? history.getDetailContent().substring(0, 3999) : history.getDetailContent());
				detail.setInvestigateHisId(recordId);
				investigateHisDetailMapper.insert(detail);
			}
			rs = 1;
		}
		return rs;
	}

	@Override
	public List loadRemarkList(String caseNo, String suspectId, String suspectInfoType) {
		User loginUser = UserUtil.getUser();
		if (loginUser == null) {
			throw new ServiceException("无法获取当前登录用户!");
		}
		SuspectsInformationCategory sic=null;
		if(StringUtils.isNotBlank(suspectInfoType)){
			sic = SuspectsInformationCategory.ofCode(suspectInfoType);
			if(sic==null) throw new ServiceException("嫌疑人信息分类不是指定的取值范围" + SuspectsInformationCategory.codes());
		}
		InvestigateHis his = new InvestigateHis();
		his.setCreatedById(loginUser.getAccount());
		his.setCaseNo(caseNo);
		his.setSuspectId(suspectId);
		if(sic!=null){
			his.setSuspectInfoType(suspectInfoType);
		}
		return investigateHisDetailMapper.selectDetails(his);
	}

}

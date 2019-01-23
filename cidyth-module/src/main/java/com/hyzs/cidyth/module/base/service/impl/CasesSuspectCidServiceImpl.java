package com.hyzs.cidyth.module.base.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyzs.cidyth.module.base.dao.CasesSuspectCidMapper;
import com.hyzs.cidyth.module.base.entity.CasesSuspectCid;
import com.hyzs.cidyth.module.base.service.CasesSuspectCidService;

import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("CasesSuspectCidService")
public class CasesSuspectCidServiceImpl implements CasesSuspectCidService {

	private static final Logger logger = LoggerFactory.getLogger(CasesSuspectCidServiceImpl.class);

	@Autowired
	private CasesSuspectCidMapper casesSuspectCidMapper;

	@Override
	public List<CasesSuspectCid> listCasesSuspectByAjbh(String ajbh) {
		return casesSuspectCidMapper.listCasesSuspectByAjbh(ajbh);
	}

	@Override
	public boolean isExist(String rybh, String ajbh) {
		List<CasesSuspectCid> lsCasesSuspectCid = listCasesSuspectByAjbh(ajbh);
		if(CollectionUtils.isNotEmpty(lsCasesSuspectCid)){
			for(CasesSuspectCid casesSuspectCid : lsCasesSuspectCid){
				if(StringUtils.isNotEmpty(casesSuspectCid.getRybh()) && casesSuspectCid.getRybh().equals(rybh)){
					return true;
				}
			}
		}
		return false;
	}
}

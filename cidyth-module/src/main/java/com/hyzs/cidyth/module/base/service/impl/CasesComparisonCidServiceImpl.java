package com.hyzs.cidyth.module.base.service.impl;

import com.hyzs.cidyth.module.base.dao.CasesComparisonCidMapper;
import com.hyzs.cidyth.module.base.entity.CasesComparison;
import com.hyzs.cidyth.module.base.service.CasesComparisonCidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("CasesComparisonCidService")
public class CasesComparisonCidServiceImpl implements CasesComparisonCidService {

	private static final Logger logger = LoggerFactory.getLogger(CasesComparisonCidServiceImpl.class);

	@Autowired
	private CasesComparisonCidMapper casesComparisonCidMapper;

	@Override
	public List<CasesComparison> listCasesComparisonByAjbh(String ajbh) {
		return casesComparisonCidMapper.listCasesComparisonByAjbh(ajbh);
	}
}

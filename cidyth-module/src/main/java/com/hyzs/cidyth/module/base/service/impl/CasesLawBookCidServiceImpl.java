package com.hyzs.cidyth.module.base.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyzs.cidyth.module.base.dao.CasesLawBookCidMapper;
import com.hyzs.cidyth.module.base.entity.CasesLawBookCid;
import com.hyzs.cidyth.module.base.service.CasesLawBookCidService;

import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("CasesLawBookCidService")
public class CasesLawBookCidServiceImpl implements CasesLawBookCidService {

	private static final Logger logger = LoggerFactory.getLogger(CasesLawBookCidServiceImpl.class);

	@Autowired
	private CasesLawBookCidMapper casesLawBookCidMapper;

	@Override
	public List<CasesLawBookCid> listCasesLawBookByAjbh(String ajbh) {
		CasesLawBookCid param = new CasesLawBookCid();
		param.setAjbh(ajbh);
		return casesLawBookCidMapper.select(param);
	}
	

}

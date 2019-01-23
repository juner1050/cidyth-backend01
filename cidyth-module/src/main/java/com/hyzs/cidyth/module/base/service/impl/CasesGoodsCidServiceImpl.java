package com.hyzs.cidyth.module.base.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyzs.cidyth.module.base.dao.CasesGoodsCidMapper;
import com.hyzs.cidyth.module.base.entity.CasesGoodsCid;
import com.hyzs.cidyth.module.base.service.CasesGoodsCidService;

import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("CasesGoodsCidService")
public class CasesGoodsCidServiceImpl implements CasesGoodsCidService {

	private static final Logger logger = LoggerFactory.getLogger(CasesGoodsCidServiceImpl.class);

	@Autowired
	private CasesGoodsCidMapper casesGoodsCidMapper;
	
	public List<CasesGoodsCid> listCasesGoods(CasesGoodsCid casesGoodsCid) {
		return casesGoodsCidMapper.select(casesGoodsCid);
	}
	
}

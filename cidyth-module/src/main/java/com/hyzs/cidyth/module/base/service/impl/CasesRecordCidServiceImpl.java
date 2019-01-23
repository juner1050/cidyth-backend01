package com.hyzs.cidyth.module.base.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyzs.cidyth.module.base.dao.CasesRecordCidMapper;
import com.hyzs.cidyth.module.base.entity.CasesRecordCid;
import com.hyzs.cidyth.module.base.service.CasesRecordCidService;

import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("CasesRecordCidService")
public class CasesRecordCidServiceImpl implements CasesRecordCidService {

	private static final Logger logger = LoggerFactory.getLogger(CasesRecordCidServiceImpl.class);

	@Autowired
	private CasesRecordCidMapper casesRecordCidMapper;
	
	@Override
	public List<CasesRecordCid> listCasesRecord(CasesRecordCid casesRecordCid) {
		return casesRecordCidMapper.select(casesRecordCid);
	}

	
	
}

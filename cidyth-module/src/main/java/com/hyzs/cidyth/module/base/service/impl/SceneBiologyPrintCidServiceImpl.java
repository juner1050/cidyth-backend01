package com.hyzs.cidyth.module.base.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyzs.cidyth.module.base.dao.SceneBiologyPrintCidMapper;
import com.hyzs.cidyth.module.base.entity.SceneBiologyPrintCid;
import com.hyzs.cidyth.module.base.service.SceneBiologyPrintCidService;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("SceneBiologyPrintCidService")
public class SceneBiologyPrintCidServiceImpl implements SceneBiologyPrintCidService {

	private static final Logger logger = LoggerFactory.getLogger(SceneBiologyPrintCidServiceImpl.class);

	@Autowired
	private SceneBiologyPrintCidMapper sceneBiologyPrintCidMapper;

	@Override
	public List<SceneBiologyPrintCid> listBiologyPrintByAjbh(String ajbh) {
		return sceneBiologyPrintCidMapper.listBiologyPrintByAjbh(ajbh);
	}

	@Override
	public int countByAjbh(String ajbh) {
		return sceneBiologyPrintCidMapper.countByAjbh(ajbh);
	}
}

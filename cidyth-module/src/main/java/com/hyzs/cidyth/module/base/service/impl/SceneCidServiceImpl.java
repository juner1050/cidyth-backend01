package com.hyzs.cidyth.module.base.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyzs.cidyth.module.base.dao.SceneCidMapper;
import com.hyzs.cidyth.module.base.entity.SceneCid;
import com.hyzs.cidyth.module.base.service.SceneCidService;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("SceneCidService")
public class SceneCidServiceImpl implements SceneCidService {

	private static final Logger logger = LoggerFactory.getLogger(SceneCidServiceImpl.class);

	@Autowired
	private SceneCidMapper sceneCidMapper;

	@Override
	public List<SceneCid> listSceneByAjbh(String ajbh) {
		return sceneCidMapper.listSceneByAjbh(ajbh);
	}



}

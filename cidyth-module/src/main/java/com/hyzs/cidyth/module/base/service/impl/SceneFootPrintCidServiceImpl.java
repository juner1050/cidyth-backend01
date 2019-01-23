package com.hyzs.cidyth.module.base.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyzs.cidyth.module.base.dao.SceneFootPrintCidMapper;
import com.hyzs.cidyth.module.base.entity.SceneFootPrintCid;
import com.hyzs.cidyth.module.base.service.SceneFootPrintCidService;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("SceneFootPrintCidService")
public class SceneFootPrintCidServiceImpl implements SceneFootPrintCidService {

	private static final Logger logger = LoggerFactory.getLogger(SceneFootPrintCidServiceImpl.class);

	@Autowired
	private SceneFootPrintCidMapper sceneFootPrintCidMapper;

	@Override
	public List<SceneFootPrintCid> listFootPrintByAjbh(String ajbh) {
		return sceneFootPrintCidMapper.listFootPrintByAjbh(ajbh);
	}

	@Override
	public int countByAjbh(String ajbh) {
		return sceneFootPrintCidMapper.countByAjbh(ajbh);
	}
}

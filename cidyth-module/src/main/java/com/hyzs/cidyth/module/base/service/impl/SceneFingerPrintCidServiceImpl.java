package com.hyzs.cidyth.module.base.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyzs.cidyth.module.base.dao.SceneFingerPrintCidMapper;
import com.hyzs.cidyth.module.base.entity.SceneFingerPrintCid;
import com.hyzs.cidyth.module.base.service.SceneFingerPrintCidService;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("SceneFingerPrintCidService")
public class SceneFingerPrintCidServiceImpl implements SceneFingerPrintCidService {

	private static final Logger logger = LoggerFactory.getLogger(SceneFingerPrintCidServiceImpl.class);

	@Autowired
	private SceneFingerPrintCidMapper sceneFingerPrintCidMapper;

	@Override
	public List<SceneFingerPrintCid> listFingerPrintByAjbh(String ajbh) {
		return sceneFingerPrintCidMapper.listFingerPrintByAjbh(ajbh);
	}

	@Override
	public int countByAjbh(String ajbh) {
		return sceneFingerPrintCidMapper.countByAjbh(ajbh);
	}

}

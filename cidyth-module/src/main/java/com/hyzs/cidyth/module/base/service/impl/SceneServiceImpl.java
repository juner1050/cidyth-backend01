package com.hyzs.cidyth.module.base.service.impl;

import com.hyzs.cidyth.common.config.MultiThreadTaskConfig;
import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import com.hyzs.cidyth.module.base.dao.SceneImageCidMapper;
import com.hyzs.cidyth.module.base.dao.SceneMapper;
import com.hyzs.cidyth.module.base.entity.Scene;
import com.hyzs.cidyth.module.base.entity.SceneCid;
import com.hyzs.cidyth.module.base.service.*;
import com.hyzs.cidyth.module.time.service.TimeNodeService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("SceneService")
public class SceneServiceImpl implements SceneService {

	private static final Logger logger = LoggerFactory.getLogger(SceneServiceImpl.class);

	@Autowired
	private SceneMapper sceneMapper;
	@Autowired
	private SceneCidService sceneCidService;
	@Autowired
	private SceneFingerPrintService sceneFingerPrintService;
	@Autowired
	private SceneFootPrintService sceneFootPrintService;
	@Autowired
	private SceneBiologyPrintService sceneBiologyPrintService;
	@Autowired
	private TimeNodeService timeNodeService;


	@Override
	public List<Scene> list(String ajbh) {
		Scene scene = new Scene();
		scene.setAjbh(ajbh);
		List<Scene> lsScene = sceneMapper.select(scene);

		return lsScene;
	}

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh) {
		List<SceneCid> lsSceneCid = sceneCidService.listSceneByAjbh(ajbh);
		if(CollectionUtils.isNotEmpty(lsSceneCid)){
			for(SceneCid sceneCid : lsSceneCid){
				if(!isExist(sceneCid.getXxid())){
					Scene scene = new Scene();
					BeanUtils.copyProperties(sceneCid, scene);
					scene.setAjbh(ajbh);
					sceneMapper.insert(scene);
				}
				// 新增手印痕迹
				sceneFingerPrintService.insert(ajbh, sceneCid.getXxid());
				// 新增足迹痕迹
				sceneFootPrintService.insert(ajbh, sceneCid.getXxid());
				// 新增生物痕迹
				sceneBiologyPrintService.insert(ajbh, sceneCid.getXxid());
			}
		}
	}

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh, Dept sendDept, Dept receiveDept) {
		List<SceneCid> lsSceneCid = sceneCidService.listSceneByAjbh(ajbh);
		if(CollectionUtils.isNotEmpty(lsSceneCid)){
			StringBuilder stringBuilder = new StringBuilder();
			for(SceneCid sceneCid : lsSceneCid){
				if(!isExist(sceneCid.getXxid())){
					Scene scene = new Scene();
					BeanUtils.copyProperties(sceneCid, scene);
					scene.setAjbh(ajbh);
					sceneMapper.insert(scene);
				}
				// 新增手印痕迹
				sceneFingerPrintService.insert(ajbh, sceneCid.getXxid(), sendDept, receiveDept);
				// 新增足迹痕迹
				sceneFootPrintService.insert(ajbh, sceneCid.getXxid(), sendDept, receiveDept);
				// 新增生物痕迹
				sceneBiologyPrintService.insert(ajbh, sceneCid.getXxid(), sendDept, receiveDept);
				stringBuilder.append(sceneCid.getXcbh()).append("、");
			}
			if(stringBuilder.length() > 0){
				stringBuilder.deleteCharAt(stringBuilder.length()-1);
				timeNodeService.autoInsertBySyncData(ajbh, TimeNodeEnum.SCENE_SURVEY, "现勘编号：" + stringBuilder.toString(), sendDept.getCode(), sendDept.getFullname(), receiveDept.getCode(), receiveDept.getFullname());
			}
		}
	}

	private boolean isExist(String xxid) {
		Scene scene = new Scene();
		scene.setXxid(xxid);
		return sceneMapper.selectCount(scene) > 0 ? true : false;
	}

}

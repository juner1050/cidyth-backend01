package com.hyzs.cidyth.module.base.service.impl;

import com.hyzs.cidyth.common.config.MultiThreadTaskConfig;
import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import com.hyzs.cidyth.module.base.dao.SceneFingerPrintMapper;
import com.hyzs.cidyth.module.base.entity.SceneFingerPrint;
import com.hyzs.cidyth.module.base.entity.SceneFingerPrintCid;
import com.hyzs.cidyth.module.base.service.SceneFingerPrintCidService;
import com.hyzs.cidyth.module.base.service.SceneFingerPrintService;
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
@Service("SceneFingerPrintService")
public class SceneFingerPrintServiceImpl implements SceneFingerPrintService {

	private static final Logger logger = LoggerFactory.getLogger(SceneFingerPrintServiceImpl.class);

	@Autowired
	private SceneFingerPrintMapper sceneFingerPrintMapper;
	@Autowired
	private SceneFingerPrintCidService sceneFingerPrintCidService;
	@Autowired
	private TimeNodeService timeNodeService;

	@Override
	public List<SceneFingerPrint> listFingerPrintByAjbh(String ajbh) {
		SceneFingerPrint sceneFingerPrintParam = new SceneFingerPrint();
		sceneFingerPrintParam.setAjbh(ajbh);
		return listFingerPrints(sceneFingerPrintParam);
	}
	
	@Override
	public List<SceneFingerPrint> listFingerPrints(String ajbh, String xxid) {
		SceneFingerPrint sceneFingerPrintParam = new SceneFingerPrint();
		sceneFingerPrintParam.setAjbh(ajbh);
		sceneFingerPrintParam.setXxid(xxid);
		return listFingerPrints(sceneFingerPrintParam);
	}
	
	private List<SceneFingerPrint> listFingerPrints(SceneFingerPrint sceneFingerPrintParam) {
		List<SceneFingerPrint> lsSceneFingerPrint = sceneFingerPrintMapper.select(sceneFingerPrintParam);

		return lsSceneFingerPrint;
	}

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh, String sceneId) {
		List<SceneFingerPrintCid> lsSceneFingerPrintCid = sceneFingerPrintCidService.listFingerPrintByAjbh(ajbh);
		if(CollectionUtils.isNotEmpty(lsSceneFingerPrintCid)){
			for(SceneFingerPrintCid sceneFingerPrintCid : lsSceneFingerPrintCid){
				if(!isExist(sceneFingerPrintCid.getId())){
					SceneFingerPrint sceneFingerPrint = new SceneFingerPrint();
					BeanUtils.copyProperties(sceneFingerPrintCid, sceneFingerPrint);
					sceneFingerPrint.setAjbh(ajbh);
					sceneFingerPrint.setXxid(String.valueOf(sceneId));
					sceneFingerPrintMapper.insert(sceneFingerPrint);
				}
			}
		}
	}

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh, String sceneId, Dept sendDept, Dept receiveDept) {
		List<SceneFingerPrintCid> lsSceneFingerPrintCid = sceneFingerPrintCidService.listFingerPrintByAjbh(ajbh);
		if(CollectionUtils.isNotEmpty(lsSceneFingerPrintCid)){
			StringBuilder stringBuilder = new StringBuilder();
			for(SceneFingerPrintCid sceneFingerPrintCid : lsSceneFingerPrintCid){
				if(!isExist(sceneFingerPrintCid.getId())){
					SceneFingerPrint sceneFingerPrint = new SceneFingerPrint();
					BeanUtils.copyProperties(sceneFingerPrintCid, sceneFingerPrint);
					sceneFingerPrint.setAjbh(ajbh);
					sceneFingerPrint.setXxid(String.valueOf(sceneId));
					sceneFingerPrintMapper.insert(sceneFingerPrint);
					stringBuilder.append(sceneFingerPrint.getYlbw()).append("、");
				}
			}
			if(stringBuilder.length() > 0){
				stringBuilder.deleteCharAt(stringBuilder.length()-1);
				timeNodeService.autoInsertBySyncData(ajbh, TimeNodeEnum.SCENE_FINGER, "遗留部位：" + stringBuilder.toString(), sendDept.getCode(), sendDept.getFullname(), receiveDept.getCode(), receiveDept.getFullname());
			}
		}
	}

	private boolean isExist(String id) {
		SceneFingerPrint sceneFingerPrint = new SceneFingerPrint();
		sceneFingerPrint.setId(id);
		return sceneFingerPrintMapper.selectCount(sceneFingerPrint) > 0 ? true : false;
	}
}

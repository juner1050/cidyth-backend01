package com.hyzs.cidyth.module.base.service.impl;

import com.hyzs.cidyth.common.config.MultiThreadTaskConfig;
import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import com.hyzs.cidyth.module.base.dao.SceneFootPrintMapper;
import com.hyzs.cidyth.module.base.entity.SceneFootPrint;
import com.hyzs.cidyth.module.base.entity.SceneFootPrintCid;
import com.hyzs.cidyth.module.base.service.SceneFootPrintCidService;
import com.hyzs.cidyth.module.base.service.SceneFootPrintService;
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
@Service("SceneFootPrintService")
public class SceneFootPrintServiceImpl implements SceneFootPrintService {

	private static final Logger logger = LoggerFactory.getLogger(SceneFootPrintServiceImpl.class);

	@Autowired
	private SceneFootPrintMapper sceneFootPrintMapper;
	@Autowired
	private SceneFootPrintCidService sceneFootPrintCidService;
	@Autowired
	private TimeNodeService timeNodeService;
	
	@Override
	public List<SceneFootPrint> listFootPrintByAjbh(String ajbh) {
		SceneFootPrint sceneFootPrintParam = new SceneFootPrint();
		sceneFootPrintParam.setAjbh(ajbh);//��������
		return listFootPrints(sceneFootPrintParam);
	}

	@Override
	public List<SceneFootPrint> listFootPrints(String ajbh, String xxid) {
		SceneFootPrint sceneFootPrintParam = new SceneFootPrint();
		sceneFootPrintParam.setAjbh(ajbh);
		sceneFootPrintParam.setXxid(xxid);
		return listFootPrints(sceneFootPrintParam);
	}
	
	private List<SceneFootPrint> listFootPrints(SceneFootPrint sceneFootPrintParam) {
		List<SceneFootPrint> lsSceneFootPrint = sceneFootPrintMapper.select(sceneFootPrintParam);
		return lsSceneFootPrint;
	}
	
	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh, String sceneId) {
		List<SceneFootPrintCid> lsSceneFootPrintCid = sceneFootPrintCidService.listFootPrintByAjbh(ajbh);
		if(CollectionUtils.isNotEmpty(lsSceneFootPrintCid)){
			for(SceneFootPrintCid sceneFootPrintCid : lsSceneFootPrintCid){
				if(!isExist(sceneFootPrintCid.getId())){
					SceneFootPrint sceneFootPrint = new SceneFootPrint();
					BeanUtils.copyProperties(sceneFootPrintCid, sceneFootPrint);
					sceneFootPrint.setAjbh(ajbh);
					sceneFootPrint.setXxid(sceneId);
					sceneFootPrintMapper.insert(sceneFootPrint);
				}
			}
		}
	}

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh, String sceneId, Dept sendDept, Dept receiveDept) {
		List<SceneFootPrintCid> lsSceneFootPrintCid = sceneFootPrintCidService.listFootPrintByAjbh(ajbh);
		if(CollectionUtils.isNotEmpty(lsSceneFootPrintCid)){
			StringBuilder stringBuilder = new StringBuilder();
			for(SceneFootPrintCid sceneFootPrintCid : lsSceneFootPrintCid){
				if(!isExist(sceneFootPrintCid.getId())){
					SceneFootPrint sceneFootPrint = new SceneFootPrint();
					BeanUtils.copyProperties(sceneFootPrintCid, sceneFootPrint);
					sceneFootPrint.setAjbh(ajbh);
					sceneFootPrint.setXxid(sceneId);
					sceneFootPrintMapper.insert(sceneFootPrint);
					stringBuilder.append(sceneFootPrint.getYlbw()).append("、");
				}
			}
			if(stringBuilder.length() > 0){
				stringBuilder.deleteCharAt(stringBuilder.length()-1);
				timeNodeService.autoInsertBySyncData(ajbh, TimeNodeEnum.SCENE_FOOT, "遗留部位：" + stringBuilder.toString(), sendDept.getCode(), sendDept.getFullname(), receiveDept.getCode(), receiveDept.getFullname());
			}
		}
	}

	private boolean isExist(String id) {
		SceneFootPrint sceneFootPrint = new SceneFootPrint();
		sceneFootPrint.setId(id);
		return sceneFootPrintMapper.selectCount(sceneFootPrint) > 0 ? true : false;
	}

}

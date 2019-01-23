package com.hyzs.cidyth.module.base.service.impl;

import com.hyzs.cidyth.common.config.MultiThreadTaskConfig;
import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import com.hyzs.cidyth.module.base.dao.SceneBiologyPrintMapper;
import com.hyzs.cidyth.module.base.entity.SceneBiologyPrint;
import com.hyzs.cidyth.module.base.entity.SceneBiologyPrintCid;
import com.hyzs.cidyth.module.base.service.SceneBiologyPrintCidService;
import com.hyzs.cidyth.module.base.service.SceneBiologyPrintService;
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
@Service("SceneBiologyPrintService")
public class SceneBiologyPrintServiceImpl implements SceneBiologyPrintService {

	private static final Logger logger = LoggerFactory.getLogger(SceneBiologyPrintServiceImpl.class);

	@Autowired
	private SceneBiologyPrintMapper sceneBiologyPrintMapper;
	@Autowired
	private SceneBiologyPrintCidService sceneBiologyPrintCidService;
	@Autowired
	private TimeNodeService timeNodeService;
	
	@Override
	public List<SceneBiologyPrint> listBiologyPrintByAjbh(String ajbh) {
		SceneBiologyPrint sceneBiologyPrintParam = new SceneBiologyPrint();
		sceneBiologyPrintParam.setAjbh(ajbh);//��������
		return listBiologyPrints(sceneBiologyPrintParam);
	}
	
	@Override
	public List<SceneBiologyPrint> listBiologyPrints(String ajbh, String xxid) {
		SceneBiologyPrint sceneBiologyPrintParam = new SceneBiologyPrint();
		sceneBiologyPrintParam.setAjbh(ajbh);//��������
		sceneBiologyPrintParam.setXxid(xxid);//�ֳ���ϢID
		return listBiologyPrints(sceneBiologyPrintParam);
	}

	private List<SceneBiologyPrint> listBiologyPrints(SceneBiologyPrint sceneBiologyPrintParam) {
		List<SceneBiologyPrint> lsSceneBiologyPrint = sceneBiologyPrintMapper.select(sceneBiologyPrintParam);
		return lsSceneBiologyPrint;
	}

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh, String sceneId) {
		List<SceneBiologyPrintCid> lsSceneBiologyPrintCid = sceneBiologyPrintCidService.listBiologyPrintByAjbh(ajbh);
		if(CollectionUtils.isNotEmpty(lsSceneBiologyPrintCid)){
			for(SceneBiologyPrintCid sceneBiologyPrintCid : lsSceneBiologyPrintCid){
				if(!isExist(sceneBiologyPrintCid.getId())){
					SceneBiologyPrint sceneBiologyPrint = new SceneBiologyPrint();
					BeanUtils.copyProperties(sceneBiologyPrintCid, sceneBiologyPrint);
					sceneBiologyPrint.setAjbh(ajbh);
					sceneBiologyPrint.setXxid(sceneId);
					sceneBiologyPrintMapper.insert(sceneBiologyPrint);
				}
			}
		}
	}

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh, String sceneId, Dept sendDept, Dept receiveDept) {
		List<SceneBiologyPrintCid> lsSceneBiologyPrintCid = sceneBiologyPrintCidService.listBiologyPrintByAjbh(ajbh);
		if(CollectionUtils.isNotEmpty(lsSceneBiologyPrintCid)){
			StringBuilder stringBuilder = new StringBuilder();
			for(SceneBiologyPrintCid sceneBiologyPrintCid : lsSceneBiologyPrintCid){
				if(!isExist(sceneBiologyPrintCid.getId())){
					SceneBiologyPrint sceneBiologyPrint = new SceneBiologyPrint();
					BeanUtils.copyProperties(sceneBiologyPrintCid, sceneBiologyPrint);
					sceneBiologyPrint.setAjbh(ajbh);
					sceneBiologyPrint.setXxid(sceneId);
					sceneBiologyPrintMapper.insert(sceneBiologyPrint);
					stringBuilder.append(sceneBiologyPrint.getYlbw()).append("、");
				}
			}
			if(stringBuilder.length() > 0){
				stringBuilder.deleteCharAt(stringBuilder.length()-1);
				timeNodeService.autoInsertBySyncData(ajbh, TimeNodeEnum.SCENE_BIOLOGY, "遗留部位：" + stringBuilder.toString(), sendDept.getCode(), sendDept.getFullname(), receiveDept.getCode(), receiveDept.getFullname());
			}
		}
	}

	private boolean isExist(String id) {
		SceneBiologyPrint sceneBiologyPrint = new SceneBiologyPrint();
		sceneBiologyPrint.setId(id);
		return sceneBiologyPrintMapper.selectCount(sceneBiologyPrint) > 0 ? true : false;
	}
}

package com.hyzs.cidyth.module.base.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hyzs.cidyth.common.enums.SceneImageTypeEnum;
import com.hyzs.cidyth.module.base.dao.SceneImageCidMapper;
import com.hyzs.cidyth.module.base.entity.SceneImageCid;
import com.hyzs.cidyth.module.base.service.SceneImageCidService;
import com.hyzs.psd.gafa.daf.mybatis.tk.entity.Example;
import org.codehaus.plexus.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("SceneImageCidService")
public class SceneImageCidServiceImpl implements SceneImageCidService {

	private static final Logger logger = LoggerFactory.getLogger(SceneImageCidServiceImpl.class);

	@Autowired
	private SceneImageCidMapper sceneImageCidMapper;

	@Override
	public List<Map<String, Object>> getSceneImage(String investigationId) {
		/*List<Map<String, Object>> lsResult = Lists.newArrayList();
		Example query = new Example(SceneImageCid.class);
		query.createCriteria()
				.andEqualTo("investigationId", investigationId)
				.andEqualTo("lb", SceneImageTypeEnum.SCENE_IMG.code());
		query.or(query.createCriteria()
				.andEqualTo("investigationId", investigationId)
				.andEqualTo("lb", SceneImageTypeEnum.SCENE_PIC.code()));

		List<SceneImageCid> lsSceneImageCid = sceneImageCidMapper.selectByExample(query);
		lsSceneImageCid.stream().forEach(i ->{
					// 未删除则添加
					if(i.getDeleteFlag().equals("0") && SceneImageTypeEnum.codes().contains(i.getLb())){
						Map<String, Object> map = Maps.newHashMap();
						SceneImageTypeEnum sceneImageTypeEnum = SceneImageTypeEnum.ofCode(i.getLb());
						map.put("imageId", i.getId());
						map.put("name", sceneImageTypeEnum == null ? "" : sceneImageTypeEnum.descp());
						lsResult.add(map);
					}
				}
		);
		return lsResult;*/
		return Lists.newArrayList();
	}

	@Override
	public List<Map<String, Object>> getSceneAllImage(String investigationId) {
		/*List<Map<String, Object>> lsResult = Lists.newArrayList();
		Example query = new Example(SceneImageCid.class);
		query.createCriteria()
				.andEqualTo("investigationId", investigationId);

		List<SceneImageCid> lsSceneImageCid = sceneImageCidMapper.selectByExample(query);
		lsSceneImageCid.stream().forEach(i ->{
				// 未删除则添加
				if(i.getDeleteFlag().equals("0") && SceneImageTypeEnum.codes().contains(i.getLb())){
					Map<String, Object> map = Maps.newHashMap();
					SceneImageTypeEnum sceneImageTypeEnum = SceneImageTypeEnum.ofCode(i.getLb());
					map.put("imageId", i.getId());
					map.put("name", sceneImageTypeEnum == null ? "" : sceneImageTypeEnum.descp());
					lsResult.add(map);
				}
			}
		);
		return lsResult;*/
		return Lists.newArrayList();
	}

	@Override
	public Map<String, Object> getImage(String id) {
		/*Map<String, Object> mapResult = Maps.newHashMap();
		SceneImageCid sceneImageCid = new SceneImageCid();
		sceneImageCid.setId(id);
		sceneImageCid = sceneImageCidMapper.selectOne(sceneImageCid);
		if(sceneImageCid == null){
			return null;
		}
		SceneImageTypeEnum sceneImageTypeEnum = SceneImageTypeEnum.ofCode(sceneImageCid.getLb());
		mapResult.put("imageId", sceneImageCid.getId());
		mapResult.put("name", sceneImageTypeEnum == null ? "" : sceneImageTypeEnum.descp());
		return mapResult;*/
		return Maps.newHashMap();
	}
}

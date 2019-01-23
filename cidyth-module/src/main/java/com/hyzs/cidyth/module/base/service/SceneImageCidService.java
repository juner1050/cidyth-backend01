package com.hyzs.cidyth.module.base.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface SceneImageCidService {

	/**
	 * 根据investigationId获取现场图片
	 * @param investigationId
	 * @return
	 */
	List<Map<String, Object>> getSceneImage(String investigationId);
	/**
	 * 根据investigationId获取全部现场图片
	 * @param investigationId
	 * @return
	 */
	List<Map<String, Object>> getSceneAllImage(String investigationId);
	/**
	 * 根据id获取现场图片
	 * @param id
	 * @return
	 */
	Map<String, Object> getImage(String id);
}

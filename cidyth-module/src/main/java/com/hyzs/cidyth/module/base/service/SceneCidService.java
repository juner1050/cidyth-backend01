package com.hyzs.cidyth.module.base.service;

import java.util.List;

import com.hyzs.cidyth.module.base.entity.SceneCid;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface SceneCidService {

	/**
	 * 根据案件编号获取现场信息
	 * @param ajbh
	 * @return
	 */
	List<SceneCid> listSceneByAjbh(String ajbh);
}

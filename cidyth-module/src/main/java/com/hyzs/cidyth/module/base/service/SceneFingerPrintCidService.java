package com.hyzs.cidyth.module.base.service;

import com.hyzs.cidyth.module.base.entity.SceneFingerPrintCid;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface SceneFingerPrintCidService {

	/**
	 * 根据案件编号获取手印痕迹
	 * @param ajbh
	 * @return
	 */
	List<SceneFingerPrintCid> listFingerPrintByAjbh(String ajbh);
	/**
	 * 获取统计结果
	 * @param finger
	 * @return
	 */
	int countByAjbh(String ajbh);
}

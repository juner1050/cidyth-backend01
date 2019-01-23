package com.hyzs.cidyth.module.base.service;

import com.hyzs.cidyth.module.base.entity.SceneFootPrintCid;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface SceneFootPrintCidService {

	/**
	 * 根据案件编号获取足迹痕迹
	 * @param ajbh
	 * @return
	 */
	List<SceneFootPrintCid> listFootPrintByAjbh(String ajbh);
	/**
	 * 获取统计结果
	 * @param finger
	 * @return
	 */
	int countByAjbh(String ajbh);
}

package com.hyzs.cidyth.module.base.service;

import java.util.List;

import com.hyzs.cidyth.module.base.entity.SceneBiologyPrintCid;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface SceneBiologyPrintCidService {

	/**
	 * 根据案件编号获取生物痕迹
	 * @param ajbh
	 * @return
	 */
	List<SceneBiologyPrintCid> listBiologyPrintByAjbh(String ajbh);
	/**
	 * 获取统计结果
	 * @param finger
	 * @return
	 */
	int countByAjbh(String ajbh);
}

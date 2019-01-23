package com.hyzs.cidyth.module.base.service;


import com.hyzs.cidyth.module.base.entity.SceneBiologyPrint;
import com.hyzs.cidyth.module.uc.vo.Dept;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface SceneBiologyPrintService {

	/**
	 * 根据案件编号获取生物痕迹
	 * @param ajbh
	 * @return
	 */
	List<SceneBiologyPrint> listBiologyPrintByAjbh(String ajbh);

	/**
	 * 根据案件编号和现场信息id获取生物痕迹
	 * @param ajbh 案件编码
	 * @param xxid 现场信息ID
	 * @return
	 */
	List<SceneBiologyPrint> listBiologyPrints(String ajbh, String xxid);

	/**
	 * 新增生物痕迹
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh, String sceneId);
	/**
	 * 新增生物痕迹
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh, String sceneId, Dept sendDept, Dept receiveDept);
}

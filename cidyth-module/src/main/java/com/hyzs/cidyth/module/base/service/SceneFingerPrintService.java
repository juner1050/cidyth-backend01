package com.hyzs.cidyth.module.base.service;

import com.hyzs.cidyth.module.base.entity.SceneFingerPrint;
import com.hyzs.cidyth.module.uc.vo.Dept;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface SceneFingerPrintService {

	/**
	 * 根据案件编号获取手印痕迹
	 * @param ajbh
	 * @return
	 */
	List<SceneFingerPrint> listFingerPrintByAjbh(String ajbh);

	/**
	 * 根据案件编号和现场信息id获取手印痕迹
	 * @param ajbh 案件编号
	 * @param xxid 现场信息ID
	 * @return
	 */
	List<SceneFingerPrint> listFingerPrints(String ajbh, String xxid);

	/**
	 * 新增手印痕迹
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh, String sceneId);
	/**
	 * 新增手印痕迹
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh, String sceneId, Dept sendDept, Dept receiveDept);
}

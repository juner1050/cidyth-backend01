package com.hyzs.cidyth.module.base.service;

import com.hyzs.cidyth.module.base.entity.SceneFootPrint;
import com.hyzs.cidyth.module.uc.vo.Dept;

import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface SceneFootPrintService {

	/**
	 * 根据案件编号获取足迹痕迹
	 * @param ajbh 案件编码
	 * @return
	 */
	List<SceneFootPrint> listFootPrintByAjbh(String ajbh);
	
	/**
	 * 根据案件编号获取足迹痕迹
	 * @param ajbh 案件编码
	 * @param xxid 现场信息ID
	 * @return
	 */
	List<SceneFootPrint> listFootPrints(String ajbh, String xxid);

	/**
	 * 新增足迹痕迹
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh, String sceneId);
	/**
	 * 新增足迹痕迹
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh, String sceneId, Dept sendDept, Dept receiveDept);
}

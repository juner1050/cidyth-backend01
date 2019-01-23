package com.hyzs.cidyth.module.base.service;

import java.util.List;

import com.hyzs.cidyth.module.base.entity.Scene;
import com.hyzs.cidyth.module.uc.vo.Dept;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface SceneService {

	/**
	 * 根据案件编号获取现场信息
	 * @param ajbh
	 * @return
	 */
	List<Scene> list(String ajbh);

	/**
	 * 新增现场信息
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh);
	/**
	 * 新增现场信息
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh, Dept sendDept, Dept receiveDept);
}

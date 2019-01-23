package com.hyzs.cidyth.module.base.dao;

import com.hyzs.cidyth.module.base.entity.SceneFingerPrint;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface SceneFingerPrintMapper extends Mapper<SceneFingerPrint> {

	/**
	 * 新增手印痕迹
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh);
}
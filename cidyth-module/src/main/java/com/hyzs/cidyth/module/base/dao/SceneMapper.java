package com.hyzs.cidyth.module.base.dao;

import com.hyzs.cidyth.module.base.entity.Scene;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface SceneMapper extends Mapper<Scene> {
	
	/**
	 * 新增现场信息
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh);
}
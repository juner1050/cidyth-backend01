package com.hyzs.cidyth.module.base.dao;

import java.util.List;

import com.hyzs.cidyth.module.base.entity.SceneCid;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "xkSqlSessionFactory")
public interface SceneCidMapper extends Mapper<SceneCid> {
	
	/**
	 * 根据案件编号获取现场信息
	 * @param ajbh
	 * @return
	 */
	List<SceneCid> listSceneByAjbh(String ajbh);
	
}
package com.hyzs.cidyth.module.base.dao;

import com.hyzs.cidyth.module.base.entity.SceneBiologyPrint;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface SceneBiologyPrintMapper extends Mapper<SceneBiologyPrint> {

	/**
	 * 新增生物痕迹
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh);
}
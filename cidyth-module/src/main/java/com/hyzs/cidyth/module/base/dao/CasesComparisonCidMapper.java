package com.hyzs.cidyth.module.base.dao;

import com.hyzs.cidyth.module.base.entity.CasesComparison;
import com.hyzs.cidyth.module.base.entity.SceneCid;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

import java.util.List;

@DataSourceName(name = "xjSqlSessionFactory")
public interface CasesComparisonCidMapper extends Mapper<CasesComparison> {

	/**
	 * 根据案件编号获取刑技平台的比中信息保存到本地库
	 * @param ajbh
	 * @return
	 */
	List<CasesComparison> listCasesComparisonByAjbh(String ajbh);
	
}
package com.hyzs.cidyth.module.base.dao;

import java.util.List;

import com.hyzs.cidyth.module.base.entity.SceneBiologyPrintCid;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "xkSqlSessionFactory")
public interface SceneBiologyPrintCidMapper extends Mapper<SceneBiologyPrintCid> {

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
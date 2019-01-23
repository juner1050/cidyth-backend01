package com.hyzs.cidyth.module.base.dao;

import java.util.List;

import com.hyzs.cidyth.module.base.entity.SceneFootPrintCid;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "xkSqlSessionFactory")
public interface SceneFootPrintCidMapper extends Mapper<SceneFootPrintCid> {

	/**
	 * 根据案件编号获取足迹痕迹
	 * @param ajbh
	 * @return
	 */
	List<SceneFootPrintCid> listFootPrintByAjbh(String ajbh);
	/**
	 * 获取统计结果
	 * @param finger
	 * @return
	 */
	int countByAjbh(String ajbh);
}
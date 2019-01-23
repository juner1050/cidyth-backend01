package com.hyzs.cidyth.module.base.dao;

import java.util.List;
import java.util.Map;

import com.hyzs.cidyth.module.base.entity.CasesComparison;
import com.hyzs.cidyth.module.base.entity.TechnologyCompare;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "xjSqlSessionFactory")
public interface TechnologyCompareCidMapper extends Mapper<TechnologyCompare> {

	/**
	 * 获取比中信息
	 * @param ajbh
	 * @return
	 */
	List<TechnologyCompare> selectByPager(Map<String, Object> map);
	/**
	 * 查询指定案件的比中信息
	 * @param ajbh
	 * @return
	 */
	List<TechnologyCompare> selectByAjbh(String ajbh);

}
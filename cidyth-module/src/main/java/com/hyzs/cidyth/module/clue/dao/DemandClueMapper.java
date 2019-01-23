package com.hyzs.cidyth.module.clue.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hyzs.cidyth.module.clue.entity.Clue;
import com.hyzs.cidyth.module.clue.entity.DemandClue;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface DemandClueMapper extends Mapper<DemandClue> {
	/**
	 * 根据需求id查询回复的线索
	 * 
	 * @param demandIdList
	 *            需求id
	 * @return
	 */
	List<Clue> selectClueByDemandIdList(@Param("demandIdList") Collection<String> demandIdList,
			@Param("clueType") Integer clueType);
	/**
	 * 多条件查询
	 * @param condition
	 * @return
	 */
	List<DemandClue> selectBySelective(DemandClue condition);
	
	/**
	 * 按月查询机构反馈的线索
	 * 
	 * @param year
	 *            年份
	 * @param monthList
	 *            月份列表
	 * @param deptIdList
	 *            机构代码
	 * @param clueType
	 *            线索类型
	 * @return
	 */
	List<Clue> selectMonthlyClueOfDept(@Param("year") Integer year, @Param("monthList") Collection<Integer> monthList,
			@Param("deptIdList") Collection<String> deptIdList, @Param("clueType") Integer clueType);

}
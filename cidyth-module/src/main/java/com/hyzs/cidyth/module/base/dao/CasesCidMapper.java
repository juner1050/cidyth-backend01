package com.hyzs.cidyth.module.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

/**
 * 访问警综数据库
 */
@DataSourceName(name = "other1SqlSessionFactory")
public interface CasesCidMapper extends Mapper<Cases> {

	/**
	 * 获取警综案件列表
	 * @author 陈铭
	 * @date 2018-04-10 16:31:03
	 * @param entity 案件视图对象
	 * @return java.util.List<com.hyzs.cidyth.module.base.entity.Cases>
	 */
	List<Cases> selectListPackCases(Map<String,Object> entity);
	
	/**
	 * 根据案件编号查询
	 * @author 陈铭
	 * @date 2018-04-10 16:31:03
	 * @param entity 案件视图对象
	 * @return java.util.List<com.hyzs.cidyth.module.base.entity.Cases>
	 */
	Cases selectCaseByAjbh(String ajbh);
	
	/**
	 * 查询前N天到当天所立的案件
	 * @param dyas
	 * @return
	 */
	List<Cases> selectCasesInLastNDays(@Param("days") Integer days);
}
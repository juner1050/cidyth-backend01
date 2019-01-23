package com.hyzs.cidyth.module.demand.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hyzs.cidyth.module.demand.entity.DemandFlowRole;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface DemandFlowRoleMapper extends Mapper<DemandFlowRole> {
	/**
	 * 根据流转节点或者角色id查询关系
	 * 
	 * @param flowNodeNameList
	 *            流转节点列表
	 * @param roleIdList
	 *            角色id列表
	 * @return
	 */
	List<DemandFlowRole> selectBySelective(@Param("flowNodeNameList") Collection<String> flowNodeNameList,
			@Param("roleIdList") Collection<String> roleIdList);

}
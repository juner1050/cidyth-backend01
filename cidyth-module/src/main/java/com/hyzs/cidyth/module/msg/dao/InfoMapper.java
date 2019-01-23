package com.hyzs.cidyth.module.msg.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hyzs.cidyth.module.msg.entity.Info;
import com.hyzs.cidyth.module.msg.entity.InfoAcceptance;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface InfoMapper extends Mapper<Info> {

	List<Info> selectMonthlyInfoOfDept(@Param("year") Integer year, @Param("monthList") Collection<Integer> monthList,
			@Param("deptIdList") Collection<String> subDeptIdList);
	/**
	 * 根据下列条件之一查询:
	 * (1)无条件
	 * (2)案件编号
	 * (3)案件编号、需求发起人、需求接受机构
	 * (4)案件编号、需求发起人、需求接受人
	 * 
	 * @param caseNoList 案件编号
	 * @param fbry 发布人编号
	 * @param acceptDeptIdList 接受机构编码
	 * @param acceptUserIdList 接受人用户编号
	 */
	List<Info> selectBySelective(@Param("caseNoList") Collection<String> caseNoList, @Param("fbry") String fbry,
			@Param("acceptDeptIdList") Collection<String> acceptDeptIdList,
			@Param("acceptUserIdList") Collection<String> acceptUserIdList);
	/**
	 * 批量插入消息接受记录
	 * @param acceptList 
	 * @return
	 */
	int insertInfoAccpt(@Param("infoAcceptList") List<InfoAcceptance> acceptList);
	/**
	 * 根据消息查询消息接受目标
	 * @param infoId
	 * @return
	 */
	List<InfoAcceptance> selectInfoAcceptByInfoId(Long infoId);
}
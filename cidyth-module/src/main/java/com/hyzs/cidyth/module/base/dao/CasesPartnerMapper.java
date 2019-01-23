package com.hyzs.cidyth.module.base.dao;

import java.util.Collection;
import java.util.List;

import com.hyzs.cidyth.common.config.MultiThreadTaskConfig;
import org.apache.ibatis.annotations.Param;

import com.hyzs.cidyth.module.base.entity.CasesPartner;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;
import org.springframework.scheduling.annotation.Async;

@DataSourceName(name = "masterSqlSessionFactory")
public interface CasesPartnerMapper extends Mapper<CasesPartner> {
	/**
	 * 批量插入
	 * @param acceptList
	 * @return
	 */
	public int batchInsertCasePartners(@Param("casePartnerList") List<CasesPartner> casePartnerList);
	/**
	 * 根据案件编号查询合作组成员
	 * 
	 * @param ajbh
	 *            案件编号
	 * @return
	 */
	public List<CasesPartner> selectCasePartnersByAjbh(String ajbh);
	/**
	 * 指定的组员是否存在
	 * @param ajbh 案件编号
	 * @param jybh 组员警号
	 * @return 大于0表示存在,否则表示不存在
	 */
	public int isExist(@Param("ajbh")String ajbh,@Param("jybh")String jybh);
	/**
	 * 根据案件编号和组员警号移除组员
	 * 
	 * @param ajbh
	 *            案件编号
	 * @param jybhList
	 *            指定的警员编号列表
	 * @return
	 */
	public int deleteCasePartner(@Param("ajbh") String ajbh, @Param("jybhList") Collection<String> jybhList);
}

package com.hyzs.cidyth.module.base.dao;

import com.hyzs.cidyth.module.base.entity.CasesGroup;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.special.InsertListMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

@DataSourceName(name = "masterSqlSessionFactory")
public interface CasesGroupMapper extends Mapper<CasesGroup>, InsertListMapper<CasesGroup> {
	/**
	 * 批量插入
	 * @param acceptList
	 * @return
	 */
	public int batchInsertCaseGroups(@Param("caseGroupList") List<CasesGroup> caseGroupList);
	/**
	 * 根据案件编号查询合作组成员
	 * 
	 * @param ajbh
	 *            案件编号
	 * @return
	 */
	public List<CasesGroup> selectCaseGroupsByAjbh(String ajbh);
	/**
	 * 指定的组员是否存在
	 * @param ajbh 案件编号
	 * @param jybh 组员警号
	 * @return 大于0表示存在,否则表示不存在
	 */
	public int isExist(@Param("ajbh") String ajbh, @Param("jybh") String jybh);
	/**
	 * 根据案件编号和组员警号移除组员
	 * 
	 * @param ajbh
	 *            案件编号
	 * @param jybhList
	 *            指定的警员编号列表
	 * @return
	 */
	public int deleteCaseGroup(@Param("ajbh") String ajbh, @Param("jybhList") Collection<String> jybhList);
}

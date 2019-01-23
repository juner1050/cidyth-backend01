package com.hyzs.cidyth.module.base.dao;

import java.util.List;

import com.hyzs.cidyth.module.base.entity.CasesSuspectCid;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "other1SqlSessionFactory")
public interface CasesSuspectCidMapper extends Mapper<CasesSuspectCid> {
	
	/**
	 * 根据案件编号从警综库获取案件嫌疑人
	 * @param ajbh 案件编号
	 * @return
	 */
	List<CasesSuspectCid> listCasesSuspectByAjbh(String ajbh);
	
}
package com.hyzs.cidyth.module.base.dao;

import java.util.List;

import com.hyzs.cidyth.module.base.entity.CasesInformantCid;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "other1SqlSessionFactory")
public interface CasesInformantCidMapper extends Mapper<CasesInformantCid> {
	
	
	/**
	 * 根据案件编号从警综库获取案件报案人、受害人
	 * @param ajbh 案件编号
	 * @return
	 */
	List<CasesInformantCid> listCasesInformantByAjbh(String ajbh);
	
}
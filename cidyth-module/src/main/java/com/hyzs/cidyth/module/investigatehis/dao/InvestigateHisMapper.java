package com.hyzs.cidyth.module.investigatehis.dao;

import com.hyzs.cidyth.module.investigatehis.entity.InvestigateHis;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface InvestigateHisMapper extends Mapper<InvestigateHis> {

}

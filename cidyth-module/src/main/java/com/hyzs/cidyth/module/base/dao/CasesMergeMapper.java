package com.hyzs.cidyth.module.base.dao;

import com.hyzs.cidyth.module.base.entity.CasesMerge;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface CasesMergeMapper extends Mapper<CasesMerge> {
}
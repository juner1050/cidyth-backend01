package com.hyzs.cidyth.module.base.dao;

import com.hyzs.cidyth.module.base.entity.CasesComparison;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.special.InsertListMapper;

import java.util.List;

@DataSourceName(name = "masterSqlSessionFactory")
public interface CasesComparisonMapper extends Mapper<CasesComparison> {

}
package com.hyzs.cidyth.module.analysis.dao;

import com.hyzs.cidyth.module.analysis.entity.AnalysisNode;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.special.InsertListMapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface AnalysisNodeMapper extends Mapper<AnalysisNode>, InsertListMapper<AnalysisNode> {
}
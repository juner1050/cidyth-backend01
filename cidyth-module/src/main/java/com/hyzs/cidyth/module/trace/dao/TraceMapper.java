package com.hyzs.cidyth.module.trace.dao;

import com.hyzs.cidyth.module.trace.entity.Trace;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;
@DataSourceName(name = "masterSqlSessionFactory")
public interface TraceMapper extends Mapper<Trace> {
}
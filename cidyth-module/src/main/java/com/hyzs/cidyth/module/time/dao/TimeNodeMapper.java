package com.hyzs.cidyth.module.time.dao;

import com.hyzs.cidyth.module.time.entity.TimeNode;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface TimeNodeMapper extends Mapper<TimeNode> {
}
package com.hyzs.cidyth.module.mind.dao;

import com.hyzs.cidyth.module.mind.entity.Mind;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;
@DataSourceName(name = "masterSqlSessionFactory")
public interface MindMapper extends Mapper<Mind> {
}
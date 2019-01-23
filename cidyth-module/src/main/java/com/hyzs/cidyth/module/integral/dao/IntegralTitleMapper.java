package com.hyzs.cidyth.module.integral.dao;

import com.hyzs.cidyth.module.integral.entity.IntegralTitle;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface IntegralTitleMapper extends Mapper<IntegralTitle> {
}
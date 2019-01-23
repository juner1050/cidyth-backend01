package com.hyzs.cidyth.module.appraise.dao;

import com.hyzs.cidyth.module.appraise.entity.ClueAppraise;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface ClueAppraiseMapper extends Mapper<ClueAppraise> {
}
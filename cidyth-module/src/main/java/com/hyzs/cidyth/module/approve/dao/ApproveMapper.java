package com.hyzs.cidyth.module.approve.dao;

import com.hyzs.cidyth.module.approve.entity.Approve;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface ApproveMapper extends Mapper<Approve> {
}
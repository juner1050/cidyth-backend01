package com.hyzs.cidyth.module.base.dao;

import com.hyzs.cidyth.module.base.entity.CasesMergeHis;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.MySqlMapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface CasesMergeHisMapper extends Mapper<CasesMergeHis>, MySqlMapper<CasesMergeHis> {
}
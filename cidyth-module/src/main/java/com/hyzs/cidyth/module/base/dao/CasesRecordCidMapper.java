package com.hyzs.cidyth.module.base.dao;

import com.hyzs.cidyth.module.base.entity.CasesRecordCid;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "other1SqlSessionFactory")
public interface CasesRecordCidMapper extends Mapper<CasesRecordCid> {
}
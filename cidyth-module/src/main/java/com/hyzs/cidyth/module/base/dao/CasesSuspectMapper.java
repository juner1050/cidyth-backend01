package com.hyzs.cidyth.module.base.dao;

import com.hyzs.cidyth.module.base.entity.CasesSuspect;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

import java.util.List;

@DataSourceName(name = "masterSqlSessionFactory")
public interface CasesSuspectMapper extends Mapper<CasesSuspect> {

    List<CasesSuspect> listCasesSuspectByAjbh(List<String> lsAjbh);

}
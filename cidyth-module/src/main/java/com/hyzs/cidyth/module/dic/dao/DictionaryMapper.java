package com.hyzs.cidyth.module.dic.dao;

import com.hyzs.cidyth.module.dic.entity.Dictionary;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;
@DataSourceName(name = "masterSqlSessionFactory")
public interface DictionaryMapper extends Mapper<Dictionary> {
}
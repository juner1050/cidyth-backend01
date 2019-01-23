package com.hyzs.cidyth.module.clue.dao;

import com.hyzs.cidyth.module.clue.entity.Clue;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;
@DataSourceName(name = "masterSqlSessionFactory")
public interface ClueMapper extends Mapper<Clue> {
}
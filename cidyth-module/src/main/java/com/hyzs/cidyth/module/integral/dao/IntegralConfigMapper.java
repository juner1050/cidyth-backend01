package com.hyzs.cidyth.module.integral.dao;

import com.hyzs.cidyth.module.integral.entity.IntegralConfig;
import com.hyzs.cidyth.module.integral.vo.IntegralConfigVO;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

import java.util.List;

@DataSourceName(name = "masterSqlSessionFactory")
public interface IntegralConfigMapper extends Mapper<IntegralConfig> {

    List<IntegralConfigVO> initData();

}
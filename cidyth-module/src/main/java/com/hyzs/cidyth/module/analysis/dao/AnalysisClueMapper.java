package com.hyzs.cidyth.module.analysis.dao;

import com.hyzs.cidyth.module.analysis.dto.QueryAnalysisParam;
import com.hyzs.cidyth.module.analysis.entity.AnalysisClue;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

import java.util.List;

@DataSourceName(name = "masterSqlSessionFactory")
public interface AnalysisClueMapper extends Mapper<AnalysisClue> {

    /**
     * 研判线索列表
     * @param param
     * @return
     */
    List<AnalysisClue> list(QueryAnalysisParam param);

}
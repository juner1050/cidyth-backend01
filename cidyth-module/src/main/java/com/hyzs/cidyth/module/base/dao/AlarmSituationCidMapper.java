package com.hyzs.cidyth.module.base.dao;

import com.hyzs.cidyth.module.base.entity.AlarmSituationCid;
import com.hyzs.cidyth.module.base.entity.CasesGoodsCid;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "other1SqlSessionFactory")
public interface AlarmSituationCidMapper extends Mapper<AlarmSituationCid> {
}
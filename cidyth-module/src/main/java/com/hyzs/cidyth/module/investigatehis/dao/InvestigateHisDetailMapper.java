package com.hyzs.cidyth.module.investigatehis.dao;

import java.util.List;

import com.hyzs.cidyth.module.investigatehis.entity.InvestigateHis;
import com.hyzs.cidyth.module.investigatehis.entity.InvestigateHisDetail;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface InvestigateHisDetailMapper extends Mapper<InvestigateHisDetail> {
	
	public List<InvestigateHisDetail> selectDetails(InvestigateHis param);
}

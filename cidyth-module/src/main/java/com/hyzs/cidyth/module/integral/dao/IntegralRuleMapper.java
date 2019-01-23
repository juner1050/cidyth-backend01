package com.hyzs.cidyth.module.integral.dao;

import com.hyzs.cidyth.module.integral.entity.IntegralRule;
import com.hyzs.cidyth.module.integral.vo.IntegralRuleOption;
import com.hyzs.cidyth.module.integral.vo.IntegralRuleVO;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

import java.util.List;

@DataSourceName(name = "masterSqlSessionFactory")
public interface IntegralRuleMapper extends Mapper<IntegralRule> {

    List<IntegralRuleVO> list(IntegralRule integralRule);

    List<IntegralRuleOption> listIntegralRuleOptionByRuleType(String ruleType);
}
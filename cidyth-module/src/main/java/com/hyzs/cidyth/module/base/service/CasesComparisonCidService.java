package com.hyzs.cidyth.module.base.service;

import com.hyzs.cidyth.module.base.entity.CasesComparison;

import java.util.List;

/**
 * Created by 1 on 2018/10/12.
 */
public interface CasesComparisonCidService {

    /**
     * 根据案件编号获取刑技平台的比中信息保存到本地库
     * @param ajbh
     * @return
     */
    List<CasesComparison> listCasesComparisonByAjbh(String ajbh);

}

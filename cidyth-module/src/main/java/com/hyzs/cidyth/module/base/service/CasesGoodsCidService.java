package com.hyzs.cidyth.module.base.service;

import java.util.List;

import com.hyzs.cidyth.module.base.entity.CasesGoodsCid;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface CasesGoodsCidService {

	/**
	 * 获取作案物品
	 * @param casesGoodsCid
	 * @return
	 */
	List<CasesGoodsCid> listCasesGoods(CasesGoodsCid casesGoodsCid);
    
}

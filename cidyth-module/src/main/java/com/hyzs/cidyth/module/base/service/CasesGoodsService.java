package com.hyzs.cidyth.module.base.service;

import java.util.List;

import com.hyzs.cidyth.module.base.entity.CasesGoods;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface CasesGoodsService {
	
	/**
	 * 新增作案物品（从警综库查询物品，再插入到本地库）
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh);

	/**
	 * 获取涉案物品
	 * @param ajbh 案件编号
	 * @return
	 */
	List<CasesGoods> list(String ajbh);
}

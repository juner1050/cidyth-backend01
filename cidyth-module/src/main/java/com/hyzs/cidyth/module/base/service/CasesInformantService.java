package com.hyzs.cidyth.module.base.service;

import com.hyzs.cidyth.module.base.entity.CasesInformant;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface CasesInformantService {

	/**
	 * 新增案件报案人、受害人（从警综库查询笔录，再插入到本地库）
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh);

	/**
	 * 获取案件报案人、受害人
	 * @param ajbh 案件编号
	 * @return
	 */
	List<CasesInformant> list(String ajbh);
}

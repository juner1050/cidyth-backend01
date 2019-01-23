package com.hyzs.cidyth.module.base.service;

import com.hyzs.cidyth.module.base.entity.CasesLawBook;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface CasesLawBookService {

	/**
	 * 根据案件编号获取现场信息
	 * @param ajbh
	 * @return
	 */
	List<CasesLawBook> listCasesLawBookByAjbh(String ajbh);

	/**
	 * 新增法律文书信息
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh);
}

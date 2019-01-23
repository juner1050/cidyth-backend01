package com.hyzs.cidyth.module.base.service;

import java.util.List;

import com.hyzs.cidyth.module.base.entity.CasesLawBookCid;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface CasesLawBookCidService {

	/**
	 * 根据案件编号获取法律文书
	 * @param ajbh
	 * @return
	 */
	List<CasesLawBookCid> listCasesLawBookByAjbh(String ajbh);
}

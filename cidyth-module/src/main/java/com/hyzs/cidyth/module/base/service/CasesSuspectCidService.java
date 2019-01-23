package com.hyzs.cidyth.module.base.service;

import java.util.List;

import com.hyzs.cidyth.module.base.entity.CasesSuspectCid;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface CasesSuspectCidService {

	/**
	 * 根据案件编号从警综库获取案件嫌疑人
	 * @param ajbh 案件编号
	 * @return
	 */
	List<CasesSuspectCid> listCasesSuspectByAjbh(String ajbh);

	/**
	 * 根据人员编号判断嫌疑人是否存在
	 * @param rybh 人员编号
	 * @param ajbh 案件编号
	 * @return
	 */
	boolean isExist(String rybh, String ajbh);
}

package com.hyzs.cidyth.module.base.service;

import com.hyzs.cidyth.module.base.entity.CasesMergeHis;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface CasesMergeHisService {

	/**
	 * 批量插入
	 * @param lsString
	 */
	void insertList(List<String> lsString, String cbabh, String xsbh);

	/**
	 * 批量插入
	 * @param lsCasesMergeHis
	 */
	void insertList(List<CasesMergeHis> lsCasesMergeHis);

	/**
	 * 根据案件编号获取所在同一批案件的所有案件编号
	 * @param ajbh
	 * @return
	 */
	List<String> listMergeCaseCodeByCaseCode(String ajbh);

	/**
	 * 根据串并案编号获取同一批串并的案件编号
	 * @param ajbh
	 * @return
	 */
	List<String> listCaseCodeByCbabh(String ajbh);

	/**
	 * 根据线索编号获取串并案数量
	 * @param xsbh
	 * @return
	 */
	Integer countMergeCase(String xsbh);

}

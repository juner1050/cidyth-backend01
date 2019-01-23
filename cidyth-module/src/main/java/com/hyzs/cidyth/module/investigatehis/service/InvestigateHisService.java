package com.hyzs.cidyth.module.investigatehis.service;

import java.util.List;

import com.hyzs.cidyth.module.investigatehis.vo.InvestigateHistory;

public interface InvestigateHisService {
	/**
	 * 保存侦查记录
	 * 
	 * @param history
	 */
	public int saveInvestigateHistory(InvestigateHistory history);

	/**
	 * 查询备注列表
	 * 
	 * @param caseNo 案件编号
	 * @param suspectId 嫌疑人身份标识(身份证)
	 * @param suspectInfoType 嫌疑人信息分类
	 * @return
	 */
	public List loadRemarkList(String caseNo, String suspectId, String suspectInfoType);
}

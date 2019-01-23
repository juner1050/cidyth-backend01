package com.hyzs.cidyth.module.base.service;

import java.util.List;

import com.hyzs.cidyth.module.base.entity.CasesRecordCid;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface CasesRecordCidService {

	/**
	 * 获取案件笔录
	 * @param casesGoods
	 * @return
	 */
	List<CasesRecordCid> listCasesRecord(CasesRecordCid casesRecordCid);
}

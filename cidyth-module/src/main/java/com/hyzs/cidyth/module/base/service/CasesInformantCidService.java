package com.hyzs.cidyth.module.base.service;

import java.util.List;

import com.hyzs.cidyth.module.base.entity.CasesInformantCid;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface CasesInformantCidService {

	/**
	 * 获取案件报案人、受害人
	 * @param ajbh 案件编号
	 * @return
	 */
	List<CasesInformantCid> listCasesInformantByAjbh(String ajbh);

	/**
	 * 根据案件编号、人员编号获取报案人、受害人的具体类型
	 * @param rybh
	 * @param ajbh
	 * @return
	 */
	String getCasesInformantType(String rybh, String ajbh);
}

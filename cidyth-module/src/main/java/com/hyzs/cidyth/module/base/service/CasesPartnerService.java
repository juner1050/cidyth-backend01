package com.hyzs.cidyth.module.base.service;

import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.base.entity.CasesPartner;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface CasesPartnerService {

	/**
	 * 获取用户当月的所有参与的案件
	 * @param page 分页对象
	 * @return
	 */
	List<CasesPartner> listCasesPartnerByMonth(Page page);

	/**
	 * 统计本案参与人数
	 * @param ajbh 案件编号
	 * @return
	 */
	int countCasesJoinPerson(String ajbh);

}

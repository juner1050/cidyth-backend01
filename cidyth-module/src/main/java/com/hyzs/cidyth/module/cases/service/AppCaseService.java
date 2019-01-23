package com.hyzs.cidyth.module.cases.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.base.vo.PersonalCasesVO;
import com.hyzs.cidyth.module.cases.vo.AppCaseList;

/**
 * app端案件服务
 * @author derrick
 *
 */
public interface AppCaseService {
	/**
	 * 分页查询案件列表
	 * @param page
	 * @return
	 */
	PageInfo<AppCaseList> pageCaseList(Page page);

	/**
	 * 分页查询合成案件列表
	 * @param page
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	PageInfo<PersonalCasesVO> listCasesPartner(Page page, String beginTime, String endTime);

	/**
	 * 个人中心-破案统计
	 * @return
	 */
	Map<String, Object> finishTotal();
}

package com.hyzs.cidyth.module.base.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.analysis.vo.AnalysisSuspectDetailVO;
import com.hyzs.cidyth.module.base.entity.CasesSuspect;
import com.hyzs.cidyth.module.uc.vo.Dept;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface CasesSuspectService {

	/**
	 * 新增案件嫌疑人（从警综库查询笔录，再插入到本地库）
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh);
	
	/**
	 * 新增案件嫌疑人（从警综库查询笔录，再插入到本地库）
	 * @param ajbh 案件编号
	 * @param sendDept 发送人机构
	 * @param receiveDept 接收人机构
	 */
	void insert(String ajbh, Dept sendDept, Dept receiveDept);

	/**
	 * 获取案件嫌疑人
	 * @param ajbh 案件编号
	 * @return
	 */
	List<CasesSuspect> list(String ajbh);

	/**
	 * 根据案件编号、证件号码
	 * @param ajbh
	 * @param zjhm
	 * @return
	 */
	CasesSuspect getCasesSuspect(String ajbh, String zjhm);

	/**
	 * 保存嫌疑人
	 * @param casesSuspect
	 */
	void save(CasesSuspect casesSuspect);

	/**
	 * 根据条件查询嫌疑人
	 * @param casesSuspect
	 * @return
	 */
	PageInfo<CasesSuspect> listSuspectDetail(List<String> lsAjbh, Page page);

	/**
	 * 根据条件查询嫌疑人
	 * @param casesSuspect
	 * @return
	 */
	List<CasesSuspect> listSuspectDetail(List<String> lsAjbh);
}

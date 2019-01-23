package com.hyzs.cidyth.module.base.service;

import java.util.List;

import com.hyzs.cidyth.module.base.entity.CasesRecord;
import com.hyzs.cidyth.module.base.vo.CasesRecordKLParam;
import com.hyzs.cidyth.module.uc.vo.Dept;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface CasesRecordService {

	/**
	 * 新增案件笔录（从警综库查询笔录，再插入到本地库）
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh);

	/**
	 * 新增案件笔录（从警综库查询笔录，再插入到本地库）
	 * @param ajbh 案件编号
	 * @param sendDept 发送人机构
	 * @param receiveDept 接收人机构
	 * @return
	 */
	void insert(String ajbh, Dept sendDept, Dept receiveDept);

	/**
	 * 获取案件笔录
	 * @param ajbh 案件编号
	 * @return
	 */
	List<CasesRecord> list(String ajbh);

	/**
	 * 获取考拉系统的电子笔录
	 * @param casesRecordKLParam 人员编号、案件编号、身份证号
	 * @return
	 */
	List<CasesRecord> listRecordKL(List<CasesRecordKLParam> casesRecordKLParam);

	/**
	 * 获取考拉系统的电子笔录保存到本地数据库
	 * @param casesRecordKLParam 人员编号、案件编号、身份证号
	 * @return
	 */
	void insertRecordKL(List<CasesRecordKLParam> casesRecordKLParam);
	/**
	 * 获取考拉系统的电子笔录保存到本地数据库
	 * @param casesRecordKLParam 人员编号、案件编号、身份证号
	 * @return
	 */
	void insertRecordKL(List<CasesRecordKLParam> casesRecordKLParam, Dept sendDept, Dept receiveDept);
}

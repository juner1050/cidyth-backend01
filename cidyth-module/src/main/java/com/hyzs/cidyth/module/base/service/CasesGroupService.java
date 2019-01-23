package com.hyzs.cidyth.module.base.service;

import com.hyzs.cidyth.module.base.entity.CasesGroup;
import com.hyzs.cidyth.module.uc.vo.User;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface CasesGroupService {

	/**
	 * 保存到合成作战小组，如果不存在则插入
	 * @param ajbh 案件编号
	 * @param loginUser 用户
	 */
	void insert(String ajbh, User loginUser);

	/**
	 * 判断该案件的人员是否已存在合成作战小组里
	 * @param ajbh 案件编号
	 * @param account 用户账号
	 * @return
	 */
	boolean isExist(String ajbh, String account);

	/**
	 * 统计本案参与人数
	 * @param ajbh 案件编号
	 * @return
	 */
	int countCasesJoinPerson(String ajbh);

	/**
	 * 批量插入
	 * @param lsCasesGroup
	 */
	void insertList(List<CasesGroup> lsCasesGroup);

	/**
	 * 获取我参与的案件的案件编号
	 * @return
	 */
	List<String> listMyJoinCaseCode();

}

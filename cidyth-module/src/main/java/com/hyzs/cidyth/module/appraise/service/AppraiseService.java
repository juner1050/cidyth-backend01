package com.hyzs.cidyth.module.appraise.service;

import java.util.List;

import com.hyzs.cidyth.module.appraise.vo.Appraise;

/**
 * 评价服务
 * @author derrick
 *
 */
public interface AppraiseService {

	public int saveAppraise(Appraise appraise);
	/**
	 * 查询评价
	 * @param userAccount 评价人账号
	 * @param clueId 目标id
	 * @return
	 */
	List<Appraise> loadAppraise(String userAccount, String clueId);

	/**
	 * 根据线索id获取评分
	 * @param id
	 * @return
	 */
	Float getScoreByClueId(int id);
}

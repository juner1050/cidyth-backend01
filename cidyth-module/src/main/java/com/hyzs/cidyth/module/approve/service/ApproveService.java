package com.hyzs.cidyth.module.approve.service;

import com.hyzs.cidyth.module.approve.entity.Approve;

/**
 * 点赞服务
 * @author derrick
 *
 */
public interface ApproveService {

	/**
	 * 点赞
	 * @param referenceId
	 * @param referenceType
	 */
	int insert(Approve approve);

	/**
	 * 统计点赞数
	 * @param referenceId 目标记录id
	 * @param referenceType 目标记录类型
	 * @return
	 */
	int count(Integer referenceId, String referenceType);

}

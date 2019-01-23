package com.hyzs.cidyth.module.time.service;

import java.util.Date;
import java.util.List;

import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import com.hyzs.cidyth.module.time.entity.TimeNode;
import com.hyzs.cidyth.module.time.vo.TimeNodeVO;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface TimeNodeService {

	/**
	 * 根据案件编号获取时间轴
	 * @param ajbh
	 * @return
	 */
	List<TimeNodeVO> listByAjbh(String ajbh);
	/**
	 * 时间轴新增
	 * @param ajbh
	 * @param referenceId
	 * @param referenceType
	 * @param title
	 * @param content
	 * @param receiveOrgCode
	 */
	void insert(String ajbh, Integer referenceId, String referenceType, String title, String content, String receiveOrgCode);
	/**
	 * 时间轴新增
	 * @param ajbh
	 * @param referenceId
	 * @param referenceType
	 * @param title
	 * @param content
	 * @param receiveOrgCode
	 */
	void insert(String ajbh, Integer referenceId, String referenceType, String title, String content, String receiveOrgCode, Date lrsj);
	/**
	 * 时间轴新增
	 * @param ajbh
	 * @param referenceId
	 * @param referenceType
	 * @param title
	 * @param content
	 */
	void insert(String ajbh, Integer referenceId, String referenceType, String title, String content);
	/**
	 * 时间轴修改
	 * @param timeNode
	 */
	int update(TimeNode timeNode);

	/**
	 * 同步现勘、刑技数据时插入时间轴
	 * @param ajbh 案件编号
	 * @param timeNodeEnum 时间轴枚举
	 * @param title 标题
	 * @param content 发送内容
	 * @param sendOrgCode 发送机构编号
	 * @param sendOrgName 发送机构名称
	 * @param receiveOrgCode 接收机构编号
	 * @param receiveOrgName 接收机构名称
	 */
	void autoInsertBySyncData(String ajbh, TimeNodeEnum timeNodeEnum, String content, String sendOrgCode, String sendOrgName, String receiveOrgCode, String receiveOrgName);
}

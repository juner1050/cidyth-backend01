package com.hyzs.cidyth.module.dashboard.service;


import java.util.List;

import com.hyzs.cidyth.module.dashboard.vo.CaseSolvedOfOrgType;
import com.hyzs.cidyth.module.dashboard.vo.HonorCanvas;
import com.hyzs.cidyth.module.dashboard.vo.StatisticsModleOfOrganization;
import com.hyzs.cidyth.module.dashboard.vo.ThingsWillBeDone;

/**
 * 案件侦破概况服务
 * @author derrick
 *
 */
public interface DashboardService {
	/**
	 * 按警种维度加载案件当日N天前到当天为止侦破情况
	 * @param days 天数
	 * @return
	 * @throws Exception 
	 */
	CaseSolvedOfOrgType getDailyCaseSolveSurveyByOrganizationType(int days) throws Exception;
	/**
	 * 从当前年的1月分月查询当前登录用户所属机构的下级机构受理的案件
	 * @throws Exception 
	 */
	List<List<StatisticsModleOfOrganization>> getMonthlyCaseOfOrganization() throws Exception;
	/**
	 * 从当前年的1月分月查询当前登录用户所属机构的下级机构受理的需求
	 * @return
	 * @throws Exception 
	 */
	List<List<StatisticsModleOfOrganization>> getMonthlyDemandOfOrganization() throws Exception;
	/**
	 * 查询各个机构的反馈情况
	 * @return
	 * @throws Exception 
	 */
	List<StatisticsModleOfOrganization> getMonthlyFeedbackOfOrganization() throws Exception;
	/**
	 * 信息发布情况
	 * @return
	 * @throws Exception 
	 */
	List<List<StatisticsModleOfOrganization>> getMonthlyPublishedInfoOfOrganization() throws Exception;
	
	/**
	 * 超过指定天数没有处理的事项(代办事项)<br/>
	 * <b>说明:</b><br/>
	 * 1、如果当前登录用户是相关警种的单位领导,则找出 <font color='red'>其他单位发送给本单位的但没有指派</font>以及<font color='red'>签收了但是没有反馈</font>的需求<br/>
	 * 2、如果当前登录用户是办事民警,则找出 <font color='red'>领导指派过来的但没有签收</font>以及<font color='red'>签收了但是没有反馈</font>的需求<br/>
	 * @param days 天数
	 * @return
	 * @throws Exception 
	 */
	List<ThingsWillBeDone> getThingsWillBeDone(int days) throws Exception;
	/**
	 * 获取排名前几位的荣誉
	 * @param rank 名次数
	 * @return
	 */
	List<HonorCanvas> getHonorCanvas(int rank);
}

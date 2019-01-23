package com.hyzs.cidyth.module.demand.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hyzs.cidyth.module.dashboard.entity.Backlog;
import com.hyzs.cidyth.module.demand.entity.Demand;
import com.hyzs.cidyth.module.demand.vo.DemandVO;
import com.hyzs.cidyth.module.personal.vo.PersonalDemandVO;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface DemandMapper extends Mapper<Demand> {
	/**
	 * 根据下列条件之一查询:
	 * (1)无条件
	 * (2)案件编号
	 * (3)案件编号、需求发起人、需求接受机构
	 * (4)案件编号、需求发起人、需求接受人
	 * 
	 * @param caseNoList
	 *            案件编号
	 * @param demandCreateUserId
	 *            需求发起人用户编号
	 * @param acceptDeptIdList
	 *            接受机构编码
	 * @param acceptUserIdList
	 *            接受人用户编号
	 * @return
	 */
	List<Demand> selectBySelective(@Param("caseNoList") Collection<String> caseNoList,
			@Param("demandCreateUserId") String demandCreateUserId,
			@Param("acceptDeptIdList") Collection<String> acceptDeptIdList,
			@Param("acceptUserIdList") Collection<String> acceptUserIdList);

	/**
	 * 根据案件编号查询当天需求
	 * 
	 * @param caseNoList
	 *            案件编号
	 * @param acceptDeptIdList
	 *            接受机构编码
	 * @return
	 */
	List<Demand> selectDailyDemandByCaseAndAccptDept(@Param("caseNoList") Collection<String> caseNoList,
			@Param("acceptDeptIdList") Collection<String> acceptDeptIdList,@Param("status") String status);

	/**
	 * 按指定的月份查询指定部门受理的所有需求
	 * 
	 * @param year
	 *            年份
	 * @param monthList
	 *            月份
	 * @param deptIdList
	 *            部门
	 * @return
	 */
	List<Demand> selectMonthlyDemandOfDept(@Param("year") Integer year,
			@Param("monthList") Collection<Integer> monthList, @Param("deptIdList") Collection<String> deptIdList);

	/**
	 * 根据接受人用户id、接受机构、天数查询代办
	 * 
	 * @param accptUserId
	 *            接受人用户编号
	 * @param accptDeptId
	 *            接收人用户所属机构代码
	 * @param days 天数
	 * @return
	 */
	List<Backlog> selectAllDemandWillBeDone(@Param("accptUserId") String accptUserId,
			@Param("accptDeptId") String accptDeptId,@Param("days") int days);
	
	/**
	 * 根据条件查询需求
	 * @param demandVO 需求视图对象
	 * @return
	 */
	List<Demand> selectByPage(DemandVO demandVO);

	/**
     * 修改需求处理状态
     * @param id 需求id
     * @param qszt 签收状态（枚举名）
     * @return
     */
	void updateQszt(@Param("id") Integer id, @Param("qszt") String qszt);

	/**
	 * 个人工作台：我的请求
	 * @param lrry
	 * @param qszt
	 */
	List<Demand> listPersonalMyDemand(String lrry);
	
	/**
	 * 个人工作台：待办任务
	 * @param lrry
	 * @param qszt
	 */
	List<Demand> listPersonalHandleDemand(String lrry);
	
	/**
     * 个人工作台：本月用户点评情况
	 * @param startCreateTime 开始时间
	 * @param endCreateTime 结束时间
	 * @param lrry 录入人员
	 * @return
	 */
    List<PersonalDemandVO> listPersonalMonthFeedBacked(@Param("startCreateTime") String startCreateTime, @Param("endCreateTime") String endCreateTime, @Param("lrry") String lrry);

	/**
	 * 获取案件下的所有该单位接收的需求状态不等于【未指派】和【已指派】的所有数据
	 * @param ajbh 案件编号
	 * @param jsdwbh 接收单位编号
	 * @return
	 */
	List<Demand> listExecuteDemand(@Param("ajbh") String ajbh, @Param("jsdwbh") String jsdwbh);

	/**
	 * 检查除了本案件外的所有需求是否存在【需求类型】【需求内容】一直的信息
	 * @param demand
	 * @return
	 */
	List<Demand> checkDemandInfoExists(Demand demand);

	/**
	 * 首页：待办事项
	 * @return
	 */
	List<Map<String, String>> waitHandler();

	/**
	 * 统计需要account需要指派的需求数量
	 * @param account
	 * @return
	 */
	int countByDemandInit(@Param("account") String account);
	/**
	 * 我要指派的需求
	 * @param account
	 * @return
	 */
	List<Demand> myAllocate(@Param("account") String account);
}
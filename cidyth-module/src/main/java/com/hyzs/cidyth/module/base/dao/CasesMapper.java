package com.hyzs.cidyth.module.base.dao;

import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.MySqlMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@DataSourceName(name = "masterSqlSessionFactory")
public interface CasesMapper extends Mapper<Cases>, MySqlMapper<Cases> {
	/**
	 * 查询一段时间内案件
	 * @param beginCreateTime 创建起始始时间
	 * @param endCreateTime 创建截止时间
	 * @return
	 */
	List<Cases> selectCaseByCreateTime(@Param("beginCreateTime") String beginCreateTime, @Param("endCreateTime") String endCreateTime);
	/**
	 * 查询一段时间内案件
	 * @param casesVO 视图对象
	 * @return
	 */
	List<Cases> listCaseLocal(Map<String, Object> map);
	/**
     * 获取本地案件状态的数量分组（每组的本地案件状态和数量用逗号分隔）
	 * @return
	 */
	List<String> countGroupByBdajstate();

	/**
	 * 按指定的月份查询指定部门受理的所有案件
	 * @param monthList 月份
	 * @param deptIdList 部门
	 * @return
	 */
	List<Cases> selectMonthlyCaseOfDept(@Param("year") Integer year,@Param("monthList") Collection<Integer> monthList,@Param("deptIdList") Collection<String> deptIdList);
	 /**
     * 根据案件编号侦结该案件
     * @param ajbh 案件编号
     * @return
     */
	void updateBdajstateByAjbh(@Param("ajbh") String ajbh, @Param("bdajstate") Integer bdajstate, @Param("bdajstatebz")String bdajstatebz);

	/**
     * 根据串并案编号获取案件
     * @param cbabh 串并案编号
     * @return
     */
	List<Cases> listCasesByCbabh(String cbabh);
	/**
	 * 根据案件编号审核案件
	 * @param ajbh 案件编号
	 * @param checkPerson 审核人员
	 * @param checkStatus 审核状态
	 * @param checkResult 审核结果
	 * @return
	 */
	void casesCheck(@Param("ajbh") String ajbh, @Param("checkPerson") String checkPerson, @Param("checkStatus") Integer checkStatus, @Param("checkResult") String checkResult);
	/**
	 * 查询最晚的立案时间
	 * @return
	 */
	Date selectLatestLasj();
	/**
	 * 获取本月合成作战案件情况
	 * @param jybh 警员编号
	 * @return
	 */
	List<Cases> listCasesByMonth(@Param("jybh") String jybh, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

	/**
	 * 根据 A-机构编号-年-月 的前缀编号查找警综最大的编号
	 * @param prefixCode
	 */
	String getMaxAjbhByYearMonth(String prefixCode);

	/**
	 * 统计我参与的破案数量
	 * @param jybh
	 * @return
	 */
	Integer helpFinish(String jybh);

	void addCoordinator(@Param("ajbh") String ajbh, @Param("ajxbry") String ajxbry);

	/**
	 * 首页：分局侦查情况
	 * @param kssj 开始时间
	 * @param jssj 结束时间
	 * @return
	 */
	List<Map<String, Object>> branchInvestigate(@Param("kssj") String kssj, @Param("jssj") String jssj);

	List<String> listLocalAjbh();

	/**
	 * 获取现勘统计数据
	 * @param kssj 开始时间
	 * @param jssj 结束时间
	 * @return
	 */
	List<Map<String, Object>> listXK(@Param("kssj") String kssj, @Param("jssj") String jssj);

}
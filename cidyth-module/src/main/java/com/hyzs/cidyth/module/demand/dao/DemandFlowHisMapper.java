package com.hyzs.cidyth.module.demand.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hyzs.cidyth.module.demand.entity.DemandFlowHis;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface DemandFlowHisMapper extends Mapper<DemandFlowHis> {

	/**
     * 修改需求流转的操作状态
     * @author 陈铭
     * @date 2018-04-25 17:06:05
     * @param xqid 需求id
     * @param jsrybh 接收人员编号
     * @param qszt 操作状态（枚举名）
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
	void updateQszt(@Param("qszt") String qszt, @Param("xqid") Integer xqid, @Param("jsrybh") String jsrybh);

	/**
	 * 根据需求获取指派过的节点
	 * @param xqid
	 * @return
	 */
	List<DemandFlowHis> listMyAllocatedFlow(Integer xqid);
	
	List<DemandFlowHis> selectByStatus$xqid(@Param("xqid") Integer xqid, @Param("statusList")List<String> statusList);

	/**
	 * 首页：超期反馈
	 * @return
	 */
	List<Map<String, Object>> overdueFeedback();

	/**
	 * 首页：超期反馈详情
	 * @return
	 */
	List<Map<String, Object>> overdueFeedbackDetail(String prefixCode);

	/**
	 * 首页：待办事项
	 * @param qszt
	 * @return
	 */
	List<Map<String, Object>> waitHandlerDetail(@Param("qszt") String qszt, @Param("account") String account);

}
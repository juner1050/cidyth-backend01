package com.hyzs.cidyth.module.clue.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hyzs.cidyth.module.clue.entity.Clue;
import com.hyzs.cidyth.module.clue.vo.ClueAppraiseSave;
import com.hyzs.cidyth.module.clue.vo.ClueEvaluation;
import com.hyzs.cidyth.module.clue.vo.ClueVO;
import com.hyzs.cidyth.module.demand.entity.Demand;

public interface ClueService {

	/**
     * 插入需求线索表，同时插入到思维导图（维护线索需求和线索的关系图）
     * @author 陈铭
     * @date 2018-04-16 17:21:53
     * @param lsClueId 线索id列表
     * @param demand 需求
     * @return 
     */
	void insertDemandClue(List<Integer> lsClueId, Demand demand);
	/**
     * 反馈线索（插入需求表时，同时新增一条需求线索表的记录）
     * @author 陈铭
     * @date 2018-04-16 17:30:28
     * @param clueVO 线索对象
     * @return 
     */

	Map<String, Object> insert(ClueVO clueVO);
	/**
	 * 线索详情
	 * @author 陈铭
	 * @date 2018-04-16 17:30:28
	 * @param id 线索id
	 * @return
	 */
	ClueVO detail(Integer id);
	/**
     * 根据案件编号查询总数
     * @param ajbh 案件编号
     * @return
     */
	int getCountByAjbh(String ajbh);
	/**
     * 评价线索
     * @param ajbh 案件编号
     * @return
     */
	//	int evaluate(ClueVO clueVO);
	/**
     * 上传线索
     * @param clueVO 线索对象
     * @return
     */
	void uploadClue(ClueVO clueVO);

	/**
	 * 根据案件编号获取线索
	 * @param ajbh 案件编号
	 * @return
	 */
	ClueEvaluation listClueEvaluateByCases(String ajbh);

	/**
	 * 保存案件侦结时，对关键线索评分的数据
	 * @param lsClueAppraiseSave
	 */
	void saveClueEvaluate(String ajbh, List<ClueAppraiseSave> lsClueAppraiseSave);

	/**
	 * 根据需求id获取线索
	 * @param xqid 需求id
	 * @return
	 */
	List<ClueVO> listClueByDemandId(Integer xqid);
}

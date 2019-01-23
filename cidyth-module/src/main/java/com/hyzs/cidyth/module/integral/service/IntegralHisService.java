package com.hyzs.cidyth.module.integral.service;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.clue.vo.ClueAppraiseSave;
import com.hyzs.cidyth.module.clue.vo.ClueEvaluation;
import com.hyzs.cidyth.module.integral.entity.IntegralHis;
import com.hyzs.cidyth.module.integral.vo.CasesJoinRankVO;
import com.hyzs.cidyth.module.integral.vo.IntegralHisRank;
import com.hyzs.cidyth.module.integral.vo.IntegralHisVO;
import com.hyzs.cidyth.module.uc.vo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IntegralHisService {

	/**
	 * 查询积分历史
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param integralHisVO 积分历史
	 * @param page 积分历史
	 * @return
	 */
	PageInfo<IntegralHisVO> list(IntegralHisVO integralHisVO, Page page);

	/**
	 * 新增积分历史
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param integralHisVO 积分历史实体
	 * @return
	 */
	int insert(IntegralHisVO integralHisVO);

	/**
	 * 新增积分历史
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param ajbh 案件编号
	 * @param awardUser 奖励用户
	 * @param ruleType 规则类型
	 * @return
	 */
	int insert(String ajbh, User awardUser, String ruleType);

	/**
	 * 新增积分历史
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param ajbh 案件编号
	 * @param awardUser 奖励用户
	 * @param ruleType 规则类型
	 * @param score 分数
	 * @return
	 */
	int insert(String ajbh, User awardUser, String ruleType, Float score);

	/**
	 * 新增积分历史
	 * @param ajbh 案件编号
	 * @param lsClueAppraiseSave 案件关键线索评价
	 * @return
	 */
	int insert(String ajbh, List<ClueAppraiseSave> lsClueAppraiseSave);

	/**
	 * 编辑积分历史
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param integralHisVO 积分历史实体
	 * @return
	 */
	int update(IntegralHisVO integralHisVO);

	/**
	 * 删除积分历史
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param id 积分历史id
	 * @return
	 */
	int delete(Integer id);

	/**
	 * 统计案件的用户的总积分
	 * @param ajbh 案件编号
	 * @param jlry 奖励人员编号
	 * @return
	 */
	//Float countCasesScore(String ajbh, String jlry);

	/**
	 * 统计用户该案件的积分
	 * @param integralHis 积分历史对象
	 * @return
	 */
	Float countScore(IntegralHis integralHis);
	/**
	 * 统计用户该案件的积分
	 * @param integralHis 积分历史对象
	 * @return
	 */
	Float countScore(String ajbh);
	/**
	 * 我的积分排行名次
	 * @param ajbh 案件编号
	 * @param jlry 奖励人员
	 * @return
	 */
	int scoreRank(String ajbh, String jlry);

	/**
	 * 参与人员积分排名
	 * @param ajbh 案件编号
	 * @return
	 */
	List<CasesJoinRankVO> listCasesScore(String ajbh);

	/**
	 * 获取破案、提案、线索、需求、信息、奖励积分
	 * @return
	 */
	Map<String, Object> nodeScore();

	/**
	 * 首页：荣誉墙
	 * @return
	 */
	List<IntegralHisRank> honorWall();
}

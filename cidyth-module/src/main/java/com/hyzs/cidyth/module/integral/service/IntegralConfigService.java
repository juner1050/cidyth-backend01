package com.hyzs.cidyth.module.integral.service;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.integral.entity.IntegralConfig;
import com.hyzs.cidyth.module.integral.vo.IntegralConfigVO;

import java.util.List;
import java.util.Map;

public interface IntegralConfigService {

	/**
	 * 查询积分配置
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param integralConfigVO 积分配置
	 * @param page 积分配置
	 * @return
	 */
	PageInfo<IntegralConfigVO> list(IntegralConfigVO integralConfigVO, Page page);

	/**
	 * 新增积分配置
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param integralConfigVO 积分配置实体
	 * @return
	 */
	int insert(IntegralConfigVO integralConfigVO);

	/**
	 * 编辑积分配置
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param integralConfigVO 积分配置实体
	 * @return
	 */
	int update(IntegralConfigVO integralConfigVO);

	/**
	 * 删除积分配置
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param id 积分配置id
	 * @return
	 */
	int delete(Integer id);

	/**
	 * 查询积分配置
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param integralConfig 积分配置对象
	 * @return
	 */
	IntegralConfig selectOne(IntegralConfig integralConfig);

	/**
	 * 查询积分配置
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @return
	 */
	Map<String, Object> initData();

	/**
	 * 根据根则类型获取该项的分数
	 * @param ruleType
	 * @return
	 */
	Float getScoreByRuleType(String ruleType);

	/**
	 * 根据规则类型获取该规则奖励次数
	 * @param ruleType
	 * @return
	 */
	Integer getAwardLimit(String ruleType);
}

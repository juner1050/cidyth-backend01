package com.hyzs.cidyth.module.integral.service;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.integral.vo.IntegralRuleOption;
import com.hyzs.cidyth.module.integral.vo.IntegralRuleVO;

import java.util.List;

public interface IntegralRuleService {

	/**
	 * 查询积分规则
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param integralRuleVO 积分规则
	 * @param page 积分规则
	 * @return
	 */
	PageInfo<IntegralRuleVO> list(IntegralRuleVO integralRuleVO, Page page);

	/**
	 * 新增积分规则
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param integralRuleVO 积分规则实体
	 * @return
	 */
	int insert(IntegralRuleVO integralRuleVO);

	/**
	 * 编辑积分规则
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param integralRuleVO 积分规则实体
	 * @return
	 */
	int update(IntegralRuleVO integralRuleVO);

	/**
	 * 删除积分规则
	 * @author 陈铭
	 * @date 2018-04-16 17:21:53
	 * @param id 积分规则id
	 * @return
	 */
	int delete(Integer id);

	/**
	 * 根据规则类型获取所有子集规则分数
	 * @param ruleType 规则类型
	 * @return
	 */
	List<IntegralRuleOption> listIntegralRuleOptionByRuleType(String ruleType);
}

package com.hyzs.cidyth.module.integral.service.impl;

import java.util.List;

import com.hyzs.cidyth.module.uc.common.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.helper.ContextUserHelper;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.integral.dao.IntegralRuleMapper;
import com.hyzs.cidyth.module.integral.entity.IntegralRule;
import com.hyzs.cidyth.module.integral.service.IntegralRuleService;
import com.hyzs.cidyth.module.integral.vo.IntegralRuleOption;
import com.hyzs.cidyth.module.integral.vo.IntegralRuleVO;
import com.hyzs.cidyth.module.uc.vo.User;


@Service("IntegralRuleService")
public class IntegralRuleServiceImpl implements IntegralRuleService {
	
	private static final Logger logger = LoggerFactory.getLogger(IntegralRuleServiceImpl.class);

   	@Autowired
	private IntegralRuleMapper integralRuleMapper;

	@Override
	public PageInfo<IntegralRuleVO> list(IntegralRuleVO integralRuleVO, Page page) {
		IntegralRule integralRuleParam = new IntegralRule();
		BeanUtils.copyProperties(integralRuleVO, integralRuleParam);


		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		PageInfo<IntegralRuleVO> pageInfo = new PageInfo<IntegralRuleVO>(integralRuleMapper.list(integralRuleParam));

		return pageInfo;
	}

	@Override
	public int insert(IntegralRuleVO integralRuleVO) {
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		IntegralRule integralRule = new IntegralRule();
		BeanUtils.copyProperties(integralRuleVO, integralRule);
		integralRuleMapper.insertSelective(integralRule);
		return integralRule.getId();
	}

	@Override
	public int update(IntegralRuleVO integralRuleVO) {
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		IntegralRule integralRule = new IntegralRule();
		BeanUtils.copyProperties(integralRuleVO, integralRule);
		integralRuleMapper.updateByPrimaryKeySelective(integralRule);
		return integralRule.getId();
	}

	@Override
	public int delete(Integer id) {
		if(id == null){
			throw new ServiceException("id为空!");
		}
		return integralRuleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<IntegralRuleOption> listIntegralRuleOptionByRuleType(String ruleType) {
		return integralRuleMapper.listIntegralRuleOptionByRuleType(ruleType);
	}
}

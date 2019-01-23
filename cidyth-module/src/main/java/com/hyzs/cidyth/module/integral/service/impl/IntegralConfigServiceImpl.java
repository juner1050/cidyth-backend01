package com.hyzs.cidyth.module.integral.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hyzs.cidyth.module.uc.common.UserUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.enums.IntegralTypeEnum;
import com.hyzs.cidyth.common.helper.ContextUserHelper;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.integral.dao.IntegralConfigMapper;
import com.hyzs.cidyth.module.integral.entity.IntegralConfig;
import com.hyzs.cidyth.module.integral.service.IntegralConfigService;
import com.hyzs.cidyth.module.integral.vo.IntegralConfigVO;
import com.hyzs.cidyth.module.uc.vo.User;


@Service("IntegralConfigService")
public class IntegralConfigServiceImpl implements IntegralConfigService {
	
	private static final Logger logger = LoggerFactory.getLogger(IntegralConfigServiceImpl.class);

   	@Autowired
	private IntegralConfigMapper integralConfigMapper;

	@Override
	public PageInfo<IntegralConfigVO> list(IntegralConfigVO integralConfigVO, Page page) {
		IntegralConfig integralConfigParam = new IntegralConfig();
		BeanUtils.copyProperties(integralConfigVO, integralConfigParam);

		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		PageInfo<IntegralConfig> pageInfo = new PageInfo<IntegralConfig>(integralConfigMapper.select(integralConfigParam));
		PageInfo<IntegralConfigVO> pageResult = new PageInfo<IntegralConfigVO>();
		BeanUtils.copyProperties(pageInfo, pageResult);

		List<IntegralConfigVO> lsIntegralConfigVO = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(pageInfo.getList())){
			for(IntegralConfig item : pageInfo.getList()){
				IntegralConfigVO vo = new IntegralConfigVO();
				BeanUtils.copyProperties(item, vo);
				vo.setIntegralTypeCn(IntegralTypeEnum.ofCode(String.valueOf(vo.getIntegralType())) == null ? "" : IntegralTypeEnum.ofCode(String.valueOf(vo.getIntegralType())).descp());
				lsIntegralConfigVO.add(vo);
			}
		}


		pageResult.setList(lsIntegralConfigVO);
		return pageResult;
	}

	@Override
	public int insert(IntegralConfigVO integralConfigVO) {
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		if(integralConfigVO.getIntegralType() == null){
			throw new ServiceException("积分类型不能为空");
		}else{
			IntegralConfig queryObj = new IntegralConfig();
			queryObj.setRuleType(integralConfigVO.getRuleType());
			int count = integralConfigMapper.selectCount(queryObj);
			// 不存在则保存到数据库
			if(count == 0){
				IntegralConfig integralConfig = new IntegralConfig();
				BeanUtils.copyProperties(integralConfigVO, integralConfig);
				return integralConfigMapper.insertSelective(integralConfig);
			}else{
				return update(integralConfigVO);
			}
		}
	}

	@Override
	public int update(IntegralConfigVO integralConfigVO) {
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		if(integralConfigVO.getIntegralType() == null){
			throw new ServiceException("积分类型不能为空");
		}else{
			if(integralConfigVO.getIntegralType().intValue() == Integer.valueOf(IntegralTypeEnum.NO_FIXED_VALUE.code()).intValue()
					&& integralConfigVO.getUpperLimit() < integralConfigVO.getLowerLimit()){
				throw new ServiceException("上限分数不能小于下限分数!");
			}
		}
		IntegralConfig integralConfig = new IntegralConfig();
		BeanUtils.copyProperties(integralConfigVO, integralConfig);
		integralConfig.setRuleType(null);
		integralConfigMapper.updateByPrimaryKeySelective(integralConfig);
		return integralConfig.getId();
	}

	@Override
	public int delete(Integer id) {
		if(id == null){
			throw new ServiceException("id为空!");
		}
		return integralConfigMapper.deleteByPrimaryKey(id);
	}

	@Override
	public IntegralConfig selectOne(IntegralConfig integralConfig) {
		return integralConfigMapper.selectOne(integralConfig);
	}

	@Override
	public Map<String, Object> initData() {
		List<IntegralConfigVO> lsIntegralConfigVO = integralConfigMapper.initData();
		for(IntegralConfigVO vo : lsIntegralConfigVO){
			IntegralTypeEnum integralTypeEnum = IntegralTypeEnum.ofCode(String.valueOf(vo.getIntegralType()));
			vo.setIntegralTypeCn(integralTypeEnum == null ? "" : integralTypeEnum.descp());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("initData", lsIntegralConfigVO);
		map.put("integralType", IntegralTypeEnum.codes());
		return map;
	}

	@Override
	public Float getScoreByRuleType(String ruleType) {
		IntegralConfig integralConfig = new IntegralConfig();
		integralConfig.setRuleType(ruleType);
		integralConfig = integralConfigMapper.selectOne(integralConfig);
		if(integralConfig == null){
			return 0f;
		}
		return integralConfig.getUpperLimit();
	}

	@Override
	public Integer getAwardLimit(String ruleType) {
		IntegralConfig integralConfig = new IntegralConfig();
		integralConfig.setRuleType(ruleType);
		integralConfig = integralConfigMapper.selectOne(integralConfig);
		if(integralConfig != null && integralConfig.getAwardLimit() != null){
			return integralConfig.getAwardLimit();
		}else{
			return 0;
		}
	}
}

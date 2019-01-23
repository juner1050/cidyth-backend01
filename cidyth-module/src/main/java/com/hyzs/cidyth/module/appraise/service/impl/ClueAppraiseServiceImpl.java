package com.hyzs.cidyth.module.appraise.service.impl;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyzs.cidyth.common.enums.ClueTypeEnum;
import com.hyzs.cidyth.common.helper.ContextUserHelper;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.appraise.dao.ClueAppraiseMapper;
import com.hyzs.cidyth.module.appraise.entity.ClueAppraise;
import com.hyzs.cidyth.module.appraise.service.AppraiseService;
import com.hyzs.cidyth.module.appraise.vo.Appraise;
import com.hyzs.cidyth.module.clue.dao.ClueMapper;
import com.hyzs.cidyth.module.clue.dao.DemandClueMapper;
import com.hyzs.cidyth.module.clue.entity.Clue;
import com.hyzs.cidyth.module.clue.entity.DemandClue;
import com.hyzs.cidyth.module.demand.dao.DemandMapper;
import com.hyzs.cidyth.module.demand.entity.Demand;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;

/**
 * 线索评价
 * 
 * @author derrick
 *
 */
@Service("clueAppraiseService")
public class ClueAppraiseServiceImpl implements AppraiseService{

	private static final Logger logger = LoggerFactory.getLogger(ClueAppraiseServiceImpl.class);
	@Autowired
	private ClueAppraiseMapper clueAppraiseMapper;
	@Autowired
	private ClueMapper clueMapper;
	@Autowired
	private DemandClueMapper demandClueMapper;
	@Autowired
	private DemandMapper demandMapper;

	@Override
	public int saveAppraise(Appraise appraise) {
		if (appraise == null) {
			throw new ServiceException("参数不能不能为空!");
		}
		// 获取用户
		User loginUser = UserUtil.getUser();
		if (loginUser == null) {
			throw new ServiceException("获取当前登录用户失败!");
		}
		Dept dept = loginUser.getDepartment();
		if (dept == null) {
			throw new ServiceException("获取当前登录用户机构失败!");
		}
		if (appraise.clueEntity.getClueId() == null) {
			throw new ServiceException("线索Id不能为空!");
		}
		if (appraise.clueEntity.getPffz() < 0) {
			throw new ServiceException("分值不合法!");
		}
		Clue clue = clueMapper.selectByPrimaryKey(appraise.clueEntity.getClueId());
		if (clue == null) {
			throw new ServiceException("目标线索不存在或者已经被移除,无法对其进行评价!");
		}
		DemandClue condition = new DemandClue();
		condition.setClueId(appraise.clueEntity.getClueId());
		condition.setClueType(Integer.parseInt(ClueTypeEnum.RETURN.code()));
		List<DemandClue> result = demandClueMapper.selectBySelective(condition);
		if (CollectionUtils.isEmpty(result)) {
			throw new ServiceException("不是需求发起人,没有评价权限!");
		} else {
			Demand demand = demandMapper.selectByPrimaryKey(result.get(0).getDemandId());
			if (demand == null || !(loginUser.getAccount().equals(demand.getQqry()))) {
				throw new ServiceException("不是需求发起人,没有评价权限!");
			}
		}
		if(CollectionUtils.isNotEmpty(loadAppraise(loginUser.getAccount(),clue.getId()+""))){
			throw new ServiceException("不能重复评价!");
		}
		appraise.clueEntity.setPfrbh(loginUser.getAccount());
		appraise.clueEntity.setPfrxm(loginUser.getName());
		appraise.clueEntity.setLrsj(new Date());
		appraise.clueEntity.setPfrdwbh(dept.getCode());
		appraise.clueEntity.setPfrdwmc(dept.getFullname());
		return clueAppraiseMapper.insert(appraise.clueEntity);
	}
	@Override
	public List<Appraise> loadAppraise(String userAccount,String clueId){
		List<Appraise> result = Lists.newArrayList();
		if(StringUtils.isBlank(clueId)){
			throw new ServiceException("参数不能为空!");
		}
		Integer cid = null;
		try{
			cid = Integer.parseInt(clueId);
		}catch(NumberFormatException exp){
			throw new ServiceException("线索id值不合法!");
		}
		if(StringUtils.isBlank(userAccount)){
			// 获取用户
			User loginUser = UserUtil.getUser();
			if (loginUser == null||StringUtils.isBlank(loginUser.getAccount())) {
				throw new ServiceException("获取当前登录用户失败!");
			}else{
				userAccount = loginUser.getAccount();
			}
		}
		ClueAppraise ca = new ClueAppraise();
		ca.setPfrbh(userAccount);
		ca.setClueId(cid);
		List<ClueAppraise> datas = clueAppraiseMapper.select(ca);
		if(CollectionUtils.isNotEmpty(datas)){
			for(ClueAppraise data:datas){
				result.add(new Appraise(data));
			}
		}
		return result;
	}

	@Override
	public Float getScoreByClueId(int id) {
		ClueAppraise ca = new ClueAppraise();
		ca.setClueId(id);
		ca = clueAppraiseMapper.selectOne(ca);
		if(ca != null){
			return ca.getPffz();
		}
		return 0f;
	}
}

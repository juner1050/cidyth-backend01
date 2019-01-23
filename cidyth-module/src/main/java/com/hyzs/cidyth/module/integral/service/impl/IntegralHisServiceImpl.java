package com.hyzs.cidyth.module.integral.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hyzs.cidyth.common.enums.IntegralTypeEnum;
import com.hyzs.cidyth.common.enums.RuleTypeEnum;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.clue.dao.ClueMapper;
import com.hyzs.cidyth.module.clue.entity.Clue;
import com.hyzs.cidyth.module.clue.vo.ClueAppraiseSave;
import com.hyzs.cidyth.module.integral.dao.IntegralConfigMapper;
import com.hyzs.cidyth.module.integral.dao.IntegralHisMapper;
import com.hyzs.cidyth.module.integral.dao.IntegralTitleMapper;
import com.hyzs.cidyth.module.integral.entity.IntegralConfig;
import com.hyzs.cidyth.module.integral.entity.IntegralHis;
import com.hyzs.cidyth.module.integral.entity.IntegralTitle;
import com.hyzs.cidyth.module.integral.service.IntegralConfigService;
import com.hyzs.cidyth.module.integral.service.IntegralHisService;
import com.hyzs.cidyth.module.integral.vo.CasesJoinRankVO;
import com.hyzs.cidyth.module.integral.vo.IntegralHisRank;
import com.hyzs.cidyth.module.integral.vo.IntegralHisVO;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Service("IntegralHisService")
public class IntegralHisServiceImpl implements IntegralHisService {

	private static final Logger logger = LoggerFactory.getLogger(IntegralHisServiceImpl.class);

	@Autowired
	private IntegralHisMapper integralHisMapper;
	@Autowired
	private IntegralConfigService integralConfigService;
	@Autowired
	private IntegralConfigMapper integralConfigMapper;
	@Autowired
	private ClueMapper clueMapper;
	@Autowired
	private IntegralTitleMapper integralTitleMapper;
	@Autowired
	private UserCenterService userCenterService;

	@Override
	public PageInfo<IntegralHisVO> list(IntegralHisVO integralHisVO, Page page) {
		IntegralHis integralHisParam = new IntegralHis();
		BeanUtils.copyProperties(integralHisVO, integralHisParam);

		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		PageInfo<IntegralHisVO> pageInfo = new PageInfo<IntegralHisVO>(integralHisMapper.list(integralHisParam));

		return pageInfo;
	}

	@Override
	public int insert(IntegralHisVO integralHisVO) {
		User awardUser = null;
		try {
			awardUser = userCenterService.getUserByUserName(integralHisVO.getJlry());
		} catch (Exception e) {
			throw new ServiceException("无法获取奖励用户");
		}
		return insert(integralHisVO.getAjbh(), awardUser, integralHisVO.getRuleType(), integralHisVO.getScore());
	}

	@Override
	public int insert(String ajbh, User awardUser, String ruleType) {
		return insert(ajbh, awardUser, ruleType, null);
	}

	@Override
	public int insert(String ajbh, User awardUser, String ruleType, Float score) {
		User loginUser = UserUtil.getUser();
		// 根据规则类型获取对象
		IntegralConfig integralConfig = new IntegralConfig();
		integralConfig.setRuleType(ruleType);
		integralConfig = integralConfigMapper.selectOne(integralConfig);
		if(integralConfig == null){
			throw new ServiceException("无法获取积分配置对象!");
		}
		int resultId = 0;
		IntegralHis his = new IntegralHis();
		his.setAjbh(ajbh);
		his.setJlry(awardUser.getAccount());
		his.setJlrymc(awardUser.getName());
		his.setJlryjg(awardUser.getDepartment().getCode());
		his.setJlryjgmc(awardUser.getDepartment().getFullname());
		his.setRuleType(ruleType);
		his.setYear(LocalDate.now().getYear());
		his.setMonth(LocalDate.now().getMonth().getValue());
		his.setDay(LocalDate.now().getDayOfMonth());
		his.setLrry(loginUser.getAccount());
		his.setLrrymc(loginUser.getName());
		// 如果是定值积分
		if(integralConfig.getIntegralType().intValue() == Integer.valueOf(IntegralTypeEnum.FIXED_VALUE.code()).intValue()){
			his.setScore(0f);
		}else{
			if(score == null){
				his.setScore(integralConfig.getUpperLimit());
			}else{
				if(integralConfig.getLowerLimit() <= score && score <= integralConfig.getUpperLimit()){
					his.setScore(score);
				}else{
					throw new ServiceException("积分超出范围!");
				}
			}
		}
		resultId = integralHisMapper.insertSelective(his);
		return resultId;
	}

	@Override
	@Transactional(value="masterTransactionManager",rollbackFor={RuntimeException.class,Exception.class})
	public int insert(String ajbh, List<ClueAppraiseSave> lsClueAppraiseSave) {
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("获取当前登录用户失败!");
		}
		for(ClueAppraiseSave clueAppraiseSave : lsClueAppraiseSave){
			Clue clue = clueMapper.selectByPrimaryKey(clueAppraiseSave.getClueId());
			User clueUser = null;
			if(clue == null){
				throw new ServiceException("获取线索失败!");
			}
			if(StringUtils.isNotBlank(clueAppraiseSave.getRuleType())){
				throw new ServiceException("获取规则类型失败!");
			}
			try {
				clueUser = userCenterService.getUserByUserName(clue.getLrry());
			} catch (Exception e) {
				throw new ServiceException("获取线索提供人员失败!");
			}
			if(clueUser == null){
				throw new ServiceException("获取线索提供人员失败!");
			}
			if(clueUser.getDepartment() == null){
				throw new ServiceException("获取线索提供人员机构失败!");
			}
			String ruleType = clueAppraiseSave.getRuleType();
			Float score = clueAppraiseSave.getScore();
			if(score == null){
				throw new ServiceException("获取积分失败!");
			}
			insert(ajbh, clueUser, ruleType, score);
		}
		return 1;
	}

	@Override
	public int update(IntegralHisVO integralHisVO) {
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		IntegralHis integralHis = new IntegralHis();
		BeanUtils.copyProperties(integralHisVO, integralHis);
		integralHisMapper.updateByPrimaryKeySelective(integralHis);
		return integralHis.getId();
	}

	@Override
	public int delete(Integer id) {
		if(id == null){
			throw new ServiceException("id为空!");
		}
		return integralHisMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 是否超过积分配置的奖励次数
	 * false：未超过，true：已超过
	 * @param integralHis
	 * @return
	 */
	public boolean isOverLimit(IntegralHis integralHis){
		String ruleType = integralHis.getRuleType();
		IntegralConfig integralConfig = new IntegralConfig();
		integralConfig.setRuleType(ruleType);
		integralConfig = integralConfigService.selectOne(integralConfig);
		if(integralConfig == null){
			return false;
		}else{
			if(integralConfig.getAwardLimit() == null || integralConfig.getAwardLimit() <= 0){
				return false;
			}else{
				// 奖励人员的规则类型的奖励次数
				int integralHisCount = integralHisMapper.selectCount(integralHis);
				if(integralHisCount < integralConfig.getAwardLimit()){
					return false;
				}else{
					return true;
				}
			}
		}
	}

	/*@Override
	public Float countCasesScore(String ajbh, String jlry) {
		Float score = integralHisMapper.countCasesScore(ajbh, jlry);
		return score == null ? 0f : score;
	}*/

	@Override
	public Float countScore(IntegralHis integralHis) {
		User loginUser = UserUtil.getUser();
		integralHis.setJlry(loginUser.getAccount());
		Float score = integralHisMapper.countScore(integralHis);
		return score == null ? 0f : score;
	}

	//根据案件编号获取案件的总分数
	@Override
	public Float countScore(String ajbh) {
		IntegralHis integralHis = new IntegralHis();
		integralHis.setAjbh(ajbh);
		Float score = integralHisMapper.countScore(integralHis);
		return score == null ? 0f : score;
	}

	@Override
	public int scoreRank(String ajbh, String jlry) {
		List<IntegralHisVO> lsIntegralHisVO = integralHisMapper.listCasesScore(ajbh);
		// 循环判断当前人员的本案积分排名第几
		if(CollectionUtils.isNotEmpty(lsIntegralHisVO)){
			for (int i = 0; i < lsIntegralHisVO.size(); i++) {
				IntegralHisVO integralHisVO =  lsIntegralHisVO.get(i);
				if(integralHisVO.getJlry().equals(jlry)){
					return integralHisVO.getRank();
				}
			}
		}
		return 0;
	}

	@Override
	public List<CasesJoinRankVO> listCasesScore(String ajbh) {
		List<IntegralHisVO> lsIntegralHisVO = integralHisMapper.listCasesScore(ajbh);
		//List<IntegralTitle> lsIntegralTitle = integralTitleMapper.selectAll();
		List<CasesJoinRankVO> lsCasesJoinRankVO = Lists.newArrayList();
		// 案件的总积分
		Float totalScore = countScore(ajbh);
		for (IntegralHisVO integralHisVO : lsIntegralHisVO) {
			CasesJoinRankVO casesJoinRankVO = new CasesJoinRankVO();
			BeanUtils.copyProperties(integralHisVO, casesJoinRankVO);
			BigDecimal b = new BigDecimal(integralHisVO.getScore() / totalScore * 100);
			casesJoinRankVO.setScoreRatio(b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
			lsCasesJoinRankVO.add(casesJoinRankVO);
		}
		return lsCasesJoinRankVO;
	}

	@Override
	public Map<String, Object> nodeScore(){
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		//破案、提案、线索、需求、信息类型
		List<String> lsNodeName = Lists.newArrayList(
				RuleTypeEnum.CASES_FINISH.name(),
				RuleTypeEnum.CASES_PICK.name(),
				RuleTypeEnum.RETURN_CLUE.name(),
				RuleTypeEnum.DEMAND.name(),
				RuleTypeEnum.INFO.name()
			);
		//奖励包含的所有类型
		List<String> lsAwardName = Lists.newArrayList(
				RuleTypeEnum.SUSPECT_VERIFY.name(),
				RuleTypeEnum.SUSPECT_LOCK.name(),
				RuleTypeEnum.SUSPECT_CAPTURE.name(),
				RuleTypeEnum.SUSPECT_CAPTURE_HELP.name(),
				RuleTypeEnum.IMPORTANT_CLUE.name()
		);
		Map<String, Object> mapResult = Maps.newHashMap();
		List<String> lsNodeScore = integralHisMapper.nodeScore(loginUser.getAccount(), lsNodeName);
		List<String> lsAwardScore = integralHisMapper.nodeScore(loginUser.getAccount(), lsAwardName);
		if(CollectionUtils.isNotEmpty(lsNodeScore)){
			for(String item : lsNodeScore){
				String[] arrItem = item.split(",");
				String name = arrItem[0];
				Float score = Float.parseFloat(arrItem[1]);
				if(name.equals(RuleTypeEnum.CASES_FINISH.name())){
					mapResult.put(RuleTypeEnum.CASES_FINISH.name(), score);
				}
				if(name.equals(RuleTypeEnum.CASES_PICK.name())){
					mapResult.put(RuleTypeEnum.CASES_PICK.name(), score);
				}
				if(name.equals(RuleTypeEnum.RETURN_CLUE.name())){
					mapResult.put(RuleTypeEnum.RETURN_CLUE.name(), score);
				}
				if(name.equals(RuleTypeEnum.DEMAND.name())){
					mapResult.put(RuleTypeEnum.DEMAND.name(), score);
				}
				if(name.equals(RuleTypeEnum.INFO.name())){
					mapResult.put(RuleTypeEnum.INFO.name(), score);
				}
			}
		}
		if(CollectionUtils.isNotEmpty(lsAwardScore)){
			Float score = 0f;
			for(String item : lsAwardScore){
				String[] arrItem = item.split(",");
				score += Float.parseFloat(arrItem[1]);
			}
			mapResult.put(RuleTypeEnum.MAIN_CLUE.name(), score);
		}
		return mapResult;
	}

	@Override
	public List<IntegralHisRank> honorWall() {
		List<Dept> lsDept = null;
		Map<String, String> mapDept = Maps.newHashMap();
		try {
			lsDept = userCenterService.getBranchDept();
			for (Dept dept : lsDept) {
				mapDept.put(dept.getCode().substring(0, 6), dept.getFullname());
			}
			if (CollectionUtils.isEmpty(lsDept)) {
				return null;
			}
		} catch (Exception e) {
			throw new ServiceException("无法获取机构数据");
		}
		PageHelper.startPage(1, 10);
		List<IntegralHisRank> lsIntegralHisRank = integralHisMapper.listRankScore();
		PageHelper.orderBy("priority");
		List<IntegralTitle> lsIntegralTitle = integralTitleMapper.selectAll();
		for (IntegralHisRank rank : lsIntegralHisRank) {
			for (IntegralTitle title : lsIntegralTitle) {
				// 是否为头衔第一级
				if(title.getUpperLimit() == 0){
					// 是否满足最低分数
					if(rank.getScore() >= title.getLowerLimit()){
						setRankTitle(rank, title, lsIntegralTitle, mapDept);
						break;
					}
				} else if(title.getLowerLimit() == 0){
					if(rank.getScore() < title.getUpperLimit()){
						setRankTitle(rank, title, lsIntegralTitle, mapDept);
						break;
					}
				} else {
					if(rank.getScore() >= title.getLowerLimit() && rank.getScore() < title.getUpperLimit()){
						setRankTitle(rank, title, lsIntegralTitle, mapDept);
						break;
					}
				}
			}
			// 没有任何title匹配说明名额已满，则获取title集合的第一层元素（最接近的元素）作为该排名的title
			if(org.apache.axis.utils.StringUtils.isEmpty(rank.getTitle())){
				setRankTitle(rank, lsIntegralTitle.get(0), lsIntegralTitle, mapDept);
			}
		}
		return lsIntegralHisRank;
	}

	private void setRankTitle(IntegralHisRank rank, IntegralTitle title, List<IntegralTitle> titles, Map<String, String> mapDept){
		rank.setTitle(title.getName());
		rank.setJlryjgmc(mapDept.get(rank.getJlryjg()));
		title.setQuota(title.getQuota() - 1);
		if(title.getQuota() == 0){
			titles.remove(title);
		}
	}
}

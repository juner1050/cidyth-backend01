package com.hyzs.cidyth.module.personal.service.impl;

import com.hyzs.cidyth.common.enums.ClueAccptEnum;
import com.hyzs.cidyth.common.enums.DemandImportantEnum;
import com.hyzs.cidyth.common.enums.RoleEnum;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.base.dao.CasesMapper;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.base.service.CasesGroupService;
import com.hyzs.cidyth.module.base.service.CasesService;
import com.hyzs.cidyth.module.base.vo.CasesVO;
import com.hyzs.cidyth.module.cases.service.PcCasesService;
import com.hyzs.cidyth.module.demand.dao.DemandMapper;
import com.hyzs.cidyth.module.demand.entity.Demand;
import com.hyzs.cidyth.module.integral.service.IntegralHisService;
import com.hyzs.cidyth.module.personal.service.PersonalService;
import com.hyzs.cidyth.module.personal.vo.PersonalBO;
import com.hyzs.cidyth.module.personal.vo.PersonalCasesVO;
import com.hyzs.cidyth.module.personal.vo.PersonalDemandVO;
import com.hyzs.cidyth.module.personal.vo.PersonalRankVO;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Role;
import com.hyzs.cidyth.module.uc.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("PersonalService")
public class PersonalServiceImpl implements PersonalService {

	private static final Logger logger = LoggerFactory.getLogger(PersonalServiceImpl.class);

	@Autowired
	private DemandMapper demandMapper;
	@Autowired
	private CasesMapper casesMapper;
	@Autowired
	private PcCasesService pcCasesService;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private CasesGroupService casesGroupService;
	@Autowired
	private IntegralHisService integralHisService;

	@Override
	public List<PersonalDemandVO> listPersonalMyDemand() {
		// 获取用户
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		List<Demand> lsDemand = demandMapper.listPersonalMyDemand(loginUser.getAccount());
		List<PersonalDemandVO> lsResult = new ArrayList<>();
		for(Demand demand : lsDemand){
			PersonalDemandVO personalDemand = new PersonalDemandVO();
			BeanUtils.copyProperties(demand, personalDemand);
			// 获取指派领导名称、电话
			if(StringUtils.isNotEmpty(demand.getZpld())){
				String[] zpld = demand.getZpld().split(",");
				List<String> lsZpldName = new ArrayList<>();
				List<String> lsZpldMobile = new ArrayList<>();
				for(String item : zpld){
					try {
						User user = userCenterService.getUserByUserName(item);
						if(user != null){
							lsZpldName.add(user.getName());
							lsZpldMobile.add(user.getPhone());
						}
					} catch (Exception e) {
						throw new ServiceException("无法获取用户");
					}
				}
				personalDemand.setZpldCn(String.join("、", lsZpldName));
				personalDemand.setZplddh(String.join("、", lsZpldMobile));
			}
			personalDemand.setCcts(DateUtil.subWorkDays(demand.getQqsj(), new Date()));
			lsResult.add(personalDemand);
		}
		return lsResult;
	}

	@Override
	public List<PersonalDemandVO> listPersonalHandleDemand() {
		// 获取用户
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		List<Demand> lsDemand = demandMapper.listPersonalHandleDemand(loginUser.getAccount());
		List<PersonalDemandVO> lsResult = new ArrayList<>();
		for(Demand demand : lsDemand){
			PersonalDemandVO personalDemand = new PersonalDemandVO();
			BeanUtils.copyProperties(demand, personalDemand);
			personalDemand.setJjcdCn(DemandImportantEnum.ofCode(String.valueOf(demand.getJjcd())).descp());
			personalDemand.setCcts(DateUtil.subWorkDays(demand.getQqsj(), new Date()));
			lsResult.add(personalDemand);
		}
		return lsResult;
	}

	@Override
	public List<PersonalDemandVO> listPersonalMonthFeedBacked() {
		// 获取用户
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		List<PersonalDemandVO> lsPersonalDemandVO = demandMapper.listPersonalMonthFeedBacked(
				DateUtil.getMonthStartTime(), 
				DateUtil.getMonthEndTime(), 
				loginUser.getAccount());
		for(PersonalDemandVO personalDemand : lsPersonalDemandVO){
			personalDemand.setCcts(DateUtil.subWorkDays(personalDemand.getQqsj(), personalDemand.getFkrq()));
			if(personalDemand.getPffz() == null){
				personalDemand.setPffzCn(ClueAccptEnum.LEVEL_2.descp());
			}else{
				personalDemand.setPffzCn(ClueAccptEnum.ofCode(String.valueOf(personalDemand.getPffz().intValue())).descp());
			}

		}
		return lsPersonalDemandVO;
	}

	@Override
	public PersonalBO detail() {
		// 获取用户
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new ServiceException("无法获取登录用户");
		}
		// 是否拥有【主办侦查员】角色
		boolean isInverstigateRole = false;
		for(Role role : loginUser.getRoles()){
			if(role.getTips().equals(RoleEnum.CID_INVERSTIGATE_PERSON.code())){
				isInverstigateRole = true;
			}
		}

		Demand demand = new Demand();
		demand.setLrry(loginUser.getAccount());
		
		Cases cases = new Cases();
		
		// 如果拥有【主办侦查员】则统计当前人员的主办案件数量，否则统计提取案件数量
		if(isInverstigateRole){
			cases.setAjzbry(loginUser.getAccount());
		}else{
			cases.setLrry(loginUser.getAccount());
		}
		
		// 我主办/提取的案件数量
		int myCasesCount = casesMapper.selectCount(cases);
		
		// 我创建的需求数量
		int myDemandCount = demandMapper.selectCount(demand);
		
		// 用户排名对象
		PersonalRankVO personalRankVO = new PersonalRankVO();
		personalRankVO.setMainHandleCount(myCasesCount);// 主办提取数量
		personalRankVO.setCreateDemandCount(myDemandCount);// 创建需求数量
		personalRankVO.setDeptBureauRank(0);// 单位排名
		personalRankVO.setBranchBureauRank(0);// 分局排名
		personalRankVO.setCityBureauRank(0);// 市局排名

		List<String> lsMyJoinCaseCode = casesGroupService.listMyJoinCaseCode();
		List<CasesVO> lsCasesVO = pcCasesService.listCasesVOByAjbhs(lsMyJoinCaseCode);
		lsCasesVO.forEach(i -> {
			PersonalCasesVO personalCasesVO = new PersonalCasesVO();
			BeanUtils.copyProperties(i, personalCasesVO);
			personalCasesVO.setScore(integralHisService.countScore(i.getAjbh()));
			personalCasesVO.setScoreRank(integralHisService.scoreRank(i.getAjbh(), loginUser.getAccount()));
			personalCasesVO.setJoinPersonNum(casesGroupService.countCasesJoinPerson(i.getAjbh()));

		});
		// 本月合成作战案件情况
		/*CasesPartner casesPartner = new CasesPartner();
		casesPartner.setJybh(loginUser.getAccount());
		List<CasesPartner> lsCasesPartner = casesPartnerMapper.select(casesPartner);
		List<PersonalCasesVO> lsPersonalCasesVO = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(lsCasesPartner)){
			for (CasesPartner partner : lsCasesPartner) {
				PersonalCasesVO personalCasesVO = new PersonalCasesVO();
				CasesVO casesVO = casesService.getCasesVOByCaseNO(partner.getAjbh());
				BeanUtils.copyProperties(casesVO, personalCasesVO);
				personalCasesVO.setJoinPersonNum(0);// 设置参与本案人数
				personalCasesVO.setScore(0f);// 设置本案得分
				personalCasesVO.setScoreRank(1);// 设置积分排名
				lsPersonalCasesVO.add(personalCasesVO);
			}
		}*/

		// 个人工作台
		PersonalBO personalBO = new PersonalBO();
		personalBO.setLsPersonalMyDemand(listPersonalMyDemand());// 我的请求
		personalBO.setLsPersonalHandleDemand(listPersonalHandleDemand());// 待办任务 
		personalBO.setLsPersonalMonthFeedBacked(listPersonalMonthFeedBacked());// 本月用户点评情况
		personalBO.setLsPersonalCases(null);// 本月合成案件情况
		personalBO.setUser(loginUser);// 用于基本信息
		personalBO.setPersonalRankVO(personalRankVO);// 用户排名对象
		
		return personalBO;
	}

}

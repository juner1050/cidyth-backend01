package com.hyzs.cidyth.module.dashboard.service.impl;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.enums.DemandFlowStepStatus;
import com.hyzs.cidyth.common.utils.BatchSqlParamListBuilder;
import com.hyzs.cidyth.common.utils.Constant;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.base.dao.CasesCidMapper;
import com.hyzs.cidyth.module.base.dao.CasesMapper;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.clue.dao.DemandClueMapper;
import com.hyzs.cidyth.module.clue.entity.Clue;
import com.hyzs.cidyth.module.dashboard.TestDataBuilder;
import com.hyzs.cidyth.module.dashboard.entity.Backlog;
import com.hyzs.cidyth.module.dashboard.service.DashboardService;
import com.hyzs.cidyth.module.dashboard.vo.CaseSolvedOfOrgType;
import com.hyzs.cidyth.module.dashboard.vo.HonorCanvas;
import com.hyzs.cidyth.module.dashboard.vo.StatisticsModleOfOrganization;
import com.hyzs.cidyth.module.dashboard.vo.ThingsWillBeDone;
import com.hyzs.cidyth.module.demand.dao.DemandMapper;
import com.hyzs.cidyth.module.demand.entity.Demand;
import com.hyzs.cidyth.module.demand.service.DemandFlowService;
import com.hyzs.cidyth.module.msg.dao.InfoMapper;
import com.hyzs.cidyth.module.msg.entity.Info;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.service.impl.UserCenterServiceHelper;
import com.hyzs.cidyth.module.uc.service.impl.UserCenterServiceHelper.ServiceContextParameter;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;

@Service("dashboardService")
public class DashboardServiceImpl implements DashboardService {
	private static final Logger logger = LoggerFactory.getLogger(DashboardServiceImpl.class);
	@Autowired
	private CasesMapper caseDao;
	@Autowired
	private CasesCidMapper casesCidMapper;//警踪数据访问接口
	@Autowired
	private DemandMapper demandDao;
	@Autowired
	private DemandClueMapper demandClueDao;
	@Autowired
	private InfoMapper infoMapper;
	@Autowired
	private DemandFlowService demandFlowService;
	@Autowired
	private UserCenterServiceHelper dashboardServiceHelper;
	/**
	 * 下级机构名过滤器
	 */
	private final static Predicate<Dept> DEFAULT_DIRECT_SUBDEPTNAME_FILTER = new Predicate<Dept>() {
		@Override
		public boolean apply(Dept input) {
			if (input != null && StringUtils.isNotBlank(input.getFullname())
					&& (input.getFullname().contains("分局") || input.getFullname().contains("派出所"))) {
				return true;
			}
			return false;
		}
	};
	/**
	 * 构造统计模型
	 * 
	 * @param parameter
	 * @param statisticsModleList
	 */
	private void buildStatisticsModle(Collection<Dept> deptList,
			List<StatisticsModleOfOrganization> statisticsModleList) {
		if (CollectionUtils.isNotEmpty(deptList) && statisticsModleList != null) {
			Iterator<Dept> it = deptList.iterator();
			while (it.hasNext()) {
				Dept dept = it.next();
				StatisticsModleOfOrganization coo = new StatisticsModleOfOrganization();
				String subDeptId = dept.getCode();
				String subDeptName = dept.getFullname();
				coo.setOrganizationCode(subDeptId);// 机构代码
				coo.setOrganizationName(subDeptName);// 机构名称
				if (!statisticsModleList.contains(coo)) {
					statisticsModleList.add(coo);
				}
			}
		}
	}

	private void buildStatisticsModle(Map<Integer, String> deptCategories,
			List<StatisticsModleOfOrganization> statisticsModleList) {
		if (!deptCategories.isEmpty() && statisticsModleList != null) {
			Iterator<Entry<Integer, String>> it = deptCategories.entrySet().iterator();
			while (it.hasNext()) {
				Entry<Integer, String> entry = it.next();
				StatisticsModleOfOrganization coo = new StatisticsModleOfOrganization();
				coo.setOrganizationCode(entry.getKey().toString());
				coo.setOrganizationName(entry.getValue());
				if (!statisticsModleList.contains(coo)) {
					statisticsModleList.add(coo);
				}
			}
		}
	}
	@Override
	public List<List<StatisticsModleOfOrganization>> getMonthlyCaseOfOrganization() throws Exception {
		List<List<StatisticsModleOfOrganization>> result = new ArrayList<List<StatisticsModleOfOrganization>>();
		User loginUser = UserUtil.getUser();
		if (loginUser == null) {
			throw new ServiceException("无法获取当前登录用户!");
		}
		ServiceContextParameter parameter = dashboardServiceHelper.buildServiceContextParameter(loginUser.getDeptid());
		if (parameter != null) {
			Collection<Dept> subDeptList = parameter.getDirectSubDeptList(DEFAULT_DIRECT_SUBDEPTNAME_FILTER);
			if(CollectionUtils.isEmpty(subDeptList)){
				for (int m : parameter.getMonthList()) {
					result.add(Lists.<StatisticsModleOfOrganization>newArrayList());
				}
			}else{
				for (int m : parameter.getMonthList()) {
					List<StatisticsModleOfOrganization> set = Lists.newArrayList();
					this.buildStatisticsModle(subDeptList, set);
					result.add(set);
				}
				List<Cases> caseList = caseDao.selectMonthlyCaseOfDept(parameter.getYear(), parameter.getMonthList(),
						parameter.getDirectSubDeptIdList());
				if (CollectionUtils.isNotEmpty(caseList)) {
					for (Cases cas : caseList) {
						if (cas.getSlLrsj()!=null) {
							int month = Integer.parseInt(cas.getAb());
							for (StatisticsModleOfOrganization target : result.get(month - 1)) {
								if (cas.getSljsdw().equals(target.getOrganizationCode())) {
									target.setAmount(target.getAmount() + 1);// 累计案件数
								}
							}
						}
					}
				}
			}
		}
		return result;
	}

	@Override
	public CaseSolvedOfOrgType getDailyCaseSolveSurveyByOrganizationType(int days) throws Exception {
		CaseSolvedOfOrgType csoot = new CaseSolvedOfOrgType();
		User loginUser = UserUtil.getUser();
		if (loginUser == null) {
			throw new ServiceException("无法获取当前登录用户!");
		}
		ServiceContextParameter parameter = dashboardServiceHelper.buildServiceContextParameter(loginUser.getDeptid());
		if (parameter != null) {
			List<Cases> caseList = casesCidMapper.selectCasesInLastNDays(days < 0 ? 0 : days);
			if (CollectionUtils.isNotEmpty(caseList)) {
				csoot.setTotalCase(caseList.size());// 总案件数
				int batchSize = 300;
				List<String> caseNoLists = BatchSqlParamListBuilder.getBatchList(caseList, batchSize,
						new BatchSqlParamListBuilder.ElementResolver<Cases, String>() {
							@Override
							public String apply(List<Cases> input) {
								List<String> caseNoList = Lists.newArrayList();
								for (Cases cas : input) {
									if (StringUtils.isNotBlank(cas.getAjbh())) {
										caseNoList.add(cas.getAjbh());
									}
								}
								StringBuilder result = new StringBuilder("'");
								Joiner.on("','").appendTo(result, caseNoList);
								result.append("'");
								return result.toString();
							}
						});

				Map<Integer, String> deptCategories = parameter.getDepartmentCategories();// 所有警种
				Map<Integer, Set<Dept>> subDeptMap = parameter.getDirectSubDeptByCategory();// 警种与下级机构的映射关系
				if (deptCategories != null && !deptCategories.isEmpty()) {
					Iterator<Entry<Integer, Set<Dept>>> it = subDeptMap.entrySet().iterator();
					while (it.hasNext()) {
						Entry<Integer, Set<Dept>> entry = it.next();
						int orgType = entry.getKey();
						String orgTypeName = deptCategories.get(orgType);

						Set<Dept> value = entry.getValue();
						if (CollectionUtils.isNotEmpty(value)) {
							// 下级机构id
							List<String> accptDepartmentIdList = BatchSqlParamListBuilder.getBatchList(
									Lists.newArrayList(value), 300,
									new BatchSqlParamListBuilder.ElementResolver<Dept, String>() {
										@Override
										public String apply(List<Dept> input) {
											List<String> deptIdList = Lists.newArrayList();
											for (Dept dept : input) {
												if (StringUtils.isNotBlank(dept.getCode())) {
													deptIdList.add(dept.getCode());
												}
											}
											StringBuilder result = new StringBuilder("'");
											Joiner.on("','").appendTo(result, deptIdList);
											result.append("'");
											return result.toString();
										}
									});
							
//							List<Demand> demandList = demandDao.selectDailyDemandByCaseAndAccptDept(caseNoLists,
//									accptDepartmentIdList);
//							if (CollectionUtils.isNotEmpty(demandList)) {
//								List<Integer> demandIdList = Lists.newArrayList();
//								for (Demand dem : demandList) {
//									if (dem.getId() != null) {
//										demandIdList.add(dem.getId());
//									}
//									String jsdw = dem.getJsdw();// 接受单位
//									if (StringUtils.isNotBlank(jsdw)) {
//										csoot.addDemand(orgType, orgTypeName, 1);
//									}
//									String rwqsry = dem.getRwqsry();// 签收人;
//									if (StringUtils.isNotBlank(rwqsry)) {
//										csoot.addSignedDemand(orgType, orgTypeName, 1);
//									}
//								}
//								List<String> paramDemandIdList = BatchSqlParamListBuilder.getBatchList(demandIdList,
//										batchSize, new BatchSqlParamListBuilder.ElementResolver<Integer, String>() {
//											@Override
//											public String apply(List<Integer> input) {
//												StringBuilder result = new StringBuilder();
//												Joiner.on(",").appendTo(result, input);
//												return result.toString();
//											}
//										});
//								List<Clue> clueList = demandClueDao.selectClueByDemandIdList(paramDemandIdList,
//										Integer.parseInt(ClueTypeEnum.RETURN.code()));// 反馈线索
//								if (CollectionUtils.isNotEmpty(clueList)) {
//									for (Clue clue : clueList) {
//										String fkdw = clue.getFkdw();
//										if (StringUtils.isNotBlank(fkdw)) {
//											csoot.addReply(orgType, orgTypeName, 1);
//										}
//									}
//								}
//							}
		
							int received=0,signed=0,replied=0;
							List<Demand> demandList = demandDao.selectDailyDemandByCaseAndAccptDept(caseNoLists,
									accptDepartmentIdList,DemandFlowStepStatus.WAIT_FOR_SIGN.name());
							if(CollectionUtils.isNotEmpty(demandList)){
								received = demandList.size();
							}
							List<Demand> signedDemandList = demandDao.selectDailyDemandByCaseAndAccptDept(caseNoLists,
									accptDepartmentIdList,DemandFlowStepStatus.SIGNED.name());
							if(CollectionUtils.isNotEmpty(signedDemandList)){
								signed=signedDemandList.size();
							}
							List<Demand> repliedList = demandDao.selectDailyDemandByCaseAndAccptDept(caseNoLists,
									accptDepartmentIdList,DemandFlowStepStatus.FEEDBACKED.name());
							if(CollectionUtils.isNotEmpty(repliedList)){
								replied = repliedList.size();
							}
							csoot.addDemand(orgType, orgTypeName,received+signed+replied);
							csoot.addSignedDemand(orgType, orgTypeName, received+signed);
							csoot.addReply(orgType, orgTypeName, signed);
						}
					}

				}
			}
		}

		return csoot;
	}
	@Override
	public List<List<StatisticsModleOfOrganization>> getMonthlyDemandOfOrganization() throws Exception {
		List<List<StatisticsModleOfOrganization>> result = new ArrayList<List<StatisticsModleOfOrganization>>();
		User loginUser = UserUtil.getUser();
		if (loginUser == null) {
			throw new ServiceException("无法获取当前登录用户!");
		}
		ServiceContextParameter parameter = dashboardServiceHelper.buildServiceContextParameter(loginUser.getDeptid());
		if (parameter != null) {
			Collection<Dept> subDeptList = parameter.getDirectSubDeptList(DEFAULT_DIRECT_SUBDEPTNAME_FILTER);
			if(CollectionUtils.isEmpty(subDeptList)){
				for (int m : parameter.getMonthList()) {
					result.add(Lists.<StatisticsModleOfOrganization>newArrayList());
				}
			}else{
				for (int m : parameter.getMonthList()) {
					List<StatisticsModleOfOrganization> set = Lists.newArrayList();
					this.buildStatisticsModle(subDeptList, set);
					result.add(set);
				}
				List<Demand> demandList = demandDao.selectMonthlyDemandOfDept(parameter.getYear(),
						parameter.getMonthList(), parameter.getDirectSubDeptIdList());
				if (CollectionUtils.isNotEmpty(demandList)) {
					for (Demand dem : demandList) {
						if (StringUtils.isNotBlank(dem.getAjbh()) && StringUtils.isNotBlank(dem.getJsdwbh())) {
							int month = Integer.parseInt(dem.getAjbh());
							for (StatisticsModleOfOrganization target : result.get(month - 1)) {
								if (dem.getJsdwbh().equals(target.getOrganizationCode())) {
									target.setAmount(target.getAmount() + 1);// 累计案件数
								}
							}
						}
					}
				}
			}
		}
		return result;
	}

	@Override
	public List<List<StatisticsModleOfOrganization>> getMonthlyPublishedInfoOfOrganization() throws Exception {
		List<List<StatisticsModleOfOrganization>> result = new ArrayList<List<StatisticsModleOfOrganization>>();
		User loginUser = UserUtil.getUser();
		if (loginUser == null) {
			throw new ServiceException("无法获取当前登录用户!");
		}
		ServiceContextParameter parameter = dashboardServiceHelper.buildServiceContextParameter(loginUser.getDeptid());
		if (parameter != null) {
			Map<Integer, String> deptCategories = parameter.getDepartmentCategories();// 所有警种
			Map<Integer, Set<Dept>> subDeptMap = parameter.getDirectSubDeptByCategory();// 警种与下级机构的映射关系
			if (deptCategories != null && !deptCategories.isEmpty()) {
				for (int m : parameter.getMonthList()) {
					List<StatisticsModleOfOrganization> set = Lists.newArrayList();
					this.buildStatisticsModle(deptCategories, set);
					result.add(set);
				}
				Iterator<Entry<Integer, Set<Dept>>> it = subDeptMap.entrySet().iterator();
				while (it.hasNext()) {
					Entry<Integer, Set<Dept>> entry = it.next();
					int orgType = entry.getKey();
					String orgTypeName = deptCategories.get(orgType);
					Set<Dept> value = entry.getValue();
					if (CollectionUtils.isNotEmpty(value)) {
						// 下级机构id
						List<String> subDeptIdList = BatchSqlParamListBuilder.getBatchList(Lists.newArrayList(value),
								300, new BatchSqlParamListBuilder.ElementResolver<Dept, String>() {
									@Override
									public String apply(List<Dept> input) {
										List<String> deptIdList = Lists.newArrayList();
										for (Dept dept : input) {
											if (StringUtils.isNotBlank(dept.getCode())) {
												deptIdList.add(dept.getCode());
											}
										}
										StringBuilder result = new StringBuilder("'");
										Joiner.on("','").appendTo(result, deptIdList);
										result.append("'");
										return result.toString();
									}
								});
						List<Info> infoList = infoMapper.selectMonthlyInfoOfDept(parameter.getYear(), parameter.getMonthList(),
								subDeptIdList);
						if (CollectionUtils.isNotEmpty(infoList)) {
							for (Info info : infoList) {
								if (StringUtils.isNotBlank(info.getAjbh()) && StringUtils.isNotBlank(info.getFbdwbh())) {
									int month = Integer.parseInt(info.getAjbh());
									for (StatisticsModleOfOrganization target : result.get(month - 1)) {
										if(target.getOrganizationCode().equals(orgType)){
											target.setAmount(target.getAmount() + 1);// 累计案件数
										}
									}
								}
							}
						}
					}
				}
			}else{
				for (int m : parameter.getMonthList()) {
					result.add(Lists.<StatisticsModleOfOrganization>newArrayList());
				}
			}
		}
		return result;
	}

	@Override
	public List<StatisticsModleOfOrganization> getMonthlyFeedbackOfOrganization() throws Exception {
		List<StatisticsModleOfOrganization> result = new ArrayList<StatisticsModleOfOrganization>();
		User loginUser = UserUtil.getUser();
		if (loginUser == null) {
			throw new ServiceException("无法获取当前登录用户!");
		}
		ServiceContextParameter parameter = dashboardServiceHelper.buildServiceContextParameter(loginUser.getDeptid());
		if (parameter != null) {
			Map<Integer, String> deptCategories = parameter.getDepartmentCategories();// 所有警种
			Map<Integer, Set<Dept>> subDeptMap = parameter.getDirectSubDeptByCategory();// 警种与下级机构的映射关系
			if (deptCategories != null && !deptCategories.isEmpty()) {
				Iterator<Entry<Integer, Set<Dept>>> it = subDeptMap.entrySet().iterator();
				while (it.hasNext()) {
					Entry<Integer, Set<Dept>> entry = it.next();
					int orgType = entry.getKey();
					String orgTypeName = deptCategories.get(orgType);

					StatisticsModleOfOrganization smoo = new StatisticsModleOfOrganization();
					smoo.setOrganizationCode("" + orgType);
					smoo.setOrganizationName(orgTypeName);
					if (!result.contains(smoo)) {
						result.add(smoo);
					}

					Set<Dept> value = entry.getValue();
					if (CollectionUtils.isNotEmpty(value)) {
						// 下级机构id
						List<String> subDeptIdList = BatchSqlParamListBuilder.getBatchList(Lists.newArrayList(value),
								300, new BatchSqlParamListBuilder.ElementResolver<Dept, String>() {
									@Override
									public String apply(List<Dept> input) {
										List<String> deptIdList = Lists.newArrayList();
										for (Dept dept : input) {
											if (StringUtils.isNotBlank(dept.getCode())) {
												deptIdList.add(dept.getCode());
											}
										}
										StringBuilder result = new StringBuilder("'");
										Joiner.on("','").appendTo(result, deptIdList);
										result.append("'");
										return result.toString();
									}
								});

						List<Clue> clewList = demandClueDao.selectMonthlyClueOfDept(parameter.getYear(),
								parameter.getMonthList(), subDeptIdList, 1);

						/*
						 * TestDataBuilder.buildMonthlyFeedbackOfOrganization();
						 */
						if (CollectionUtils.isNotEmpty(clewList)) {
							int amount = 0;
							for (Clue clue : clewList) {
								// 累计隶属于该警种的反馈数量
								if (StringUtils.isNotBlank(clue.getFkdwbh()) && null != (clue.getSffj())) {
									amount = amount + clue.getSffj();
								}
							}
							for (StatisticsModleOfOrganization target : result) {
								if (target.getOrganizationCode().equals("" + orgType)) {
									// 找到了当前警种
									target.setAmount(target.getAmount() + amount);
									break;
								}
							}
						}
					}
				}
			}
		}
		return result;
	}

	@Override
	public List<HonorCanvas> getHonorCanvas(int rank) {
		if (rank <= 0)
			rank = 5;

		return TestDataBuilder.buildHonorCanvas(rank);
	}

	@Override
	public List<ThingsWillBeDone> getThingsWillBeDone(int days) throws Exception {
		List<ThingsWillBeDone> result = null;
		// 获取当前登录用户
		User loginUser = UserUtil.getUser();
		if (loginUser == null) {
			throw new ServiceException("无法获取当前登录用户!");
		}
		List<Backlog> demandWillBeProcess/*
											 * = TestDataBuilder.
											 * buildThingsWillBeDone()
											 */;
		days = (days < 0 ? 0 : days);
		MutablePair<User, Boolean> allcatorRigthHolder = MutablePair.of(loginUser, false);
		if (demandFlowService.isAllocateRole(Constant.SystemInfo.WEB_PORTAL)) {// 当前用户有指派需求的权限,则是领导
			allcatorRigthHolder.setRight(true);
			String deptId = loginUser.getDeptid();// 登录用户所属机构id
			demandWillBeProcess = demandDao.selectAllDemandWillBeDone(loginUser.getAccount(), deptId, days);
		} else {
			// 非领导
			demandWillBeProcess = demandDao.selectAllDemandWillBeDone(loginUser.getAccount(), null, days);
		}
		if (CollectionUtils.isNotEmpty(demandWillBeProcess)) {
			result = Lists.transform(demandWillBeProcess, new Function<Backlog, ThingsWillBeDone>() {
				@Override
				public ThingsWillBeDone apply(Backlog demand) {
					ThingsWillBeDone twbd = null;
					if (demand != null) {
						if (allcatorRigthHolder.getRight()) {
							twbd = new ThingsWillBeDone(demand, loginUser);
						} else {
							twbd = new ThingsWillBeDone(demand);
						}
					}
					return twbd;
				}

			});
		}
		return result;
	}
}

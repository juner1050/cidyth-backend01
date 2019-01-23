package com.hyzs.cidyth.portal.controller.dashboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hyzs.cidyth.common.utils.NumberChineseExchanger;
import com.hyzs.cidyth.module.dashboard.service.DashboardService;
import com.hyzs.cidyth.module.dashboard.vo.CaseSolvedOfOrgType;
import com.hyzs.cidyth.module.dashboard.vo.HonorCanvas;
import com.hyzs.cidyth.module.dashboard.vo.StatisticsModleOfOrganization;
import com.hyzs.cidyth.module.dashboard.vo.ThingsWillBeDone;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags={"看板接口"})
@RestController
@RequestMapping("dashboard")
public class DashboardController {
	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);
	@Autowired
	private DashboardService dashboardService;

	/**
	 * <b>接口功能：</b>按警种维度获取案件侦破情况<br>
	 * <b>接口URL:</b>/dashboard/organizationDailyCaseSolveSurvey<br>
	 * <b>请求类型：</b>GET<br>
	 * <b>入参格式：</b>无 <br>
	 * 返回json,格式如下：<br>
	 * {"code":"状态码,例如:200、500...",<br>
	 * "message":"成功为:success,失败为：error",<br>
	 * "data":{<br>
	 * "totalCase":"当日总案件数",<br>
	 * "records":[{<br>
	 * "orgType":"机构种类代码",<br>
	 * "orgTypeName":"机构种类名称,例如：网警、技侦...",<br>
	 * "demand":"需求数",<br>
	 * "signedDemand":"签收需求数",<br>
	 * "replied":"回复数"<br>
	 * },<br>
	 * {<br>
	 * "orgType":"机构种类代码",<br>
	 * "orgTypeName":"机构种类名称,例如：网警、技侦...",<br>
	 * "demand":"需求数",<br>
	 * "signedDemand":"签收需求数",<br>
	 * "replied":"回复数"<br>
	 * }]<br>
	 * }<br>
	 * }<br>
	 * @throws Exception 
	 */
	@GetMapping(value = "organizationDailyCaseSolveSurvey")
	@ApiOperation(value = "按警种维度获取案件侦破情况", httpMethod = "GET", response = CaseSolvedOfOrgType.class, notes = "按警种维度获取案件侦破情况")
	public CaseSolvedOfOrgType organizationDailyCaseSolveSurvey() throws Exception {
		int days = 29;
		CaseSolvedOfOrgType result = dashboardService.getDailyCaseSolveSurveyByOrganizationType(days);
		return result;
	}

	/**
	 * 发案情况
	 * 
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value = "getMonthlyCaseOfOrganization")
	@ApiOperation(value = "发案情况", httpMethod = "GET", response = Map.class, notes = "发案情况")
	public Map<String, Object> getMonthlyCaseOfOrganization() throws Exception {
		List<List<StatisticsModleOfOrganization>> data = dashboardService.getMonthlyCaseOfOrganization();
		return this.lineOrBarStatisticsModelResponse(data);
	}

	/**
	 * 需求情况
	 * 
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value = "getMonthlyDemandOfOrganization")
	@ApiOperation(value = "需求情况", httpMethod = "GET", response = Map.class, notes = "需求情况")
	public Map<String, Object> getMonthlyDemandOfOrganization() throws Exception {
		List<List<StatisticsModleOfOrganization>> data = dashboardService.getMonthlyDemandOfOrganization();
		return this.lineOrBarStatisticsModelResponse(data);
	}

	/**
	 * 反馈情况
	 * 
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value = "getMonthlyFeedBackOfOrganization")
	@ApiOperation(value = "反馈情况", httpMethod = "GET", response = Map.class, notes = "反馈情况")
	public Map<String, Object> getMonthlyFeedBackOfOrganization() throws Exception {
		List<StatisticsModleOfOrganization> data = dashboardService.getMonthlyFeedbackOfOrganization();
		return this.pieStatisticsModelResponse(data);
	}

	/**
	 * 信息发布情况
	 * 
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value = "getMonthlyPublishedInfoOfOrganization")
	@ApiOperation(value = "信息发布情况", httpMethod = "GET", response = Map.class, notes = "信息发布情况")
	public Map<String, Object> getMonthlyPublishedInfoOfOrganization() throws Exception {
		List<List<StatisticsModleOfOrganization>> data = dashboardService.getMonthlyPublishedInfoOfOrganization();
		return this.radarStatisticsModleResponse(data);
	}

	/**
	 * 代办事项
	 * 
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value = "getThingsWillBeDone")
	@ApiOperation(value = "代办事项", httpMethod = "GET", response = ThingsWillBeDone.class, notes = "代办事项")
	public List<ThingsWillBeDone> getThingsWillBeDone() throws Exception {
		int days = 3;
		List<ThingsWillBeDone> datas = dashboardService.getThingsWillBeDone(days);
		return datas;
	}

	/**
	 * 联合作战荣誉墙
	 * 
	 * @return
	 */
	@GetMapping(value = "getHonorCanvas")
	@ApiOperation(value = "联合作战荣誉墙", httpMethod = "GET", response = HonorCanvas.class, notes = "联合作战荣誉墙")
	public List<HonorCanvas> getHonorCanvas() {
		List<HonorCanvas> datas = dashboardService.getHonorCanvas(5);
		return datas;
	}

	private Map<String, Object> radarStatisticsModleResponse(List<List<StatisticsModleOfOrganization>> data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("legend", null);
		result.put("indicator", null);
		result.put("series", null);
		boolean needContinue = true;
		if (CollectionUtils.isNotEmpty(data)) {
			List<String> legend = Lists.newArrayList();
			List<Map<String, Object>> indicator = null;
			List<Map<String, Object>> series = null;
			for (int m = 0; m < data.size(); m++) {
				String month = NumberChineseExchanger.NumberToChinese(m + 1);
				if (m + 1 >= 10 && m + 1 < 20) {
					month = month.substring(1);
				}
				String mon = month + "月份";
				legend.add(mon);

				List<StatisticsModleOfOrganization> d = data.get(m);
				if (m == 0 && CollectionUtils.isEmpty(d)) {
					needContinue = false;
				}
				if (needContinue) {
					series = (List<Map<String, Object>>) result.get("series");
					if (series == null) {
						series = Lists.newArrayList();
						result.put("series", series);
					}
					if (m == 0) {
						indicator = Lists.newArrayList();
						Map<String, Object> seriesData = Maps.newHashMap();
						seriesData.put("name", mon);
						List<Integer> dd = Lists.newArrayList();
						for (StatisticsModleOfOrganization coo : d) {
							indicator.add(new HashMap<String, Object>() {
								{
									put("name", StringUtils.isNotBlank(coo.getOrganizationName())
											? coo.getOrganizationName() : "未知机构");
								}
							});
							dd.add(coo.getAmount());
						}
						seriesData.put("value", dd);
						series.add(seriesData);
					} else {
						Map<String, Object> seriesData = Maps.newHashMap();
						seriesData.put("name", mon);
						List<Integer> dd = Lists.newArrayList();
						for (StatisticsModleOfOrganization coo : d) {
							dd.add(coo.getAmount());
						}
						seriesData.put("value", dd);
						series.add(seriesData);
					}
				}

			}
			result.put("legend", legend);
			result.put("indicator", indicator);
			result.put("series", series);
		}
		return result;
	}

	private Map<String, Object> lineOrBarStatisticsModelResponse(List<List<StatisticsModleOfOrganization>> data) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("legend", null);
		result.put("xAxis", null);
		result.put("series", null);
		boolean needContinue = true;
		if (CollectionUtils.isNotEmpty(data)) {
			List<String> legend = Lists.newArrayList();
			List<String> xAxis = null;
			List<Map<String, Object>> series = null;
			for (int m = 0; m < data.size(); m++) {
				String month = NumberChineseExchanger.NumberToChinese(m + 1);
				if (m + 1 >= 10 && m + 1 < 20) {
					month = month.substring(1);
				}
				String mon = month + "月份";
				legend.add(mon);

				List<StatisticsModleOfOrganization> d = data.get(m);
				if (m == 0 && CollectionUtils.isEmpty(d)) {
					needContinue = false;
				}
				if (needContinue) {
					series = (List<Map<String, Object>>) result.get("series");
					if (series == null) {
						series = Lists.newArrayList();
						result.put("series", series);
					}
					if (m == 0) {
						xAxis = Lists.newArrayList();
						Map<String, Object> seriesData = Maps.newHashMap();
						seriesData.put("name", mon);
						List<Integer> dd = Lists.newArrayList();
						for (StatisticsModleOfOrganization coo : d) {
							xAxis.add(StringUtils.isNotBlank(coo.getOrganizationName()) ? coo.getOrganizationName()
									: "未知机构");
							dd.add(coo.getAmount());
						}
						seriesData.put("data", dd);
						series.add(seriesData);
					} else {
						Map<String, Object> seriesData = Maps.newHashMap();
						seriesData.put("name", mon);
						List<Integer> dd = Lists.newArrayList();
						for (StatisticsModleOfOrganization coo : d) {
							dd.add(coo.getAmount());
						}
						seriesData.put("data", dd);
						series.add(seriesData);
					}
				}

			}
			result.put("legend", legend);
			result.put("xAxis", xAxis);
			result.put("series", series);
		}
		return result;
	}

	private Map<String, Object> pieStatisticsModelResponse(List<StatisticsModleOfOrganization> datas) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("legend", null);
		result.put("series", null);
		if (CollectionUtils.isNotEmpty(datas)) {
			List<String> legend = Lists.newArrayList();
			List<Map<String, Object>> series = Lists.newArrayList();
			for (int m = 0; m < datas.size(); m++) {
				StatisticsModleOfOrganization data = datas.get(m);
				String leg = StringUtils.isNotBlank(data.getOrganizationName()) ? data.getOrganizationName() : "未知机构";
				int amount = data.getAmount();
				legend.add(leg);
				Map<String, Object> seriesData = Maps.newHashMap();
				seriesData.put("name", leg);
				seriesData.put("value", amount);
				series.add(seriesData);
			}

			result.put("legend", legend);
			result.put("series", series);
		}
		return result;
	}
}

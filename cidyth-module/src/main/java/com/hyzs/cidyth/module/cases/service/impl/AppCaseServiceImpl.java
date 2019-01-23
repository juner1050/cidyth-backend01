package com.hyzs.cidyth.module.cases.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.base.service.CasesService;
import com.hyzs.cidyth.module.base.vo.PersonalCasesVO;
import com.hyzs.cidyth.module.cases.service.AppCaseService;
import com.hyzs.cidyth.module.cases.vo.AppCaseList;
import com.hyzs.cidyth.module.cases.vo.AppCaseList.Parameter;
import com.hyzs.cidyth.module.dic.service.DicService;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;
import com.hyzs.psd.gafa.utils.bean.GenericBeanUtils;
import com.hyzs.psd.gafa.utils.bean.GenericBeanUtils.ConvertedBeanPropertyValueResolver;
@Service
public class AppCaseServiceImpl implements AppCaseService {
	private static final Logger logger = LoggerFactory.getLogger(AppCaseServiceImpl.class);
	@Autowired
	private CasesService casesService;
	@Autowired
	private DicService dicService;
	@Autowired
	private UserCenterService userCenterService;
	@Override
	public PageInfo<AppCaseList> pageCaseList(Page page) {
		PageInfo<AppCaseList> result = new PageInfo<AppCaseList>();
		List<AppCaseList> datas = null;
		if (page == null) {
			page = new Page();
		}
		result.setPageNum(page.getPageNum());
		result.setPageSize(page.getPageSize());
		PageInfo<Cases> pagedData = casesService.listCaseLocal(null, page);
		if (pagedData != null) {
			result.setTotal(pagedData.getTotal());
			result.setPages(pagedData.getPages());

			Collection<Cases> caseList = pagedData.getList();
			if (CollectionUtils.isNotEmpty(caseList)) {
				datas = Lists.newArrayList();
				result.setList(datas);

				String[] caseProperties = new String[] { "ab", "sdtd" };
				for (Cases cases : caseList) {
					AppCaseList data = new AppCaseList(cases);
					Parameter parameter = data.parameter();
					GenericBeanUtils.convertSimpleBean2Map(cases, false, new ConvertedBeanPropertyValueResolver() {
						@Override
						public Object resolvePropertyValue(String propertyName, Object propertyValue) {
							if (StringUtils.equalsAny(propertyName, caseProperties)) {
								parameter.put(propertyName + "Cn", propertyValue == null ? ""
										: dicService.getValueByKey(propertyName, propertyValue.toString()));
							} else if ("zbdw".equals(propertyName) && StringUtils.isNotEmpty(cases.getZbdw())) {// 主办单位不为空，从用户中心获取
								Dept deptZbdw = null;
								try {
									deptZbdw = userCenterService.getDeptByCode(cases.getZbdw());
								} catch (Exception e) {
									e.printStackTrace();
								}
								parameter.setZbdwCn(deptZbdw == null ? "" : deptZbdw.getFullname());
							} else if ("ajzbry".equals(propertyName) && StringUtils.isNotEmpty(cases.getAjzbry())) {// 主办人员不为空，从用户中心获取
								User userAjzbry = null;
								try {
									userAjzbry = userCenterService.getUserByUserName(cases.getAjzbry());
								} catch (Exception e) {
									e.printStackTrace();
								}
								parameter.setAjzbryCn(userAjzbry == null ? "" : userAjzbry.getName());
							}
							return propertyValue;
						}
					});
					datas.add(data);
				}
			}
		} else {
			result.setTotal(0);
			result.setPages(0);
		}
		
		return result;
	}
	
	@Override
	public PageInfo<PersonalCasesVO> listCasesPartner(Page page, String beginTime, String endTime) {
		return casesService.listCasesByMonth(page, beginTime, endTime);
	}

	@Override
	public Map<String, Object> finishTotal() {
		return casesService.finishTotal();
	}
}

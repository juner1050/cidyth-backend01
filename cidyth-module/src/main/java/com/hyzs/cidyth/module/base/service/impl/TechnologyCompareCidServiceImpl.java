package com.hyzs.cidyth.module.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.common.utils.GenericBeanUtils;
import com.hyzs.cidyth.common.utils.GenericBeanUtils.ConvertedBeanPropertyValueResolver;
import com.hyzs.cidyth.module.base.dao.TechnologyCompareCidMapper;
import com.hyzs.cidyth.module.base.entity.CasesComparison;
import com.hyzs.cidyth.module.base.entity.SceneFingerPrint;
import com.hyzs.cidyth.module.base.entity.TechnologyCompare;
import com.hyzs.cidyth.module.base.service.SceneFingerPrintService;
import com.hyzs.cidyth.module.base.service.TechnologyCompareCidService;
import com.hyzs.cidyth.module.base.vo.CompareInfoLoadParam;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Service("TechnologyCompareCidService")
public class TechnologyCompareCidServiceImpl implements TechnologyCompareCidService {

	private static final Logger logger = LoggerFactory.getLogger(TechnologyCompareCidServiceImpl.class);

	@Autowired
	private TechnologyCompareCidMapper technologyCompareCidMapper;
	@Autowired
	private SceneFingerPrintService sceneFingerPrintService;

	@Override
	public PageInfo<Map<String, Object>> loadCompareInfo(CompareInfoLoadParam param, Page page) {

		/* 访问刑技平台数据库 */
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		PageInfo<TechnologyCompare> result = new PageInfo<TechnologyCompare>(
				technologyCompareCidMapper.selectByPager(GenericBeanUtils.convertSimpleBean2Map(param)));

		List<Map<String, Object>> list = null;

		if (CollectionUtils.isNotEmpty(result.getList())) {
			list = new ArrayList<Map<String, Object>>();
			for (TechnologyCompare bean : result.getList()) {
				Map<String, Object> data = this.convertCompareInfo(bean);
				if (data != null && !data.isEmpty()) {
					list.add(data);
				}
			}
		}

		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
		pageInfo.setTotal(result.getTotal());
		pageInfo.setPageNum(result.getPageNum());
		pageInfo.setPageSize(result.getPageSize());
		return pageInfo;
	}

	@Override
	public void export(HttpServletResponse response, Map<String, Object> param) {
		// TODO Auto-generated method stub

	}

	private Map<String, Object> convertCompareInfo(TechnologyCompare bean) {
		Map.Entry<SceneFingerPrint, SceneFingerPrint> fingerPrintHolder = new Entry<SceneFingerPrint, SceneFingerPrint>() {
			private SceneFingerPrint fingerPrint = null;

			@Override
			public SceneFingerPrint getKey() {
				return fingerPrint;
			}

			@Override
			public SceneFingerPrint getValue() {
				return fingerPrint;
			}

			@Override
			public SceneFingerPrint setValue(SceneFingerPrint value) {
				this.fingerPrint = value;
				return this.fingerPrint;
			}
		};
		if (StringUtils.isNotBlank(bean.getAjbh())) {
			List<SceneFingerPrint> printerList = sceneFingerPrintService.listFingerPrintByAjbh(bean.getAjbh());
			fingerPrintHolder.setValue(CollectionUtils.isNotEmpty(printerList) ? printerList.get(0) : null);
		}

		Map<String, Object> data = GenericBeanUtils.convertSimpleBean2Map(bean, false,
				new ConvertedBeanPropertyValueResolver() {
					@Override
					public Object resolvePropertyValue(String propertyName, Object propertyValue) {
						if ("afsj".equals(propertyName) && propertyValue != null) {
							String value = (String) propertyValue;
							Date result = DateUtil.parse2Date(value, DateUtil.DEFAULT_SUPPORTED_PATTERN);
							if (result != null) {
								return DateUtil.dateToStr(result, "yyyy-MM-dd");
							}
						}
						if ("zwbh".equals(propertyName) && fingerPrintHolder.getValue() != null
								&& StringUtils.isNotBlank(fingerPrintHolder.getValue().getZwbm())) {// 指纹编码
							return fingerPrintHolder.getValue().getZwbm();
						}
						if ("tqbw".equals(propertyName) && fingerPrintHolder.getValue() != null
								&& StringUtils.isNotBlank(fingerPrintHolder.getValue().getYlbw())) {// 指纹遗留部位
							return fingerPrintHolder.getValue().getYlbw();
						}
						if ("createDate".equals(propertyName) && propertyValue != null) {// 比中时间
							return DateUtil.dateToStr((Date) propertyValue, "yyyy-MM-dd");
						}
						return propertyValue;
					}
				});
		return data;
	}

	@Override
	public PageInfo<Map<String, Object>> loadCompareInfoByAjbh(String ajbh, Page page) {
		/* 访问刑技平台数据库 */
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		PageInfo<TechnologyCompare> result = new PageInfo<TechnologyCompare>(StringUtils.isNotBlank(ajbh)
				? technologyCompareCidMapper.selectByAjbh(ajbh) : Lists.<TechnologyCompare>newArrayList());
		List<Map<String, Object>> list = null;

		if (CollectionUtils.isNotEmpty(result.getList())) {
			list = new ArrayList<Map<String, Object>>();
			for (TechnologyCompare bean : result.getList()) {
				Map<String, Object> data = this.convertCompareInfo(bean);
				if (data != null && !data.isEmpty()) {
					list.add(data);
				}
			}
		}
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
		pageInfo.setTotal(result.getTotal());
		pageInfo.setPageNum(result.getPageNum());
		pageInfo.setPageSize(result.getPageSize());
		return pageInfo;
	}

}

package com.hyzs.cidyth.module.uc.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hyzs.cidyth.common.utils.BatchSqlParamListBuilder;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.common.utils.DateUtil.UpToNow;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.DeptCategory;
import com.hyzs.psd.gafa.exception.BizException;
/**
 * 用户中心辅助工具类
 * @author Administrator
 *
 */
@Component("userCenterServiceHelper")
public class UserCenterServiceHelper {
	private static final Logger logger = LoggerFactory.getLogger(UserCenterServiceHelper.class);
	@Autowired
	@Qualifier("userCenterService")
	private UserCenterService userCenterService;
	
	public ServiceContextParameter buildServiceContextParameter(String parentDeptId){
		ServiceContextParameter  parameter = new ServiceContextParameter();
		UpToNow utn = DateUtil.getMonthsUpToNow();// 当前月份列表
		// 警种信息
		List<DeptCategory> departmentCategories;
		try {
			departmentCategories = userCenterService.getAllDeptCategory();
		} catch (Exception e1) {
			throw new BizException("从远程获取机构类别(警种)信息失败!", e1);
		}
		Collection<Dept> subDeptList = null;
		try {
			subDeptList = userCenterService.getDirectSubDeptByParentCode$Category(parentDeptId, null);
		} catch (Exception e) {
			throw new BizException("查询机构" + parentDeptId + "的直接下级机构失败!", e);
		}
		if (CollectionUtils.isNotEmpty(subDeptList)) {
			parameter.setYear(utn.getYear());
			parameter.setMonthList(utn.getMonths());
			parameter.setDepartmentCategories(departmentCategories);
			parameter.setDirectSubDeptList(subDeptList);
		}
		return parameter;
	}
	
	
	public class ServiceContextParameter {
		private int year;// 年份
		private Collection<Integer> monthList;// 月列表
		private Collection<DeptCategory> departmentCategories;// 警种信息
		private Collection<Dept> directSubDeptList;// 当前登录用户所属机构的所有直接下级机构

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public Collection<Integer> getMonthList() {
			return monthList;
		}

		public void setMonthList(Collection<Integer> monthList) {
			this.monthList = monthList;
		}
		/**
		 * 获取所有警种
		 * @return
		 */
		public Map<Integer,String> getDepartmentCategories() {
			Map<Integer,String> result=null;
			if(CollectionUtils.isNotEmpty(departmentCategories)){
				result = Maps.newHashMap();
				for(DeptCategory cat:departmentCategories){
					result.put(cat.getId(), cat.getName());
				}
			}
			return result;
		}

		public void setDepartmentCategories(Collection<DeptCategory> departmentCategories) {
			this.departmentCategories = departmentCategories;
		}

		/**
		 * 按机构种类(警种)获取直接下级机构
		 * 
		 * @return Map对象,key为机构种类(警种)代码,value为对应机构种类（警种）对应的机构列表
		 */
		public Map<Integer, Set<Dept>> getDirectSubDeptByCategory() {
			return userCenterService.extractDeptByCategoryFormDeptList(this.departmentCategories,this.directSubDeptList);
		}

		/**
		 * 直接子机构列表
		 * 
		 * @return
		 */
		public Collection<Dept> getDirectSubDeptList() {
			return directSubDeptList;
		}
		/**
		 * 直接子机构列表
		 * @param filter 过滤器
		 * @return
		 */
		public Collection<Dept> getDirectSubDeptList(Predicate<Dept> filter){
			if(filter!=null&&CollectionUtils.isNotEmpty(directSubDeptList)){
				Collection<Dept> depts = Collections2.filter(directSubDeptList,filter);
				return depts;
			}else{
				return getDirectSubDeptList();
			}
		}
		public void setDirectSubDeptList(Collection<Dept> directSubDeptList) {
			if (CollectionUtils.isNotEmpty(directSubDeptList)) {
				this.directSubDeptList = directSubDeptList;
			}
		}
		/**
		 * 直接子机构代码列表
		 * 
		 * @return
		 */
		public Collection<String> getDirectSubDeptIdList() {
			// 子机构代码
			Collection<String> subDeptIdList = null;
			if (CollectionUtils.isNotEmpty(this.directSubDeptList)) {
				subDeptIdList = BatchSqlParamListBuilder.getBatchList(Lists.newArrayList(directSubDeptList), 300,
						new BatchSqlParamListBuilder.ElementResolver<Dept, String>() {
							@Override
							public String apply(List<Dept> input) {
								List<String> jgdmList = Lists.newArrayList();
								for (Dept dept : input) {
									String jgdm = dept.getCode();// 获取机构代码
									jgdmList.add(jgdm);
								}
								StringBuilder result = new StringBuilder("'");
								Joiner.on("','").appendTo(result, jgdmList);
								result.append("'");
								return result.toString();
							}
						});
			}
			return subDeptIdList;
		}

	}
}

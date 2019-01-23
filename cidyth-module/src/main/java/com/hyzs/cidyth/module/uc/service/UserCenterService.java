package com.hyzs.cidyth.module.uc.service;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hyzs.cidyth.common.utils.HttpUtil;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.DeptCategory;
import com.hyzs.cidyth.module.uc.vo.PolityLevel;
import com.hyzs.cidyth.module.uc.vo.Role;
import com.hyzs.cidyth.module.uc.vo.User;

public abstract class UserCenterService {
	private static final Logger logger = LoggerFactory.getLogger(UserCenterService.class);

	private final ObjectMapper jsonParser = new ObjectMapper();

	private String serviceUrlPrefix;// 用户中心服务url前缀

	public UserCenterService() {
		jsonParser.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		// jsonParser.setDateFormat(dateFormat)
		jsonParser.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public String getServiceUrlPrefix() {
		return serviceUrlPrefix;
	}

	public void setServiceUrlPrefix(String serviceUrlPrefix) {
		this.serviceUrlPrefix = serviceUrlPrefix;
	}

	/**
	 * 根据用户名（警号）查询用户
	 *
	 * @param userName
	 *            用户名（警号）
	 * @return
	 * @throws Exception
	 */
	public abstract User getUserByUserName(String userName) throws Exception;

	/**
	 * 根据用户名（警号）查询用户
	 *
	 * @param userName
	 *            用户名（警号）
	 * @return
	 * @throws Exception
	 */
	public abstract User getUserLazyByUserName(String userName) throws Exception;

	/**
	 * 根据用户名和密码查询用户
	 * 
	 * @param userName
	 *            用户名(警号)
	 * @param password
	 *            密码
	 * @return
	 * @throws Exception
	 */
	public abstract User getUserByUserNameAndPassword(String userName, String password) throws Exception;

	/**
	 * 查询机构下的用户
	 * 
	 * @param departmentCode
	 *            机构代码
	 * @return
	 * @throws Exception
	 */
	public abstract List<User> getUsersByDepartmentCode(String departmentCode) throws Exception;

	/**
	 * 根据权限代码和机构代码(可空)查询的用户
	 * 
	 * @param departmentCode
	 *            机构代码(可以为空)
	 * @param premissionCode
	 *            权限代码
	 * @return
	 * @throws Exception
	 */
	public abstract List<User> getUsersByDepartmentCodeAndPremission(String departmentCode, List<String> premessionCode)
			throws Exception;

	/**
	 * 查询最顶层机构
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract List<Dept> getTopDepartment() throws Exception;

	/**
	 * 根据机构代码查询机构
	 * 
	 * @param code
	 *            机构代码
	 * @return
	 * @throws Exception
	 */
	public abstract Dept getDeptByCode(String code) throws Exception;

	/**
	 * 根据父级机构编码查询所有各级子机构
	 * 
	 * @param pcodes
	 *            父级机构编码
	 * @return
	 * @throws Exception
	 */
	public abstract Set<Dept> getSubDepartmentsByParentDeptCodes(Set<String> pcodes) throws Exception;

	/**
	 * 查询直接下级机构,可选条件如下： (1)父机构编码 (2)父机构id和警种类别编码
	 *
	 * @param parentCode
	 *            上级机构代码
	 * @param category
	 *            警种类别代码
	 * @return
	 * @throws Exception
	 */
	public abstract Set<Dept> getDirectSubDeptByParentCode$Category(String parentCode, List<Integer> category)
			throws Exception;

	/**
	 * 查询所有分局
	 *
	 * @param parentCode
	 *            上级机构代码
	 * @param category
	 *            警种类别代码
	 * @return
	 * @throws Exception
	 */
	public abstract List<Dept> getBranchDept()
			throws Exception;

	/**
	 * 判断childrenCode是否是parentCode的子节点
	 * @param parentCode 父节点code
	 * @param childrenCode 子节点code
	 * @return
	 * @throws Exception
	 */
	public abstract Map<String, Object> isChildren(String parentCode, String childrenCode)
			throws Exception;

	/**
	 * 根据行政级别查询机构
	 * 
	 * @param polityLevel
	 *            行政级别代码
	 * @return
	 * @throws Exception
	 */
	public abstract List<Dept> getDeptByPolityLevel(int polityLevel) throws Exception;

	/**
	 * 获取所有行政等级
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract List<PolityLevel> getAllPolityLevel() throws Exception;

	/**
	 * 获取所有警种类别
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract List<DeptCategory> getAllDeptCategory() throws Exception;

	/**
	 * 根据用户编号查询角色
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 * @throws Exception
	 */
	public abstract List<Role> getRoleByUserId(String userId) throws Exception;

	protected String checkAndGetServiceUrl(String urlSuffix) {
		if (StringUtils.isBlank(serviceUrlPrefix)) {
			throw new IllegalArgumentException("用户中心服务url为空,请检测项目配置文件中的'uc.serviceUrlPrefix'配置项!");
		}
		StringBuilder requestUrl = new StringBuilder();
		requestUrl.append(this.serviceUrlPrefix);
		if (this.serviceUrlPrefix.endsWith("/")) {
			if (StringUtils.isNotBlank(urlSuffix)) {
				if (urlSuffix.startsWith("/")) {
					requestUrl.append(urlSuffix.substring(1));
				} else {
					requestUrl.append(urlSuffix);
				}
			}
		} else {
			if (StringUtils.isNotBlank(urlSuffix)) {
				if (urlSuffix.startsWith("/")) {
					requestUrl.append(urlSuffix);
				} else {
					requestUrl.append("/").append(urlSuffix);
				}
			}
		}
		return requestUrl.toString();
	}

	/**
	 * 从已有的机构列表中按照警种提取数据
	 * 
	 * @departmentCategories 警种
	 * @param departments
	 *            机构信息
	 * @return Map对象,key为机构种类(警种)代码,value为对应机构种类（警种）对应的机构列表
	 */
	public Map<Integer, Set<Dept>> extractDeptByCategoryFormDeptList(Collection<DeptCategory> departmentCategories,
			Collection<Dept> departments) {
		Map<Integer, Set<Dept>> result = null;
		if (CollectionUtils.isNotEmpty(departmentCategories) && CollectionUtils.isNotEmpty(departments)) {
			result = Maps.newHashMap();
			for (DeptCategory dc : departmentCategories) {
				int dcid = dc.getId();
				for (Dept dept : departments) {
					if (dept != null && dept.getPoliceType() != null) {
						Set<Dept> sd = result.get(dcid);
						if (CollectionUtils.isEmpty(sd)) {
							sd = Sets.newHashSet();
							result.put(dcid, sd);
						}
						sd.add(dept);
					}
				}
			}

		}
		return result;
	}

	/**
	 * 向用户中心发送Http get请求获取数据
	 * 
	 * @param urlSuffix
	 *            接口Url后缀
	 * @param resultType
	 *            返回结果的类型
	 * @return
	 * @throws IOException
	 */
	protected <T> T getDataFromRemote(String urlSuffix, Class<T> resultType) throws IOException {
		T result = null;
		HttpUtil httpClient = new HttpUtil();
		String response = httpClient.get(checkAndGetServiceUrl(urlSuffix));
		if (StringUtils.isNotBlank(response)) {
			result = jsonParser.readValue(response, resultType);
		}
		return result;
	}

	protected <T> T getDataFromRemote(String urlSuffix, TypeHandler typeHandler) throws IOException {
		if (typeHandler == null)
			throw new IllegalArgumentException("No typeHandler for jasonParser.");
		T result = null;
		HttpUtil httpClient = new HttpUtil();
		String response = httpClient.get(checkAndGetServiceUrl(urlSuffix));
		if (StringUtils.isNotBlank(response)) {
			result = jsonParser.readValue(response, typeHandler.process(jsonParser.getTypeFactory()));
		}
		return result;
	}

	/**
	 * 向用户中心发送Http get请求获取数据
	 * 
	 * @param urlSuffix
	 *            接口Url后缀
	 * @param requestParameter
	 *            请求参数
	 * @param resultType
	 *            返回结果的类型
	 * @return
	 * @throws IOException
	 */
	protected <T> T getDataFromRemote(String urlSuffix, Map<String, String> requestParameter, Class<T> resultType)
			throws IOException {
		T result = null;
		HttpUtil httpClient = new HttpUtil();
		String response = httpClient.get(checkAndGetServiceUrl(urlSuffix), requestParameter);
		if (StringUtils.isNotBlank(response)) {
			result = jsonParser.readValue(response, resultType);
		}
		return result;
	}

	protected <T> T getDataFromRemote(String urlSuffix, Map<String, String> requestParameter, TypeHandler typeHandler)
			throws IOException {
		if (typeHandler == null)
			throw new IllegalArgumentException("No typeHandler for jasonParser.");
		T result = null;
		HttpUtil httpClient = new HttpUtil();
		String response = httpClient.get(checkAndGetServiceUrl(urlSuffix), requestParameter);
		if (StringUtils.isNotBlank(response)) {
			result = jsonParser.readValue(response, typeHandler.process(jsonParser.getTypeFactory()));
		}
		return result;
	}

	/**
	 * 向用户中心发送HTTP post json数据
	 * 
	 * @param urlSuffix
	 *            接口Url后缀
	 * @param json
	 *            json字符串参数
	 * @param resultType
	 *            返回结果的类型
	 * @return
	 * @throws IOException
	 */
	protected <T> T postJsonDataToRemote(String urlSuffix, String json, Class<T> resultType) throws IOException {
		T result = null;
		HttpUtil httpClient = new HttpUtil();
		String response = httpClient.postJSON(checkAndGetServiceUrl(urlSuffix), json);
		if (StringUtils.isNotBlank(response)) {
			result = jsonParser.readValue(response, resultType);
		}
		return result;
	}

	protected <T> T postJsonDataToRemote(String urlSuffix, String json, TypeHandler typeHandler) throws IOException {
		if (typeHandler == null)
			throw new IllegalArgumentException("No typeHandler for jasonParser.");
		T result = null;
		HttpUtil httpClient = new HttpUtil();
		String response = httpClient.postJSON(checkAndGetServiceUrl(urlSuffix), json);
		if (StringUtils.isNotBlank(response)) {
			result = jsonParser.readValue(response, typeHandler.process(jsonParser.getTypeFactory()));
		}
		return result;
	}

	protected interface TypeHandler {
		JavaType process(com.fasterxml.jackson.databind.type.TypeFactory typeFacory);
	}

}

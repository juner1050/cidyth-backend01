package com.hyzs.cidyth.module.uc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.DeptCategory;
import com.hyzs.cidyth.module.uc.vo.PolityLevel;
import com.hyzs.cidyth.module.uc.vo.Role;
import com.hyzs.cidyth.module.uc.vo.User;

@Service("userCenterService")
@ConfigurationProperties(prefix = "uc")
public class UserCenterServiceImpl extends UserCenterService {

	@Override
	public User getUserByUserName(String userName) throws Exception {
		if (StringUtils.isBlank(userName)) {
			throw new IllegalArgumentException("用户名不能为空!");
		}
		Map<String, String> param = new HashMap<String, String>() {
			{
				put("account", userName);
			}
		};
		User user = super.getDataFromRemote("/getUserByUserName", param, User.class);
		return user;
	}

	@Override
	public User getUserLazyByUserName(String userName) throws Exception {
		if (StringUtils.isBlank(userName)) {
			throw new IllegalArgumentException("用户名不能为空!");
		}
		Map<String, String> param = new HashMap<String, String>() {
			{
				put("account", userName);
			}
		};
		User user = super.getDataFromRemote("/getUserLazyByUserName", param, User.class);
		return user;
	}

	@Override
	public User getUserByUserNameAndPassword(String userName, String password) throws Exception {
		if (StringUtils.isBlank(userName)) {
			throw new IllegalArgumentException("用户名不能为空!");
		}
		// if (StringUtils.isBlank(password)) {
		// throw new IllegalArgumentException("密码不能为空!");
		// }
		Map<String, String> param = new HashMap<String, String>() {
			{
				put("account", userName);
				put("password", password);
			}
		};
		User user = super.postJsonDataToRemote("/getUserByUserNameAndPassword",
				new ObjectMapper().writeValueAsString(param), User.class);
		return user;
	}
	@Override
	public List<User> getUsersByDepartmentCode(String departmentCode) throws Exception{
		if(StringUtils.isBlank(departmentCode)) throw new IllegalArgumentException("机构代码不能为空!");
		Map<String,String> param = new HashMap<String,String>(){
		{
			put("deptCode",departmentCode);
		}};
		List<User> users = super.getDataFromRemote("/getUsersByDepartmentCode", param,new TypeHandler() {
			@Override
			public JavaType process(TypeFactory typeFacory) {
				return typeFacory.constructParametricType(ArrayList.class, User.class);
			}
		});
		return users;
	}
	
	@Override
	public List<User> getUsersByDepartmentCodeAndPremission(String departmentCode,List<String> premessionCode) throws Exception {
		if(CollectionUtils.isEmpty(premessionCode)) throw new IllegalArgumentException("权限代码不能为空!");
		Map<String, Object> param = new HashMap<String, Object>(){
		{
			put("premissionCode",premessionCode);
			
		}};
		if(StringUtils.isNotBlank(departmentCode)){
			param.put("deptCode", departmentCode);
		}
		List<User> users = super.postJsonDataToRemote("/getUsersByDepartmentCodeAndPremission",
				new ObjectMapper().writeValueAsString(param),new TypeHandler() {
			@Override
			public JavaType process(TypeFactory typeFacory) {
				return typeFacory.constructParametricType(ArrayList.class, User.class);
			}
		});
		return users;
	}

	@Override
	public List<Dept> getTopDepartment() throws Exception {
		List<Dept> result = super.getDataFromRemote("/getTopDepartment", new TypeHandler() {
			@Override
			public JavaType process(TypeFactory typeFacory) {
				return typeFacory.constructParametricType(ArrayList.class, Dept.class);
			}
		});
		return result;
	}

	@Override
	public Dept getDeptByCode(String code) throws Exception {
		if (StringUtils.isBlank(code)) {
			throw new IllegalArgumentException("机构代码不能为空!");
		}
		Map<String, String> requestParameter = new HashMap<String, String>() {
			{
				put("code", code);
			}
		};
		Dept result = super.getDataFromRemote("/getDepartmentByCode", requestParameter, Dept.class);
		return result;
	}
	
	@Override
	public Set<Dept> getSubDepartmentsByParentDeptCodes(Set<String> pcodes) throws Exception {
		if(CollectionUtils.isEmpty(pcodes)){
			throw new ServiceException("查询条件不能全部为空!");
		}
		Set<Dept> depts = new HashSet<Dept>();
		Map<String, String> param = new HashMap<String, String>();
		StringBuilder strb = new StringBuilder();
		param.put("pcodes", Joiner.on(",").appendTo(strb, pcodes).toString());
		TypeHandler tp = new TypeHandler() {
			@Override
			public JavaType process(TypeFactory typeFacory) {
				return typeFacory.constructParametricType(HashSet.class, Dept.class);
			}
		};
		
		depts = super.getDataFromRemote("/getSubDepartmentsByParentDeptCodes",
				param, tp);
		return depts;
	}
	@Override
	public Set<Dept> getDirectSubDeptByParentCode$Category(String parentCode, List<Integer> category) throws Exception {
		if (StringUtils.isBlank(parentCode) && CollectionUtils.isEmpty(category)) {
			throw new ServiceException("查询条件不能全部为空!");
		}
		Set<Dept> depts = new HashSet<Dept>();
		Map<String, Object> param = new HashMap<String, Object>();
		TypeHandler tp = new TypeHandler() {
			@Override
			public JavaType process(TypeFactory typeFacory) {
				return typeFacory.constructParametricType(ArrayList.class, Dept.class);
			}
		};
		if (CollectionUtils.isNotEmpty(category)) {
			for (Integer cate : category) {
				param.put("policeType", cate.toString());
				if (StringUtils.isNotBlank(parentCode)) {
					param.put("pcode", parentCode);
				}
				List<Dept> result = super.postJsonDataToRemote("/getDepartmentByAny",
						new ObjectMapper().writeValueAsString(param), tp);
				if (CollectionUtils.isNotEmpty(result)) {
					for (Dept d : result) {
						depts.add(d);
					}
				}

			}
		} else {
			if (StringUtils.isNotBlank(parentCode)) {
				param.put("pcode", parentCode);
				List<Dept> result = super.postJsonDataToRemote("/getDepartmentByAny",
						new ObjectMapper().writeValueAsString(param), tp);
				if (CollectionUtils.isNotEmpty(result)) {
					for (Dept d : result) {
						depts.add(d);
					}
				}
			}
		}
		return depts;
	}

	@Override
	public List<Dept> getBranchDept() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("isJurisdiction", 1);
		List<Dept> result = super.postJsonDataToRemote("/getDepartmentByAny",
				new ObjectMapper().writeValueAsString(param), new TypeHandler() {
					@Override
					public JavaType process(TypeFactory typeFacory) {
						return typeFacory.constructParametricType(ArrayList.class, Dept.class);
					}
				});
		return result;
	}

	@Override
	public List<Dept> getDeptByPolityLevel(int polityLevel) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("polityLevel", polityLevel);
		List<Dept> result = super.postJsonDataToRemote("/getDepartmentByAny",
				new ObjectMapper().writeValueAsString(param), new TypeHandler() {
					@Override
					public JavaType process(TypeFactory typeFacory) {
						return typeFacory.constructParametricType(ArrayList.class, Dept.class);
					}
				});
		return result;
	}

	@Override
	public List<PolityLevel> getAllPolityLevel() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pid", 45);// 所有行政等级的父编码都是45
		List<PolityLevel> result = super.postJsonDataToRemote("/getDictByAny",
				new ObjectMapper().writeValueAsString(param), new TypeHandler() {
					@Override
					public JavaType process(TypeFactory typeFacory) {
						return typeFacory.constructParametricType(ArrayList.class, PolityLevel.class);
					}
				});

		return result;
	}

	@Override
	public List<DeptCategory> getAllDeptCategory() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pid", 39);// 所有警种类别的父编码都是39
		List<DeptCategory> result = super.postJsonDataToRemote("/getDictByAny",
				new ObjectMapper().writeValueAsString(param), new TypeHandler() {
					@Override
					public JavaType process(TypeFactory typeFacory) {
						return typeFacory.constructParametricType(ArrayList.class, DeptCategory.class);
					}
				});
		return result;
	}

	@Override
	public List<Role> getRoleByUserId(String userId) throws Exception {
		Map<String, String> parameter = new HashMap<String, String>() {
			{
				put("userId", userId);
			}
		};
		List<Role> result = super.getDataFromRemote("/getRoleByUserId", parameter, new TypeHandler() {
			@Override
			public JavaType process(TypeFactory typeFacory) {
				return typeFacory.constructParametricType(ArrayList.class, Role.class);
			}
		});
		return result;
	}

	@Override
	public Map<String, Object> isChildren(String parentCode, String childrenCode) throws Exception {
		Map<String, String> param = Maps.newHashMap();
		param.put("parentCode", parentCode);
		param.put("childrenCode", childrenCode);
		return super.getDataFromRemote("/isChildren", param, Map.class);
	}

	public static void main(String[] args) {
		/*try {
			Set<Dept> dps = service.getSubDepartmentsByParentDeptCodes(Sets.newHashSet("440300190000","440300190900","440306000000"));
			System.out.println(dps.size());
			
			List<User> usrs = service.getUsersByDepartmentCode("440300190000,440300190900,440306000000");
			System.out.println(usrs.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}

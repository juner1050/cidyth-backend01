package com.hyzs.cidyth.module.uc.vo;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hyzs.cidyth.common.utils.Constant;

/**
 * 用户模型
 * 
 * @author jidw
 *
 */
public class User implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2182066369463848793L;
	private static final String USER_ID = "account";
	private static final String USER_MAME = "name";
	private static final String USER_PASS = "password";
	private static final String PHONE = "phone";
	private static final String AVALIBLE = "status";
	private static final String DEPT = "department";
	private static final String ROLE = "role";
	private static final String PERMISSIONS = "permission";

	private Map<String, Object> userData;

	public User() {
		userData = Maps.newConcurrentMap();
	}

	public User(String userId, String userName) {
		userData = Maps.newConcurrentMap();
		this.setAccount(userId);
		this.setName(userName);
	}

	public User(String userId, String userName, String departmentId) {
		userData = Maps.newConcurrentMap();
		this.setAccount(userId);
		this.setName(userName);
		this.setDeptid(departmentId);
	}

	public void setAccount(String userId) {
		userData.put(USER_ID, userId);
	}

	/**
	 * 用户id
	 * 
	 * @return
	 */
	public String getAccount() {
		Object userId = userData.get(USER_ID);
		return userId == null ? null : (String) userId;
	}

	public void setName(String userName) {
		userData.put(USER_MAME, userName);
	}

	/**
	 * 用户姓名
	 * 
	 * @return
	 */
	public String getName() {
		Object userName = userData.get(USER_MAME);
		return userName == null ? null : (String) userName;
	}

	public void setPassword(String password) {
		userData.put(USER_PASS, password);
	}
	/**
	 * 手机号码
	 * @return
	 */
	public String getPhone(){
		Object phone = userData.get(PHONE);
		return phone == null ? null : (String) phone;
	}
	
	public void setPhone(String phone){
		userData.put(PHONE, phone);
	}
	/**
	 * 密码
	 * 
	 * @return
	 */
	@JsonIgnore
	public String getPassword() {
		Object pass = userData.get(USER_PASS);
		return pass == null ? null : (String) pass;
	}

	public void setStatus(String avalible) {
		userData.put(AVALIBLE, avalible);
	}

	/**
	 * 是否可用
	 * 
	 * @return
	 */
	public String getAvalible() {
		Object ava = userData.get(AVALIBLE);
		return ava == null ? null : (String) ava;
	}

	public void setDeptid(String departmentId) {
		Object dept = userData.get(DEPT);
		if (dept == null) {
			dept = new Dept();
			userData.put(DEPT, dept);
			((Dept) dept).setCode(departmentId);
		} else {
			((Dept) dept).setCode(departmentId);
		}
	}

	/**
	 * 机构编码
	 * 
	 * @return
	 */
	@JsonIgnore
	public String getDeptid() {
		Object dept = userData.get(DEPT);
		return dept == null ? null : ((Dept) dept).getCode();
	}

	public void setDepartment(Dept department) {
		Object dept = userData.get(DEPT);
		if (dept == null) {
			userData.put(DEPT, department);
		} else {
			if (department != null) {
				Dept dpt = (Dept) dept;
				BeanUtils.copyProperties(department, dpt);
			}
		}
	}

	public Set<Role> getRoles() {
		Object roleList = userData.get(ROLE);
		return roleList == null ? null : (Set<Role>) roleList;
	}

	/**
	 * 设置角色
	 * 
	 * @param roles
	 */
	public void setRoles(Set<Role> roles) {
		Object roleList = userData.get(ROLE);
		if (roleList == null) {
			if (CollectionUtils.isNotEmpty(roles)) {
				roleList = new HashSet<Role>(roles);
			} else {
				roleList = new HashSet<Role>();
			}
			userData.put(ROLE, roleList);
		} else {
			if (CollectionUtils.isNotEmpty(roles)) {
				((Set<Role>) roleList).addAll(roles);
			}
		}
	}

	/**
	 * 权限列表
	 * 
	 * @param permissions
	 */
	public Set<Premission> getPermissions(Constant.SystemInfo systemInfo) {
		Object permissionList = userData.get(PERMISSIONS);
		if (permissionList == null)
			return null;
		else {
			Set<Premission> sets = (Set<Premission>) permissionList;
			if (CollectionUtils.isEmpty(sets)) {
				return null;
			} else {
				if (systemInfo == null) {
					return sets;
				} else {
					Set<Premission> systemPremission = Sets.newHashSet();
					for (Premission p : sets) {
						if (StringUtils.isNotBlank(p.getPcodes()) && p.getPcodes().contains(systemInfo.getName())) {
							systemPremission.add(p);
						}
					}
					return systemPremission;
				}
			}

		}
	}

	public void setPermissions(Set<Premission> permissions) {
		Object permissionList = userData.get(PERMISSIONS);
		if (permissionList == null) {
			if (CollectionUtils.isNotEmpty(permissions)) {
				permissionList = new HashSet<Premission>(permissions);
			} else {
				permissionList = new HashSet<Premission>();
			}
			userData.put(PERMISSIONS, permissionList);
		} else {
			if (CollectionUtils.isNotEmpty(permissions)) {
				((Set<Premission>) permissionList).addAll(permissions);
			}
		}
	}

	/**
	 * 是否包含指定角色
	 * 
	 * @param role
	 * @return
	 */
	public boolean hasRole(Role role) {
		boolean has = false;
		Object roleList = userData.get(ROLE);
		if (roleList != null) {
			has = ((Set<Role>) roleList).contains(role);
		}
		return has;
	}

	/**
	 * 追加角色
	 * 
	 * @param roles
	 */
	public void addRole(Role... roles) {
		if (roles != null && roles.length > 0) {
			this.setRoles(Sets.newHashSet(roles));
		}
	}

	/**
	 * 移除角色
	 * 
	 * @param roles
	 */
	public void removeRole(Role... roles) {
		if (roles != null && roles.length > 0) {
			Object roleList = userData.get(ROLE);
			if (roleList != null) {
				((Set<Role>) roleList).removeAll(Lists.newArrayList(roles));
			}
		}
	}

	/**
	 * 清空角色
	 */
	public void clearRole() {
		Object roleList = userData.get(ROLE);
		if (roleList != null) {
			userData.put(ROLE, null);
		}
	}

	/**
	 * 用户所在的机构
	 * 
	 * @return
	 */
	public Dept getDepartment() {
		return (Dept) userData.get(DEPT);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (getAccount() == null) {
			if (other.getAccount() != null)
				return false;
		} else if (!getAccount().equals(other.getAccount()))
			return false;
		return true;
	}

}

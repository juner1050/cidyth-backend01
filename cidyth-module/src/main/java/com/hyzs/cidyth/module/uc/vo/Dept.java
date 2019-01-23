package com.hyzs.cidyth.module.uc.vo;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 机构模型
 * 
 * @author jidw
 *
 */
public class Dept implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6706442198782602793L;
	private static final String DEPT_CODE = "code";
	private static final String SIMPLE_NAME = "simplename";
	private static final String FULL_NAME = "fullname";
	private static final String POLITY_LEVEL = "polityLevel";
	private static final String CATEGORY = "policeType";
	private static final String PARENT_CODE = "pcode";
	private Map<String, Object> data;

	public Dept() {
		data = Maps.newConcurrentMap();
	}
	/**
	 * 构造方法
	 * @param code 机构代码
	 * @param fullname 机构全名
	 */
	public Dept(String code,String fullname){
		this();
		this.setCode(code);
		this.setFullname(fullname);
	}
	public Dept(String code,String fullname,String parentCode){
		this();
		this.setCode(code);
		this.setFullname(fullname);
		this.setPcode(parentCode);
	}
	/**
	 * 机构代码
	 * 
	 * @return
	 */
	public String getCode() {
		Object value = data.get(DEPT_CODE);
		return value == null ? null : (String) value;
	}

	public void setCode(String code) {
		data.put(DEPT_CODE, code);
	}

	/**
	 * 机构名称简称
	 * 
	 * @return
	 */
	public String getSimplename() {
		Object value = data.get(SIMPLE_NAME);
		return value == null ? null : (String) value;
	}

	public void setSimplename(String simplename) {
		data.put(SIMPLE_NAME, simplename);
	}

	/**
	 * 机构名称全称
	 * 
	 * @return
	 */
	public String getFullname() {
		Object value = data.get(FULL_NAME);
		return value == null ? null : (String) value;
	}

	public void setFullname(String fullname) {
		data.put(FULL_NAME, fullname);
	}

	/**
	 * 行政级别
	 * 
	 * @return
	 */
	public Integer getPolityLevel() {
		Object value = data.get(POLITY_LEVEL);
		return value == null ? null : (int) value;
	}

	public void setPolityLevel(int polityLevel) {
		data.put(POLITY_LEVEL, polityLevel);
	}

	/**
	 * 警种
	 * 
	 * @return
	 */
	public Integer getPoliceType() {
		Object value = data.get(CATEGORY);
		return value == null ? null : (int) value;
	}

	public void setPoliceType(int policeType) {
		data.put(CATEGORY, policeType);
	}

	/**
	 * 上级机构代码
	 * 
	 * @return
	 */
	public String getPcode() {
		Object value = data.get(PARENT_CODE);
		return value == null ? null : (String) value;
	}

	public void setPcode(String parentCode) {
		data.put(PARENT_CODE, parentCode);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
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
		Dept other = (Dept) obj;
		if (getCode() == null) {
			if (other.getCode() != null)
				return false;
		} else if (!getCode().equals(other.getCode()))
			return false;
		return true;
	}
	
	
}

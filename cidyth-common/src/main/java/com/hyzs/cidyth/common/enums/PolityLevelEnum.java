package com.hyzs.cidyth.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;


/**
 * 案件来源枚举
 * @author Administrator
 *
 */
public enum PolityLevelEnum {
	CITY("60","市局"),
	BRANCH("10","分局"),
	SUB_BRANCH("20","支队"),
	LARGE_BRANCH("30","大队"),
	POLICE("40","派出所"),
	AREA("50","片区民警");
	
	private final String code;
	private final String descp;
	PolityLevelEnum(String code,String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (PolityLevelEnum item : PolityLevelEnum.values()) {
			names.add(item.name());
		}
		return names;
	}
	/**
	 * 候选的代码
	 * @return
	 */
	public static List<String> codes() {
		List<String> values = new ArrayList<String>();
		for (PolityLevelEnum item : PolityLevelEnum.values()) {
			values.add(item.code());
		}
		return values;
	}
	/**
	 * 代码
	 * @return
	 */
	public String code() {
		return code;
	}
	/**
	 * 描述
	 * @return
	 */
	public String descp() {
		return descp;
	}
	/**
	 * 所有的枚举
	 * @return
	 */
	public static List<PolityLevelEnum> candidates() {
		return Lists.newArrayList(PolityLevelEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static PolityLevelEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (PolityLevelEnum item : PolityLevelEnum.values()) {
				if (item.name().equals(name)) {
					return item;
				}
			}
		}
		return null;
	}
	/**
	 * 根据代码找枚举
	 * @param value
	 * @return
	 */
	public static PolityLevelEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (PolityLevelEnum item : PolityLevelEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(PolityLevelEnum.ofCode("1").name());
		System.out.println(PolityLevelEnum.ofCode("2").name());
		System.out.println(PolityLevelEnum.ofCode("3").name());
	}
}

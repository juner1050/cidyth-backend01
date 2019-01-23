package com.hyzs.cidyth.common.enums;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 案件来源枚举
 * @author Administrator
 *
 */
public enum PoliceTypeEnum {

	NETWORK_INVESTIGATE("10", "网侦"),
	TECH_INVESTIGATE("20", "技侦"),
	VIDEO_INVESTIGATE("30", "视侦"),
	INFO_INVESTIGATE("40", "情报"),
	CRIMINAL_INVESTIGATE("50", "刑侦"),
	CRIMINAL_TECH_INVESTIGATE("60", "刑技"),
	POLICE_INVESTIGATE("70", "派出所"),
	LAB_INVESTIGATE("80", "警企实验室");

	private final String code;
	private final String descp;

	PoliceTypeEnum(String code, String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (PoliceTypeEnum item : PoliceTypeEnum.values()) {
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
		for (PoliceTypeEnum item : PoliceTypeEnum.values()) {
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
	public static List<PoliceTypeEnum> candidates() {
		return Lists.newArrayList(PoliceTypeEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static PoliceTypeEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (PoliceTypeEnum item : PoliceTypeEnum.values()) {
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
	public static PoliceTypeEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (PoliceTypeEnum item : PoliceTypeEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(PoliceTypeEnum.ofCode("1").name());
		System.out.println(PoliceTypeEnum.ofCode("2").name());
		System.out.println(PoliceTypeEnum.ofCode("3").name());
	}
}

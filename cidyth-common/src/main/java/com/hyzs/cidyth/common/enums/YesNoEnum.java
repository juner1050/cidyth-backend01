package com.hyzs.cidyth.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;


/**
 * 是否枚举
 * @author Administrator
 *
 */
public enum YesNoEnum {
	YES("1","是"),
	NO("0","否");
	
	private final String code;
	private final String descp;
	YesNoEnum(String code,String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (YesNoEnum item : YesNoEnum.values()) {
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
		for (YesNoEnum item : YesNoEnum.values()) {
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
	public static List<YesNoEnum> candidates() {
		return Lists.newArrayList(YesNoEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static YesNoEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (YesNoEnum item : YesNoEnum.values()) {
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
	public static YesNoEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (YesNoEnum item : YesNoEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(YesNoEnum.ofCode("1").name());
		System.out.println(YesNoEnum.ofCode("2").name());
		System.out.println(YesNoEnum.ofCode("3").name());
	}
}

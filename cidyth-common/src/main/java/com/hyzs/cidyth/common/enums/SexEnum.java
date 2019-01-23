package com.hyzs.cidyth.common.enums;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 性别枚举
 * @author Administrator
 *
 */
public enum SexEnum {
	MALE("1","男"),
	FEMALE("2","女");

	private final String code;
	private final String descp;
	SexEnum(String code, String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (SexEnum item : SexEnum.values()) {
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
		for (SexEnum item : SexEnum.values()) {
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
	public static List<SexEnum> candidates() {
		return Lists.newArrayList(SexEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static SexEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (SexEnum item : SexEnum.values()) {
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
	public static SexEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (SexEnum item : SexEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(SexEnum.ofCode("1").name());
		System.out.println(SexEnum.ofCode("2").name());
		System.out.println(SexEnum.ofCode("3").name());
	}
}

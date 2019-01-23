package com.hyzs.cidyth.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;


/**
 * 线索评价分值名称枚举
 * @author Administrator
 *
 */
public enum ClueAccptEnum {
	LEVEL_5("5","优"),
	LEVEL_4("4","良"),
	LEVEL_3("3","好"),
	LEVEL_2("2","一般"),
	LEVEL_1("1","差");
	
	private final String code;
	private final String descp;
	ClueAccptEnum(String code,String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (ClueAccptEnum item : ClueAccptEnum.values()) {
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
		for (ClueAccptEnum item : ClueAccptEnum.values()) {
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
	public static List<ClueAccptEnum> candidates() {
		return Lists.newArrayList(ClueAccptEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static ClueAccptEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (ClueAccptEnum item : ClueAccptEnum.values()) {
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
	public static ClueAccptEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (ClueAccptEnum item : ClueAccptEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(ClueAccptEnum.ofCode("1").name());
		System.out.println(ClueAccptEnum.ofCode("2").name());
		System.out.println(ClueAccptEnum.ofCode("3").name());
		
		
	}
}

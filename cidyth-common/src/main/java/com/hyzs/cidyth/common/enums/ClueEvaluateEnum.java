package com.hyzs.cidyth.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;


/**
 * 线索评价枚举
 * @author Administrator
 *
 */
public enum ClueEvaluateEnum {
	NOT_EVALUATE("0","未评分"),
	EVALUATE("1","已评分");
	
	private final String code;
	private final String descp;
	ClueEvaluateEnum(String code,String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (ClueEvaluateEnum item : ClueEvaluateEnum.values()) {
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
		for (ClueEvaluateEnum item : ClueEvaluateEnum.values()) {
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
	public static List<ClueEvaluateEnum> candidates() {
		return Lists.newArrayList(ClueEvaluateEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static ClueEvaluateEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (ClueEvaluateEnum item : ClueEvaluateEnum.values()) {
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
	public static ClueEvaluateEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (ClueEvaluateEnum item : ClueEvaluateEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(ClueEvaluateEnum.ofCode("1").name());
		System.out.println(ClueEvaluateEnum.ofCode("2").name());
		System.out.println(ClueEvaluateEnum.ofCode("3").name());
		
		
	}
}

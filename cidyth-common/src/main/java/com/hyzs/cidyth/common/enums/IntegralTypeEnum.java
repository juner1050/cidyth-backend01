package com.hyzs.cidyth.common.enums;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 积分类型枚举
 * @author Administrator
 *
 */
public enum IntegralTypeEnum {
	FIXED_VALUE("0","定值积分"),
	NO_FIXED_VALUE("1","非定值积分");

	private final String code;
	private final String descp;
	IntegralTypeEnum(String code, String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (IntegralTypeEnum item : IntegralTypeEnum.values()) {
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
		for (IntegralTypeEnum item : IntegralTypeEnum.values()) {
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
	public static List<IntegralTypeEnum> candidates() {
		return Lists.newArrayList(IntegralTypeEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static IntegralTypeEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (IntegralTypeEnum item : IntegralTypeEnum.values()) {
				if (item.name().equals(name)) {
					return item;
				}
			}
		}
		return null;
	}
	/**
	 * 根据代码找枚举
	 * @param code
	 * @return
	 */
	public static IntegralTypeEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (IntegralTypeEnum item : IntegralTypeEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(IntegralTypeEnum.ofCode("1").name());
		System.out.println(IntegralTypeEnum.ofCode("2").name());
		System.out.println(IntegralTypeEnum.ofCode("3").name());
	}
}

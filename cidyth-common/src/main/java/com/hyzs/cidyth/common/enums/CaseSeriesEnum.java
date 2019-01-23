package com.hyzs.cidyth.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;


/**
 * 案件串并状态枚举
 * @author Administrator
 *
 */
public enum CaseSeriesEnum {
	SERIES("1","是"),
	NOT_SERIES("0","否");
	
	private final String code;
	private final String descp;
	CaseSeriesEnum(String code,String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (CaseSeriesEnum item : CaseSeriesEnum.values()) {
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
		for (CaseSeriesEnum item : CaseSeriesEnum.values()) {
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
	public static List<CaseSeriesEnum> candidates() {
		return Lists.newArrayList(CaseSeriesEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static CaseSeriesEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (CaseSeriesEnum item : CaseSeriesEnum.values()) {
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
	public static CaseSeriesEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (CaseSeriesEnum item : CaseSeriesEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(CaseSeriesEnum.ofCode("1").name());
		System.out.println(CaseSeriesEnum.ofCode("2").name());
		System.out.println(CaseSeriesEnum.ofCode("3").name());
	}
}

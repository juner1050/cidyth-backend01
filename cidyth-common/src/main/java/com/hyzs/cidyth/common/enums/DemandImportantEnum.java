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
public enum DemandImportantEnum {
	HIGH("1","高"),
	MIDDLE("2","中"),
	LOW("3","低");
	
	private final String code;
	private final String descp;
	DemandImportantEnum(String code,String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (DemandImportantEnum item : DemandImportantEnum.values()) {
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
		for (DemandImportantEnum item : DemandImportantEnum.values()) {
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
	public static List<DemandImportantEnum> candidates() {
		return Lists.newArrayList(DemandImportantEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static DemandImportantEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (DemandImportantEnum item : DemandImportantEnum.values()) {
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
	public static DemandImportantEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (DemandImportantEnum item : DemandImportantEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(DemandImportantEnum.ofCode("1").name());
		System.out.println(DemandImportantEnum.ofCode("2").name());
		System.out.println(DemandImportantEnum.ofCode("3").name());
	}
}

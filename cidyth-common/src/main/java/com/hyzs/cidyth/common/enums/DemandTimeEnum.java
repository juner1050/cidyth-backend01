package com.hyzs.cidyth.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;


/**
 * 案件时间段状态枚举
 * @author Administrator
 *
 */
public enum DemandTimeEnum {
	CUSTOM("0","自定义时间段"),
	THREE_DAY("1","3天内"),
	THIRTY_DAY("2","30天内");
	
	private final String code;
	private final String descp;
	DemandTimeEnum(String code,String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (DemandTimeEnum item : DemandTimeEnum.values()) {
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
		for (DemandTimeEnum item : DemandTimeEnum.values()) {
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
	public static List<DemandTimeEnum> candidates() {
		return Lists.newArrayList(DemandTimeEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static DemandTimeEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (DemandTimeEnum item : DemandTimeEnum.values()) {
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
	public static DemandTimeEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (DemandTimeEnum item : DemandTimeEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		
	}
}

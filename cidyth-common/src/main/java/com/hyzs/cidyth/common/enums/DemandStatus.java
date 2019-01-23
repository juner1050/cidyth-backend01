package com.hyzs.cidyth.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

/**
 * 需求状态枚举
 * @author jidw
 *
 */
public enum DemandStatus {
	INIT("0","待指派"),ALLOCATED("10","已指派"),WAIT_FOR_SIGN("20","待签收"),RETREAT("25","已退回"),SIGNED("30","已签收"),FEEDBACKED("40","已反馈"),DELAY("50","已延期");

	private final String code;
	private final String descp;
	DemandStatus(String code,String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (DemandStatus item : DemandStatus.values()) {
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
		for (DemandStatus item : DemandStatus.values()) {
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
	public static List<DemandStatus> candidates() {
		return Lists.newArrayList(DemandStatus.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static DemandStatus ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (DemandStatus item : DemandStatus.values()) {
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
	public static DemandStatus ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (DemandStatus item : DemandStatus.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(DemandStatus.names());
		System.out.println(DemandStatus.codes());
		System.out.println(DemandStatus.candidates());
		
		System.out.println(DemandStatus.ofName("SIGNED").code());
		System.out.println(DemandStatus.ofCode("2").name());
	}
}

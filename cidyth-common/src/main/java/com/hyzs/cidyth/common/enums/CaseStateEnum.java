package com.hyzs.cidyth.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;


/**
 * 案件本地状态枚举
 * @author Administrator
 *
 */
public enum CaseStateEnum {
	EXECUTE("1","侦办中"),
	FINISH("2","已侦结"),
	ABORT("3","挂起");
	
	private final String code;
	private final String descp;
	CaseStateEnum(String code,String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (CaseStateEnum item : CaseStateEnum.values()) {
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
		for (CaseStateEnum item : CaseStateEnum.values()) {
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
	public static List<CaseStateEnum> candidates() {
		return Lists.newArrayList(CaseStateEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static CaseStateEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (CaseStateEnum item : CaseStateEnum.values()) {
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
	public static CaseStateEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (CaseStateEnum item : CaseStateEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(CaseStateEnum.ofCode("1").name());
		System.out.println(CaseStateEnum.ofCode("2").name());
		System.out.println(CaseStateEnum.ofCode("3").name());
	}
}

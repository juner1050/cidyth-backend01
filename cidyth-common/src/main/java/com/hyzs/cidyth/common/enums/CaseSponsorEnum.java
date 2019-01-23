package com.hyzs.cidyth.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;


/**
 * 案件主办枚举
 * @author Administrator
 *
 */
public enum CaseSponsorEnum {
	CASE_SELF("1","我主办"),
	CASE_ASSIST("2","我协办"),
	CASE_PICK("3","我提取"),
	CASE_PARTNER("4","我合成作战");
	
	private final String code;
	private final String descp;
	CaseSponsorEnum(String code,String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (CaseSponsorEnum item : CaseSponsorEnum.values()) {
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
		for (CaseSponsorEnum item : CaseSponsorEnum.values()) {
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
	public static List<CaseSponsorEnum> candidates() {
		return Lists.newArrayList(CaseSponsorEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static CaseSponsorEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (CaseSponsorEnum item : CaseSponsorEnum.values()) {
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
	public static CaseSponsorEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (CaseSponsorEnum item : CaseSponsorEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(CaseSponsorEnum.ofCode("1").name());
		System.out.println(CaseSponsorEnum.ofCode("2").name());
		System.out.println(CaseSponsorEnum.ofCode("3").name());
	}
}

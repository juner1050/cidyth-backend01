package com.hyzs.cidyth.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

/**
 * 线索类型状态枚举
 * @author Administrator
 *
 */
public enum ClueTypeEnum {
	UPLOAD("0","上传线索"),RETURN("1","反馈线索");
	private final String code;
	private final String descp;
	ClueTypeEnum(String code,String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (ClueTypeEnum item : ClueTypeEnum.values()) {
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
		for (ClueTypeEnum item : ClueTypeEnum.values()) {
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
	public static List<ClueTypeEnum> candidates() {
		return Lists.newArrayList(ClueTypeEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static ClueTypeEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (ClueTypeEnum item : ClueTypeEnum.values()) {
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
	public static ClueTypeEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (ClueTypeEnum item : ClueTypeEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(ClueTypeEnum.names());
		System.out.println(ClueTypeEnum.codes());
		System.out.println(ClueTypeEnum.candidates());
		
		System.out.println(ClueTypeEnum.ofCode("1").name());
		System.out.println(ClueTypeEnum.UPLOAD.code);
	}
}

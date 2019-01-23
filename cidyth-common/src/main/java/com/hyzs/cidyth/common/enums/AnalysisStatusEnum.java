package com.hyzs.cidyth.common.enums;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * 分析线索-办理状态
 * @author Administrator
 *
 */
public enum AnalysisStatusEnum {
	NOT_HANDLER(10, "未办理"),
	HANDLER(20, "办理中"),
	FINISH(30, "已完成");

	private final Integer code;
	private final String descp;
	AnalysisStatusEnum(Integer code, String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (AnalysisStatusEnum item : AnalysisStatusEnum.values()) {
			names.add(item.name());
		}
		return names;
	}
	/**
	 * 候选的代码
	 * @return
	 */
	public static List<Integer> codes() {
		List<Integer> values = new ArrayList<Integer>();
		for (AnalysisStatusEnum item : AnalysisStatusEnum.values()) {
			values.add(item.code());
		}
		return values;
	}
	/**
	 * 代码
	 * @return
	 */
	public Integer code() {
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
	public static List<AnalysisStatusEnum> candidates() {
		return Lists.newArrayList(AnalysisStatusEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static AnalysisStatusEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (AnalysisStatusEnum item : AnalysisStatusEnum.values()) {
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
	public static AnalysisStatusEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (AnalysisStatusEnum item : AnalysisStatusEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Float a = 30.2342f;
		Float b = 54.3123f;
		System.out.println(new DecimalFormat("#.00").format((a / b * 100)));
		String fileName = "D:\\a\\b\\c\\aaa.exe";
		System.out.println("suffixName = " + fileName.lastIndexOf("\\"));
		System.out.println("suffixName = " + fileName.substring(fileName.lastIndexOf("\\")));
	}
}

package com.hyzs.cidyth.common.enums;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * 分析线索-节点状态
 * @author Administrator
 *
 */
public enum AnalysisNodeEnum {
	WAIT_SEND(10, "待发送"),
	WAIT_ALLOCATE(20, "待指派"),
	WAIT_SIGN(30, "待签收"),
	SIGNED(40, "已签收"),
	RETREAT(50, "已退回"),
	FINISH(60, "已完成");

	private final Integer code;
	private final String descp;
	AnalysisNodeEnum(Integer code, String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (AnalysisNodeEnum item : AnalysisNodeEnum.values()) {
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
		for (AnalysisNodeEnum item : AnalysisNodeEnum.values()) {
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
	public static List<AnalysisNodeEnum> candidates() {
		return Lists.newArrayList(AnalysisNodeEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static AnalysisNodeEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (AnalysisNodeEnum item : AnalysisNodeEnum.values()) {
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
	public static AnalysisNodeEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (AnalysisNodeEnum item : AnalysisNodeEnum.values()) {
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

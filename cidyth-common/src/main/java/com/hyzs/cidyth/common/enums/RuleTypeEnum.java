package com.hyzs.cidyth.common.enums;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 积分规则类型枚举
 * @author Administrator
 *
 */
public enum RuleTypeEnum {
	CASES_PICK("10","案件提取"),
	CASES_CREATE("20","案件创建"),
	ALARM_PICK("30","警情提取"),
	DEMAND("40","创建需求"),
	INFO("50","发布信息"),
	RETURN_CLUE("60","反馈线索"),
	LEADER_REWARD("70","领导赏分"),
	CASES_FINISH("80","破案"),
	CASES_MERGE("90","串并案"),
	MAIN_CLUE("95","关键线索"),
	SUSPECT_VERIFY("100","查实嫌疑人电子信息"),
	SUSPECT_LOCK("110","锁定嫌疑人"),
	SUSPECT_CAPTURE("120","抓获嫌疑人"),
	SUSPECT_CAPTURE_HELP("130","协助抓获嫌疑人"),
	IMPORTANT_CLUE("140","重要线索");

	private final String code;
	private final String descp;
	RuleTypeEnum(String code, String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (RuleTypeEnum item : RuleTypeEnum.values()) {
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
		for (RuleTypeEnum item : RuleTypeEnum.values()) {
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
	public static List<RuleTypeEnum> candidates() {
		return Lists.newArrayList(RuleTypeEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static RuleTypeEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (RuleTypeEnum item : RuleTypeEnum.values()) {
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
	public static RuleTypeEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (RuleTypeEnum item : RuleTypeEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println("0f = " + 0f);
	}
}

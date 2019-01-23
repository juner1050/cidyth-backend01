package com.hyzs.cidyth.common.enums;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 附件类型枚举
 * @author Administrator
 *
 */
public enum TimeNodeEnum {
	CASE("0","案件"),
	CASE_CREATE("5","案件创建"),
	CASE_PICK("10","案件提取"),
	SCENE_SURVEY("20","现场勘验"),
	SCENE_BIOLOGY("30","生物痕迹"),
	SCENE_FOOT("40","足迹痕迹"),
	SCENE_FINGER("50","手印痕迹"),
	COMPARISON_INFO("60","比中信息"),
	CASE_SUSPECT("70","确定嫌疑人"),
	DEMAND("80","发布需求"),
	INFO("90","发布信息"),
	CLUE("100","反馈线索"),
	REPLY("110","回复信息"),
	CASE_RECORD("120","笔录信息"),
	CASE_MERGE("150","案件串并"),
	CASE_ABORT("160","案件挂起"),
	CASE_FINISH("170","案件侦结");

	private final String code;
	private final String descp;
	TimeNodeEnum(String code, String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (TimeNodeEnum item : TimeNodeEnum.values()) {
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
		for (TimeNodeEnum item : TimeNodeEnum.values()) {
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
	public static List<TimeNodeEnum> candidates() {
		return Lists.newArrayList(TimeNodeEnum.values());
	}

	/**
	 * 获取枚举键值对（name-value）
	 * @return
	 */
	public static Map<String, Object> toMap(){
		Map<String, Object> resultMap = Maps.newHashMap();
		TimeNodeEnum.candidates().stream().forEach(i ->
				resultMap.put(i.name(), i.descp())
		);
		return resultMap;
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static TimeNodeEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (TimeNodeEnum item : TimeNodeEnum.values()) {
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
	public static TimeNodeEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (TimeNodeEnum item : TimeNodeEnum.values()) {
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

package com.hyzs.cidyth.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

/**
 * 嫌疑人信息分类
 * 
 * @author derrick
 *
 */
public enum SuspectsInformationCategory {
	CRIMINAL_HIS("CRIMINAL_HIS", "犯罪前科"), EXPRESS("EXPRESS", "快递信息"), RELATED_PSN("RELATED_PSN", "关系人"), RENTALS(
			"RENTALS",
			"出租屋信息"), HOTEL("HOTEL", "旅馆信息"), EXIT_ENTRY("EXIT_ENTRY", "出入境信息"), TRAVEL_TRACK("TRAVEL_TRACK", "出行轨迹");

	private final String code;
	private final String descp;

	SuspectsInformationCategory(String code, String desc) {
		this.code = code;
		this.descp = desc;
	}

	public String code() {
		return code;
	}

	public String descp() {
		return descp;
	}

	/**
	 * 候选的名字
	 * 
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (SuspectsInformationCategory item : SuspectsInformationCategory.values()) {
			names.add(item.name());
		}
		return names;
	}

	/**
	 * 候选的代码
	 * 
	 * @return
	 */
	public static List<String> codes() {
		List<String> values = new ArrayList<String>();
		for (SuspectsInformationCategory item : SuspectsInformationCategory.values()) {
			values.add(item.code());
		}
		return values;
	}

	/**
	 * 所有的枚举
	 * 
	 * @return
	 */
	public static List<SuspectsInformationCategory> candidates() {
		return Lists.newArrayList(SuspectsInformationCategory.values());
	}

	/**
	 * 根据名字找枚举
	 * 
	 * @param name
	 * @return
	 */
	public static SuspectsInformationCategory ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (SuspectsInformationCategory item : SuspectsInformationCategory.values()) {
				if (item.name().equals(name)) {
					return item;
				}
			}
		}
		return null;
	}

	/**
	 * 根据代码找枚举
	 * 
	 * @param value
	 * @return
	 */
	public static SuspectsInformationCategory ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (SuspectsInformationCategory item : SuspectsInformationCategory.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}
}

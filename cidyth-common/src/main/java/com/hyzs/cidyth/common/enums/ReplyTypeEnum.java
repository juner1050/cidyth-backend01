package com.hyzs.cidyth.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

/**
 * 回复种类
 * 
 * @author jidw
 *
 */
public enum ReplyTypeEnum {
	DEMAND("需求的回复"), CLUE("线索的回复"), INFO("信息的回复"), RESP("回复的回复");
	private final String descp;

	ReplyTypeEnum(String descp) {
		this.descp = descp;
	}

	/**
	 * 候选的名字
	 * 
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (ReplyTypeEnum item : ReplyTypeEnum.values()) {
			names.add(item.name());
		}
		return names;
	}

	/**
	 * 描述
	 * 
	 * @return
	 */
	public String descp() {
		return descp;
	}

	/**
	 * 所有的枚举
	 * 
	 * @return
	 */
	public static List<ReplyTypeEnum> candidates() {
		return Lists.newArrayList(ReplyTypeEnum.values());
	}

	/**
	 * 根据名字找枚举
	 * 
	 * @param name
	 * @return
	 */
	public static ReplyTypeEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (ReplyTypeEnum item : ReplyTypeEnum.values()) {
				if (item.name().equals(name)) {
					return item;
				}
			}
		}
		return null;
	}
}

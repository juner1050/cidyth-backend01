package com.hyzs.cidyth.common.enums;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 点赞种类
 * 
 * @author jidw
 *
 */
public enum ApproveTypeEnum {
	CLUE("线索的点赞"), INFO("信息的点赞");
	private final String descp;

	ApproveTypeEnum(String descp) {
		this.descp = descp;
	}

	/**
	 * 候选的名字
	 * 
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (ApproveTypeEnum item : ApproveTypeEnum.values()) {
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
	public static List<ApproveTypeEnum> candidates() {
		return Lists.newArrayList(ApproveTypeEnum.values());
	}

	/**
	 * 根据名字找枚举
	 * 
	 * @param name
	 * @return
	 */
	public static ApproveTypeEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (ApproveTypeEnum item : ApproveTypeEnum.values()) {
				if (item.name().equals(name)) {
					return item;
				}
			}
		}
		return null;
	}
}

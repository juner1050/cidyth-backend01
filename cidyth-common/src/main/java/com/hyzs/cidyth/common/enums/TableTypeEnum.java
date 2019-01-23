package com.hyzs.cidyth.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;


/**
 * 附件类型枚举
 * @author Administrator
 *
 */
public enum TableTypeEnum {
	CASE("0","案件"),
	DEMAND("1","需求"),
	INFO("2","信息"),
	UPLOAD_CLUE("3","上传线索"),
	RETURN_CLUE("4","反馈线索"),
	REPLY("5","回复"),
	INTEGRAL_TITLE_AVATAR("6","头衔图片");
	
	private final String code;
	private final String descp;
	TableTypeEnum(String code,String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (TableTypeEnum item : TableTypeEnum.values()) {
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
		for (TableTypeEnum item : TableTypeEnum.values()) {
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
	public static List<TableTypeEnum> candidates() {
		return Lists.newArrayList(TableTypeEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static TableTypeEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (TableTypeEnum item : TableTypeEnum.values()) {
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
	public static TableTypeEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (TableTypeEnum item : TableTypeEnum.values()) {
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

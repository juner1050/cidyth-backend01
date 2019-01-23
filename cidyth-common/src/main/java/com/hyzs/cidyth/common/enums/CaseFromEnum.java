package com.hyzs.cidyth.common.enums;

import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * 案件来源枚举
 * @author Administrator
 *
 */
public enum CaseFromEnum {
	CREATE("1","手动创建"),
	PACK("2","警综提取");

	private final String code;
	private final String descp;
	CaseFromEnum(String code,String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (CaseFromEnum item : CaseFromEnum.values()) {
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
		for (CaseFromEnum item : CaseFromEnum.values()) {
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
	public static List<CaseFromEnum> candidates() {
		return Lists.newArrayList(CaseFromEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static CaseFromEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (CaseFromEnum item : CaseFromEnum.values()) {
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
	public static CaseFromEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (CaseFromEnum item : CaseFromEnum.values()) {
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

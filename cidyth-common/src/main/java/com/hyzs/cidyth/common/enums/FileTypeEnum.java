package com.hyzs.cidyth.common.enums;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


/**
 * 文件类型枚举
 * @author Administrator
 *
 */
public enum FileTypeEnum {
	EXAMINATION_BOOK("1","审批文书"),
	LAW_BOOK("2","法律文书"),
	EVIDENCE_MATERIAL("3","证据材料"),
	FILE_OTHER("4","其他");
	private final String code;
	private final String descp;
	FileTypeEnum(String code, String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (FileTypeEnum item : FileTypeEnum.values()) {
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
		for (FileTypeEnum item : FileTypeEnum.values()) {
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
	public static List<FileTypeEnum> candidates() {
		return Lists.newArrayList(FileTypeEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static FileTypeEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (FileTypeEnum item : FileTypeEnum.values()) {
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
	public static FileTypeEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (FileTypeEnum item : FileTypeEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println("args = " + LocalTime.now().isBefore(LocalTime.of(18, 00, 00)));
		System.out.println(LocalTime.of(11, 45, 12));
		System.out.println(LocalTime.of(18, 00, 00));
		System.out.println(LocalTime.of(18, 45, 12));
		System.out.println(LocalTime.now());
		System.out.println(LocalTime.now().isBefore(LocalTime.of(11, 5)));
		System.out.println(LocalTime.now().isBefore(LocalTime.of(11, 7)));

	}
}

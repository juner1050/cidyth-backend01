package com.hyzs.cidyth.common.enums;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 现场图片枚举
 * @author Administrator
 *
 */
public enum SceneImageTypeEnum {
	SCENE_IMG("1","现场图"),
	SCENE_PIC("2","现场照片"),
	SCENE_FINGER("3","指纹"),
	SCENE_FOOT("4","足迹"),
	SCENE_TOOL("5","工具"),
	SCENE_BIOLOGY("8","生物");

	private final String code;
	private final String descp;
	SceneImageTypeEnum(String code, String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (SceneImageTypeEnum item : SceneImageTypeEnum.values()) {
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
		for (SceneImageTypeEnum item : SceneImageTypeEnum.values()) {
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
	public static List<SceneImageTypeEnum> candidates() {
		return Lists.newArrayList(SceneImageTypeEnum.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static SceneImageTypeEnum ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (SceneImageTypeEnum item : SceneImageTypeEnum.values()) {
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
	public static SceneImageTypeEnum ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (SceneImageTypeEnum item : SceneImageTypeEnum.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(SceneImageTypeEnum.ofCode("1").name());
		System.out.println(SceneImageTypeEnum.ofCode("2").name());
		System.out.println(SceneImageTypeEnum.ofCode("3").name());
	}
}

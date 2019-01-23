package com.hyzs.cidyth.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

public enum RoleEnum {
	
	CID_LEADER_PERSON("cid_leader_person","领导指派"),
	CID_INFO_PERSON("cid_info_person","情报分析师"),
	CID_TECH_PERSON("cid_tech_person","技术分析师"),
	CID_COMPOSE_PERSON("cid_compose_person","融合作战人员"),
	CID_INVERSTIGATE_PERSON("cid_inverstigate_person","主办侦查员"),
	CID_CITY_LEADER("cid_city_leader","市局领导"),
	CID_BRANCH_LEADER("cid_branch_leader","分局领导"),
	CID_POLICE_LEADER("cid_police_leader","派出所领导");
	private final String code;
	private final String descp;
	RoleEnum(String code,String desc){
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

	}
}

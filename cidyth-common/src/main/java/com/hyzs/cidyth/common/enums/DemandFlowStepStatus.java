package com.hyzs.cidyth.common.enums;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

/**
 * 需求操作步骤的节点状态
 * @author jidw
 *
 */
public enum DemandFlowStepStatus {
	INIT("0","待指派"),ALLOCATED("10","已指派"),WAIT_FOR_SIGN("20","待签收"),RETREAT("25","已退回"),SIGNED("30","已签收"),FEEDBACKED("40","已反馈"),DELAY("50","已延期");
	private final String code;
	private final String descp;
	DemandFlowStepStatus(String code,String desc){
		this.code = code;
		this.descp = desc;
	}
	/**
	 * 候选的名字
	 * @return
	 */
	public static List<String> names() {
		List<String> names = new ArrayList<String>();
		for (DemandFlowStepStatus item : DemandFlowStepStatus.values()) {
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
		for (DemandFlowStepStatus item : DemandFlowStepStatus.values()) {
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
	public static List<DemandFlowStepStatus> candidates() {
		return Lists.newArrayList(DemandFlowStepStatus.values());
	}
	/**
	 * 根据名字找枚举
	 * @param name
	 * @return
	 */
	public static DemandFlowStepStatus ofName(String name) {
		if (StringUtils.isNotBlank(name)) {
			for (DemandFlowStepStatus item : DemandFlowStepStatus.values()) {
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
	public static DemandFlowStepStatus ofCode(String code) {
		if (StringUtils.isNotBlank(code)) {
			for (DemandFlowStepStatus item : DemandFlowStepStatus.values()) {
				if (item.code().equals(code)) {
					return item;
				}
			}
		}
		return null;
	}

}

package com.hyzs.cidyth.common.enums;
/**
 * 需求处理动作枚举
 * @author jidw
 *
 */
public enum DemandProcessAction {
	ALLOCATE("demand_allocate","指派"),SIGN("","签收");
	private final String premissionCode;//权限code
	private final String description;
	DemandProcessAction(String premissionCode,String descp){
		this.premissionCode = premissionCode;
		this.description = descp;
	}
	
	public String getPremissionCode() {
		return premissionCode;
	}

	public String getDescription() {
		return description;
	}
	
	public static void main(String[] args) {
		System.out.println(DemandProcessAction.ALLOCATE.name());
	}
}

package com.hyzs.cidyth.common.enums;
/**
 * 系统菜单枚举
 * @author jidw
 *
 */
public enum SystemMenuEnum {

	DEMAND_ALLOCATE("demand_allocate","需求指派"),
	ANALYSIS_ALLOCATE("analysis_allocate","研判线索指派");

	private final String premissionCode;//权限code
	private final String description;
	SystemMenuEnum(String premissionCode, String descp){
		this.premissionCode = premissionCode;
		this.description = descp;
	}
	
	public String getPremissionCode() {
		return premissionCode;
	}

	public String getDescription() {
		return description;
	}


}

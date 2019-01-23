package com.hyzs.cidyth.common.utils;

import org.apache.commons.lang3.StringUtils;

import com.hyzs.cidyth.common.enums.PolityLevelEnum;

public class DepartmentUtil {

	/**
	 * 获取当前用户能根据多少位机构编号查询
	 * @param polityLevel 机构级别
	 * @param deptCode 机构编号
	 * @return 返回部分机构编号
	 */
	public static String getSectionDeptCode(Integer polityLevel, String deptCode){
		if(polityLevel == null || StringUtils.isEmpty(deptCode)){
			return null;
		}else{
			if(Integer.valueOf(PolityLevelEnum.CITY.code()).equals(polityLevel)){// 市局
				return null;
			}else if(Integer.valueOf(PolityLevelEnum.BRANCH.code()).equals(polityLevel)){// 分局
				return deptCode.substring(0, 6);
			}else if(Integer.valueOf(PolityLevelEnum.SUB_BRANCH.code()).equals(polityLevel)){// 支队
				return deptCode.substring(0, 6);
			}else if(Integer.valueOf(PolityLevelEnum.LARGE_BRANCH.code()).equals(polityLevel)){// 大队
				return deptCode.substring(0, 6);
			}else if(Integer.valueOf(PolityLevelEnum.POLICE.code()).equals(polityLevel)){// 派出所
				return deptCode;
			}else if(Integer.valueOf(PolityLevelEnum.AREA.code()).equals(polityLevel)){// 片区民警
				return deptCode;
			}else{
				return deptCode;
			}
		}
	}

	/**
	 * 获取当前用户访问考拉的用户级别
	 * @param polityLevel 机构级别
	 * @param deptCode 机构编号
	 * @return
	 */
	public static String getUserLevel(Integer polityLevel, String deptCode){
		if(polityLevel == null || StringUtils.isEmpty(deptCode)){
			return null;
		}else{
			if(Integer.valueOf(PolityLevelEnum.CITY.code()).equals(polityLevel)){// 市局
				return "1";
			}else if(Integer.valueOf(PolityLevelEnum.BRANCH.code()).equals(polityLevel)){// 分局
				return "2";
			}else if(Integer.valueOf(PolityLevelEnum.SUB_BRANCH.code()).equals(polityLevel)){// 支队
				return "3";
			}else if(Integer.valueOf(PolityLevelEnum.LARGE_BRANCH.code()).equals(polityLevel)){// 大队
				return "3";
			}else if(Integer.valueOf(PolityLevelEnum.POLICE.code()).equals(polityLevel)){// 派出所
				return "4";
			}else if(Integer.valueOf(PolityLevelEnum.AREA.code()).equals(polityLevel)){// 片区民警
				return "4";
			}else{
				return "4";
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(getSectionDeptCode(60, "440306090000"));
		System.out.println(getSectionDeptCode(50, "440306090000"));
		System.out.println(getSectionDeptCode(40, "440306090000"));
		System.out.println(getSectionDeptCode(10, "440306090000"));
	}
}

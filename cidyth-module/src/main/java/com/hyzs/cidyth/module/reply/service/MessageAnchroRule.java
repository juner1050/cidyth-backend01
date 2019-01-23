package com.hyzs.cidyth.module.reply.service;

import org.apache.commons.lang3.StringUtils;

import com.hyzs.cidyth.common.enums.ReplyTypeEnum;
/**
 * 消息定位锚点生成规则
 * @author derrick
 *
 */
public class MessageAnchroRule {
	/**
	 * 
	 * @param ajbh 案件编号
	 * @param demandId 需求id
	 * @param clueId 线索id
	 * @return
	 */
	public static String buildForClue(String ajbh, String demandId, String clueId) {
		StringBuilder strb = new StringBuilder();
		strb.append(StringUtils.isNotBlank(ajbh) ? ajbh : "");// 案件编号
		strb.append("_");
		strb.append(StringUtils.isNotBlank(demandId) ? demandId : "");// 需求编号
		strb.append("_");
		strb.append(ReplyTypeEnum.CLUE);
		strb.append("_");
		strb.append(StringUtils.isNotBlank(clueId) ? clueId : "");// 线索编号
		return strb.toString();
	}

	/**
	 * 
	 * @param ajbh
	 *            案件编号
	 * @param demandId
	 *            需求id
	 * @return
	 */
	public static String buildForDemand(String ajbh, String demandId) {
		StringBuilder strb = new StringBuilder();
		strb.append(StringUtils.isNotBlank(ajbh) ? ajbh : "");// 案件编号
		strb.append("_");
		strb.append(StringUtils.isNotBlank(demandId) ? demandId : "");// 需求编号
		strb.append("_");
		strb.append(ReplyTypeEnum.DEMAND);
		return strb.toString();
	}
	/**
	 * 
	 * @param ajbh 案件编号
	 * @param infoId 信息id
	 * @return
	 */
	public static String buildForInfo(String ajbh, String infoId) {
		StringBuilder strb = new StringBuilder();
		strb.append(StringUtils.isNotBlank(ajbh) ? ajbh : "");// 案件编号
		strb.append("_");
		strb.append(StringUtils.isNotBlank(infoId) ? infoId : "");
		strb.append("_");
		strb.append(ReplyTypeEnum.INFO);
		return strb.toString();
	}

}

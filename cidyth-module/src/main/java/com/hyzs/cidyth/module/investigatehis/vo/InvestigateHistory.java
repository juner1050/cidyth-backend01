package com.hyzs.cidyth.module.investigatehis.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="侦查历史")
public class InvestigateHistory implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 案件编号
	 */
	@ApiModelProperty(value = "案件编号")
	private String caseNo;
	/**
	 * 嫌疑人身份标识
	 */
	@ApiModelProperty(value = "嫌疑人身份标识(身份证)")
	private String suspectId;
	/**
	 * 嫌疑人信息分类
	 */
	@ApiModelProperty(value = "嫌疑人信息分类", allowableValues = "CRIMINAL_HIS,EXPRESS,RELATED_PSN,RENTALS,HOTEL,EXIT_ENTRY,TRAVEL_TRACK")
	private String suspectInfoType;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String detailContent;

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getSuspectId() {
		return suspectId;
	}

	public void setSuspectId(String suspectId) {
		this.suspectId = suspectId;
	}

	public String getSuspectInfoType() {
		return suspectInfoType;
	}

	public void setSuspectInfoType(String suspectInfoType) {
		this.suspectInfoType = suspectInfoType;
	}

	public String getDetailContent() {
		return detailContent;
	}

	public void setDetailContent(String detailContent) {
		this.detailContent = detailContent;
	}
}

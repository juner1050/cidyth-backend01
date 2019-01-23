package com.hyzs.cidyth.module.cases.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "CasesInformantVO（报案人/受害人）", description = "报案人/受害人")
public class CasesInformantVO {

	/**
	 * 姓名
	 */
	@ApiModelProperty("姓名")
	private String xm;
	/**
	 * 人员编号
	 */
	@ApiModelProperty("人员编号")
	private String rybh;
	/**
	 * 联系电话
	 */
	@ApiModelProperty("联系电话")
	private String lxdh;
	/**
	 * 出生日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty("出生日期")
	private Date csrq;
	/**
	 * 性别
	 */
	@ApiModelProperty("性别")
	private String xbCn;
	/**
	 * 证件种类
	 */
	@ApiModelProperty("证件种类")
	private String zjzlCn;
	/**
	 * 证件号码
	 */
	@ApiModelProperty("证件号码")
	private String zjhm;
	/**
	 * 人员文化程度
	 */
	@ApiModelProperty("人员文化程度")
	private String rywhcdCn;
	/**
	 * 详细地址描述
	 */
	@ApiModelProperty("详细地址描述")
	private String xxdzms;
	/**
	 * 人员类型名称
	 */
	@ApiModelProperty("人员类型名称")
	private String rylxCn;

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public Date getCsrq() {
		return csrq;
	}

	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}

	public String getXbCn() {
		return xbCn;
	}

	public void setXbCn(String xbCn) {
		this.xbCn = xbCn;
	}

	public String getZjzlCn() {
		return zjzlCn;
	}

	public void setZjzlCn(String zjzlCn) {
		this.zjzlCn = zjzlCn;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getRywhcdCn() {
		return rywhcdCn;
	}

	public void setRywhcdCn(String rywhcdCn) {
		this.rywhcdCn = rywhcdCn;
	}

	public String getXxdzms() {
		return xxdzms;
	}

	public void setXxdzms(String xxdzms) {
		this.xxdzms = xxdzms;
	}

	public String getRylxCn() {
		return rylxCn;
	}

	public void setRylxCn(String rylxCn) {
		this.rylxCn = rylxCn;
	}

	public String getRybh() {
		return rybh;
	}

	public void setRybh(String rybh) {
		this.rybh = rybh;
	}
}

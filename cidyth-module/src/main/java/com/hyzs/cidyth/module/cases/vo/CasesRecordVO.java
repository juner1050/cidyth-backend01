package com.hyzs.cidyth.module.cases.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("CasesRecordVO（笔录）")
public class CasesRecordVO {

	/**
	 * 主键id
	 */
	@ApiModelProperty("id")
	private Integer id;

	/**
	 * 笔录来源
	 */
	@ApiModelProperty("笔录来源")
	private String blly;

	/**
	 * 人员类型
	 */
	@ApiModelProperty("笔录对象人员类型")
	private String rylx;

	/**
	 * 对象名称
	 */
	@ApiModelProperty("笔录对象名称")
	private String targetXm;
	/**
	 * 对象性别名称
	 */
	@ApiModelProperty("笔录对象性别")
	private String targetXbCn;
	/**
	 * 记录人员
	 */
	@ApiModelProperty("记录人员")
	private String recorder;
	/**
	 * 记录地点
	 */
	@ApiModelProperty("记录地点")
	private String jldd;
	/**
	 * 记录主体
	 */
	@ApiModelProperty("记录内容")
	private String body;
	/**
	 * 开始记录时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@ApiModelProperty("笔录开始时间")
	private Date starttime;
	/**
	 * 结束记录时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@ApiModelProperty("笔录结束时间")
	private Date endtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTargetXm() {
		return targetXm;
	}
	public void setTargetXm(String targetXm) {
		this.targetXm = targetXm;
	}
	public String getTargetXbCn() {
		return targetXbCn;
	}
	public void setTargetXbCn(String targetXbCn) {
		this.targetXbCn = targetXbCn;
	}
	public String getRecorder() {
		return recorder;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getJldd() {
		return jldd;
	}

	public void setJldd(String jldd) {
		this.jldd = jldd;
	}

	public String getBlly() {
		return blly;
	}

	public void setBlly(String blly) {
		this.blly = blly;
	}

	public String getRylx() {
		return rylx;
	}

	public void setRylx(String rylx) {
		this.rylx = rylx;
	}
}

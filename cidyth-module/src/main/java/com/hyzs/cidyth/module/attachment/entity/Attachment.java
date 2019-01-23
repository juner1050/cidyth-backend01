package com.hyzs.cidyth.module.attachment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_enclosure")
public class Attachment {
	/**
	 * 主键记录id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
	private Integer id;

	/**
	 * 附件所属目标主键id,需求id、线索id、信息id、回复id...
	 */
	@Column(name = "reference_id")
	private String referenceId;

	/**
	 * 附件用途:CASE(案件)、DEMAND(上传)、INFO(反馈线索)、UPLOAD_CLUE(反馈线索)、RETURN_CLUE(反馈线索)、REPLY(回复)
	 * 
	 * @see com.hyzs.cidyth.common.enums.TableTypeEnum
	 */
	private String fjlx;

	/**
	 * 文件唯一标识或部分url
	 */
	@Column(name = "file_id")
	private String fileId;

	/**
	 * 原文件名称
	 */
	private String fjmc;
	/**
	 * 文件大小
	 */
	private Long wjdx;
	/**
	 * 文件格式
	 */
	private String wjgs;
	/**
	 * 备注信息
	 */
	private String smbz;

	/**
	 * 上传操作人用户id
	 */
	private String lrry;
	/**
	 * 上传操作人姓名
	 */
	private String lrrymc;
	/**
	 * 上传时间
	 */
	private Date lrsj;
	/**
	 * 文件类型（1、审批文书；2、法律文书；3、证据材料；4、其他）
	 */
	@Column(name = "file_type")
	private Integer fileType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getFjlx() {
		return fjlx;
	}

	public void setFjlx(String fjlx) {
		this.fjlx = fjlx;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFjmc() {
		return fjmc;
	}

	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

	public String getSmbz() {
		return smbz;
	}

	public void setSmbz(String smbz) {
		this.smbz = smbz;
	}

	public String getLrry() {
		return lrry;
	}

	public void setLrry(String lrry) {
		this.lrry = lrry;
	}

	public String getLrrymc() {
		return lrrymc;
	}

	public void setLrrymc(String lrrymc) {
		this.lrrymc = lrrymc;
	}

	public Date getLrsj() {
		return lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public Long getWjdx() {
		return wjdx;
	}

	public void setWjdx(Long wjdx) {
		this.wjdx = wjdx;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public String getWjgs() {
		return wjgs;
	}

	public void setWjgs(String wjgs) {
		this.wjgs = wjgs;
	}
}
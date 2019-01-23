package com.hyzs.cidyth.module.msg.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hyzs.cidyth.module.attachment.vo.AttachmentUpload;
import org.springframework.web.multipart.MultipartFile;

import com.hyzs.cidyth.module.msg.entity.InfoAcceptance;

public class InfoVO {

	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 案件编号
	 */
	private String ajbh;
	/**
	 * 信息主题
	 */
	private String xxzt;
	/**
	 * 发布内容
	 */
	private String fbnr;
	/**
	 * 发布日期
	 */
	private Date fbrq;
	/**
	 * 发布人员
	 */
	private String fbry;
	/**
	 * 录入人员
	 */
	private String lrry;
	/**
	 * 录入人员名称
	 */
	private String lrrymc;
	/**
	 * 录入时间
	 */
	private Date lrsj;
	
//	/**
//	 * 发布范围（单位code，逗号分隔）
//	 */
//	private String fbfw;
//	/**
//	 * 发布范围名称
//	 */
//	private String fbfwCn;
	
	/**
	 * 消息接受对象列表(用于存放多个接受人及其相关发信息)
	 */
	private List<InfoAcceptance> acceptList;
	/**
	 * 附件列表
	 */
	private List<AttachmentUpload> files;
	/**
	 * 附件说明列表
	 */
	private String fileComment;
	/**
	 * 下载附件
	 */
	private List<Map<String, Object>> lsAttachment;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAjbh() {
		return ajbh;
	}
	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}
	public String getXxzt() {
		return xxzt;
	}
	public void setXxzt(String xxzt) {
		this.xxzt = xxzt;
	}
	public String getFbnr() {
		return fbnr;
	}
	public void setFbnr(String fbnr) {
		this.fbnr = fbnr;
	}
	
	public String getFbry() {
		return fbry;
	}
	public void setFbry(String fbry) {
		this.fbry = fbry;
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

	public List<AttachmentUpload> getFiles() {
		return files;
	}
	public void setFiles(List<AttachmentUpload> files) {
		this.files = files;
	}
	public String getFileComment() {
		return fileComment;
	}
	public void setFileComment(String fileComment) {
		this.fileComment = fileComment;
	}
	//	public String getFbfw() {
//		return fbfw;
//	}
//	public void setFbfw(String fbfw) {
//		this.fbfw = fbfw;
//	}
//	public String getFbfwCn() {
//		return fbfwCn;
//	}
//
//	public void setFbfwCn(String fbfwCn) {
//		this.fbfwCn = fbfwCn;
//	}
	public List<InfoAcceptance> getAcceptList() {
		return acceptList;
	}
	public void setAcceptList(List<InfoAcceptance> acceptList) {
		this.acceptList = acceptList;
	}

	public List<Map<String, Object>> getLsAttachment() {
		return lsAttachment;
	}

	public void setLsAttachment(List<Map<String, Object>> lsAttachment) {
		this.lsAttachment = lsAttachment;
	}

	public Date getFbrq() {
		return fbrq;
	}

	public void setFbrq(Date fbrq) {
		this.fbrq = fbrq;
	}
}

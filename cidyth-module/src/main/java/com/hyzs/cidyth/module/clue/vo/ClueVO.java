package com.hyzs.cidyth.module.clue.vo;

import com.hyzs.cidyth.module.attachment.vo.AttachmentUpload;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public class ClueVO {

	/**
	 * id
	 */
	private Integer id;
	/**
	 * 案件编号
	 */
	@ApiModelProperty(value = "案件编号", required = true)
	private String ajbh;
	/**
	 * 需求id
	 */
	@ApiModelProperty(value = "需求id", required = true)
	private Integer demandId;
	/**
	 * 线索主题
	 */
	@ApiModelProperty(value = "线索主题", required = true)
	private String theme;
	/**
	 * 线索内容
	 */
	@ApiModelProperty(value = "线索内容", required = true)
	private String xsnr;
	/**
	 * 线索类型（ClueTypeEnum枚举的值）
	 */
	private Integer xslx;
	/**
	 * 是否评分（0、未评分，1、已评分）
	 */
	private Integer sfpf;
	/**
	 * 评分分值
	 */
	private Float pffz;
	/**
	 * 评分人员
	 */
	private String pfry;
	/**
	 * 评分人员名称
	 */
	private String pfrymc;
	/**
	 * 评分内容
	 */
	private String pfnr;
	/**
	 * 说明备注
	 */
	private String smbz;
	/**
	 * 反馈人员
	 */
	private String fkry;
	/**
	 * 反馈人员
	 */
	private String fkrymc;
	/**
	 * 上传反馈附件（多个文件）
	 */
	@ApiModelProperty(value = "上传反馈附件")
	private List<AttachmentUpload> files;
	/**
	 * 下载反馈附件（多个文件）
	 */
	@ApiModelProperty(value = "下载反馈附件")
	private List<Map<String, Object>> lsAttachment;
	/**
	 * 反馈附件说明
	 */
	@ApiModelProperty(value = "反馈附件说明")
	private String fileComment;

	/**
	 * 思维导图上坐标
	 */
	private String mindTop;

	/**
	 * 思维导图左坐标
	 */
	private String mindLeft;

	/**
	 * 是否发送短信通知（true/false）
	 */
	@ApiModelProperty(value = "是否发送短信通知（true/false）")
	private boolean sendMessage;
	
	public String getAjbh() {
		return ajbh;
	}
	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDemandId() {
		return demandId;
	}
	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getXsnr() {
		return xsnr;
	}
	public void setXsnr(String xsnr) {
		this.xsnr = xsnr;
	}
	public String getFkry() {
		return fkry;
	}
	public void setFkry(String fkry) {
		this.fkry = fkry;
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
	public String getSmbz() {
		return smbz;
	}
	public void setSmbz(String smbz) {
		this.smbz = smbz;
	}
	public Float getPffz() {
		return pffz;
	}
	public void setPffz(Float pffz) {
		this.pffz = pffz;
	}
	public String getPfry() {
		return pfry;
	}
	public void setPfry(String pfry) {
		this.pfry = pfry;
	}
	public String getPfnr() {
		return pfnr;
	}
	public void setPfnr(String pfnr) {
		this.pfnr = pfnr;
	}
	public String getPfrymc() {
		return pfrymc;
	}
	public void setPfrymc(String pfrymc) {
		this.pfrymc = pfrymc;
	}

	public Integer getSfpf() {
		return sfpf;
	}

	public void setSfpf(Integer sfpf) {
		this.sfpf = sfpf;
	}
	public String getMindTop() {
		return mindTop;
	}
	public void setMindTop(String mindTop) {
		this.mindTop = mindTop;
	}
	public String getMindLeft() {
		return mindLeft;
	}
	public void setMindLeft(String mindLeft) {
		this.mindLeft = mindLeft;
	}
	public Integer getXslx() {
		return xslx;
	}
	public void setXslx(Integer xslx) {
		this.xslx = xslx;
	}

	public boolean isSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(boolean sendMessage) {
		this.sendMessage = sendMessage;
	}

	public List<Map<String, Object>> getLsAttachment() {
		return lsAttachment;
	}

	public void setLsAttachment(List<Map<String, Object>> lsAttachment) {
		this.lsAttachment = lsAttachment;
	}

	public String getFkrymc() {
		return fkrymc;
	}

	public void setFkrymc(String fkrymc) {
		this.fkrymc = fkrymc;
	}
}

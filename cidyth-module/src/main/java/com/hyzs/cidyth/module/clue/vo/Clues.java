package com.hyzs.cidyth.module.clue.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import com.hyzs.cidyth.module.clue.entity.Clue;
import com.hyzs.cidyth.module.websocket.data.WebSocketMessage;

/**
 * 线索基本模型
 * 
 * @author jidw
 *
 */
public class Clues implements java.io.Serializable,WebSocketMessage{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6469443400952972710L;
	private Integer demandId;//需求id
	private String demandCreateUserId;//需求创建人id
	private Clue clue;
	private List<Map<String, Object>> attachments;// 附件信息

	public Clues() {
		clue = new Clue();
	}

	public Clues(Clue clue) {
		this.clue = clue;
	}
	
	@JsonIgnore
	public Clues setClue(Clue clue){
		this.clue = clue;
		return this;
	}
	@JsonIgnore
	public Clue getClue() {
		return this.clue;
	}

	/**
	 * 线索id
	 * 
	 * @return
	 */
	public Integer getId() {
		return clue == null ? null : clue.getId();
	}

	@JsonIgnore
	public void setId(Integer id) {
		if (clue != null) {
			clue.setId(id);
		}
	}

	/**
	 * 案件编号
	 * 
	 * @return
	 */
	public String getAjbh() {
		return clue == null ? null : clue.getAjbh();
	}

	public void setAjbh(String ajbh) {
		if (clue != null) {
			clue.setAjbh(ajbh);
		}
	}

	/**
	 * 主题
	 * 
	 * @return
	 */
	public String getTheme() {
		return clue == null ? null : clue.getTheme();
	}

	public void setTheme(String theme) {
		if (clue != null) {
			clue.setTheme(theme);
		}
	}

	/**
	 * 线索内容
	 * 
	 * @return
	 */
	public String getXsnr() {
		return clue == null ? null : clue.getXsnr();
	}

	public void setXsnr(String xsnr) {
		if (clue != null) {
			clue.setXsnr(xsnr);
		}
	}

	/**
	 * 反馈单位编号
	 * 
	 * @return
	 */
	public String getFkdwbh() {
		return clue == null ? null : clue.getFkdwbh();
	}

	public void setFkdwbh(String fkdwbh) {
		if (clue != null) {
			clue.setFkdwbh(fkdwbh);
		}
	}

	/**
	 * 反馈单位名称
	 * 
	 * @return
	 */
	public String getFkdw() {
		return clue == null ? null : clue.getFkdw();
	}

	public void setFkdw(String fkdw) {
		if (clue != null) {
			clue.setFkdw(fkdw);
		}
	}

	/**
	 * 线索录入人员编号(反馈人员编号)
	 * 
	 * @return
	 */
	public String getFkry() {
		return clue == null ? null : clue.getFkry();
	}

	public void setFkry(String fkry) {
		if (clue != null) {
			clue.setFkry(fkry);
			clue.setLrry(fkry);
		}
	}

	/**
	 * 录入人员姓名
	 * 
	 * @return
	 */
	public String getLrrymc() {
		return clue == null ? null : clue.getLrrymc();
	}

	public void setLrrymc(String lrrymc) {
		if (clue != null) {
			clue.setLrrymc(lrrymc);
		}
	}

	/**
	 * 反馈日期
	 * 
	 * @return
	 */
	public Date getFkrq() {
		return clue == null ? null : clue.getFkrq();
	}

	/**
	 * 附件（0、否，1、是）
	 * 
	 * @return
	 */
	@JsonIgnore
	public Integer getSffj() {
		return clue == null ? null : clue.getSffj();
	}

	public void setSffj(Integer sffj) {
		if (clue != null) {
			clue.setSffj(sffj);
		}
	}

	/**
	 * 备注信息
	 * 
	 * @return
	 */
	public String getSmbz() {
		return clue == null ? null : clue.getSmbz();
	}

	public void setSmbz(String smbz) {
		if (clue != null) {
			clue.setSmbz(smbz);
		}
	}

	/**
	 * 修改人员编号
	 * 
	 * @return
	 */
	public String getXgry() {
		return clue == null ? null : clue.getXgry();
	}

	public void setXgry(String xgry) {
		if (clue != null) {
			clue.setXgry(xgry);
		}
	}

	/**
	 * 修改人员姓名
	 * 
	 * @return
	 */
	public String getXgrymc() {
		return clue == null ? null : clue.getXgrymc();
	}

	public void setXgrymc(String xgrymc) {
		if (clue != null) {
			clue.setXgrymc(xgrymc);
		}
	}

	/**
	 * 修改时间
	 * 
	 * @return
	 */
	public Date getXgsj() {
		return clue == null ? null : clue.getXgsj();
	}

	public void setXgsj(Date xgsj) {
		if (clue != null) {
			clue.setXgsj(xgsj);
		}
	}

	/**
	 * 附件
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getAttachments() {
		return attachments;
	}

	public Clues setAttachments(List<Map<String, Object>> attachments) {
		this.attachments = attachments;
		return this;
	}

	// 附件
	private MultipartFile[] files;

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	
	@JsonIgnore
	public Integer getDemandId() {
		return demandId;
	}
	@JsonIgnore
	public Clues setDemandId(Integer demandId) {
		this.demandId = demandId;
		return this;
	}

	@JsonIgnore
	public String getDemandCreateUserId() {
		return demandCreateUserId;
	}
	@JsonIgnore
	public Clues setDemandCreateUserId(String demandCreateUserId) {
		this.demandCreateUserId = demandCreateUserId;
		return this;
	}

	@Override
	public String fromUser() {
		return getFkry();
	}

	@Override
	public Set<String> toUsers() {
		Set<String> to = Sets.newHashSet();
		if(StringUtils.isNotBlank(fromUser())){
			to.add(fromUser());
		}
		if(StringUtils.isNotBlank(demandCreateUserId)){
			to.add(demandCreateUserId);
		}
		return to;
	}
}

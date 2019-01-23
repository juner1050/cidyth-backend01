package com.hyzs.cidyth.module.investigatehis.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 调查/侦查历史记录
 * 
 * @author derrick
 *
 */
@Table(name = "t_investigate_his")
public class InvestigateHis {
	/**
	 * 调查记录id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
	private Long id;
	/**
	 * 警员编号
	 */
	@Column(name = "CREATED_BY_ID")
	private String createdById;
	/**
	 * 警员姓名
	 */
	@Column(name = "CREATED_BY_NAME")
	private String createdByName;
	/**
	 * 嫌疑人身份标识(一般为身份证号)
	 */
	@Column(name = "SUSPECT_ID")
	private String suspectId;
	/**
	 * 查看过的嫌疑人信息分类
	 */
	@Column(name = "SUSPECT_INFO_TYPE")
	private String suspectInfoType;
	/**
	 * 涉及的案件编号
	 */
	@Column(name = "CASE_NO")
	private String caseNo;
	/**
	 * 生成时间
	 */
	@Column(name = "CREATE_TIME")
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedById() {
		return createdById;
	}

	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}

	public String getCreatedByName() {
		return createdByName;
	}

	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
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

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

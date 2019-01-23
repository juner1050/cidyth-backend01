package com.hyzs.cidyth.module.investigatehis.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 调查/侦查历史记录详情
 * 
 * @author derrick
 *
 */
@Table(name = "t_investigate_his_detail")
public class InvestigateHisDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
	private Long id;
	/**
	 * 生成时间
	 */
	@Column(name = "CREATE_TIME")
	private Date createTime;
	/**
	 * 内容
	 */
	@Column(name = "DETAIL_CONTENT")
	private String detailContent;
	/**
	 * 历史记录id
	 */
	@Column(name = "INVESTIGATE_HIS_ID")
	private Long investigateHisId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDetailContent() {
		return detailContent;
	}

	public void setDetailContent(String detailContent) {
		this.detailContent = detailContent;
	}

	public Long getInvestigateHisId() {
		return investigateHisId;
	}

	public void setInvestigateHisId(Long investigateHisId) {
		this.investigateHisId = investigateHisId;
	}
}

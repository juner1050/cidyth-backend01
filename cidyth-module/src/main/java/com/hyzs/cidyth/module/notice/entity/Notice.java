package com.hyzs.cidyth.module.notice.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_notifaction")
public class Notice implements  java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6082051177822431990L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
	private Long id;//通知id
	
	private String tznr;//通知内容
	
	private Date tzsj;//通知时间

	private String fsrbh;//发送人编号

	private String fsrmc;//发送人名称

	private String fsrjgbh;//接收人机构编号

	private String fsrjgmc;//接收人名称

	private String jsrbh;//接受人编号

	private String jsrmc;//接收人名称

	private String jsrjgbh;//接收人机构编号

	private String jsrjgmc;//接收人机构名称

	private String anchor;//通知内容锚点

	private String ajbh;//案件编号

	private Integer tzmbid;//通知目标ID

	private String tzmb;//通知目标

	private Integer tzlyid;//通知来源ID

	private String tzly;//通知来源

	private String sfyd;//是否已读

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTznr() {
		return tznr;
	}

	public void setTznr(String tznr) {
		this.tznr = tznr;
	}

	public Date getTzsj() {
		return tzsj;
	}

	public void setTzsj(Date tzsj) {
		this.tzsj = tzsj;
	}

	public String getJsrbh() {
		return jsrbh;
	}

	public void setJsrbh(String jsrbh) {
		this.jsrbh = jsrbh;
	}

	public String getAnchor() {
		return anchor;
	}

	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}

	public String getSfyd() {
		return sfyd;
	}

	public void setSfyd(String sfyd) {
		this.sfyd = sfyd;
	}

	public String getFsrbh() {
		return fsrbh;
	}

	public void setFsrbh(String fsrbh) {
		this.fsrbh = fsrbh;
	}

	public String getFsrmc() {
		return fsrmc;
	}

	public void setFsrmc(String fsrmc) {
		this.fsrmc = fsrmc;
	}

	public String getFsrjgbh() {
		return fsrjgbh;
	}

	public void setFsrjgbh(String fsrjgbh) {
		this.fsrjgbh = fsrjgbh;
	}

	public String getFsrjgmc() {
		return fsrjgmc;
	}

	public void setFsrjgmc(String fsrjgmc) {
		this.fsrjgmc = fsrjgmc;
	}

	public String getJsrmc() {
		return jsrmc;
	}

	public void setJsrmc(String jsrmc) {
		this.jsrmc = jsrmc;
	}

	public String getJsrjgbh() {
		return jsrjgbh;
	}

	public void setJsrjgbh(String jsrjgbh) {
		this.jsrjgbh = jsrjgbh;
	}

	public String getJsrjgmc() {
		return jsrjgmc;
	}

	public void setJsrjgmc(String jsrjgmc) {
		this.jsrjgmc = jsrjgmc;
	}

	public String getAjbh() {
		return ajbh;
	}

	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}

	public Integer getTzmbid() {
		return tzmbid;
	}

	public void setTzmbid(Integer tzmbid) {
		this.tzmbid = tzmbid;
	}

	public String getTzmb() {
		return tzmb;
	}

	public void setTzmb(String tzmb) {
		this.tzmb = tzmb;
	}

	public Integer getTzlyid() {
		return tzlyid;
	}

	public void setTzlyid(Integer tzlyid) {
		this.tzlyid = tzlyid;
	}

	public String getTzly() {
		return tzly;
	}

	public void setTzly(String tzly) {
		this.tzly = tzly;
	}
}

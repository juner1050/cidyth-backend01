package com.hyzs.cidyth.module.base.entity;

import java.util.Date;

import javax.persistence.*;

//合成作战工作组
@Table(name = "t_case_partner")
public class CasesPartner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
	@Column(name = "zyid")
	private Long zyId;// 组员id
	private String sfyx;//工作组是否有效,'1'有效;'0'表示无效
	private String ajbh;// 案件编号
	private String jgdm;// 机构代码
	private String jgmc;// 机构名称
	private String jybh;// 警员编号
	private String jyxm;// 警员姓名
	private String sjhm;// 手机号码
	private String smbz;// 说明备注
	private Date lrsj;// 录入时间
	private String lrryjgdm;// 录入人机构代码
	private String lrryjgmc;// 录入人机构名称
	private String lrrybh;// 录入人员编号
	private String lrrymc;// 录入人员名称

	

	public Long getZyId() {
		return zyId;
	}

	public void setZyId(Long zyId) {
		this.zyId = zyId;
	}

	public String getSfyx() {
		return sfyx;
	}

	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
	}

	public String getAjbh() {
		return ajbh;
	}

	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}

	public String getJgdm() {
		return jgdm;
	}

	public void setJgdm(String jgdm) {
		this.jgdm = jgdm;
	}

	public String getJgmc() {
		return jgmc;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public String getJybh() {
		return jybh;
	}

	public void setJybh(String jybh) {
		this.jybh = jybh;
	}

	public String getJyxm() {
		return jyxm;
	}

	public void setJyxm(String jyxm) {
		this.jyxm = jyxm;
	}

	public String getSjhm() {
		return sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getSmbz() {
		return smbz;
	}

	public void setSmbz(String smbz) {
		this.smbz = smbz;
	}

	public Date getLrsj() {
		return lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public String getLrryjgdm() {
		return lrryjgdm;
	}

	public void setLrryjgdm(String lrryjgdm) {
		this.lrryjgdm = lrryjgdm;
	}

	public String getLrryjgmc() {
		return lrryjgmc;
	}

	public void setLrryjgmc(String lrryjgmc) {
		this.lrryjgmc = lrryjgmc;
	}

	public String getLrrybh() {
		return lrrybh;
	}

	public void setLrrybh(String lrrybh) {
		this.lrrybh = lrrybh;
	}

	public String getLrrymc() {
		return lrrymc;
	}

	public void setLrrymc(String lrrymc) {
		this.lrrymc = lrrymc;
	}
}

package com.hyzs.cidyth.module.personal.vo;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 个人工作台对象
 * @author derrick
 *
 */
public class PersonalDemandVO {

	/**
	 * 需求id
	 */
	private Integer id;

	/**
	 * 需求名称
	 */
	private String xqmc;
	
	/**
	 * 需求内容
	 */
	private String xqnr;
	
	/**
	 * 需求录入时间
	 */
	private Date lrsj;

	/**
	 * 需求请求时间
	 */
	private Date qqsj;

	/**
	 * 签收状态
	 */
	private String qszt;

	/**
	 * 签收状态名称
	 */
	private String qsztCn;

	/**
	 * 指派领导
	 */
	private String zpld;

	/**
	 * 指派领导名称
	 */
	private String zpldCn;
	
	/**
	 * 指派领导电话
	 */
	private String zplddh;

	/**
	 * 需求创建单位
	 */
	private String qqdw;
	
	/**
	 * 录入人员名称
	 */
	private String lrrymc;
	
	/**
	 * 紧急程度
	 */
	private String jjcd;
	
	/**
	 * 紧急程度名称
	 */
	private String jjcdCn;
	
	/**
	 * 超出天数
	 */
	private Integer ccts;

	/**
	 * 评分分值
	 */
	private BigDecimal pffz;

	/**
	 * 评分分值名称
	 */
	private String pffzCn;
	
	/**
	 * 评分内容
	 */
	private String pfnr;
	
	/**
	 * 反馈日期
	 */
	private Date fkrq;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getLrsj() {
		return lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public Date getQqsj() {
		return qqsj;
	}

	public void setQqsj(Date qqsj) {
		this.qqsj = qqsj;
	}

	public String getQszt() {
		return qszt;
	}

	public void setQszt(String qszt) {
		this.qszt = qszt;
	}

	public String getQsztCn() {
		return qsztCn;
	}

	public void setQsztCn(String qsztCn) {
		this.qsztCn = qsztCn;
	}

	public String getZpld() {
		return zpld;
	}

	public void setZpld(String zpld) {
		this.zpld = zpld;
	}

	public String getZpldCn() {
		return zpldCn;
	}

	public void setZpldCn(String zpldCn) {
		this.zpldCn = zpldCn;
	}

	public String getZplddh() {
		return zplddh;
	}

	public void setZplddh(String zplddh) {
		this.zplddh = zplddh;
	}

	public String getQqdw() {
		return qqdw;
	}

	public void setQqdw(String qqdw) {
		this.qqdw = qqdw;
	}

	public String getLrrymc() {
		return lrrymc;
	}

	public void setLrrymc(String lrrymc) {
		this.lrrymc = lrrymc;
	}

	public String getJjcd() {
		return jjcd;
	}

	public void setJjcd(String jjcd) {
		this.jjcd = jjcd;
	}

	public String getJjcdCn() {
		return jjcdCn;
	}

	public void setJjcdCn(String jjcdCn) {
		this.jjcdCn = jjcdCn;
	}

	public Integer getCcts() {
		return ccts;
	}

	public void setCcts(Integer ccts) {
		this.ccts = ccts;
	}

	public BigDecimal getPffz() {
		return pffz;
	}

	public void setPffz(BigDecimal pffz) {
		this.pffz = pffz;
	}

	public String getPfnr() {
		return pfnr;
	}

	public void setPfnr(String pfnr) {
		this.pfnr = pfnr;
	}

	public Date getFkrq() {
		return fkrq;
	}

	public void setFkrq(Date fkrq) {
		this.fkrq = fkrq;
	}

	public String getXqmc() {
		return xqmc;
	}

	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	public String getXqnr() {
		return xqnr;
	}

	public void setXqnr(String xqnr) {
		this.xqnr = xqnr;
	}

	public String getPffzCn() {
		return pffzCn;
	}

	public void setPffzCn(String pffzCn) {
		this.pffzCn = pffzCn;
	}
	
}
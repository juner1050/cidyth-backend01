package com.hyzs.cidyth.module.base.vo;


public class CompareInfoLoadParam implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5100144196715167543L;

	public CompareInfoLoadParam() {

	}

	/**
	 * 开始和结束比中时间
	 */
	private String startBzsj;
	private String endBzsj;
	/**
	 * 案发开始时间和结束时间
	 */
	private String startAfsj;
	private String endAfsj;

	private String saryxm;// 涉案人员姓名
	private String sarysfz;// 涉案人员身份证号

	private String bzly;// 比中来源.0-DNA比中，1-指纹比中

	private String bh;// 编号(警情/案件/物证编号)

	private String ajlb;// 案件类别

	private String sldwdm;// 受理单位代码

	private String pazt;// 破案状态.取值为1.已破案;0.未破案

	private String glajzt;// 关联案件状态.取值为:1.已关联;0.未关联
	private String order;
	private Integer limit;
	private Integer offset;
	public String getStartBzsj() {
		return startBzsj;
	}

	public void setStartBzsj(String startBzsj) {
		this.startBzsj = startBzsj;
	}

	public String getEndBzsj() {
		return endBzsj;
	}

	public void setEndBzsj(String endBzsj) {
		this.endBzsj = endBzsj;
	}

	public String getStartAfsj() {
		return startAfsj;
	}

	public void setStartAfsj(String startAfsj) {
		this.startAfsj = startAfsj;
	}

	public String getEndAfsj() {
		return endAfsj;
	}

	public void setEndAfsj(String endAfsj) {
		this.endAfsj = endAfsj;
	}

	public String getSaryxm() {
		return saryxm;
	}

	public void setSaryxm(String saryxm) {
		this.saryxm = saryxm;
	}

	public String getSarysfz() {
		return sarysfz;
	}

	public void setSarysfz(String sarysfz) {
		this.sarysfz = sarysfz;
	}

	public String getBzly() {
		return bzly;
	}

	public void setBzly(String bzly) {
		this.bzly = bzly;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getAjlb() {
		return ajlb;
	}

	public void setAjlb(String ajlb) {
		this.ajlb = ajlb;
	}

	public String getSldwdm() {
		return sldwdm;
	}

	public void setSldwdm(String sldwdm) {
		this.sldwdm = sldwdm;
	}

	public String getPazt() {
		return pazt;
	}

	public void setPazt(String pazt) {
		this.pazt = pazt;
	}

	public String getGlajzt() {
		return glajzt;
	}

	public void setGlajzt(String glajzt) {
		this.glajzt = glajzt;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

}
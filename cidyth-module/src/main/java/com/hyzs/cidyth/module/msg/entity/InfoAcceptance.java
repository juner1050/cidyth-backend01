package com.hyzs.cidyth.module.msg.entity;

/**
 * 消息接受实体
 * 
 * @author jidw
 *
 */
public class InfoAcceptance implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6579163978664148512L;
	
	private String ajbh;// 案件编号
	private Long infoId;// 信息id
	private String jsdwbh;// 接受单位编号
	private String jsdwmc;// 接受单位名称
	private String jsrybh;// 接受人编号
	private String jsrxm;// 接受人姓名

	public String getAjbh() {
		return ajbh;
	}

	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}

	public Long getInfoId() {
		return infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	public String getJsdwbh() {
		return jsdwbh;
	}

	public void setJsdwbh(String jsdwbh) {
		this.jsdwbh = jsdwbh;
	}

	public String getJsdwmc() {
		return jsdwmc;
	}

	public void setJsdwmc(String jsdwmc) {
		this.jsdwmc = jsdwmc;
	}

	public String getJsrybh() {
		return jsrybh;
	}

	public void setJsrybh(String jsrybh) {
		this.jsrybh = jsrybh;
	}

	public String getJsrxm() {
		return jsrxm;
	}

	public void setJsrxm(String jsrxm) {
		this.jsrxm = jsrxm;
	}
}

package com.hyzs.cidyth.module.cases.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "CasesSuspectVO（嫌疑人）", description = "嫌疑人")
public class CasesSuspectVO {
	/**
	 * 姓名
	 */
	@ApiModelProperty("姓名")
	private String xm;
	/**
	 * 人员编号
	 */
	@ApiModelProperty("人员编号")
	private String rybh;
	/**
	 * 联系电话
	 */
	@ApiModelProperty("联系电话")
	private String lxdh;
	/**
	 * 出生日期
	 */
	@JsonFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty("出生日期")
	private Date csrq;
	/**
	 * 性别
	 */
	@ApiModelProperty("性别")
	private String xbCn;
	/**
	 * 证件种类
	 */
	@ApiModelProperty("证件种类")
	private String zjzlCn;
	/**
	 * 证件号码
	 */
	@ApiModelProperty("证件号码")
	private String zjhm;
	/**
	 * 人员文化程度
	 */
	@ApiModelProperty("人员文化程度")
	private String rywhcdCn;
	/**
	 * 详细地址描述
	 */
	@ApiModelProperty("详细地址描述")
	private String xxdzms;
	/**
	 * 工作单位
	 */
	@ApiModelProperty("工作单位")
	private String gzdw;
	/**
	 * 身份
	 */
	@ApiModelProperty("身份")
	private String sfCn;
	/**
	 * 身高
	 */
	@ApiModelProperty("身高")
	private String sg;
	/**
	 * 脸型
	 */
	@ApiModelProperty("脸型")
	private String lxCn;
	/**
	 * 体型
	 */
	@ApiModelProperty("体型")
	private String txCn;
	/**
	 * 血型
	 */
	@ApiModelProperty("血型")
	private String xxCn;
	/**
	 * 职业
	 */
	@ApiModelProperty("职业")
	private String zyCn;
	/**
	 * 体貌特征
	 */
	@ApiModelProperty("体貌特征")
	private String tmtzCn;
	/**
	 * 作案工具
	 */
	@ApiModelProperty("作案工具")
	private String zagjCn;
	/**
	 * 手段特点
	 */
	@ApiModelProperty("手段特点")
	private String sdtdCn;
	/**
	 * 选择时间
	 */
	@ApiModelProperty("选择时间")
	private String xzsjCn;
	/**
	 * 选择处所
	 */
	@ApiModelProperty("选择处所")
	private String xzcsCn;
	/**
	 * 选择对象
	 */
	@ApiModelProperty("选择对象")
	private String xzdxCn;
	/**
	 * 损失金额
	 */
	@ApiModelProperty("损失金额")
	private String ssje;
	/**
	 * 死亡情况
	 */
	@ApiModelProperty("死亡情况")
	private String swqk;
	/**
	 * 违法经历
	 */
	@ApiModelProperty("违法经历")
	private String wfjl;
	/**
	 * 违法情况
	 */
	@ApiModelProperty("违法情况")
	private String wfqk;
	/**
	 * 户籍地址
	 */
	@ApiModelProperty("户籍地址")
	private String hjdz;

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public Date getCsrq() {
		return csrq;
	}

	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}

	public String getXbCn() {
		return xbCn;
	}

	public void setXbCn(String xbCn) {
		this.xbCn = xbCn;
	}

	public String getZjzlCn() {
		return zjzlCn;
	}

	public void setZjzlCn(String zjzlCn) {
		this.zjzlCn = zjzlCn;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getRywhcdCn() {
		return rywhcdCn;
	}

	public void setRywhcdCn(String rywhcdCn) {
		this.rywhcdCn = rywhcdCn;
	}

	public String getXxdzms() {
		return xxdzms;
	}

	public void setXxdzms(String xxdzms) {
		this.xxdzms = xxdzms;
	}

	public String getGzdw() {
		return gzdw;
	}

	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}

	public String getSfCn() {
		return sfCn;
	}

	public void setSfCn(String sfCn) {
		this.sfCn = sfCn;
	}

	public String getSg() {
		return sg;
	}

	public void setSg(String sg) {
		this.sg = sg;
	}

	public String getLxCn() {
		return lxCn;
	}

	public void setLxCn(String lxCn) {
		this.lxCn = lxCn;
	}

	public String getTxCn() {
		return txCn;
	}

	public void setTxCn(String txCn) {
		this.txCn = txCn;
	}

	public String getXxCn() {
		return xxCn;
	}

	public void setXxCn(String xxCn) {
		this.xxCn = xxCn;
	}

	public String getZyCn() {
		return zyCn;
	}

	public void setZyCn(String zyCn) {
		this.zyCn = zyCn;
	}

	public String getTmtzCn() {
		return tmtzCn;
	}

	public void setTmtzCn(String tmtzCn) {
		this.tmtzCn = tmtzCn;
	}

	public String getZagjCn() {
		return zagjCn;
	}

	public void setZagjCn(String zagjCn) {
		this.zagjCn = zagjCn;
	}

	public String getSdtdCn() {
		return sdtdCn;
	}

	public void setSdtdCn(String sdtdCn) {
		this.sdtdCn = sdtdCn;
	}

	public String getXzsjCn() {
		return xzsjCn;
	}

	public void setXzsjCn(String xzsjCn) {
		this.xzsjCn = xzsjCn;
	}

	public String getXzcsCn() {
		return xzcsCn;
	}

	public void setXzcsCn(String xzcsCn) {
		this.xzcsCn = xzcsCn;
	}

	public String getXzdxCn() {
		return xzdxCn;
	}

	public void setXzdxCn(String xzdxCn) {
		this.xzdxCn = xzdxCn;
	}

	public String getSsje() {
		return ssje;
	}

	public void setSsje(String ssje) {
		this.ssje = ssje;
	}

	public String getSwqk() {
		return swqk;
	}

	public void setSwqk(String swqk) {
		this.swqk = swqk;
	}

	public String getWfjl() {
		return wfjl;
	}

	public void setWfjl(String wfjl) {
		this.wfjl = wfjl;
	}

	public String getWfqk() {
		return wfqk;
	}

	public void setWfqk(String wfqk) {
		this.wfqk = wfqk;
	}

	public String getRybh() {
		return rybh;
	}

	public void setRybh(String rybh) {
		this.rybh = rybh;
	}

	public String getHjdz() {
		return hjdz;
	}

	public void setHjdz(String hjdz) {
		this.hjdz = hjdz;
	}
}

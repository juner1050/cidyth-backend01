package com.hyzs.cidyth.module.base.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.codehaus.plexus.util.StringUtils;

import com.hyzs.cidyth.common.utils.DateUtil;


/**
 * 涉案人员比中信息
 * 
 * @author jidw
 *
 */
public class TechnologyCompare implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7100412017800389856L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String bzly;// 比中来源(数据来源) 0-DNA比中，1-指纹比中
	private Date createDate;// 比中时间
	private String xchjbh;// 现场痕迹编号
	private String tqbw;// 提取部位

	private String jqbh;// 警情编号
	private String ajbh;// 案件编号
	private String xkbh;// 现勘编号
	private String zwbh;// 指纹编号
	private String dna;// DNA
	private String ajlb;// 案件类别
	private String afsj;// 发案时间
	private String afdd;// 案发地点
	private String jyaq;// 简要案情

	private String rybh;// 人员编号

	private String rywzbh;// 人员采样作业编号
	private String xm;// 姓名
	private String birthday;
	private String sfzh;// 身份证号
	private String hjd;// 户籍地

	public String getBzly() {
		return bzly;
	}

	public void setBzly(String bzly) {
		this.bzly = bzly;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getXchjbh() {
		return xchjbh;
	}

	public void setXchjbh(String xchjbh) {
		this.xchjbh = xchjbh;
	}

	public String getTqbw() {
		return tqbw;
	}

	public void setTqbw(String tqbw) {
		this.tqbw = tqbw;
	}

	public String getJqbh() {
		return jqbh;
	}

	public void setJqbh(String jqbh) {
		this.jqbh = jqbh;
	}

	public String getAjbh() {
		return ajbh;
	}

	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}

	public String getXkbh() {
		return xkbh;
	}

	public void setXkbh(String xkbh) {
		this.xkbh = xkbh;
	}

	public String getZwbh() {
		return zwbh;
	}

	public void setZwbh(String zwbh) {
		this.zwbh = zwbh;
	}

	public String getAjlb() {
		return ajlb;
	}

	public void setAjlb(String ajlb) {
		this.ajlb = ajlb;
	}

	public String getAfsj() {
		return afsj;
	}

	public void setAfsj(String afsj) {
		this.afsj = afsj;
	}

	public String getAfdd() {
		return afdd;
	}

	public void setAfdd(String afdd) {
		this.afdd = afdd;
	}

	public String getJyaq() {
		return jyaq;
	}

	public void setJyaq(String jyaq) {
		this.jyaq = jyaq;
	}

	public String getRybh() {
		return rybh;
	}

	public void setRybh(String rybh) {
		this.rybh = rybh;
	}

	public String getRywzbh() {
		return rywzbh;
	}

	public void setRywzbh(String rywzbh) {
		this.rywzbh = rywzbh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getSfzh() {
		return sfzh;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getHjd() {
		return hjd;
	}

	public void setHjd(String hjd) {
		this.hjd = hjd;
	}
	/**
	 * 生日
	 * @return
	 */
	public String getBirthday() {
		String birthday="";
		if (StringUtils.isNotBlank(this.sfzh) && Pattern.compile("^\\d+$").matcher(this.sfzh).find()
				&& this.sfzh.length() >= 15) {
			try {
				birthday = DateUtil.dateToStr(new SimpleDateFormat("yyyyMMdd").parse(this.sfzh.substring(6, 14)), "yyyy-MM-dd");
			} catch (ParseException e) {
				
			}
		}
		return birthday;
	}
}

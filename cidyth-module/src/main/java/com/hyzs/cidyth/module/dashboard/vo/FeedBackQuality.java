package com.hyzs.cidyth.module.dashboard.vo;
/**
 * 反馈质量
 * @author jidw
 *
 */
public class FeedBackQuality implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6117403625679801686L;
	
	private int excellent;//优
	private int well;//良
	private int good;//好
	private int generic;//一般
	private int inferior;//差
	
	public int getExcellent() {
		return excellent;
	}
	public void setExcellent(int excellent) {
		this.excellent = excellent;
	}
	public int getWell() {
		return well;
	}
	public void setWell(int well) {
		this.well = well;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getGeneric() {
		return generic;
	}
	public void setGeneric(int generic) {
		this.generic = generic;
	}
	public int getInferior() {
		return inferior;
	}
	public void setInferior(int inferior) {
		this.inferior = inferior;
	}
}

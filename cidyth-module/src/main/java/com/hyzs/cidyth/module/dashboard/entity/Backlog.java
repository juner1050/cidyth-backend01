package com.hyzs.cidyth.module.dashboard.entity;

import java.util.Date;

import com.hyzs.cidyth.module.demand.entity.Demand;
import com.hyzs.cidyth.module.demand.entity.DemandFlowHis;

/**
 * 代办事项entity
 * 
 * @author jidw
 *
 */
public class Backlog {
	private Demand demand;
	private DemandFlowHis demandFlowHis;
	private long milliseconedOfTimeout;//超时毫秒数
	public Backlog() {
		demand = new Demand();
		demandFlowHis = new DemandFlowHis();
	}

	/**
	 * 需求id
	 * 
	 * @return
	 */
	public Integer getId() {
		return demand.getId();
	}

	public void setId(Integer id) {
		demand.setId(id);
		demandFlowHis.setXqid(id);
	}

	/**
	 * 案件编号
	 * 
	 * @return
	 */
	public String getAjbh() {
		return demand.getAjbh();
	}

	public void setAjbh(String ajbh) {
		demand.setAjbh(ajbh);
	}

	/**
	 * 需求名称
	 * 
	 * @return
	 */
	public String getXqmc() {
		return demand.getXqmc();
	}

	public void setXqmc(String xqmc) {
		demand.setXqmc(xqmc);
	}

	/**
	 * 需求内容
	 * 
	 * @return
	 */
	public String getXqnr() {
		return demand.getXqnr();
	}

	public void setXqnr(String xqnr) {
		demand.setXqnr(xqnr);
	}

	/**
	 * 请求单位编号
	 * 
	 * @return
	 */
	public String getQqdwbh() {
		return demand.getQqdwbh();
	}

	public void setQqdwbh(String qqdwbh) {
		demand.setQqdwbh(qqdwbh);
	}

	/**
	 * 请求单位
	 * 
	 * @return
	 */
	public String getQqdw() {
		return demand.getQqdw();
	}

	public void setQqdw(String qqdw) {
		demand.setQqdw(qqdw);
	}

	/**
	 * 请求发起人警号
	 * 
	 * @return
	 */
	public String getLrry() {
		return demand.getLrry();
	}

	public void setLrry(String lrry) {
		demand.setLrry(lrry);
	}

	/**
	 * 请求发起人姓名
	 * 
	 * @return
	 */
	public String getLrrymc() {
		return demand.getLrrymc();
	}

	public void setLrrymc(String lrrymc) {
		demand.setLrrymc(lrrymc);
	}

	/**
	 * 录入时间
	 * 
	 * @return
	 */
	public Date getLrsj() {
		return demand.getLrsj();
	}

	public void setLrsj(Date lrsj) {
		demand.setLrsj(lrsj);
	}

	/**
	 * 指派人员机构编号
	 * 
	 * @return
	 */
	public String getZpryjgbh() {
		return demandFlowHis.getZpryjgbh();
	}

	public void setZpryjgbh(String zpryjgbh) {
		demandFlowHis.setZpryjgbh(zpryjgbh);
	}

	/**
	 * 指派人员机构名称
	 * 
	 * @return
	 */
	public String getZpryjgmc() {
		return demandFlowHis.getZpryjgmc();
	}

	public void setZpryjgmc(String zpryjgmc) {
		demandFlowHis.setZpryjgmc(zpryjgmc);
	}

	/**
	 * 指派人员警号
	 * 
	 * @return
	 */
	public String getZprybh() {
		return demandFlowHis.getZprybh();
	}

	public void setZprybh(String zprybh) {
		demandFlowHis.setZprybh(zprybh);
	}

	/**
	 * 指派人姓名
	 * 
	 * @return
	 */
	public String getZprymc() {
		return demandFlowHis.getZprymc();
	}

	public void setZprymc(String zprymc) {
		demandFlowHis.setZprymc(zprymc);
	}

	/**
	 * 接受人员机构编号
	 * 
	 * @return
	 */
	public String getJsryjgbh() {
		return demandFlowHis.getJsryjgbh();
	}

	public void setJsryjgbh(String jsryjgbh) {
		demandFlowHis.setJsryjgbh(jsryjgbh);
	}

	/**
	 * 接受人员机构名称
	 * 
	 * @return
	 */
	public String getJsryjgmc() {
		return demandFlowHis.getJsryjgmc();
	}

	public void setJsryjgmc(String jsryjgmc) {
		demandFlowHis.setJsryjgmc(jsryjgmc);
	}

	/**
	 * 接受人警号
	 * 
	 * @return
	 */
	public String getJsrybh() {
		return demandFlowHis.getJsrybh();
	}

	public void setJsrybh(String jsrybh) {
		demandFlowHis.setJsrybh(jsrybh);
	}

	/**
	 * 接受人姓名
	 * 
	 * @param zprymc
	 */
	public String getJsrymc() {
		return demandFlowHis.getJsrymc();
	}

	public void setJsrymc(String jsrymc) {
		demandFlowHis.setJsrymc(jsrymc);
	}

	/**
	 * 当前步骤的操作状态: 0.INIT(待指派); 20.WAIT_FOR_SIGN(待签收); 30.SIGNED(已签收)
	 * 
	 * @see com.hyzs.cidyth.common.enums.DemandFlowStepStatus
	 * 
	 * @return
	 */
	public String getQszt() {
		return demandFlowHis.getQszt();
	}

	public void setQszt(String qszt) {
		demandFlowHis.setQszt(qszt);
	}
	/**
	 * 超时毫秒数
	 * @return 超时毫秒数
	 */
	public long getMilliseconedOfTimeout() {
		return milliseconedOfTimeout;
	}

	public void setMilliseconedOfTimeout(long milliseconedOfTimeout) {
		this.milliseconedOfTimeout = milliseconedOfTimeout;
	}

	
}

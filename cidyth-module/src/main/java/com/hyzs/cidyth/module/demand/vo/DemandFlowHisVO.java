package com.hyzs.cidyth.module.demand.vo;

import com.hyzs.cidyth.module.demand.entity.DemandFlowHis;
import io.swagger.annotations.ApiModelProperty;

/**
 * 需求操作(流转)记录
 * 
 * @author Administrator
 *
 */
public class DemandFlowHisVO extends DemandFlowHis {

	/**
	 * 紧急程度（需求表）
	 */
	@ApiModelProperty(value="紧急程度")
	private Integer jjcd;

	public Integer getJjcd() {
		return jjcd;
	}
	/**
	 * 节点状态
	 */
	@ApiModelProperty(value="节点状态")
	public String qsztCn;
	/**
	 * 是否发送短信通知（true/false）
	 */
	@ApiModelProperty(value="是否发送短信通知（true/false）")
	private boolean sendMessage;

	/**
	 * 案件编号
	 */
	@ApiModelProperty(value="案件编号")
	private String ajbh;

	public void setJjcd(Integer jjcd) {
		this.jjcd = jjcd;
	}

	public boolean isSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(boolean sendMessage) {
		this.sendMessage = sendMessage;
	}

	public String getQsztCn() {
		return qsztCn;
	}

	public void setQsztCn(String qsztCn) {
		this.qsztCn = qsztCn;
	}

	public String getAjbh() {
		return ajbh;
	}

	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}
}
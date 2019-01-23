package com.hyzs.cidyth.module.base.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@ApiModel(value="CasesAbortDTO",description="案件挂起")
public class CasesAbortDTO {

	/**
	 * 案件编号
	 */
	@ApiModelProperty(value="案件编号", required = true)
	private String ajbh;
	/**
	 * 受理接收单位
	 */
	@ApiModelProperty(value="说明备注", required = true)
	private String bdajstatebz;

	public String getAjbh() {
		return ajbh;
	}

	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}

	public String getBdajstatebz() {
		return bdajstatebz;
	}

	public void setBdajstatebz(String bdajstatebz) {
		this.bdajstatebz = bdajstatebz;
	}

}

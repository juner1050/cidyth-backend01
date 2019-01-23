package com.hyzs.cidyth.module.base.dto;

import com.hyzs.cidyth.module.clue.vo.ClueAppraiseSave;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@ApiModel(value="CasesFinishDTO",description="案件侦结")
public class CasesFinishDTO {

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

	/**
	 * 关键线索奖励
	 */
	@ApiModelProperty(value="关键线索奖励")
	private List<ClueAppraiseSave> lsClueAppraiseSave;

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

	public List<ClueAppraiseSave> getLsClueAppraiseSave() {
		return lsClueAppraiseSave;
	}

	public void setLsClueAppraiseSave(List<ClueAppraiseSave> lsClueAppraiseSave) {
		this.lsClueAppraiseSave = lsClueAppraiseSave;
	}
}

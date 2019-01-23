package com.hyzs.cidyth.module.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 合成作战案件情况
 * @author Administrator
 *
 */
@ApiModel("PersonalCasesVO：本月合成作战案件情况")
public class PersonalCasesVO {

	/**
	 * 案件：编号
	 */
	@ApiModelProperty("案件编号")
	private String ajbh;

	/**
	 * 案件：名称
	 */

	@ApiModelProperty("案件名称")
	private String ajmc;

	/**
	 * 案件：主办人员
	 */
	@ApiModelProperty("主办人员")
	private String ajzbryCn;

	/**
	 * 案件：主办单位
	 */
	@ApiModelProperty("主办单位")
	private String zbdwCn;

	/**
	 * 案件：本地状态
	 */
	@ApiModelProperty("案件状态")
	private String bdajstateCn;

	/**
	 * 案件：本案得分
	 */
	@ApiModelProperty("本案得分")
	private Float score;

	/**
	 * 案件：本案积分排名
	 */
	@ApiModelProperty("本案积分排名")
	private Integer scoreRank;
	
	/**
	 * 案件：参与人数
	 */
	@ApiModelProperty("参与人数")
	private Integer joinPersonNum;

	public String getAjbh() {
		return ajbh;
	}

	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}

	public String getAjmc() {
		return ajmc;
	}

	public void setAjmc(String ajmc) {
		this.ajmc = ajmc;
	}

	public String getAjzbryCn() {
		return ajzbryCn;
	}

	public void setAjzbryCn(String ajzbryCn) {
		this.ajzbryCn = ajzbryCn;
	}

	public String getZbdwCn() {
		return zbdwCn;
	}

	public void setZbdwCn(String zbdwCn) {
		this.zbdwCn = zbdwCn;
	}

	public String getBdajstateCn() {
		return bdajstateCn;
	}

	public void setBdajstateCn(String bdajstateCn) {
		this.bdajstateCn = bdajstateCn;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Integer getScoreRank() {
		return scoreRank;
	}

	public void setScoreRank(Integer scoreRank) {
		this.scoreRank = scoreRank;
	}

	public Integer getJoinPersonNum() {
		return joinPersonNum;
	}

	public void setJoinPersonNum(Integer joinPersonNum) {
		this.joinPersonNum = joinPersonNum;
	}
}

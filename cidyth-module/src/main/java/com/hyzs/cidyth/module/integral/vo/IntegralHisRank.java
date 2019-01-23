package com.hyzs.cidyth.module.integral.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class IntegralHisRank {

    /**
     * 排名
     */
    @ApiModelProperty("排名")
    private Integer rank;
    /**
     * 头衔
     */
    @ApiModelProperty("头衔")
    private String title;
    /**
     * 案件编号
     */
    @ApiModelProperty("案件编号")
    private String ajbh;
    /**
     * 奖励人员编号
     */
    @ApiModelProperty("奖励人员编号")
    private String jlry;
    /**
     * 奖励人员名称
     */
    @ApiModelProperty("奖励人员名称")
    private String jlrymc;
    /**
     * 奖励人员机构编号
     */
    @ApiModelProperty("奖励人员机构编号")
    private String jlryjg;
    /**
     * 奖励人员机构名称
     */
    @ApiModelProperty("奖励人员机构名称")
    private String jlryjgmc;
    /**
     * 积分
     */
    @ApiModelProperty("积分")
    private Double score;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getAjbh() {
        return ajbh;
    }

    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

    public String getJlry() {
        return jlry;
    }

    public void setJlry(String jlry) {
        this.jlry = jlry;
    }

    public String getJlrymc() {
        return jlrymc;
    }

    public void setJlrymc(String jlrymc) {
        this.jlrymc = jlrymc;
    }

    public String getJlryjg() {
        return jlryjg;
    }

    public void setJlryjg(String jlryjg) {
        this.jlryjg = jlryjg;
    }

    public String getJlryjgmc() {
        return jlryjgmc;
    }

    public void setJlryjgmc(String jlryjgmc) {
        this.jlryjgmc = jlryjgmc;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
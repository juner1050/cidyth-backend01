package com.hyzs.cidyth.module.integral.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 参与人数积分排名
 */
public class CasesJoinRankVO {

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String jlrymc;
    /**
     * 单位名称
     */
    @ApiModelProperty("单位名称")
    private String jlryjgmc;
    /**
     * 积分头衔
     */
    @ApiModelProperty("积分头衔")
    private String title;
    /**
     * 本案得分
     */
    @ApiModelProperty("本案得分")
    private Float score;
    /**
     * 积分排名
     */
    @ApiModelProperty("积分排名")
    private Integer rank;
    /**
     * 积分比例
     */
    @ApiModelProperty("积分比例")
    private Float scoreRatio;

    public String getJlrymc() {
        return jlrymc;
    }

    public void setJlrymc(String jlrymc) {
        this.jlrymc = jlrymc;
    }

    public String getJlryjgmc() {
        return jlryjgmc;
    }

    public void setJlryjgmc(String jlryjgmc) {
        this.jlryjgmc = jlryjgmc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Float getScoreRatio() {
        return scoreRatio;
    }

    public void setScoreRatio(Float scoreRatio) {
        this.scoreRatio = scoreRatio;
    }
}
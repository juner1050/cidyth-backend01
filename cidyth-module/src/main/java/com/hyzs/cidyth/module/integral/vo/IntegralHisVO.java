package com.hyzs.cidyth.module.integral.vo;

import com.hyzs.cidyth.module.integral.entity.IntegralHis;
import io.swagger.annotations.ApiModelProperty;

public class IntegralHisVO extends IntegralHis {

    /**
     * 积分排名
     */
    @ApiModelProperty("案件积分排名")
    private Integer rank;
    /**
     * 积分头衔
     */
    @ApiModelProperty("积分头衔")
    private String title;
    /**
     * 积分比例
     */
    @ApiModelProperty("积分比例")
    private Float scoreRatio;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getScoreRatio() {
        return scoreRatio;
    }

    public void setScoreRatio(Float scoreRatio) {
        this.scoreRatio = scoreRatio;
    }
}
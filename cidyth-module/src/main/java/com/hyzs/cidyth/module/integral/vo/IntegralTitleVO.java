package com.hyzs.cidyth.module.integral.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by 1 on 2018/6/12.
 */
public class IntegralTitleVO {

    /**
     * 主键
     */
    @ApiModelProperty("id")
    private Integer id;
    /**
     * 头衔名称
     */
    @ApiModelProperty(value = "头衔名称", required = true)
    private String name;
    /**
     * 头衔头像：base64图片
     */
    @ApiModelProperty("头衔头像：base64图片")
    private String avatar;
    /**
     * 上限人数
     */
    @ApiModelProperty(value = "上限人数", required = true)
    private Integer upperLimit;
    /**
     * 下限人数
     */
    @ApiModelProperty(value = "下限人数", required = true)
    private Integer lowerLimit;
    /**
     * 录入时间
     */
    @ApiModelProperty("录入时间")
    private Date lrsj;
    /**
     * 录入人员名称
     */
    @ApiModelProperty("录入人员名称")
    private String lrrymc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Integer getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Integer lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public String getLrrymc() {
        return lrrymc;
    }

    public void setLrrymc(String lrrymc) {
        this.lrrymc = lrrymc;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

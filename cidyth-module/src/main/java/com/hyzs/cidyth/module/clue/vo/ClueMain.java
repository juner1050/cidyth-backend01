package com.hyzs.cidyth.module.clue.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 案件的线索对象
 * Created by 1 on 2018/6/20.
 */
@ApiModel("ClueMain：关键线索")
public class ClueMain {

    /**
     * 线索id
     */
    @ApiModelProperty("线索id")
    private Integer id;
    /**
     * 线索主题
     */
    @ApiModelProperty("线索主题")
    private String theme;
    /**
     * 线索内容
     */
    @ApiModelProperty("线索内容")
    private String xsnr;
    /**
     * 反馈单位
     */
    @ApiModelProperty("反馈单位")
    private String fkdw;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getXsnr() {
        return xsnr;
    }

    public void setXsnr(String xsnr) {
        this.xsnr = xsnr;
    }

    public String getFkdw() {
        return fkdw;
    }

    public void setFkdw(String fkdw) {
        this.fkdw = fkdw;
    }

    public String getLrrymc() {
        return lrrymc;
    }

    public void setLrrymc(String lrrymc) {
        this.lrrymc = lrrymc;
    }
}

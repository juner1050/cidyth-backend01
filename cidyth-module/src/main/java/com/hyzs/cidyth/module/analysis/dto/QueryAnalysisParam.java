package com.hyzs.cidyth.module.analysis.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查询列表参数对象
 * Created by 1 on 2018/10/30.
 */
public class QueryAnalysisParam {

    /**
     * 线索名称
     */
    @ApiModelProperty(value = "线索名称")
    private String xsmc;
    /**
     * 录入人员名称
     */
    @ApiModelProperty(value = "创建人")
    private String lrrymc;
    /**
     * 办理状态
     */
    @ApiModelProperty(value = "办理状态")
    private String blzt;
    /**
     * 发送时间开始
     */
    @ApiModelProperty(value = "发送时间开始")
    private String fssjks;
    /**
     * 发送时间结束
     */
    @ApiModelProperty(value = "发送时间结束")
    private String fssjjs;
    /**
     * 录入人员
     */
    private String lrry;

    public String getXsmc() {
        return xsmc;
    }

    public void setXsmc(String xsmc) {
        this.xsmc = xsmc;
    }

    public String getLrrymc() {
        return lrrymc;
    }

    public void setLrrymc(String lrrymc) {
        this.lrrymc = lrrymc;
    }

    public String getBlzt() {
        return blzt;
    }

    public void setBlzt(String blzt) {
        this.blzt = blzt;
    }

    public String getFssjks() {
        return fssjks;
    }

    public void setFssjks(String fssjks) {
        this.fssjks = fssjks;
    }

    public String getFssjjs() {
        return fssjjs;
    }

    public void setFssjjs(String fssjjs) {
        this.fssjjs = fssjjs;
    }

    public String getLrry() {
        return lrry;
    }

    public void setLrry(String lrry) {
        this.lrry = lrry;
    }
}

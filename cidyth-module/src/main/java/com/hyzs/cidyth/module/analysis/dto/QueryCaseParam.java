package com.hyzs.cidyth.module.analysis.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查询列表参数对象
 * Created by 1 on 2018/10/30.
 */
public class QueryCaseParam {

    /**
     * 案件编号
     */
    @ApiModelProperty(value = "案件编号")
    private String ajbh;

    /**
     * 案件名称
     */
    @ApiModelProperty(value = "案件名称")
    private String ajmc;

    /**
     * 主办单位
     */
    @ApiModelProperty(value = "主办单位")
    private String zbdw;

    /**
     * 主办人员
     */
    @ApiModelProperty(value = "主办人员")
    private String ajzbry;

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

    public String getZbdw() {
        return zbdw;
    }

    public void setZbdw(String zbdw) {
        this.zbdw = zbdw;
    }

    public String getAjzbry() {
        return ajzbry;
    }

    public void setAjzbry(String ajzbry) {
        this.ajzbry = ajzbry;
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
}

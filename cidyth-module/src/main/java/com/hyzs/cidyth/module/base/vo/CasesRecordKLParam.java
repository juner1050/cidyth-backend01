package com.hyzs.cidyth.module.base.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 1 on 2018/6/4.
 */
@ApiModel(value = "CasesRecordKLParam（人员编号、案件编号、身份证号）", description = "考拉系统电子笔录的查询条件")
public class CasesRecordKLParam {

    /**
     * 人员编号
     */
    @ApiModelProperty("人员编号")
    private String rybh;
    /**
     * 案件编号
     */
    @ApiModelProperty("案件编号")
    private String ajbh;
    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String sfzh;

    public String getRybh() {
        return rybh;
    }

    public void setRybh(String rybh) {
        this.rybh = rybh;
    }

    public String getAjbh() {
        return ajbh;
    }

    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }
}

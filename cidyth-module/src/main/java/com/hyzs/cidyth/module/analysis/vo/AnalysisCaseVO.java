package com.hyzs.cidyth.module.analysis.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class AnalysisCaseVO {

    /**
     * 案件编号
     */
    @ApiModelProperty("案件编号")
    private String ajbh;

    /**
     * 案件名称
     */
    @ApiModelProperty("案件名称")
    private String ajmc;

    /**
     * 主要案情
     */
    @ApiModelProperty("主要案情")
    private String zyaq;

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

    public String getZyaq() {
        return zyaq;
    }

    public void setZyaq(String zyaq) {
        this.zyaq = zyaq;
    }
}
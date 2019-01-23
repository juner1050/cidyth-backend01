package com.hyzs.cidyth.module.analysis.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AnalysisCaseDetailVO {

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

    /**
     * 案发时间
     */
    @ApiModelProperty("案发时间")
    private String fasjcz;

    /**
     * 本地案件状态
     */
    @ApiModelProperty("本地案件状态")
    private String bdajstate;

    /**
     * 案件类型
     */
    @ApiModelProperty("案件类型")
    private String ajlx;

    /**
     * 主办侦查员
     */
    @ApiModelProperty("主办侦查员")
    private String ajzbry;

    /**
     * 主办单位
     */
    @ApiModelProperty("主办单位")
    private String zbdw;

    /**
     * 文件编号集合
     */
    @ApiModelProperty("文件编号集合")
    private List<String> fileIds;

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

    public String getFasjcz() {
        return fasjcz;
    }

    public void setFasjcz(String fasjcz) {
        this.fasjcz = fasjcz;
    }

    public String getBdajstate() {
        return bdajstate;
    }

    public void setBdajstate(String bdajstate) {
        this.bdajstate = bdajstate;
    }

    public String getAjlx() {
        return ajlx;
    }

    public void setAjlx(String ajlx) {
        this.ajlx = ajlx;
    }

    public String getAjzbry() {
        return ajzbry;
    }

    public void setAjzbry(String ajzbry) {
        this.ajzbry = ajzbry;
    }

    public String getZbdw() {
        return zbdw;
    }

    public void setZbdw(String zbdw) {
        this.zbdw = zbdw;
    }

    public List<String> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<String> fileIds) {
        this.fileIds = fileIds;
    }
}
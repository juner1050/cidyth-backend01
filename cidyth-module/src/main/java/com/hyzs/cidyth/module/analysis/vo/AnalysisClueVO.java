package com.hyzs.cidyth.module.analysis.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class AnalysisClueVO {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Integer id;

    /**
     * 研判编号
     */
    @ApiModelProperty("研判编号")
    private String xsbh;

    /**
     * 研判名称
     */
    @ApiModelProperty("研判名称")
    private String xsmc;

    /**
     * 案件类型
     */
    @ApiModelProperty("案件类型")
    private String ab;

    /**
     * 案件数量
     */
    @ApiModelProperty("案件数量")
    private Integer ajsl;

    /**
     * 接收单位名称
     */
    @ApiModelProperty("接收单位名称")
    private String jsdwmc;

    /**
     * 办理状态
     */
    @ApiModelProperty("办理状态")
    private String blzt;

    /**
     * 发送单位名称
     */
    @ApiModelProperty("发送单位名称")
    private String fsdwmc;

    /**
     * 发送人员名称
     */
    @ApiModelProperty("发送人员名称")
    private String lrrymc;

    /**
     * 录入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("录入时间")
    private Date lrsj;

    /**
     * 操作状态
     */
    @ApiModelProperty("操作状态")
    private String operateStatus;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取分析线索编号
     *
     * @return xsbh - 分析线索编号
     */
    public String getXsbh() {
        return xsbh;
    }

    /**
     * 设置分析线索编号
     *
     * @param xsbh 分析线索编号
     */
    public void setXsbh(String xsbh) {
        this.xsbh = xsbh;
    }

    /**
     * 获取分析线索名称
     *
     * @return xsmc - 分析线索名称
     */
    public String getXsmc() {
        return xsmc;
    }

    /**
     * 设置分析线索名称
     *
     * @param xsmc 分析线索名称
     */
    public void setXsmc(String xsmc) {
        this.xsmc = xsmc;
    }

    /**
     * 获取接收单位名称
     *
     * @return jsdwmc - 接收单位名称
     */
    public String getJsdwmc() {
        return jsdwmc;
    }

    /**
     * 设置接收单位名称
     *
     * @param jsdwmc 接收单位名称
     */
    public void setJsdwmc(String jsdwmc) {
        this.jsdwmc = jsdwmc;
    }

    /**
     * 获取办理状态
     *
     * @return blzt - 办理状态
     */
    public String getBlzt() {
        return blzt;
    }

    /**
     * 设置办理状态
     *
     * @param blzt 办理状态
     */
    public void setBlzt(String blzt) {
        this.blzt = blzt;
    }

    /**
     * 获取录入人员名称
     *
     * @return lrrymc - 录入人员名称
     */
    public String getLrrymc() {
        return lrrymc;
    }

    /**
     * 设置录入人员名称
     *
     * @param lrrymc 录入人员名称
     */
    public void setLrrymc(String lrrymc) {
        this.lrrymc = lrrymc;
    }

    /**
     * 获取录入时间
     *
     * @return lrsj - 录入时间
     */
    public Date getLrsj() {
        return lrsj;
    }

    /**
     * 设置录入时间
     *
     * @param lrsj 录入时间
     */
    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public String getOperateStatus() {
        return operateStatus;
    }

    public void setOperateStatus(String operateStatus) {
        this.operateStatus = operateStatus;
    }

    public String getAb() {
        return ab;
    }

    public void setAb(String ab) {
        this.ab = ab;
    }

    public Integer getAjsl() {
        return ajsl;
    }

    public void setAjsl(Integer ajsl) {
        this.ajsl = ajsl;
    }

    public String getFsdwmc() {
        return fsdwmc;
    }

    public void setFsdwmc(String fsdwmc) {
        this.fsdwmc = fsdwmc;
    }
}
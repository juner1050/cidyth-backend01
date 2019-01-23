package com.hyzs.cidyth.module.analysis.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_analysis_clue")
public class AnalysisClue {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分析线索编号
     */
    private String xsbh;

    /**
     * 案别
     */
    private String ab;

    /**
     * 分析线索名称
     */
    private String xsmc;

    /**
     * 分析线索说明
     */
    private String smbz;

    /**
     * 下步侦查工作意见
     */
    private String gzyj;

    /**
     * 接收单位编号
     */
    private String jsdwbh;

    /**
     * 接收单位名称
     */
    private String jsdwmc;

    /**
     * 办理状态
     */
    private String blzt;

    /**
     * 发送单位编号
     */
    private String fsdwbh;

    /**
     * 发送单位名称
     */
    private String fsdwmc;
    /**
     * 研判时间
     */
    private Date ypsj;
    /**
     * 录入人员
     */
    private String lrry;

    /**
     * 录入人员名称
     */
    private String lrrymc;

    /**
     * 录入时间
     */
    private Date lrsj;

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
     * 获取分析线索说明
     *
     * @return smbz - 分析线索说明
     */
    public String getSmbz() {
        return smbz;
    }

    /**
     * 设置分析线索说明
     *
     * @param smbz 分析线索说明
     */
    public void setSmbz(String smbz) {
        this.smbz = smbz;
    }

    /**
     * 获取下步侦查工作意见
     *
     * @return gzyj - 下步侦查工作意见
     */
    public String getGzyj() {
        return gzyj;
    }

    /**
     * 设置下步侦查工作意见
     *
     * @param gzyj 下步侦查工作意见
     */
    public void setGzyj(String gzyj) {
        this.gzyj = gzyj;
    }

    /**
     * 获取接收单位编号
     *
     * @return jsdwbh - 接收单位编号
     */
    public String getJsdwbh() {
        return jsdwbh;
    }

    /**
     * 设置接收单位编号
     *
     * @param jsdwbh 接收单位编号
     */
    public void setJsdwbh(String jsdwbh) {
        this.jsdwbh = jsdwbh;
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
     * 获取录入人员
     *
     * @return lrry - 录入人员
     */
    public String getLrry() {
        return lrry;
    }

    /**
     * 设置录入人员
     *
     * @param lrry 录入人员
     */
    public void setLrry(String lrry) {
        this.lrry = lrry;
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

    public String getFsdwbh() {
        return fsdwbh;
    }

    public void setFsdwbh(String fsdwbh) {
        this.fsdwbh = fsdwbh;
    }

    public String getFsdwmc() {
        return fsdwmc;
    }

    public void setFsdwmc(String fsdwmc) {
        this.fsdwmc = fsdwmc;
    }

    public Date getYpsj() {
        return ypsj;
    }

    public void setYpsj(Date ypsj) {
        this.ypsj = ypsj;
    }

    public String getAb() {
        return ab;
    }

    public void setAb(String ab) {
        this.ab = ab;
    }
}
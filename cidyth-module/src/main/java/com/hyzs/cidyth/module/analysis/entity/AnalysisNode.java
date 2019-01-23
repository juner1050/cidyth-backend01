package com.hyzs.cidyth.module.analysis.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_analysis_node")
public class AnalysisNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分析线索编号
     */
    private String xsbh;

    /**
     * 发送人员编号
     */
    private String fsrybh;

    /**
     * 发送人员名称
     */
    private String fsrymc;

    /**
     * 发送人员机构编号
     */
    private String fsryjgbh;

    /**
     * 发送人员机构名称
     */
    private String fsryjgmc;

    /**
     * 接收人员编号
     */
    private String jsrybh;

    /**
     * 接收人员名称
     */
    private String jsrymc;

    /**
     * 接收人员机构编号
     */
    private String jsryjgbh;

    /**
     * 接收人员机构名称
     */
    private String jsryjgmc;

    /**
     * 节点状态
     */
    private String jdzt;

    /**
     * 是否结束
     */
    private String sfjs;

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
     * 修改时间
     */
    private Date xgsj;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
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
     * 获取发送人员编号
     *
     * @return fsrybh - 发送人员编号
     */
    public String getFsrybh() {
        return fsrybh;
    }

    /**
     * 设置发送人员编号
     *
     * @param fsrybh 发送人员编号
     */
    public void setFsrybh(String fsrybh) {
        this.fsrybh = fsrybh;
    }

    /**
     * 获取发送人员名称
     *
     * @return fsrymc - 发送人员名称
     */
    public String getFsrymc() {
        return fsrymc;
    }

    /**
     * 设置发送人员名称
     *
     * @param fsrymc 发送人员名称
     */
    public void setFsrymc(String fsrymc) {
        this.fsrymc = fsrymc;
    }

    /**
     * 获取接收人员编号
     *
     * @return jsrybh - 接收人员编号
     */
    public String getJsrybh() {
        return jsrybh;
    }

    /**
     * 设置接收人员编号
     *
     * @param jsrybh 接收人员编号
     */
    public void setJsrybh(String jsrybh) {
        this.jsrybh = jsrybh;
    }

    /**
     * 获取接收人员名称
     *
     * @return jsrymc - 接收人员名称
     */
    public String getJsrymc() {
        return jsrymc;
    }

    /**
     * 设置接收人员名称
     *
     * @param jsrymc 接收人员名称
     */
    public void setJsrymc(String jsrymc) {
        this.jsrymc = jsrymc;
    }

    /**
     * 获取节点状态
     *
     * @return jdzt - 节点状态
     */
    public String getJdzt() {
        return jdzt;
    }

    /**
     * 设置节点状态
     *
     * @param jdzt 节点状态
     */
    public void setJdzt(String jdzt) {
        this.jdzt = jdzt;
    }

    /**
     * 获取是否结束
     *
     * @return sfjs - 是否结束
     */
    public String getSfjs() {
        return sfjs;
    }

    /**
     * 设置是否结束
     *
     * @param sfjs 是否结束
     */
    public void setSfjs(String sfjs) {
        this.sfjs = sfjs;
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

    /**
     * 获取修改时间
     *
     * @return xgsj - 修改时间
     */
    public Date getXgsj() {
        return xgsj;
    }

    /**
     * 设置修改时间
     *
     * @param xgsj 修改时间
     */
    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public String getFsryjgbh() {
        return fsryjgbh;
    }

    public void setFsryjgbh(String fsryjgbh) {
        this.fsryjgbh = fsryjgbh;
    }

    public String getFsryjgmc() {
        return fsryjgmc;
    }

    public void setFsryjgmc(String fsryjgmc) {
        this.fsryjgmc = fsryjgmc;
    }

    public String getJsryjgbh() {
        return jsryjgbh;
    }

    public void setJsryjgbh(String jsryjgbh) {
        this.jsryjgbh = jsryjgbh;
    }

    public String getJsryjgmc() {
        return jsryjgmc;
    }

    public void setJsryjgmc(String jsryjgmc) {
        this.jsryjgmc = jsryjgmc;
    }
}
package com.hyzs.cidyth.module.base.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 比中信息表
 */
@Table(name = "t_xj_bzxx")
public class CasesComparison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * 比中时间
     */
    private String bzsj;
    /**
     * 比中来源（0、DNA    1、指纹）
     */
    private String bzly;
    /**
     * 提取部位
     */
    private String tqbw;
    /**
     * 警情编号
     */
    private String jqbh;
    /**
     * 案件编号
     */
    private String ajbh;
    /**
     * 勘验编号
     */
    private String kybh;
    /**
     * 物证编号
     */
    private String wzbh;
    /**
     * 比中来源编号（bzly字段为具体类型，则代表具体编号）
     */
    private String bzlybh;
    /**
     * 人员编号
     */
    private String rybh;
    /**
     * 姓名
     */
    private String xm;
    /**
     * 证件号码
     */
    private String zjhm;
    /**
     * 出生日期
     */
    private String csrq;
    /**
     * 户籍地
     */
    private String hjd;
    /**
     * 录入时间
     */
    private Date lrsj;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBzsj() {
        return bzsj;
    }

    public void setBzsj(String bzsj) {
        this.bzsj = bzsj;
    }

    public String getBzly() {
        return bzly;
    }

    public void setBzly(String bzly) {
        this.bzly = bzly;
    }

    public String getTqbw() {
        return tqbw;
    }

    public void setTqbw(String tqbw) {
        this.tqbw = tqbw;
    }

    public String getJqbh() {
        return jqbh;
    }

    public void setJqbh(String jqbh) {
        this.jqbh = jqbh;
    }

    public String getAjbh() {
        return ajbh;
    }

    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

    public String getKybh() {
        return kybh;
    }

    public void setKybh(String kybh) {
        this.kybh = kybh;
    }

    public String getWzbh() {
        return wzbh;
    }

    public void setWzbh(String wzbh) {
        this.wzbh = wzbh;
    }

    public String getBzlybh() {
        return bzlybh;
    }

    public void setBzlybh(String bzlybh) {
        this.bzlybh = bzlybh;
    }

    public String getRybh() {
        return rybh;
    }

    public void setRybh(String rybh) {
        this.rybh = rybh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getHjd() {
        return hjd;
    }

    public void setHjd(String hjd) {
        this.hjd = hjd;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }
}
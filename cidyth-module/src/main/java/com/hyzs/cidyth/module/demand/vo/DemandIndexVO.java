package com.hyzs.cidyth.module.demand.vo;

import java.util.Date;

/**
 * Created by 1 on 2018/10/29.
 * 首页的需求通用实体
 */
public class DemandIndexVO {

    private String xqmc;
    private String lrrymc;
    private String qqdw;
    private Date qqsj;
    private String jsrymc;
    private String jsdw;

    public String getXqmc() {
        return xqmc;
    }

    public void setXqmc(String xqmc) {
        this.xqmc = xqmc;
    }

    public String getLrrymc() {
        return lrrymc;
    }

    public void setLrrymc(String lrrymc) {
        this.lrrymc = lrrymc;
    }

    public String getQqdw() {
        return qqdw;
    }

    public void setQqdw(String qqdw) {
        this.qqdw = qqdw;
    }

    public Date getQqsj() {
        return qqsj;
    }

    public void setQqsj(Date qqsj) {
        this.qqsj = qqsj;
    }

    public String getJsrymc() {
        return jsrymc;
    }

    public void setJsrymc(String jsrymc) {
        this.jsrymc = jsrymc;
    }

    public String getJsdw() {
        return jsdw;
    }

    public void setJsdw(String jsdw) {
        this.jsdw = jsdw;
    }
}

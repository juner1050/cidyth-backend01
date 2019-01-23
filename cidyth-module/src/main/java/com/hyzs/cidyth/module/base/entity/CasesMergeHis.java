package com.hyzs.cidyth.module.base.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_merge_his")
public class CasesMergeHis {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;
    /**
     * 串并案编号
     */
    private String cbabh;
    /**
     * 研判编号
     */
    private String xsbh;
    /**
     * 案件编号
     */
    private String ajbh;
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

    public String getCbabh() {
        return cbabh;
    }

    public void setCbabh(String cbabh) {
        this.cbabh = cbabh;
    }

    public String getXsbh() {
        return xsbh;
    }

    public void setXsbh(String xsbh) {
        this.xsbh = xsbh;
    }

    public String getAjbh() {
        return ajbh;
    }

    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }
}
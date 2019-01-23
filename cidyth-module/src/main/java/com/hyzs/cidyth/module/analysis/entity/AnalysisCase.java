package com.hyzs.cidyth.module.analysis.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_analysis_case")
public class AnalysisCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分析线索编号
     */
    private String xsbh;

    /**
     * 案件编号
     */
    private String ajbh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
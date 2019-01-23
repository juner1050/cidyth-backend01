package com.hyzs.cidyth.module.appraise.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * 线索
 * @author derrick
 *
 */
@Table(name = "t_clue_appraise")
public class ClueAppraise {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="JDBC")
    private Integer id;
    /**
     * 线索id
     */
    @ApiModelProperty("线索id")
    private Integer clueId;
    /**
     * 评分人单位编号
     */
    @ApiModelProperty("评分人单位编号")
    private String pfrdwbh;
    /**
     * 评分人单位名称
     */
    @ApiModelProperty("评分人单位名称")
    private String pfrdwmc;
    /**
     * 评分人编号
     */
    @ApiModelProperty("评分人编号")
    private String pfrbh;
    /**
     * 评分人姓名
     */
    @ApiModelProperty("评分人姓名")
    private String pfrxm;
    /**
     * 录入时间
     */
    @ApiModelProperty("录入时间")
    private Date lrsj;
    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date xgsj;
    /**
     * 评分分值
     */
    @ApiModelProperty("评分分值")
    private Float pffz;
    /**
     * 评分内容
     */
    @ApiModelProperty("评分内容")
    private String pfnr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClueId() {
        return clueId;
    }

    public void setClueId(Integer clueId) {
        this.clueId = clueId;
    }

    public String getPfrdwbh() {
        return pfrdwbh;
    }

    public void setPfrdwbh(String pfrdwbh) {
        this.pfrdwbh = pfrdwbh;
    }

    public String getPfrdwmc() {
        return pfrdwmc;
    }

    public void setPfrdwmc(String pfrdwmc) {
        this.pfrdwmc = pfrdwmc;
    }

    public String getPfrbh() {
        return pfrbh;
    }

    public void setPfrbh(String pfrbh) {
        this.pfrbh = pfrbh;
    }

    public String getPfrxm() {
        return pfrxm;
    }

    public void setPfrxm(String pfrxm) {
        this.pfrxm = pfrxm;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public Date getXgsj() {
        return xgsj;
    }

    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public Float getPffz() {
        return pffz;
    }

    public void setPffz(Float pffz) {
        this.pffz = pffz;
    }

    public String getPfnr() {
        return pfnr;
    }

    public void setPfnr(String pfnr) {
        this.pfnr = pfnr;
    }
}
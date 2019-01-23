package com.hyzs.cidyth.module.integral.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_integral_title")
public class IntegralTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * 头衔等级
     */
    private String name;

    /**
     * 头衔类别
     */
    @Column(name = "title_type")
    private String titleType;

    /**
     * 名额
     */
    private Integer quota;

    /**
     * 上限人数
     */
    @Column(name = "upper_limit")
    private Integer upperLimit;

    /**
     * 下限人数
     */
    @Column(name = "lower_limit")
    private Integer lowerLimit;

    /**
     * 说明备注
     */
    private String smbz;

    /**
     * 录入人员
     */
    private String lrry;

    /**
     * 录入时间
     */
    private Date lrsj;

    /**
     * 修改人员
     */
    private String xgry;

    /**
     * 修改时间
     */
    private Date xgsj;

    /**
     * 录入人员名称
     */
    private String lrrymc;

    /**
     * 修改人员名称
     */
    private String xgrymc;

    /**
     * 优先级
     */
    private Integer priority;

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
     * 获取头衔等级
     *
     * @return name - 头衔等级
     */
    public String getName() {
        return name;
    }

    /**
     * 设置头衔等级
     *
     * @param name 头衔等级
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取头衔类别
     *
     * @return title_type - 头衔类别
     */
    public String getTitleType() {
        return titleType;
    }

    /**
     * 设置头衔类别
     *
     * @param titleType 头衔类别
     */
    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    /**
     * 获取上限人数
     *
     * @return upper_limit - 上限人数
     */
    public Integer getUpperLimit() {
        return upperLimit;
    }

    /**
     * 设置上限人数
     *
     * @param upperLimit 上限人数
     */
    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    /**
     * 获取下限人数
     *
     * @return lower_limit - 下限人数
     */
    public Integer getLowerLimit() {
        return lowerLimit;
    }

    /**
     * 设置下限人数
     *
     * @param lowerLimit 下限人数
     */
    public void setLowerLimit(Integer lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    /**
     * 获取说明备注
     *
     * @return smbz - 说明备注
     */
    public String getSmbz() {
        return smbz;
    }

    /**
     * 设置说明备注
     *
     * @param smbz 说明备注
     */
    public void setSmbz(String smbz) {
        this.smbz = smbz;
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
     * 获取修改人员
     *
     * @return xgry - 修改人员
     */
    public String getXgry() {
        return xgry;
    }

    /**
     * 设置修改人员
     *
     * @param xgry 修改人员
     */
    public void setXgry(String xgry) {
        this.xgry = xgry;
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
     * 获取修改人员名称
     *
     * @return xgrymc - 修改人员名称
     */
    public String getXgrymc() {
        return xgrymc;
    }

    /**
     * 设置修改人员名称
     *
     * @param xgrymc 修改人员名称
     */
    public void setXgrymc(String xgrymc) {
        this.xgrymc = xgrymc;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
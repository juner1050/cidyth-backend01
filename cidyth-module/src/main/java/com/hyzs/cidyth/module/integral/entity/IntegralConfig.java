package com.hyzs.cidyth.module.integral.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_integral_config")
public class IntegralConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * 规则名称
     */
    @Column(name = "rule_type")
    private String ruleType;

    /**
     * 积分类型（0、定值积分，1、非定值积分）
     */
    @Column(name = "integral_type")
    private Integer integralType;

    /**
     * 奖励次数（0、无限次）
     */
    @Column(name = "award_limit")
    private Integer awardLimit;

    /**
     * 上限分数
     */
    @Column(name = "upper_limit")
    private Float upperLimit;

    /**
     * 下限分数
     */
    @Column(name = "lower_limit")
    private Float lowerLimit;

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
     * 获取规则名称
     *
     * @return rule_type - 规则名称
     */
    public String getRuleType() {
        return ruleType;
    }

    /**
     * 设置规则名称
     *
     * @param ruleType 规则名称
     */
    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    /**
     * 获取积分类型（0、定值积分，1、非定值积分）
     *
     * @return integral_type - 积分类型（0、定值积分，1、非定值积分）
     */
    public Integer getIntegralType() {
        return integralType;
    }

    /**
     * 设置积分类型（0、定值积分，1、非定值积分）
     *
     * @param integralType 积分类型（0、定值积分，1、非定值积分）
     */
    public void setIntegralType(Integer integralType) {
        this.integralType = integralType;
    }

    /**
     * 获取奖励次数（0、无限次）
     *
     * @return award_limit - 奖励次数（0、无限次）
     */
    public Integer getAwardLimit() {
        return awardLimit;
    }

    /**
     * 设置奖励次数（0、无限次）
     *
     * @param awardLimit 奖励次数（0、无限次）
     */
    public void setAwardLimit(Integer awardLimit) {
        this.awardLimit = awardLimit;
    }

    /**
     * 获取上限分数
     *
     * @return upper_limit - 上限分数
     */
    public Float getUpperLimit() {
        return upperLimit;
    }

    /**
     * 设置上限分数
     *
     * @param upperLimit 上限分数
     */
    public void setUpperLimit(Float upperLimit) {
        this.upperLimit = upperLimit;
    }

    /**
     * 获取下限分数
     *
     * @return lower_limit - 下限分数
     */
    public Float getLowerLimit() {
        return lowerLimit;
    }

    /**
     * 设置下限分数
     *
     * @param lowerLimit 下限分数
     */
    public void setLowerLimit(Float lowerLimit) {
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
}
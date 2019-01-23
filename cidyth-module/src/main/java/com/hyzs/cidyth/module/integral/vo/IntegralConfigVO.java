package com.hyzs.cidyth.module.integral.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class IntegralConfigVO {

    private Integer id;

    /**
     * 规则名称
     */
    @ApiModelProperty(value = "规则名称")
    private String ruleName;

    /**
     * 规则类型
     */
    @ApiModelProperty(value = "规则类型", required = true)
    private String ruleType;

    /**
     * 积分类型（0、定值积分，1、非定值积分）
     */
    @ApiModelProperty(value = "积分类型（0、定值积分，1、非定值积分）", required = true)
    private Integer integralType;

    /**
     * 积分类型名称
     */
    @ApiModelProperty("积分类型名称")
    private String integralTypeCn;

    /**
     * 奖励次数（0、无限次）
     */
    @ApiModelProperty(value = "奖励次数（0、无限次）", required = true)
    private Integer awardLimit;

    /**
     * 上限分数
     */
    @ApiModelProperty(value = "上限分数", required = true)
    private Float upperLimit;

    /**
     * 下限分数
     */
    @ApiModelProperty("下限分数")
    private Float lowerLimit;

    /**
     * 录入时间
     */
    @ApiModelProperty("录入时间")
    private Date lrsj;

    /**
     * 录入人员名称
     */
    @ApiModelProperty("录入人员名称")
    private String lrrymc;

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

    public String getIntegralTypeCn() {
        return integralTypeCn;
    }

    public void setIntegralTypeCn(String integralTypeCn) {
        this.integralTypeCn = integralTypeCn;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }
}
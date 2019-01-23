package com.hyzs.cidyth.module.integral.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 1 on 2018/6/12.
 */
public class IntegralRuleOption {

    /**
     * 规则名称
     */
    @ApiModelProperty("规则名称")
    private String ruleName;
    /**
     * 规则类型
     */
    @ApiModelProperty("规则类型")
    private String ruleType;
    /**
     * 分数上限
     */
    @ApiModelProperty("最大分数")
    private Float upperLimit;
    /**
     * 分数下限
     */
    @ApiModelProperty("最小分数")
    private Float lowerLimit;
    /**
     * 分数类型
     */
    @ApiModelProperty("分数类型（0、获取upperLimit显示积分，1、用户输入积分，并且>=lowerLimit <= upperLimit）")
    private Integer integralType;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public Float getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Float upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Float getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Float lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Integer getIntegralType() {
        return integralType;
    }

    public void setIntegralType(Integer integralType) {
        this.integralType = integralType;
    }
}

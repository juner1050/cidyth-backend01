package com.hyzs.cidyth.module.integral.vo;

import java.util.Date;

/**
 * Created by 1 on 2018/6/12.
 */
public class IntegralRuleVO {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 规则类型
     */
    private String ruleType;
    /**
     * 父级名称
     */
    private String parentName;
    /**
     * 录入时间
     */
    private Date lrsj;
    /**
     * 录入人员名称
     */
    private String lrrymc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public String getLrrymc() {
        return lrrymc;
    }

    public void setLrrymc(String lrrymc) {
        this.lrrymc = lrrymc;
    }
}

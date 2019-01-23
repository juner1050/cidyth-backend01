package com.hyzs.cidyth.module.integral.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_integral_rule")
public class IntegralRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * 父级id
     */
    private Integer pid;

    /**
     * 规则名称
     */
    @Column(name = "rule_name")
    private String ruleName;

    /**
     * 规则类型
     */
    @Column(name = "rule_type")
    private String ruleType;

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
     * 获取父级id
     *
     * @return pid - 父级id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父级id
     *
     * @param pid 父级id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取规则名称
     *
     * @return rule_name - 规则名称
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * 设置规则名称
     *
     * @param ruleName 规则名称
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    /**
     * 获取规则类型
     *
     * @return rule_type - 规则类型
     */
    public String getRuleType() {
        return ruleType;
    }

    /**
     * 设置规则类型
     *
     * @param ruleType 规则类型
     */
    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
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
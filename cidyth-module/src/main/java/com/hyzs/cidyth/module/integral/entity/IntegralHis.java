package com.hyzs.cidyth.module.integral.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_integral_his")
public class IntegralHis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * 案件编号
     */
    @ApiModelProperty("案件编号")
    private String ajbh;

    /**
     * 奖励人员
     */
    @ApiModelProperty("奖励人员编号")
    private String jlry;

    /**
     * 奖励人员名称
     */
    @ApiModelProperty("奖励人员名称")
    private String jlrymc;

    /**
     * 奖励人员机构编号
     */
    @ApiModelProperty("奖励人员机构编号")
    private String jlryjg;

    /**
     * 奖励人员机构名称
     */
    @ApiModelProperty("奖励人员机构名称")
    private String jlryjgmc;

    /**
     * 规则类型
     */
    @Column(name = "rule_type")
    @ApiModelProperty("规则类型")
    private String ruleType;

    /**
     * 奖励年份
     */
    @ApiModelProperty("奖励年份")
    private Integer year;

    /**
     * 奖励月份
     */
    @ApiModelProperty("奖励月份")
    private Integer month;

    /**
     * 奖励日
     */
    @ApiModelProperty("奖励日")
    private Integer day;

    /**
     * 积分分数
     */
    @ApiModelProperty("积分分数")
    private Float score;

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
    @ApiModelProperty("录入时间")
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
    @ApiModelProperty("录入人员名称")
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
     * 获取案件编号
     *
     * @return ajbh - 案件编号
     */
    public String getAjbh() {
        return ajbh;
    }

    /**
     * 设置案件编号
     *
     * @param ajbh 案件编号
     */
    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

    /**
     * 获取奖励人员
     *
     * @return jlry - 奖励人员
     */
    public String getJlry() {
        return jlry;
    }

    /**
     * 设置奖励人员
     *
     * @param jlry 奖励人员
     */
    public void setJlry(String jlry) {
        this.jlry = jlry;
    }

    /**
     * 获取奖励人员名称
     *
     * @return jlrymc - 奖励人员名称
     */
    public String getJlrymc() {
        return jlrymc;
    }

    /**
     * 设置奖励人员名称
     *
     * @param jlrymc 奖励人员名称
     */
    public void setJlrymc(String jlrymc) {
        this.jlrymc = jlrymc;
    }

    /**
     * 获取奖励人员机构编号
     *
     * @return jlryjg - 奖励人员机构编号
     */
    public String getJlryjg() {
        return jlryjg;
    }

    /**
     * 设置奖励人员机构编号
     *
     * @param jlryjg 奖励人员机构编号
     */
    public void setJlryjg(String jlryjg) {
        this.jlryjg = jlryjg;
    }

    /**
     * 获取奖励人员机构名称
     *
     * @return jlryjgmc - 奖励人员机构名称
     */
    public String getJlryjgmc() {
        return jlryjgmc;
    }

    /**
     * 设置奖励人员机构名称
     *
     * @param jlryjgmc 奖励人员机构名称
     */
    public void setJlryjgmc(String jlryjgmc) {
        this.jlryjgmc = jlryjgmc;
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
     * 获取奖励年份
     *
     * @return year - 奖励年份
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 设置奖励年份
     *
     * @param year 奖励年份
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 获取奖励月份
     *
     * @return month - 奖励月份
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * 设置奖励月份
     *
     * @param month 奖励月份
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * 获取奖励日
     *
     * @return day - 奖励日
     */
    public Integer getDay() {
        return day;
    }

    /**
     * 设置奖励日
     *
     * @param day 奖励日
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * 获取积分分数
     *
     * @return score - 积分分数
     */
    public Float getScore() {
        return score;
    }

    /**
     * 设置积分分数
     *
     * @param score 积分分数
     */
    public void setScore(Float score) {
        this.score = score;
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
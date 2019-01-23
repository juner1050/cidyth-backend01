package com.hyzs.cidyth.module.base.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_merge")
public class CasesMerge {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * 研判线索编号
     */
    private String xsbh;

    /**
     * 串并案编号
     */
    private String cbabh;

    /**
     * 串并案单位
     */
    private String cbajg;

    /**
     * 串案申请人
     */
    private String cbary;

    /**
     * 串并案标题
     */
    private String cbabt;

    /**
     * 串并案原因
     */
    private String cbayy;

    /**
     * 串案时间
     */
    private Date cbasj;

    /**
     * 审批领导
     */
    private String spld;

    /**
     * 审批意见（是，否）
     */
    private String spyj;

    /**
     * 审批内容
     */
    private String spnr;

    /**
     * 备注说明
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
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取串并案编号
     *
     * @return cbabh - 串并案编号
     */
    public String getCbabh() {
        return cbabh;
    }

    /**
     * 设置串并案编号
     *
     * @param cbabh 串并案编号
     */
    public void setCbabh(String cbabh) {
        this.cbabh = cbabh;
    }

    /**
     * 获取串并案单位
     *
     * @return cbajg - 串并案单位
     */
    public String getCbajg() {
        return cbajg;
    }

    /**
     * 设置串并案单位
     *
     * @param cbajg 串并案单位
     */
    public void setCbajg(String cbajg) {
        this.cbajg = cbajg;
    }

    /**
     * 获取串案申请人
     *
     * @return cbary - 串案申请人
     */
    public String getCbary() {
        return cbary;
    }

    /**
     * 设置串案申请人
     *
     * @param cbary 串案申请人
     */
    public void setCbary(String cbary) {
        this.cbary = cbary;
    }

    /**
     * 获取串并案标题
     *
     * @return cbabt - 串并案标题
     */
    public String getCbabt() {
        return cbabt;
    }

    /**
     * 设置串并案标题
     *
     * @param cbabt 串并案标题
     */
    public void setCbabt(String cbabt) {
        this.cbabt = cbabt;
    }

    /**
     * 获取串并案原因
     *
     * @return cbayy - 串并案原因
     */
    public String getCbayy() {
        return cbayy;
    }

    /**
     * 设置串并案原因
     *
     * @param cbayy 串并案原因
     */
    public void setCbayy(String cbayy) {
        this.cbayy = cbayy;
    }

    /**
     * 获取串案时间
     *
     * @return cbasj - 串案时间
     */
    public Date getCbasj() {
        return cbasj;
    }

    /**
     * 设置串案时间
     *
     * @param cbasj 串案时间
     */
    public void setCbasj(Date cbasj) {
        this.cbasj = cbasj;
    }

    /**
     * 获取审批领导
     *
     * @return spld - 审批领导
     */
    public String getSpld() {
        return spld;
    }

    /**
     * 设置审批领导
     *
     * @param spld 审批领导
     */
    public void setSpld(String spld) {
        this.spld = spld;
    }

    /**
     * 获取审批意见（是，否）
     *
     * @return spyj - 审批意见（是，否）
     */
    public String getSpyj() {
        return spyj;
    }

    /**
     * 设置审批意见（是，否）
     *
     * @param spyj 审批意见（是，否）
     */
    public void setSpyj(String spyj) {
        this.spyj = spyj;
    }

    /**
     * 获取审批内容
     *
     * @return spnr - 审批内容
     */
    public String getSpnr() {
        return spnr;
    }

    /**
     * 设置审批内容
     *
     * @param spnr 审批内容
     */
    public void setSpnr(String spnr) {
        this.spnr = spnr;
    }

    /**
     * 获取备注说明
     *
     * @return smbz - 备注说明
     */
    public String getSmbz() {
        return smbz;
    }

    /**
     * 设置备注说明
     *
     * @param smbz 备注说明
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

    public String getXsbh() {
        return xsbh;
    }

    public void setXsbh(String xsbh) {
        this.xsbh = xsbh;
    }
}
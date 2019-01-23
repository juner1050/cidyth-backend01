package com.hyzs.cidyth.module.time.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_time_node")
public class TimeNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * 案件编号
     */
    @ApiModelProperty("案件编号")
    private String ajbh;

    /**
     * 业务id（案件、需求、线索、信息、回复的主键）
     */
    @Column(name = "reference_id")
    private Integer referenceId;

    /**
     * 业务类型名称（TableTypeEnum枚举名）
     */
    @Column(name = "reference_type")
    private String referenceType;

    /**
     * 发送单位编号
     */
    @Column(name = "send_org_code")
    @ApiModelProperty("发送单位编号")
    private String sendOrgCode;

    /**
     * 发送单位名称
     */
    @Column(name = "send_org_name")
    @ApiModelProperty("发送单位名称")
    private String sendOrgName;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 接收单位编号
     */
    @Column(name = "receive_org_code")
    @ApiModelProperty("接收单位编号")
    private String receiveOrgCode;

    /**
     * 接收单位名称
     */
    @Column(name = "receive_org_name")
    @ApiModelProperty("接收单位名称")
    private String receiveOrgName;

    /**
     * 是否显示
     */
    @Column(name = "show_status")
    @ApiModelProperty("是否显示")
    private String showStatus;
    
    /**
     * 录入人员编号
     */
    @ApiModelProperty("录入人员编号")
    private String lrry;

    /**
     * 录入人员名称
     */
    @ApiModelProperty("录入人员名称")
    private String lrrymc;

    /**
     * 录入时间
     */
    @ApiModelProperty("录入时间")
    private Date lrsj;

    /**
     * 排序
     */
    private Integer sort;

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
     * 获取业务id（案件、需求、线索、信息、回复的主键）
     *
     * @return reference_id - 业务id（案件、需求、线索、信息、回复的主键）
     */
    public Integer getReferenceId() {
        return referenceId;
    }

    /**
     * 设置业务id（案件、需求、线索、信息、回复的主键）
     *
     * @param referenceId 业务id（案件、需求、线索、信息、回复的主键）
     */
    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

    /**
     * 获取业务类型名称（TableTypeEnum枚举名）
     *
     * @return reference_type - 业务类型名称（TableTypeEnum枚举名）
     */
    public String getReferenceType() {
        return referenceType;
    }

    /**
     * 设置业务类型名称（TableTypeEnum枚举名）
     *
     * @param referenceType 业务类型名称（TableTypeEnum枚举名）
     */
    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    /**
     * 获取发送单位编号
     *
     * @return send_org_code - 发送单位编号
     */
    public String getSendOrgCode() {
        return sendOrgCode;
    }

    /**
     * 设置发送单位编号
     *
     * @param sendOrgCode 发送单位编号
     */
    public void setSendOrgCode(String sendOrgCode) {
        this.sendOrgCode = sendOrgCode;
    }

    /**
     * 获取发送单位名称
     *
     * @return send_org_name - 发送单位名称
     */
    public String getSendOrgName() {
        return sendOrgName;
    }

    /**
     * 设置发送单位名称
     *
     * @param sendOrgName 发送单位名称
     */
    public void setSendOrgName(String sendOrgName) {
        this.sendOrgName = sendOrgName;
    }

    /**
     * 获取标题
     *
     * @return send_title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取内容
     *
     * @return send_content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取接收单位编号
     *
     * @return receive_org_code - 接收单位编号
     */
    public String getReceiveOrgCode() {
        return receiveOrgCode;
    }

    /**
     * 设置接收单位编号
     *
     * @param receiveOrgCode 接收单位编号
     */
    public void setReceiveOrgCode(String receiveOrgCode) {
        this.receiveOrgCode = receiveOrgCode;
    }

    /**
     * 获取接收单位名称
     *
     * @return receive_org_name - 接收单位名称
     */
    public String getReceiveOrgName() {
        return receiveOrgName;
    }

    /**
     * 设置接收单位名称
     *
     * @param receiveOrgName 接收单位名称
     */
    public void setReceiveOrgName(String receiveOrgName) {
        this.receiveOrgName = receiveOrgName;
    }

    /**
     * 获取录入人员编号
     *
     * @return lrry - 录入人员编号
     */
    public String getLrry() {
        return lrry;
    }

    /**
     * 设置录入人员编号
     *
     * @param lrry 录入人员编号
     */
    public void setLrry(String lrry) {
        this.lrry = lrry;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
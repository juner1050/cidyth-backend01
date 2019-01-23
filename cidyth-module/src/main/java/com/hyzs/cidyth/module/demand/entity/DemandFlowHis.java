package com.hyzs.cidyth.module.demand.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import javax.persistence.*;

/**
 * 需求操作(流转)记录
 * 
 * @author Administrator
 *
 */
@Table(name = "t_demand_flow_his")
public class DemandFlowHis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
	private Integer id;

	/**
	 * 需求id
	 */
	@ApiModelProperty(value="需求id", required=true)
	private Integer xqid;

	/**
	 * 指派人员编号
	 */
	@ApiModelProperty(value="指派人员编号")
	private String zprybh;

	/**
	 * 指派人员机构编号
	 */
	@ApiModelProperty(value="指派人员机构编号")
	private String zpryjgbh;

	/**
	 * 指派人员机构名称
	 */
	@ApiModelProperty(value="指派人员机构名称")
	private String zpryjgmc;
	/**
	 * 接收人员编号
	 */
	@ApiModelProperty(value="接收人员编号", required=true)
	private String jsrybh;

	/**
	 * 接收人员机构编号
	 */
	@ApiModelProperty(value="接收人员机构编号")
	private String jsryjgbh;

	/**
	 * 接收人员机构名称
	 */
	@ApiModelProperty(value="接收人员机构名称")
	private String jsryjgmc;

	/**
	 * 当前步骤的操作状态:20.WAIT_FOR_SIGN(待签收); 30.SIGNED(已签收); 40.FEEDBACKED(已反馈)
	 * @see com.hyzs.cidyth.common.enums.DemandFlowStepStatus
	 */
	@ApiModelProperty(value="操作状态")
	private String qszt;

	/**
	 * 操作状态描述
	 */
	@ApiModelProperty(value="操作状态描述")
	private String qsztms;

	/**
	 * 操作时间
	 */
	@ApiModelProperty(value="操作时间")
	private Date qssj;
	/**
	 * 指派人姓名
	 */
	@ApiModelProperty(value="指派人姓名")
	private String zprymc;
	/**
	 * 接受人姓名
	 */
	@ApiModelProperty(value="接受人姓名")
	private String jsrymc;


	/**
	 * 录入时间
	 */
	@ApiModelProperty(value="录入时间")
	private Date lrsj;
	/**
	 * 签收时间截止
	 */
	@ApiModelProperty(value="签收时间截止")
	private Date qssjjz;
	/**
	 * 签收标识（0、非当前人签收，1、是当前人签收）
	 */
	@ApiModelProperty(value="签收标识（0、非当前人签收，1、是当前人签收）")
	private Integer qsbz;
	/**
	 * 签收超时（0、未超时，1、已超时）
	 */
	@ApiModelProperty(value="签收超时（0、未超时，1、已超时）")
	private Integer qscs;
	/**
	 * 反馈天数截止
	 */
	@ApiModelProperty(value="反馈天数截止")
	private Integer fktsjz;
	/**
	 * 反馈时间截止
	 */
	@ApiModelProperty(value="反馈时间截止")
	private Date fksjjz;
	/**
	 * 反馈超时（0、未超时，1、已超时）
	 */
	@ApiModelProperty(value="反馈超时（0、未超时，1、已超时）")
	private Integer fkcs;
	/**
	 * 反馈申请延期
	 */
	@ApiModelProperty(value="反馈申请延期")
	private Integer fksqyq;
	/**
	 * 反馈申请原因
	 */
	@ApiModelProperty(value="反馈申请原因")
	private String fksqyy;


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
	 * ��ȡ����ID
	 *
	 * @return xqid - ����ID
	 */
	public Integer getXqid() {
		return xqid;
	}

	/**
	 * ��������ID
	 *
	 * @param xqid
	 *            ����ID
	 */
	public void setXqid(Integer xqid) {
		this.xqid = xqid;
	}

	/**
	 * ��ȡָ���˾���
	 *
	 * @return zprybh - ָ���˾���
	 */
	public String getZprybh() {
		return zprybh;
	}

	/**
	 * ����ָ���˾���
	 *
	 * @param zprybh
	 *            ָ���˾���
	 */
	public void setZprybh(String zprybh) {
		this.zprybh = zprybh;
	}

	/**
	 * ��ȡָ���˻������
	 *
	 * @return zpryjgbh - ָ���˻������
	 */
	public String getZpryjgbh() {
		return zpryjgbh;
	}

	/**
	 * ����ָ���˻������
	 *
	 * @param zpryjgbh
	 *            ָ���˻������
	 */
	public void setZpryjgbh(String zpryjgbh) {
		this.zpryjgbh = zpryjgbh;
	}

	/**
	 * ��ȡָ���˻�������
	 *
	 * @return zpryjgmc - ָ���˻�������
	 */
	public String getZpryjgmc() {
		return zpryjgmc;
	}

	/**
	 * ����ָ���˻�������
	 *
	 * @param zpryjgmc
	 *            ָ���˻�������
	 */
	public void setZpryjgmc(String zpryjgmc) {
		this.zpryjgmc = zpryjgmc;
	}

	/**
	 * ��ȡ�����˾���
	 *
	 * @return jsrybh - �����˾���
	 */
	public String getJsrybh() {
		return jsrybh;
	}

	/**
	 * ���ý����˾���
	 *
	 * @param jsrybh
	 *            �����˾���
	 */
	public void setJsrybh(String jsrybh) {
		this.jsrybh = jsrybh;
	}

	/**
	 * ��ȡ�����˻������
	 *
	 * @return jsryjgbh - �����˻������
	 */
	public String getJsryjgbh() {
		return jsryjgbh;
	}

	/**
	 * ���ý����˻������
	 *
	 * @param jsryjgbh
	 *            �����˻������
	 */
	public void setJsryjgbh(String jsryjgbh) {
		this.jsryjgbh = jsryjgbh;
	}

	/**
	 * ��ȡ�����˻�������
	 *
	 * @return jsryjgmc - �����˻�������
	 */
	public String getJsryjgmc() {
		return jsryjgmc;
	}

	/**
	 * ���ý����˻�������
	 *
	 * @param jsryjgmc
	 *            �����˻�������
	 */
	public void setJsryjgmc(String jsryjgmc) {
		this.jsryjgmc = jsryjgmc;
	}

	/**
	 * 当前步骤的操作状态: 0.INIT(待指派); 20.WAIT_FOR_SIGN(待签收); 30.SIGNED(已签收)
	 * 
	 * @see com.hyzs.cidyth.common.enums.DemandFlowStepStatus
	 * @return qszt - 当前步骤的操作状态
	 */
	public String getQszt() {
		return qszt;
	}

	/**
	 * 当前步骤的操作状态: 0.INIT(待指派); 20.WAIT_FOR_SIGN(待签收); 30.SIGNED(已签收)
	 * 
	 * @see com.hyzs.cidyth.common.enums.DemandFlowStepStatus
	 * @param qszt
	 *            当前步骤的操作状态
	 */
	public void setQszt(String qszt) {
		this.qszt = qszt;
	}

	/**
	 * ��ȡǩ��ʱ��
	 *
	 * @return qssj - ǩ��ʱ��
	 */
	public Date getQssj() {
		return qssj;
	}

	/**
	 * ����ǩ��ʱ��
	 *
	 * @param qssj
	 *            ǩ��ʱ��
	 */
	public void setQssj(Date qssj) {
		this.qssj = qssj;
	}

	public String getZprymc() {
		return zprymc;
	}

	/**
	 * 指派人姓名
	 * 
	 * @param zprymc
	 */
	public void setZprymc(String zprymc) {
		this.zprymc = zprymc;
	}

	public String getJsrymc() {
		return jsrymc;
	}

	/**
	 * 接受人姓名
	 * 
	 * @param jsrymc
	 */
	public void setJsrymc(String jsrymc) {
		this.jsrymc = jsrymc;
	}

	public Date getLrsj() {
		return lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public Date getQssjjz() {
		return qssjjz;
	}

	public void setQssjjz(Date qssjjz) {
		this.qssjjz = qssjjz;
	}

	public Integer getQsbz() {
		return qsbz;
	}

	public void setQsbz(Integer qsbz) {
		this.qsbz = qsbz;
	}

	public Integer getQscs() {
		return qscs;
	}

	public void setQscs(Integer qscs) {
		this.qscs = qscs;
	}

	public Integer getFktsjz() {
		return fktsjz;
	}

	public void setFktsjz(Integer fktsjz) {
		this.fktsjz = fktsjz;
	}

	public Date getFksjjz() {
		return fksjjz;
	}

	public void setFksjjz(Date fksjjz) {
		this.fksjjz = fksjjz;
	}

	public Integer getFkcs() {
		return fkcs;
	}

	public void setFkcs(Integer fkcs) {
		this.fkcs = fkcs;
	}

	public Integer getFksqyq() {
		return fksqyq;
	}

	public void setFksqyq(Integer fksqyq) {
		this.fksqyq = fksqyq;
	}

	public String getFksqyy() {
		return fksqyy;
	}

	public void setFksqyy(String fksqyy) {
		this.fksqyy = fksqyy;
	}

	public String getQsztms() {
		return qsztms;
	}

	public void setQsztms(String qsztms) {
		this.qsztms = qsztms;
	}
}
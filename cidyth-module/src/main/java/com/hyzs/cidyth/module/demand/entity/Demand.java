package com.hyzs.cidyth.module.demand.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * 需求
 * 
 * @author derrick
 *
 */
@Table(name = "t_demand")
public class Demand {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
	private Integer id;

	/**
	 * 案件编号
	 */
	private String ajbh;

	/**
	 * 需求名称
	 */
	private String xqmc;

	/**
	 * 需求类型
	 */
	private String xqlx;

	/**
	 * 需求内容
	 */
	private String xqnr;

	/**
	 * 需求人员
	 */
	private String qqry;

	/**
	 * 请求单位
	 */
	private String qqdw;

	/**
	 * 请求单位编号
	 */
	private String qqdwbh;

	/**
	 * 请求时间
	 */
	private Date qqsj;
	
	/**
	 * 紧急程度
	 */
	private Integer jjcd;

	/**
	 * 接收单位名称
	 */
	private String jsdw;
	/**
	 * 接受单位编号
	 */
	private String jsdwbh;
	/**
	 * 指派领导
	 */
	private String zpld;

	/**
	 * 指派时间
	 */
	private Date zpsj;
	/**
	 * 反馈天数
	 */
	private Integer fkts;
	/**
	 * 任务接收人员
	 */
	private String rwjsry;

	/**
	 * 任务签收人员
	 */
	private String rwqsry;

	/**
	 * 签收时间
	 */
	private Date qssj;

	/**
	 * 催办状态
	 */
	private Integer cbzt;

	/**
	 * 需求当前处理状态: 0.INIT(待指派); 10.ALLOCATED(已指派)
	 * 
	 * @see com.hyzs.cidyth.common.enums.DemandStatus
	 */
	private String qszt;

	/**
	 * 是否附件
	 */
	private Integer sffj;

	/**
	 * 说明备注
	 */
	private String smbz;

	/**
	 * 录入人员
	 */
	private String lrry;

	/**
	 * 录入人员名称
	 */
	private String lrrymc;

	/**
	 * 录入时间
	 */
	private Date lrsj;

	/**
	 * 修改人员
	 */
	private String xgry;

	/**
	 * 修改人员名称
	 */
	private String xgrymc;

	/**
	 * 修改时间
	 */
	private Date xgsj;

	/**
	 * 思维导图上坐标
	 */
	@Column(name = "mind_top")
	private String mindTop;

	/**
	 * 思维导图左坐标
	 */
	@Column(name = "mind_left")
	private String mindLeft;

	/**
	 * 警种类别
	 */
	private String jzlb;

	/**
	 * id
	 *
	 * @return id - ����
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * ��������
	 *
	 * @param id
	 *            ����
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ��ȡ�������
	 *
	 * @return ajbh - �������
	 */
	public String getAjbh() {
		return ajbh;
	}

	/**
	 * ���ð������
	 *
	 * @param ajbh
	 *            �������
	 */
	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}

	/**
	 * ��ȡ��������
	 *
	 * @return xqmc - ��������
	 */
	public String getXqmc() {
		return xqmc;
	}

	/**
	 * ������������
	 *
	 * @param xqmc
	 *            ��������
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	/**
	 * ��ȡ��������
	 *
	 * @return xqlx - ��������
	 */
	public String getXqlx() {
		return xqlx;
	}

	/**
	 * ������������
	 *
	 * @param xqlx
	 *            ��������
	 */
	public void setXqlx(String xqlx) {
		this.xqlx = xqlx;
	}

	/**
	 * ��ȡ��������
	 *
	 * @return xqnr - ��������
	 */
	public String getXqnr() {
		return xqnr;
	}

	/**
	 * ������������
	 *
	 * @param xqnr
	 *            ��������
	 */
	public void setXqnr(String xqnr) {
		this.xqnr = xqnr;
	}

	/**
	 * ��ȡ������Ա
	 *
	 * @return qqry - ������Ա
	 */
	public String getQqry() {
		return qqry;
	}

	/**
	 * ����������Ա
	 *
	 * @param qqry
	 *            ������Ա
	 */
	public void setQqry(String qqry) {
		this.qqry = qqry;
	}

	/**
	 * ��ȡ����λ
	 *
	 * @return qqdw - ����λ
	 */
	public String getQqdw() {
		return qqdw;
	}

	/**
	 * ��������λ
	 *
	 * @param qqdw
	 *            ����λ
	 */
	public void setQqdw(String qqdw) {
		this.qqdw = qqdw;
	}

	/**
	 * ��ȡ����ʱ��
	 *
	 * @return qqsj - ����ʱ��
	 */
	public Date getQqsj() {
		return qqsj;
	}

	/**
	 * ��������ʱ��
	 *
	 * @param qqsj
	 *            ����ʱ��
	 */
	public void setQqsj(Date qqsj) {
		this.qqsj = qqsj;
	}

	/**
	 * ��ȡ���յ�λ
	 *
	 * @return jsdw - ���յ�λ
	 */
	public String getJsdw() {
		return jsdw;
	}

	public String getJsdwbh() {
		return jsdwbh;
	}

	public void setJsdwbh(String jsdwbh) {
		this.jsdwbh = jsdwbh;
	}

	/**
	 * ���ý��յ�λ
	 *
	 * @param jsdw
	 *            ���յ�λ
	 */
	public void setJsdw(String jsdw) {
		this.jsdw = jsdw;
	}

	/**
	 * ��ȡָ���쵼
	 *
	 * @return zpld - ָ���쵼
	 */
	public String getZpld() {
		return zpld;
	}

	/**
	 * ����ָ���쵼
	 *
	 * @param zpld
	 *            ָ���쵼
	 */
	public void setZpld(String zpld) {
		this.zpld = zpld;
	}

	/**
	 * ��ȡָ��ʱ��
	 *
	 * @return zpsj - ָ��ʱ��
	 */
	public Date getZpsj() {
		return zpsj;
	}

	/**
	 * ����ָ��ʱ��
	 *
	 * @param zpsj
	 *            ָ��ʱ��
	 */
	public void setZpsj(Date zpsj) {
		this.zpsj = zpsj;
	}

	/**
	 * ��ȡ���������(��t_person��Ա���yybh����)
	 *
	 * @return rwjsry - ���������(��t_person��Ա���yybh����)
	 */
	public String getRwjsry() {
		return rwjsry;
	}

	/**
	 * �������������(��t_person��Ա���yybh����)
	 *
	 * @param rwjsry
	 *            ���������(��t_person��Ա���yybh����)
	 */
	public void setRwjsry(String rwjsry) {
		this.rwjsry = rwjsry;
	}

	/**
	 * ��ȡ����ǩ����(��t_person��Ա���yybh����)
	 *
	 * @return rwqsry - ����ǩ����(��t_person��Ա���yybh����)
	 */
	public String getRwqsry() {
		return rwqsry;
	}

	/**
	 * ��������ǩ����(��t_person��Ա���yybh����)
	 *
	 * @param rwqsry
	 *            ����ǩ����(��t_person��Ա���yybh����)
	 */
	public void setRwqsry(String rwqsry) {
		this.rwqsry = rwqsry;
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

	/**
	 * ��ȡ�߰�״̬��0��Ϊ�߰죬1���߰죩
	 *
	 * @return cbzt - �߰�״̬��0��Ϊ�߰죬1���߰죩
	 */
	public Integer getCbzt() {
		return cbzt;
	}

	/**
	 * ���ô߰�״̬��0��Ϊ�߰죬1���߰죩
	 *
	 * @param cbzt
	 *            �߰�״̬��0��Ϊ�߰죬1���߰죩
	 */
	public void setCbzt(Integer cbzt) {
		this.cbzt = cbzt;
	}

	/**
	 * 需求当前处理状态: 0.INIT(待指派); 10.ALLOCATED(已指派)
	 * 
	 * @see com.hyzs.cidyth.common.enums.DemandStatus
	 * @return qszt̬ 需求的状态
	 */
	public String getQszt() {
		return qszt;
	}

	/**
	 * 需求当前处理状态: 0.INIT(待指派); 10.ALLOCATED(已指派)
	 * 
	 * @see com.hyzs.cidyth.common.enums.DemandStatus
	 * @param qszt
	 *            需求的状态
	 */
	public void setQszt(String qszt) {
		this.qszt = qszt;
	}

	/**
	 * ��ȡ������0����1���ǣ�
	 *
	 * @return sffj - ������0����1���ǣ�
	 */
	public Integer getSffj() {
		return sffj;
	}

	/**
	 * ���ø�����0����1���ǣ�
	 *
	 * @param sffj
	 *            ������0����1���ǣ�
	 */
	public void setSffj(Integer sffj) {
		this.sffj = sffj;
	}

	/**
	 * ��ȡ˵����ע
	 *
	 * @return smbz - ˵����ע
	 */
	public String getSmbz() {
		return smbz;
	}

	/**
	 * ����˵����ע
	 *
	 * @param smbz
	 *            ˵����ע
	 */
	public void setSmbz(String smbz) {
		this.smbz = smbz;
	}

	/**
	 * ��ȡ¼����Ա
	 *
	 * @return lrry - ¼����Ա
	 */
	public String getLrry() {
		return lrry;
	}

	/**
	 * ����¼����Ա
	 *
	 * @param lrry
	 *            ¼����Ա
	 */
	public void setLrry(String lrry) {
		this.lrry = lrry;
	}

	/**
	 * ��ȡ¼��ʱ��
	 *
	 * @return lrsj - ¼��ʱ��
	 */
	public Date getLrsj() {
		return lrsj;
	}

	/**
	 * ����¼��ʱ��
	 *
	 * @param lrsj
	 *            ¼��ʱ��
	 */
	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	/**
	 * ��ȡ�޸���Ա
	 *
	 * @return xgry - �޸���Ա
	 */
	public String getXgry() {
		return xgry;
	}

	/**
	 * �����޸���Ա
	 *
	 * @param xgry
	 *            �޸���Ա
	 */
	public void setXgry(String xgry) {
		this.xgry = xgry;
	}

	/**
	 * ��ȡ�޸���Ա
	 *
	 * @return xgsj - �޸���Ա
	 */
	public Date getXgsj() {
		return xgsj;
	}

	/**
	 * �����޸���Ա
	 *
	 * @param xgsj
	 *            �޸���Ա
	 */
	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	public String getQqdwbh() {
		return qqdwbh;
	}

	public void setQqdwbh(String qqdwbh) {
		this.qqdwbh = qqdwbh;
	}

	public String getLrrymc() {
		return lrrymc;
	}

	public void setLrrymc(String lrrymc) {
		this.lrrymc = lrrymc;
	}

	public String getXgrymc() {
		return xgrymc;
	}

	public void setXgrymc(String xgrymc) {
		this.xgrymc = xgrymc;
	}

	public String getMindTop() {
		return mindTop;
	}

	public void setMindTop(String mindTop) {
		this.mindTop = mindTop;
	}

	public String getMindLeft() {
		return mindLeft;
	}

	public void setMindLeft(String mindLeft) {
		this.mindLeft = mindLeft;
	}

	public Integer getJjcd() {
		return jjcd;
	}

	public void setJjcd(Integer jjcd) {
		this.jjcd = jjcd;
	}

	public String getJzlb() {
		return jzlb;
	}

	public void setJzlb(String jzlb) {
		this.jzlb = jzlb;
	}

	public Integer getFkts() {
		return fkts;
	}

	public void setFkts(Integer fkts) {
		this.fkts = fkts;
	}
}
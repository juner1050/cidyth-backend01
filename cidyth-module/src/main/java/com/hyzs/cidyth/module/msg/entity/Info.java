package com.hyzs.cidyth.module.msg.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_info")
public class Info {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * 需求id
     */
    @Column(name = "xq_id")
    private String xqId;

    /**
     * 案件编号
     */
    private String ajbh;

    /**
     * 信息主题
     */
    private String xxzt;

    /**
     * 发布范围
     */
    private String fbfw;

    /**
     * 请求类型
     */
    private String qqlx;

    /**
     * 发布内容
     */
    private String fbnr;

    /**
     * 发布人员
     */
    private String fbry;

    /**
     * 发布单位
     */
    private String fbdw;
    /**
     * 发布单位编号
     */
    private String fbdwbh;
    
    /**
     * 发布日期
     */
    private Date fbrq;

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
     * @param id ����
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ��ȡ������
     *
     * @return xq_id - ������
     */
    public String getXqId() {
        return xqId;
    }

    /**
     * ����������
     *
     * @param xqId ������
     */
    public void setXqId(String xqId) {
        this.xqId = xqId;
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
     * @param ajbh �������
     */
    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

    /**
     * ��ȡ��Ϣ����
     *
     * @return xxzt - ��Ϣ����
     */
    public String getXxzt() {
        return xxzt;
    }

    /**
     * ������Ϣ����
     *
     * @param xxzt ��Ϣ����
     */
    public void setXxzt(String xxzt) {
        this.xxzt = xxzt;
    }

    /**
     * ��ȡ������Χ����λcode�����ŷָ���
     *
     * @return fbfw - ������Χ����λcode�����ŷָ���
     */
    public String getFbfw() {
        return fbfw;
    }

    /**
     * ���÷�����Χ����λcode�����ŷָ���
     *
     * @param fbfw ������Χ����λcode�����ŷָ���
     */
    public void setFbfw(String fbfw) {
        this.fbfw = fbfw;
    }

    /**
     * ��ȡ�������ͣ��ֵ��
     *
     * @return qqlx - �������ͣ��ֵ��
     */
    public String getQqlx() {
        return qqlx;
    }

    /**
     * �����������ͣ��ֵ��
     *
     * @param qqlx �������ͣ��ֵ��
     */
    public void setQqlx(String qqlx) {
        this.qqlx = qqlx;
    }

    /**
     * ��ȡ��Ϣ����
     *
     * @return fbnr - ��Ϣ����
     */
    public String getFbnr() {
        return fbnr;
    }

    /**
     * ������Ϣ����
     *
     * @param fbnr ��Ϣ����
     */
    public void setFbnr(String fbnr) {
        this.fbnr = fbnr;
    }

    /**
     * ��ȡ������Ա
     *
     * @return fbry - ������Ա
     */
    public String getFbry() {
        return fbry;
    }

    /**
     * ���÷�����Ա
     *
     * @param fbry ������Ա
     */
    public void setFbry(String fbry) {
        this.fbry = fbry;
    }

    /**
     * ��ȡ������λ
     *
     * @return fbdw - ������λ
     */
    public String getFbdw() {
        return fbdw;
    }

    /**
     * ���÷�����λ
     *
     * @param fbdw ������λ
     */
    public void setFbdw(String fbdw) {
        this.fbdw = fbdw;
    }

    public String getFbdwbh() {
		return fbdwbh;
	}

	public void setFbdwbh(String fbdwbh) {
		this.fbdwbh = fbdwbh;
	}

	/**
     * ��ȡ��������
     *
     * @return fbrq - ��������
     */
    public Date getFbrq() {
        return fbrq;
    }

    /**
     * ���÷�������
     *
     * @param fbrq ��������
     */
    public void setFbrq(Date fbrq) {
        this.fbrq = fbrq;
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
     * @param sffj ������0����1���ǣ�
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
     * @param smbz ˵����ע
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
     * @param lrry ¼����Ա
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
     * @param lrsj ¼��ʱ��
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
     * @param xgry �޸���Ա
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
     * @param xgsj �޸���Ա
     */
    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
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
    
}
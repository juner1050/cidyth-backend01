package com.hyzs.cidyth.module.clue.entity;

import java.util.Date;
import javax.persistence.*;
/**
 * 线索
 * @author derrick
 *
 */
@Table(name = "t_clue")
public class Clue {
    /**
     * ����
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="JDBC")
    private Integer id;

    /**
     * �������
     */
    private String ajbh;

    /**
     * 需求id
     */
    private Integer xqid;

    /**
     * ����
     */
    private String theme;

    /**
     * ��������
     */
    private String xsnr;

    /**
     * 线索类型
     */
    private Integer xslx;

    /**
     * ������Ա
     */
    private String fkry;

    /**
     * ������λ
     */
    private String fkdw;
    /**
     * 反馈单位编号
     */
    private String fkdwbh;
    /**
     * ��������
     */
    private Date fkrq;

    /**
     * ������0����1���ǣ�
     */
    private Integer sffj;
    /**
     * ˵����ע
     */
    private String smbz;

    /**
     * ¼����Ա
     */
    private String lrry;

    /**
     * ¼��ʱ��
     */
    private Date lrsj;

    /**
     * �޸���Ա
     */
    private String xgry;

    /**
     * �޸���Ա
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
     * 点赞总数
     */
    @Transient
    private Integer approveCount;
    /**
     * ��ȡ����
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
     * ��ȡ����
     *
     * @return theme - ����
     */
    public String getTheme() {
        return theme;
    }

    /**
     * ��������
     *
     * @param theme ����
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     * ��ȡ��������
     *
     * @return xsnr - ��������
     */
    public String getXsnr() {
        return xsnr;
    }

    /**
     * ������������
     *
     * @param xsnr ��������
     */
    public void setXsnr(String xsnr) {
        this.xsnr = xsnr;
    }

    /**
     * ��ȡ������Ա
     *
     * @return fkry - ������Ա
     */
    public String getFkry() {
        return fkry;
    }

    /**
     * ���÷�����Ա
     *
     * @param fkry ������Ա
     */
    public void setFkry(String fkry) {
        this.fkry = fkry;
    }

    /**
     * ��ȡ������λ
     *
     * @return fkdw - ������λ
     */
    public String getFkdw() {
        return fkdw;
    }

    /**
     * ���÷�����λ
     *
     * @param fkdw ������λ
     */
    public void setFkdw(String fkdw) {
        this.fkdw = fkdw;
    }

    public String getFkdwbh() {
		return fkdwbh;
	}

	public void setFkdwbh(String fkdwbh) {
		this.fkdwbh = fkdwbh;
	}

	/**
     * ��ȡ��������
     *
     * @return fkrq - ��������
     */
    public Date getFkrq() {
        return fkrq;
    }

    /**
     * ���÷�������
     *
     * @param fkrq ��������
     */
    public void setFkrq(Date fkrq) {
        this.fkrq = fkrq;
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

	public Integer getXslx() {
		return xslx;
	}

	public void setXslx(Integer xslx) {
		this.xslx = xslx;
	}

    public String getJzlb() {
        return jzlb;
    }

    public void setJzlb(String jzlb) {
        this.jzlb = jzlb;
    }

    public Integer getApproveCount() {
        return approveCount;
    }

    public void setApproveCount(Integer approveCount) {
        this.approveCount = approveCount;
    }

    public Integer getXqid() {
        return xqid;
    }

    public void setXqid(Integer xqid) {
        this.xqid = xqid;
    }
}
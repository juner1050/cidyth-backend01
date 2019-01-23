package com.hyzs.cidyth.module.trace.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_trail")
public class Trace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * �켣��ʶ
     */
    private Integer gjbs;

    /**
     * �켣�ص�
     */
    private String gjdd;

    /**
     * �켣��Ƭ
     */
    private String gjzp;

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
     * �޸�ʱ��
     */
    private Date xgsj;

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
     * ��ȡ�켣��ʶ
     *
     * @return gjbs - �켣��ʶ
     */
    public Integer getGjbs() {
        return gjbs;
    }

    /**
     * ���ù켣��ʶ
     *
     * @param gjbs �켣��ʶ
     */
    public void setGjbs(Integer gjbs) {
        this.gjbs = gjbs;
    }

    /**
     * ��ȡ�켣�ص�
     *
     * @return gjdd - �켣�ص�
     */
    public String getGjdd() {
        return gjdd;
    }

    /**
     * ���ù켣�ص�
     *
     * @param gjdd �켣�ص�
     */
    public void setGjdd(String gjdd) {
        this.gjdd = gjdd;
    }

    /**
     * ��ȡ�켣��Ƭ
     *
     * @return gjzp - �켣��Ƭ
     */
    public String getGjzp() {
        return gjzp;
    }

    /**
     * ���ù켣��Ƭ
     *
     * @param gjzp �켣��Ƭ
     */
    public void setGjzp(String gjzp) {
        this.gjzp = gjzp;
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
     * ��ȡ�޸�ʱ��
     *
     * @return xgsj - �޸�ʱ��
     */
    public Date getXgsj() {
        return xgsj;
    }

    /**
     * �����޸�ʱ��
     *
     * @param xgsj �޸�ʱ��
     */
    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }
}
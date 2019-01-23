package com.hyzs.cidyth.module.dic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_dic")
public class Dictionary {
    /**
     * ����
     */
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * ���ͱ��
     */
    private String lxbh;

    /**
     * �ֵ��
     */
    private String zdj;
    
    /**
     * �ֵ��
     */
    @JsonIgnore
    private String fjzdj;

    /**
     * �ֵ�ֵ
     */
    private String zdz;

    /**
     * ����
     */
    @JsonIgnore
    private Integer px;

    /**
     * ʹ��״̬
     */
    @JsonIgnore
    private String zt;

    /**
     * ˵����ע
     */
    @JsonIgnore
    private String smbz;

    /**
     * ¼����Ա
     */
    @JsonIgnore
    private String lrry;

    /**
     * ¼��ʱ��
     */
    @JsonIgnore
    private Date lrsj;

    /**
     * �޸���Ա
     */
    @JsonIgnore
    private String xgry;

    /**
     * �޸���Ա
     */
    @JsonIgnore
    private Date xgsj;

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
     * ��ȡ���ͱ��
     *
     * @return lxbh - ���ͱ��
     */
    public String getLxbh() {
        return lxbh;
    }

    /**
     * �������ͱ��
     *
     * @param lxbh ���ͱ��
     */
    public void setLxbh(String lxbh) {
        this.lxbh = lxbh;
    }

    /**
     * ��ȡ�ֵ��
     *
     * @return zdj - �ֵ��
     */
    public String getZdj() {
        return zdj;
    }

    /**
     * �����ֵ��
     *
     * @param zdj �ֵ��
     */
    public void setZdj(String zdj) {
        this.zdj = zdj;
    }

    /**
     * ��ȡ�ֵ�ֵ
     *
     * @return zdz - �ֵ�ֵ
     */
    public String getZdz() {
        return zdz;
    }

    /**
     * �����ֵ�ֵ
     *
     * @param zdz �ֵ�ֵ
     */
    public void setZdz(String zdz) {
        this.zdz = zdz;
    }

    /**
     * ��ȡ����
     *
     * @return px - ����
     */
    public Integer getPx() {
        return px;
    }

    /**
     * ��������
     *
     * @param px ����
     */
    public void setPx(Integer px) {
        this.px = px;
    }

    /**
     * ��ȡʹ��״̬
     *
     * @return zt - ʹ��״̬
     */
    public String getZt() {
        return zt;
    }

    /**
     * ����ʹ��״̬
     *
     * @param zt ʹ��״̬
     */
    public void setZt(String zt) {
        this.zt = zt;
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

	public String getFjzdj() {
		return fjzdj;
	}

	public void setFjzdj(String fjzdj) {
		this.fjzdj = fjzdj;
	}
    
    
}
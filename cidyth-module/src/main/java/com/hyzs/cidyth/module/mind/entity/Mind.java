package com.hyzs.cidyth.module.mind.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_mind")
public class Mind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * �������
     */
    private String ajbh;

    /**
     * 源类型（案件、需求、信息、上传线索、反馈线索）
     */
    @Column(name = "source_type")
    private String sourceType;
    
    /**
     * Դid
     */
    @Column(name = "source_id")
    private Integer sourceId;

    /**
     * 目标类型（案件、需求、信息、上传线索、反馈线索）
     */
    @Column(name = "target_type")
    private String targetType;
    
    /**
     * Ŀ��id
     */
    @Column(name = "target_id")
    private Integer targetId;

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
     * ��ȡԴid
     *
     * @return source_id - Դid
     */
    public Integer getSourceId() {
        return sourceId;
    }

    /**
     * ����Դid
     *
     * @param sourceId Դid
     */
    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * ��ȡĿ��id
     *
     * @return target_id - Ŀ��id
     */
    public Integer getTargetId() {
        return targetId;
    }

    /**
     * ����Ŀ��id
     *
     * @param targetId Ŀ��id
     */
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    /**
     * @return smbz
     */
    public String getSmbz() {
        return smbz;
    }

    /**
     * @param smbz
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

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

    
}
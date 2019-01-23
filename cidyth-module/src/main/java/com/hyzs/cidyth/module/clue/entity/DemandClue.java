package com.hyzs.cidyth.module.clue.entity;

import javax.persistence.*;
/**
 * 需求关联的线索
 * @author derrick
 *
 */
@Table(name = "t_demand_clue")
public class DemandClue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * ��������id
     */
    @Column(name = "demand_id")
    private Integer demandId;

    /**
     * ����id
     */
    @Column(name = "clue_id")
    private Integer clueId;

    /**
     * �������ͣ�Ԥ���ֶΣ�0���ϴ�������1������������
     */
    @Column(name = "clue_type")
    private Integer clueType;

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
     * ��ȡ��������id
     *
     * @return demand_id - ��������id
     */
    public Integer getDemandId() {
        return demandId;
    }

    /**
     * ������������id
     *
     * @param demandId ��������id
     */
    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    /**
     * ��ȡ����id
     *
     * @return clue_id - ����id
     */
    public Integer getClueId() {
        return clueId;
    }

    /**
     * ��������id
     *
     * @param clueId ����id
     */
    public void setClueId(Integer clueId) {
        this.clueId = clueId;
    }

    /**
     * ��ȡ�������ͣ�Ԥ���ֶΣ�0���ϴ�������1������������
     *
     * @return clue_type - �������ͣ�Ԥ���ֶΣ�0���ϴ�������1������������
     */
    public Integer getClueType() {
        return clueType;
    }

    /**
     * �����������ͣ�Ԥ���ֶΣ�0���ϴ�������1������������
     *
     * @param clueType �������ͣ�Ԥ���ֶΣ�0���ϴ�������1������������
     */
    public void setClueType(Integer clueType) {
        this.clueType = clueType;
    }
}
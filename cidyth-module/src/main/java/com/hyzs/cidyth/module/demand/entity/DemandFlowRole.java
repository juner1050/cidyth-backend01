package com.hyzs.cidyth.module.demand.entity;

import javax.persistence.*;
/**
 * 需求操作(流转)角色定义
 * @author jidw
 *
 */
@Table(name = "t_demand_flow_role")
public class DemandFlowRole {
    /**
     * ������¼id
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * ����(��ת)�ڵ���
     */
    private String lzjdbh;

    /**
     * ��ɫ���
     */
    private String jsbh;

    /**
     * ��ɫ����
     */
    private String jsmc;

    /**
     * ��ȡ������¼id
     *
     * @return id - ������¼id
     */
    public Integer getId() {
        return id;
    }

    /**
     * ����������¼id
     *
     * @param id ������¼id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ��ȡ����(��ת)�ڵ���
     *
     * @return lzjdbh - ����(��ת)�ڵ���
     */
    public String getLzjdbh() {
        return lzjdbh;
    }

    /**
     * ���ò���(��ת)�ڵ���
     *
     * @param lzjdbh ����(��ת)�ڵ���
     */
    public void setLzjdbh(String lzjdbh) {
        this.lzjdbh = lzjdbh;
    }

    /**
     * ��ȡ��ɫ���
     *
     * @return jsbh - ��ɫ���
     */
    public String getJsbh() {
        return jsbh;
    }

    /**
     * ���ý�ɫ���
     *
     * @param jsbh ��ɫ���
     */
    public void setJsbh(String jsbh) {
        this.jsbh = jsbh;
    }

    /**
     * ��ȡ��ɫ����
     *
     * @return jsmc - ��ɫ����
     */
    public String getJsmc() {
        return jsmc;
    }

    /**
     * ���ý�ɫ����
     *
     * @param jsmc ��ɫ����
     */
    public void setJsmc(String jsmc) {
        this.jsmc = jsmc;
    }
}
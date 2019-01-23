package com.hyzs.cidyth.module.approve.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 点赞
 * @author derrick
 *
 */
@Table(name = "t_approve")
public class Approve {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator="JDBC")
    private Integer id;
    @Column(name = "reference_id")
    private Integer referenceId;
    @Column(name = "reference_type")
    private String referenceType;
    private Date lrsj;
    private String lrry;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public String getLrry() {
        return lrry;
    }

    public void setLrry(String lrry) {
        this.lrry = lrry;
    }
}
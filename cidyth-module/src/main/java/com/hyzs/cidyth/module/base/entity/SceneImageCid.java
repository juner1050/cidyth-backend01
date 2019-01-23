package com.hyzs.cidyth.module.base.entity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "xcky2.xk_zp")
public class SceneImageCid {

    /**
     * 主键ID
     */
    @Column(name = "ID")
    private String id;
    
    /**
     * 现场ID
     */
    @Column(name = "INVESTIGATION_ID")
    private String investigationId;

    /**
     * 内容（二进制数据）
     */
    @Column(name = "CONTENT")
    private byte[] content;

    /**
     * 宽度
     */
    @Column(name = "WIDTH")
    private String width;

    /**
     * 高度
     */
    @Column(name = "HEIGHT")
    private String height;

    /**
     * 照片名
     */
    @Column(name = "PHOTONAME")
    private String photoName;

	/**
	 * 类别
	 */
	@Column(name = "LB")
	private String lb;

	/**
	 * 图片类型
	 */
	@Column(name = "TYPE")
	private String type;

	/**
	 * 删除标记（0、未删除，1、已删除）
	 */
	@Column(name = "DELETE_FLAG")
	private String deleteFlag;

	public String getInvestigationId() {
		return investigationId;
	}

	public void setInvestigationId(String investigationId) {
		this.investigationId = investigationId;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
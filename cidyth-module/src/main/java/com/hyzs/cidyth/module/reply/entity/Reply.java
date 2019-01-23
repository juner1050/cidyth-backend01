package com.hyzs.cidyth.module.reply.entity;

import java.util.Date;
import javax.persistence.*;

/**
 * 回复
 * 
 * @author derrick
 *
 */
@Table(name = "t_reply")
public class Reply {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator="JDBC")
	private Integer id;

	/**
	 * 被回复目标记录id
	 */
	@Column(name = "reference_id")
	private String referenceId;

	/**
	 * 回复的消息类型
	 * 
	 * @see com.hyzs.cidyth.common.enums.ReplyTypeEnum
	 */
	private String xxlx;

	/**
	 * 类型编号
	 */
	private String lxbh;

	/**
	 * 发送人员
	 */
	private String fsry;
	/**
	 * 发送人姓名
	 */
	private String fsryxm;
	/**
	 * 发送人所属单位编号
	 */
	private String fsdw;
	/**
	 * 发送人所属单位名称
	 */
	private String fsdwmc;
	/**
	 * 日期
	 */
	private Date fsrq;

	/**
	 * 内容
	 */
	private String fsnr;

	/**
	 * 接受人员
	 */
	private String hfry;
	/**
	 * 接受人姓名
	 */
	private String hfryxm;
	/**
	 * 接受人所属单位编号
	 */
	private String hfdw;
	/**
	 * 接受人所属单位名称
	 */
	private String hfdwmc;
	/**
	 * 是否附件
	 */
	private String sffj;

	/**
	 * 说明备注
	 **/
	private String smbz;

	/**
	 * 修改人员
	 */
	private String xgry;
	/**
	 * 修改人员姓名
	 */
	private String xgryxm;

	/**
	 * 修改时间
	 */
	private Date xgsj;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getXxlx() {
		return xxlx;
	}

	public void setXxlx(String xxlx) {
		this.xxlx = xxlx;
	}

	public String getLxbh() {
		return lxbh;
	}

	public void setLxbh(String lxbh) {
		this.lxbh = lxbh;
	}

	public String getFsry() {
		return fsry;
	}

	public void setFsry(String fsry) {
		this.fsry = fsry;
	}

	public String getFsryxm() {
		return fsryxm;
	}

	public void setFsryxm(String fsryxm) {
		this.fsryxm = fsryxm;
	}

	public String getFsdw() {
		return fsdw;
	}

	public void setFsdw(String fsdw) {
		this.fsdw = fsdw;
	}

	public String getFsdwmc() {
		return fsdwmc;
	}

	public void setFsdwmc(String fsdwmc) {
		this.fsdwmc = fsdwmc;
	}

	public Date getFsrq() {
		return fsrq;
	}

	public void setFsrq(Date fsrq) {
		this.fsrq = fsrq;
	}

	public String getFsnr() {
		return fsnr;
	}

	public void setFsnr(String fsnr) {
		this.fsnr = fsnr;
	}

	public String getHfry() {
		return hfry;
	}

	public void setHfry(String hfry) {
		this.hfry = hfry;
	}

	public String getHfryxm() {
		return hfryxm;
	}

	public void setHfryxm(String hfryxm) {
		this.hfryxm = hfryxm;
	}

	public String getHfdw() {
		return hfdw;
	}

	public void setHfdw(String hfdw) {
		this.hfdw = hfdw;
	}

	public String getHfdwmc() {
		return hfdwmc;
	}

	public void setHfdwmc(String hfdwmc) {
		this.hfdwmc = hfdwmc;
	}

	public String getSffj() {
		return sffj;
	}

	public void setSffj(String sffj) {
		this.sffj = sffj;
	}

	public String getSmbz() {
		return smbz;
	}

	public void setSmbz(String smbz) {
		this.smbz = smbz;
	}

	public String getXgry() {
		return xgry;
	}

	public void setXgry(String xgry) {
		this.xgry = xgry;
	}

	public String getXgryxm() {
		return xgryxm;
	}

	public void setXgryxm(String xgryxm) {
		this.xgryxm = xgryxm;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}
}
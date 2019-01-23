package com.hyzs.cidyth.module.base.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "xk_syhj")
public class SceneFingerPrintCid {

    /**
     * 唯一号
     */
    @Column(name = "ID")
    private String id;

    /**
     * 现场信息ID
     */
    @Column(name = "XXID")
    private String xxid;

    /**
     * 照片ID
     */
    @Column(name = "ZPID")
    private String zpid;

    /**
     * 序号
     */
    @Column(name = "XH")
    private Integer xh;

    /**
     * 手印类型
     */
    @Column(name = "SYLX")
    private String sylx;

    /**
     * 指纹编码
     */
    @Column(name = "ZWBM")
    private String zwbm;

    /**
     * 遗留部位
     */
    @Column(name = "YLBW")
    private String ylbw;

    /**
     * 提取方法
     */
    @Column(name = "TQFF")
    private String tqff;

    /**
     * 提取人
     */
    @Column(name = "TQR")
    private String tqr;

    /**
     * 提取日期
     */
    @Column(name = "TQRQ")
    private Date tqrq;

    /**
     * 可靠程度
     */
    @Column(name = "KKCD")
    private String kkcd;

    /**
     * 利用情况
     */
    @Column(name = "LYQK")
    private String lyqk;

    /**
     * 存入指纹系统
     */
    @Column(name = "CRZWXT")
    private String crzwxt;

    /**
     * 是否列入现场提取登记表
     */
    @Column(name = "SFLRXCTQ")
    private String sflrxctq;

    /**
     * 获取现场信息ID
     *
     * @return XXID - 现场信息ID
     */
    public String getXxid() {
        return xxid;
    }

    /**
     * 设置现场信息ID
     *
     * @param xxid 现场信息ID
     */
    public void setXxid(String xxid) {
        this.xxid = xxid;
    }

    /**
     * 获取序号
     *
     * @return XH - 序号
     */
    public Integer getXh() {
        return xh;
    }

    /**
     * 设置序号
     *
     * @param xh 序号
     */
    public void setXh(Integer xh) {
        this.xh = xh;
    }

    /**
     * 获取手印类型
     *
     * @return SYLX - 手印类型
     */
    public String getSylx() {
        return sylx;
    }

    /**
     * 设置手印类型
     *
     * @param sylx 手印类型
     */
    public void setSylx(String sylx) {
        this.sylx = sylx;
    }

    /**
     * 获取指纹编码
     *
     * @return ZWBM - 指纹编码
     */
    public String getZwbm() {
        return zwbm;
    }

    /**
     * 设置指纹编码
     *
     * @param zwbm 指纹编码
     */
    public void setZwbm(String zwbm) {
        this.zwbm = zwbm;
    }

    /**
     * 获取遗留部位
     *
     * @return YLBW - 遗留部位
     */
    public String getYlbw() {
        return ylbw;
    }

    /**
     * 设置遗留部位
     *
     * @param ylbw 遗留部位
     */
    public void setYlbw(String ylbw) {
        this.ylbw = ylbw;
    }

    /**
     * 获取提取方法
     *
     * @return TQFF - 提取方法
     */
    public String getTqff() {
        return tqff;
    }

    /**
     * 设置提取方法
     *
     * @param tqff 提取方法
     */
    public void setTqff(String tqff) {
        this.tqff = tqff;
    }

    /**
     * 获取提取人
     *
     * @return TQR - 提取人
     */
    public String getTqr() {
        return tqr;
    }

    /**
     * 设置提取人
     *
     * @param tqr 提取人
     */
    public void setTqr(String tqr) {
        this.tqr = tqr;
    }

    /**
     * 获取提取日期
     *
     * @return TQRQ - 提取日期
     */
    public Date getTqrq() {
        return tqrq;
    }

    /**
     * 设置提取日期
     *
     * @param tqrq 提取日期
     */
    public void setTqrq(Date tqrq) {
        this.tqrq = tqrq;
    }

    /**
     * 获取可靠程度
     *
     * @return KKCD - 可靠程度
     */
    public String getKkcd() {
        return kkcd;
    }

    /**
     * 设置可靠程度
     *
     * @param kkcd 可靠程度
     */
    public void setKkcd(String kkcd) {
        this.kkcd = kkcd;
    }

    /**
     * 获取利用情况
     *
     * @return LYQK - 利用情况
     */
    public String getLyqk() {
        return lyqk;
    }

    /**
     * 设置利用情况
     *
     * @param lyqk 利用情况
     */
    public void setLyqk(String lyqk) {
        this.lyqk = lyqk;
    }

    /**
     * 获取存入指纹系统
     *
     * @return CRZWXT - 存入指纹系统
     */
    public String getCrzwxt() {
        return crzwxt;
    }

    /**
     * 设置存入指纹系统
     *
     * @param crzwxt 存入指纹系统
     */
    public void setCrzwxt(String crzwxt) {
        this.crzwxt = crzwxt;
    }

    /**
     * 获取是否列入现场提取登记表
     *
     * @return SFLRXCTQ - 是否列入现场提取登记表
     */
    public String getSflrxctq() {
        return sflrxctq;
    }

    /**
     * 设置是否列入现场提取登记表
     *
     * @param sflrxctq 是否列入现场提取登记表
     */
    public void setSflrxctq(String sflrxctq) {
        this.sflrxctq = sflrxctq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZpid() {
        return zpid;
    }

    public void setZpid(String zpid) {
        this.zpid = zpid;
    }
}
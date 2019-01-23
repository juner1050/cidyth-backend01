package com.hyzs.cidyth.module.base.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_xk_zjhj")
public class SceneFootPrint {
    /**
     * 主键
     */
    @Id
    @Column(name = "ZJID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer zjid;

    /**
     * 唯一号
     */
    @Column(name = "ID")
    private String id;

    /**
     * 案件编号
     */
    @Column(name = "AJBH")
    private String ajbh;

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
    private String xh;

    /**
     * 足迹类型
     */
    @Column(name = "ZJLX")
    private String zjlx;

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
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "TQRQ")
    private Date tqrq;

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
     * 获取主键
     *
     * @return ID - 主键
     */
    public Integer getZjid() {
        return zjid;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setZjid(Integer zjid) {
        this.zjid = zjid;
    }

    /**
     * 获取案件编号
     *
     * @return AJBH - 案件编号
     */
    public String getAjbh() {
        return ajbh;
    }

    /**
     * 设置案件编号
     *
     * @param ajbh 案件编号
     */
    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

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
    public String getXh() {
        return xh;
    }

    /**
     * 设置序号
     *
     * @param xh 序号
     */
    public void setXh(String xh) {
        this.xh = xh;
    }

    /**
     * 获取足迹类型
     *
     * @return ZJLX - 足迹类型
     */
    public String getZjlx() {
        return zjlx;
    }

    /**
     * 设置足迹类型
     *
     * @param zjlx 足迹类型
     */
    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
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
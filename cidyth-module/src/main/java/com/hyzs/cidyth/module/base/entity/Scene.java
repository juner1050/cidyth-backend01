package com.hyzs.cidyth.module.base.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_xk_xcjbxx")
public class Scene {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * 现勘编号,勘验号
     */
    @Column(name = "XXID")
    private String xxid;
    
    /**
     * 现勘编号,勘验号
     */
    @Column(name = "XCBH")
    private String xcbh;

    /**
     * 案件编号
     */
    @Column(name = "AJBH")
    private String ajbh;

    /**
     * 接处警ID
     */
    @Column(name = "JCJID")
    private String jcjid;

    /**
     * 现场复勘号
     */
    @Column(name = "XCFKH")
    private String xcfkh;

    /**
     * 勘验时间开始
     */
    @Column(name = "KYSJKS")
    private Date kysjks;

    /**
     * 勘验时间结束
     */
    @Column(name = "KYSJJS")
    private Date kysjjs;

    /**
     * 勘验地点
     */
    @Column(name = "KYDD")
    private String kydd;

    /**
     * 保护人姓名
     */
    @Column(name = "BHRXM")
    private String bhrxm;

    /**
     * 保护单位
     */
    @Column(name = "BHDW")
    private String bhdw;

    /**
     * 保护人职务
     */
    @Column(name = "BHRZW")
    private String bhrzw;

    /**
     * 保护措施
     */
    @Column(name = "BHCS")
    private String bhcs;

    /**
     * 保护时间
     */
    @Column(name = "BHSJ")
    private Date bhsj;

    /**
     * 现场物品翻动程度
     */
    @Column(name = "XCWPFD")
    private String xcwpfd;

    /**
     * 现场条件
     */
    @Column(name = "XCTJ")
    private String xctj;

    /**
     * 天气情况（选择）
     */
    @Column(name = "TQQK")
    private String tqqk;

    /**
     * 天气情况其他值（文字）
     */
    @Column(name = "TQQKQT")
    private String tqqkqt;

    /**
     * 湿度
     */
    @Column(name = "SD")
    private Float sd;

    /**
     * 相对湿度
     */
    @Column(name = "XDSD")
    private Float xdsd;

    /**
     * 风向
     */
    @Column(name = "FX")
    private String fx;

    /**
     * 光照条件
     */
    @Column(name = "GZTJ")
    private String gztj;

    /**
     * 现场指挥人员
     */
    @Column(name = "XCZHRY")
    private String xczhry;

    /**
     * 勘验检查人员
     */
    @Column(name = "KYJCRY")
    private String kyjcry;

    /**
     * 其他到达现场人员
     */
    @Column(name = "QTDDXCRY")
    private String qtddxcry;

    /**
     * 现场遗留物
     */
    @Column(name = "XCYLW")
    private String xcylw;

    /**
     * 现场处置意见（选择）
     */
    @Column(name = "XCCZYJXZ")
    private String xcczyjxz;

    /**
     * 现场处置意见（文字）
     */
    @Column(name = "XCCZYJWZ")
    private String xcczyjwz;

    /**
     * 录像
     */
    @Column(name = "LX")
    private Integer lx;

    /**
     * 录音
     */
    @Column(name = "LY")
    private Integer ly;

    /**
     * 伤亡情况（伤）
     */
    @Column(name = "SWQKS")
    private Integer swqks;

    /**
     * 伤亡情况（亡）
     */
    @Column(name = "SWQKW")
    private Integer swqkw;

    /**
     * 损失物品总价值
     */
    @Column(name = "SSWPZJZ")
    private Integer sswpzjz;

    /**
     * 见证人备注
     */
    @Column(name = "JZRBZ")
    private String jzrbz;

    /**
     * 保存标志
     */
    @Column(name = "BCBZ")
    private String bcbz;

    /**
     * 完成标志
     */
    @Column(name = "WCBZ")
    private String wcbz;

    /**
     * 合格标志
     */
    @Column(name = "HGBZ")
    private String hgbz;

    /**
     * 勘验检查情况
     */
    @Column(name = "KYJCQK")
    private String kyjcqk;

    /**
     * @return ID
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
     * 获取现勘编号,勘验号
     *
     * @return XCBH - 现勘编号,勘验号
     */
    public String getXcbh() {
        return xcbh;
    }

    /**
     * 设置现勘编号,勘验号
     *
     * @param xcbh 现勘编号,勘验号
     */
    public void setXcbh(String xcbh) {
        this.xcbh = xcbh;
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
     * 获取接处警ID
     *
     * @return JCJID - 接处警ID
     */
    public String getJcjid() {
        return jcjid;
    }

    /**
     * 设置接处警ID
     *
     * @param jcjid 接处警ID
     */
    public void setJcjid(String jcjid) {
        this.jcjid = jcjid;
    }

    /**
     * 获取现场复勘号
     *
     * @return XCFKH - 现场复勘号
     */
    public String getXcfkh() {
        return xcfkh;
    }

    /**
     * 设置现场复勘号
     *
     * @param xcfkh 现场复勘号
     */
    public void setXcfkh(String xcfkh) {
        this.xcfkh = xcfkh;
    }

    /**
     * 获取勘验时间开始
     *
     * @return KYSJKS - 勘验时间开始
     */
    public Date getKysjks() {
        return kysjks;
    }

    /**
     * 设置勘验时间开始
     *
     * @param kysjks 勘验时间开始
     */
    public void setKysjks(Date kysjks) {
        this.kysjks = kysjks;
    }

    /**
     * 获取勘验时间结束
     *
     * @return KYSJJS - 勘验时间结束
     */
    public Date getKysjjs() {
        return kysjjs;
    }

    /**
     * 设置勘验时间结束
     *
     * @param kysjjs 勘验时间结束
     */
    public void setKysjjs(Date kysjjs) {
        this.kysjjs = kysjjs;
    }

    /**
     * 获取勘验地点
     *
     * @return KYDD - 勘验地点
     */
    public String getKydd() {
        return kydd;
    }

    /**
     * 设置勘验地点
     *
     * @param kydd 勘验地点
     */
    public void setKydd(String kydd) {
        this.kydd = kydd;
    }

    /**
     * 获取保护人姓名
     *
     * @return BHRXM - 保护人姓名
     */
    public String getBhrxm() {
        return bhrxm;
    }

    /**
     * 设置保护人姓名
     *
     * @param bhrxm 保护人姓名
     */
    public void setBhrxm(String bhrxm) {
        this.bhrxm = bhrxm;
    }

    /**
     * 获取保护单位
     *
     * @return BHDW - 保护单位
     */
    public String getBhdw() {
        return bhdw;
    }

    /**
     * 设置保护单位
     *
     * @param bhdw 保护单位
     */
    public void setBhdw(String bhdw) {
        this.bhdw = bhdw;
    }

    /**
     * 获取保护人职务
     *
     * @return BHRZW - 保护人职务
     */
    public String getBhrzw() {
        return bhrzw;
    }

    /**
     * 设置保护人职务
     *
     * @param bhrzw 保护人职务
     */
    public void setBhrzw(String bhrzw) {
        this.bhrzw = bhrzw;
    }

    /**
     * 获取保护措施
     *
     * @return BHCS - 保护措施
     */
    public String getBhcs() {
        return bhcs;
    }

    /**
     * 设置保护措施
     *
     * @param bhcs 保护措施
     */
    public void setBhcs(String bhcs) {
        this.bhcs = bhcs;
    }

    /**
     * 获取保护时间
     *
     * @return BHSJ - 保护时间
     */
    public Date getBhsj() {
        return bhsj;
    }

    /**
     * 设置保护时间
     *
     * @param bhsj 保护时间
     */
    public void setBhsj(Date bhsj) {
        this.bhsj = bhsj;
    }

    /**
     * 获取现场物品翻动程度
     *
     * @return XCWPFD - 现场物品翻动程度
     */
    public String getXcwpfd() {
        return xcwpfd;
    }

    /**
     * 设置现场物品翻动程度
     *
     * @param xcwpfd 现场物品翻动程度
     */
    public void setXcwpfd(String xcwpfd) {
        this.xcwpfd = xcwpfd;
    }

    /**
     * 获取现场条件
     *
     * @return XCTJ - 现场条件
     */
    public String getXctj() {
        return xctj;
    }

    /**
     * 设置现场条件
     *
     * @param xctj 现场条件
     */
    public void setXctj(String xctj) {
        this.xctj = xctj;
    }

    /**
     * 获取天气情况（选择）
     *
     * @return TQQK - 天气情况（选择）
     */
    public String getTqqk() {
        return tqqk;
    }

    /**
     * 设置天气情况（选择）
     *
     * @param tqqk 天气情况（选择）
     */
    public void setTqqk(String tqqk) {
        this.tqqk = tqqk;
    }

    /**
     * 获取天气情况其他值（文字）
     *
     * @return TQQKQT - 天气情况其他值（文字）
     */
    public String getTqqkqt() {
        return tqqkqt;
    }

    /**
     * 设置天气情况其他值（文字）
     *
     * @param tqqkqt 天气情况其他值（文字）
     */
    public void setTqqkqt(String tqqkqt) {
        this.tqqkqt = tqqkqt;
    }

    /**
     * 获取湿度
     *
     * @return SD - 湿度
     */
    public Float getSd() {
        return sd;
    }

    /**
     * 设置湿度
     *
     * @param sd 湿度
     */
    public void setSd(Float sd) {
        this.sd = sd;
    }

    /**
     * 获取相对湿度
     *
     * @return XDSD - 相对湿度
     */
    public Float getXdsd() {
        return xdsd;
    }

    /**
     * 设置相对湿度
     *
     * @param xdsd 相对湿度
     */
    public void setXdsd(Float xdsd) {
        this.xdsd = xdsd;
    }

    /**
     * 获取风向
     *
     * @return FX - 风向
     */
    public String getFx() {
        return fx;
    }

    /**
     * 设置风向
     *
     * @param fx 风向
     */
    public void setFx(String fx) {
        this.fx = fx;
    }

    /**
     * 获取光照条件
     *
     * @return GZTJ - 光照条件
     */
    public String getGztj() {
        return gztj;
    }

    /**
     * 设置光照条件
     *
     * @param gztj 光照条件
     */
    public void setGztj(String gztj) {
        this.gztj = gztj;
    }

    /**
     * 获取现场指挥人员
     *
     * @return XCZHRY - 现场指挥人员
     */
    public String getXczhry() {
        return xczhry;
    }

    /**
     * 设置现场指挥人员
     *
     * @param xczhry 现场指挥人员
     */
    public void setXczhry(String xczhry) {
        this.xczhry = xczhry;
    }

    /**
     * 获取勘验检查人员
     *
     * @return KYJCRY - 勘验检查人员
     */
    public String getKyjcry() {
        return kyjcry;
    }

    /**
     * 设置勘验检查人员
     *
     * @param kyjcry 勘验检查人员
     */
    public void setKyjcry(String kyjcry) {
        this.kyjcry = kyjcry;
    }

    /**
     * 获取其他到达现场人员
     *
     * @return QTDDXCRY - 其他到达现场人员
     */
    public String getQtddxcry() {
        return qtddxcry;
    }

    /**
     * 设置其他到达现场人员
     *
     * @param qtddxcry 其他到达现场人员
     */
    public void setQtddxcry(String qtddxcry) {
        this.qtddxcry = qtddxcry;
    }

    /**
     * 获取现场遗留物
     *
     * @return XCYLW - 现场遗留物
     */
    public String getXcylw() {
        return xcylw;
    }

    /**
     * 设置现场遗留物
     *
     * @param xcylw 现场遗留物
     */
    public void setXcylw(String xcylw) {
        this.xcylw = xcylw;
    }

    /**
     * 获取现场处置意见（选择）
     *
     * @return XCCZYJXZ - 现场处置意见（选择）
     */
    public String getXcczyjxz() {
        return xcczyjxz;
    }

    /**
     * 设置现场处置意见（选择）
     *
     * @param xcczyjxz 现场处置意见（选择）
     */
    public void setXcczyjxz(String xcczyjxz) {
        this.xcczyjxz = xcczyjxz;
    }

    /**
     * 获取现场处置意见（文字）
     *
     * @return XCCZYJWZ - 现场处置意见（文字）
     */
    public String getXcczyjwz() {
        return xcczyjwz;
    }

    /**
     * 设置现场处置意见（文字）
     *
     * @param xcczyjwz 现场处置意见（文字）
     */
    public void setXcczyjwz(String xcczyjwz) {
        this.xcczyjwz = xcczyjwz;
    }

    /**
     * 获取录像
     *
     * @return LX - 录像
     */
    public Integer getLx() {
        return lx;
    }

    /**
     * 设置录像
     *
     * @param lx 录像
     */
    public void setLx(Integer lx) {
        this.lx = lx;
    }

    /**
     * 获取录音
     *
     * @return LY - 录音
     */
    public Integer getLy() {
        return ly;
    }

    /**
     * 设置录音
     *
     * @param ly 录音
     */
    public void setLy(Integer ly) {
        this.ly = ly;
    }

    /**
     * 获取伤亡情况（伤）
     *
     * @return SWQKS - 伤亡情况（伤）
     */
    public Integer getSwqks() {
        return swqks;
    }

    /**
     * 设置伤亡情况（伤）
     *
     * @param swqks 伤亡情况（伤）
     */
    public void setSwqks(Integer swqks) {
        this.swqks = swqks;
    }

    /**
     * 获取伤亡情况（亡）
     *
     * @return SWQKW - 伤亡情况（亡）
     */
    public Integer getSwqkw() {
        return swqkw;
    }

    /**
     * 设置伤亡情况（亡）
     *
     * @param swqkw 伤亡情况（亡）
     */
    public void setSwqkw(Integer swqkw) {
        this.swqkw = swqkw;
    }

    /**
     * 获取损失物品总价值
     *
     * @return SSWPZJZ - 损失物品总价值
     */
    public Integer getSswpzjz() {
        return sswpzjz;
    }

    /**
     * 设置损失物品总价值
     *
     * @param sswpzjz 损失物品总价值
     */
    public void setSswpzjz(Integer sswpzjz) {
        this.sswpzjz = sswpzjz;
    }

    /**
     * 获取见证人备注
     *
     * @return JZRBZ - 见证人备注
     */
    public String getJzrbz() {
        return jzrbz;
    }

    /**
     * 设置见证人备注
     *
     * @param jzrbz 见证人备注
     */
    public void setJzrbz(String jzrbz) {
        this.jzrbz = jzrbz;
    }

    /**
     * 获取保存标志
     *
     * @return BCBZ - 保存标志
     */
    public String getBcbz() {
        return bcbz;
    }

    /**
     * 设置保存标志
     *
     * @param bcbz 保存标志
     */
    public void setBcbz(String bcbz) {
        this.bcbz = bcbz;
    }

    /**
     * 获取完成标志
     *
     * @return WCBZ - 完成标志
     */
    public String getWcbz() {
        return wcbz;
    }

    /**
     * 设置完成标志
     *
     * @param wcbz 完成标志
     */
    public void setWcbz(String wcbz) {
        this.wcbz = wcbz;
    }

    /**
     * 获取合格标志
     *
     * @return HGBZ - 合格标志
     */
    public String getHgbz() {
        return hgbz;
    }

    /**
     * 设置合格标志
     *
     * @param hgbz 合格标志
     */
    public void setHgbz(String hgbz) {
        this.hgbz = hgbz;
    }

    /**
     * 获取勘验检查情况
     *
     * @return KYJCQK - 勘验检查情况
     */
    public String getKyjcqk() {
        return kyjcqk;
    }

    /**
     * 设置勘验检查情况
     *
     * @param kyjcqk 勘验检查情况
     */
    public void setKyjcqk(String kyjcqk) {
        this.kyjcqk = kyjcqk;
    }

	public String getXxid() {
		return xxid;
	}

	public void setXxid(String xxid) {
		this.xxid = xxid;
	}
    
    
}
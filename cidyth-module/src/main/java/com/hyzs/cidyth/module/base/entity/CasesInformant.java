package com.hyzs.cidyth.module.base.entity;

import java.util.Date;
import javax.persistence.*;
/**
 * 案件报案人、受害人
 * @author derrick
 *
 */
@Table(name = "t_asj_baszqt")
public class CasesInformant {
    /**
     * 主键
     */
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    private String systemid;
    /**
     * 案件编号
     */
    private String ajbh;
    
    /**
     * 人员编号
     */
    private String rybh;

    private String zzdxzqh;

    private String cym;

    private String hyzk;

    /**
     * 身份
     */
    private String sf;

    private String xrtssf;

    private String xrzhiy;

    private String brdwqh;

    /**
     * 居住地行政区划
     */
    private String jzdxzqh;

    /**
     * 姓名
     */
    private String xm;

    /**
     * 出生日期
     */
    private Date csrq;

    /**
     * 性别
     */
    private String xb;

    /**
     * 证件种类
     */
    private String zjzl;

    /**
     * 证件号码
     */
    private String zjhm;

    /**
     * 国家
     */
    private String gj;

    /**
     * 英文姓
     */
    private String ywx;

    /**
     * 英文名
     */
    private String ywm;

    /**
     * 籍贯
     */
    private String jiguan;

    /**
     * 民族
     */
    private String mz;

    private String bm;

    private String byname;

    private String qtzjlx1;

    private String qtzjhm1;

    /**
     * 户籍地址
     */
    private String hjdz;

    /**
     * 行政区划
     */
    private String xzqh;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 工作单位
     */
    private String gzdw;

    /**
     * 人员文化程度
     */
    private String rywhcd;

    private String jzyy;

    private String tlsy;

    private String lhyy;

    private String ljd;

    private String qzzlqz;

    private String qzhm;

    private String qzqfd;

    /**
     * 详细地址ms
     */
    private String xxdzms;

    /**
     * 联系人姓名
     */
    private String lxrxm;

    /**
     * 联系人电话
     */
    private String lxrdh;

    /**
     * 接待人名称
     */
    private String jdrmc;

    private String sfzy;

    /**
     * 人员类型
     */
    private String rylx;

    /**
     * 人员状态
     */
    private String rystate;

    /**
     * 人员受害形式
     */
    private String ryshxs;

    /**
     * 人员受害程度
     */
    private String rsshcd;

    private String sazz;

    private String ssje;

    private String srwhss;

    private String qfqk;

    private String swqk;

    /**
     * 备注
     */
    private String bz;

    private String ryly;

    private String ztid;

    private String lrdw;

    private String lrsj;

    private String lrr;

    private String departmentcode;

    /**
     * 创建人
     */
    private String creator;

    private String createdtime;

    private String securitygrade;

    private String reservation01;

    private String reservation02;

    private String reservation03;

    private String reservation04;

    private String reservation05;

    private String reservation06;

    private String reservation07;

    private String reservation08;

    private String reservation09;

    private String reservation10;

    private String lastupdatedby;

    private String lastupdatedtime;

    private String refreshtime;

    private String uploadflag;

    private String downloadflag;

    private String deleteflag;

    private String reservation11;

    private String reservation12;

    private String reservation13;

    private String reservation14;

    private String reservation15;

    private String reservation16;

    private String reservation17;

    private String reservation18;

    private String reservation19;

    private String reservation20;

    private String datastate;

    private String datacheck;

    private String queryid;

    /**
     * 入库登记时间
     */
    private String rkdjsj;

    /**
     * 入库修改时间
     */
    private String rkgxsj;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return systemid
     */
    public String getSystemid() {
        return systemid;
    }

    /**
     * @param systemid
     */
    public void setSystemid(String systemid) {
        this.systemid = systemid;
    }

    /**
     * 获取人员编号
     *
     * @return rybh - 人员编号
     */
    public String getRybh() {
        return rybh;
    }

    /**
     * 设置人员编号
     *
     * @param rybh 人员编号
     */
    public void setRybh(String rybh) {
        this.rybh = rybh;
    }

    /**
     * @return zzdxzqh
     */
    public String getZzdxzqh() {
        return zzdxzqh;
    }

    /**
     * @param zzdxzqh
     */
    public void setZzdxzqh(String zzdxzqh) {
        this.zzdxzqh = zzdxzqh;
    }

    /**
     * @return cym
     */
    public String getCym() {
        return cym;
    }

    /**
     * @param cym
     */
    public void setCym(String cym) {
        this.cym = cym;
    }

    /**
     * @return hyzk
     */
    public String getHyzk() {
        return hyzk;
    }

    /**
     * @param hyzk
     */
    public void setHyzk(String hyzk) {
        this.hyzk = hyzk;
    }

    /**
     * 获取身份
     *
     * @return sf - 身份
     */
    public String getSf() {
        return sf;
    }

    /**
     * 设置身份
     *
     * @param sf 身份
     */
    public void setSf(String sf) {
        this.sf = sf;
    }

    /**
     * @return xrtssf
     */
    public String getXrtssf() {
        return xrtssf;
    }

    /**
     * @param xrtssf
     */
    public void setXrtssf(String xrtssf) {
        this.xrtssf = xrtssf;
    }

    /**
     * @return xrzhiy
     */
    public String getXrzhiy() {
        return xrzhiy;
    }

    /**
     * @param xrzhiy
     */
    public void setXrzhiy(String xrzhiy) {
        this.xrzhiy = xrzhiy;
    }

    /**
     * @return brdwqh
     */
    public String getBrdwqh() {
        return brdwqh;
    }

    /**
     * @param brdwqh
     */
    public void setBrdwqh(String brdwqh) {
        this.brdwqh = brdwqh;
    }

    /**
     * 获取居住地行政区划
     *
     * @return jzdxzqh - 居住地行政区划
     */
    public String getJzdxzqh() {
        return jzdxzqh;
    }

    /**
     * 设置居住地行政区划
     *
     * @param jzdxzqh 居住地行政区划
     */
    public void setJzdxzqh(String jzdxzqh) {
        this.jzdxzqh = jzdxzqh;
    }

    /**
     * 获取姓名
     *
     * @return xm - 姓名
     */
    public String getXm() {
        return xm;
    }

    /**
     * 设置姓名
     *
     * @param xm 姓名
     */
    public void setXm(String xm) {
        this.xm = xm;
    }

    /**
     * 获取出生日期
     *
     * @return csrq - 出生日期
     */
    public Date getCsrq() {
        return csrq;
    }

    /**
     * 设置出生日期
     *
     * @param csrq 出生日期
     */
    public void setCsrq(Date csrq) {
        this.csrq = csrq;
    }

    /**
     * 获取性别
     *
     * @return xb - 性别
     */
    public String getXb() {
        return xb;
    }

    /**
     * 设置性别
     *
     * @param xb 性别
     */
    public void setXb(String xb) {
        this.xb = xb;
    }

    /**
     * 获取证件种类
     *
     * @return zjzl - 证件种类
     */
    public String getZjzl() {
        return zjzl;
    }

    /**
     * 设置证件种类
     *
     * @param zjzl 证件种类
     */
    public void setZjzl(String zjzl) {
        this.zjzl = zjzl;
    }

    /**
     * 获取证件号码
     *
     * @return zjhm - 证件号码
     */
    public String getZjhm() {
        return zjhm;
    }

    /**
     * 设置证件号码
     *
     * @param zjhm 证件号码
     */
    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    /**
     * 获取国家
     *
     * @return gj - 国家
     */
    public String getGj() {
        return gj;
    }

    /**
     * 设置国家
     *
     * @param gj 国家
     */
    public void setGj(String gj) {
        this.gj = gj;
    }

    /**
     * 获取英文姓
     *
     * @return ywx - 英文姓
     */
    public String getYwx() {
        return ywx;
    }

    /**
     * 设置英文姓
     *
     * @param ywx 英文姓
     */
    public void setYwx(String ywx) {
        this.ywx = ywx;
    }

    /**
     * 获取英文名
     *
     * @return ywm - 英文名
     */
    public String getYwm() {
        return ywm;
    }

    /**
     * 设置英文名
     *
     * @param ywm 英文名
     */
    public void setYwm(String ywm) {
        this.ywm = ywm;
    }

    /**
     * 获取籍贯
     *
     * @return jiguan - 籍贯
     */
    public String getJiguan() {
        return jiguan;
    }

    /**
     * 设置籍贯
     *
     * @param jiguan 籍贯
     */
    public void setJiguan(String jiguan) {
        this.jiguan = jiguan;
    }

    /**
     * 获取民族
     *
     * @return mz - 民族
     */
    public String getMz() {
        return mz;
    }

    /**
     * 设置民族
     *
     * @param mz 民族
     */
    public void setMz(String mz) {
        this.mz = mz;
    }

    /**
     * @return bm
     */
    public String getBm() {
        return bm;
    }

    /**
     * @param bm
     */
    public void setBm(String bm) {
        this.bm = bm;
    }

    /**
     * @return byname
     */
    public String getByname() {
        return byname;
    }

    /**
     * @param byname
     */
    public void setByname(String byname) {
        this.byname = byname;
    }

    /**
     * @return qtzjlx1
     */
    public String getQtzjlx1() {
        return qtzjlx1;
    }

    /**
     * @param qtzjlx1
     */
    public void setQtzjlx1(String qtzjlx1) {
        this.qtzjlx1 = qtzjlx1;
    }

    /**
     * @return qtzjhm1
     */
    public String getQtzjhm1() {
        return qtzjhm1;
    }

    /**
     * @param qtzjhm1
     */
    public void setQtzjhm1(String qtzjhm1) {
        this.qtzjhm1 = qtzjhm1;
    }

    /**
     * 获取户籍地址
     *
     * @return hjdz - 户籍地址
     */
    public String getHjdz() {
        return hjdz;
    }

    /**
     * 设置户籍地址
     *
     * @param hjdz 户籍地址
     */
    public void setHjdz(String hjdz) {
        this.hjdz = hjdz;
    }

    /**
     * 获取行政区划
     *
     * @return xzqh - 行政区划
     */
    public String getXzqh() {
        return xzqh;
    }

    /**
     * 设置行政区划
     *
     * @param xzqh 行政区划
     */
    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    /**
     * 获取联系电话
     *
     * @return lxdh - 联系电话
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * 设置联系电话
     *
     * @param lxdh 联系电话
     */
    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    /**
     * 获取工作单位
     *
     * @return gzdw - 工作单位
     */
    public String getGzdw() {
        return gzdw;
    }

    /**
     * 设置工作单位
     *
     * @param gzdw 工作单位
     */
    public void setGzdw(String gzdw) {
        this.gzdw = gzdw;
    }

    /**
     * 获取人员文化程度
     *
     * @return rywhcd - 人员文化程度
     */
    public String getRywhcd() {
        return rywhcd;
    }

    /**
     * 设置人员文化程度
     *
     * @param rywhcd 人员文化程度
     */
    public void setRywhcd(String rywhcd) {
        this.rywhcd = rywhcd;
    }

    /**
     * @return jzyy
     */
    public String getJzyy() {
        return jzyy;
    }

    /**
     * @param jzyy
     */
    public void setJzyy(String jzyy) {
        this.jzyy = jzyy;
    }

    /**
     * @return tlsy
     */
    public String getTlsy() {
        return tlsy;
    }

    /**
     * @param tlsy
     */
    public void setTlsy(String tlsy) {
        this.tlsy = tlsy;
    }

    /**
     * @return lhyy
     */
    public String getLhyy() {
        return lhyy;
    }

    /**
     * @param lhyy
     */
    public void setLhyy(String lhyy) {
        this.lhyy = lhyy;
    }

    /**
     * @return ljd
     */
    public String getLjd() {
        return ljd;
    }

    /**
     * @param ljd
     */
    public void setLjd(String ljd) {
        this.ljd = ljd;
    }

    /**
     * @return qzzlqz
     */
    public String getQzzlqz() {
        return qzzlqz;
    }

    /**
     * @param qzzlqz
     */
    public void setQzzlqz(String qzzlqz) {
        this.qzzlqz = qzzlqz;
    }

    /**
     * @return qzhm
     */
    public String getQzhm() {
        return qzhm;
    }

    /**
     * @param qzhm
     */
    public void setQzhm(String qzhm) {
        this.qzhm = qzhm;
    }

    /**
     * @return qzqfd
     */
    public String getQzqfd() {
        return qzqfd;
    }

    /**
     * @param qzqfd
     */
    public void setQzqfd(String qzqfd) {
        this.qzqfd = qzqfd;
    }

    /**
     * 获取详细地址ms
     *
     * @return xxdzms - 详细地址ms
     */
    public String getXxdzms() {
        return xxdzms;
    }

    /**
     * 设置详细地址ms
     *
     * @param xxdzms 详细地址ms
     */
    public void setXxdzms(String xxdzms) {
        this.xxdzms = xxdzms;
    }

    /**
     * 获取联系人姓名
     *
     * @return lxrxm - 联系人姓名
     */
    public String getLxrxm() {
        return lxrxm;
    }

    /**
     * 设置联系人姓名
     *
     * @param lxrxm 联系人姓名
     */
    public void setLxrxm(String lxrxm) {
        this.lxrxm = lxrxm;
    }

    /**
     * 获取联系人电话
     *
     * @return lxrdh - 联系人电话
     */
    public String getLxrdh() {
        return lxrdh;
    }

    /**
     * 设置联系人电话
     *
     * @param lxrdh 联系人电话
     */
    public void setLxrdh(String lxrdh) {
        this.lxrdh = lxrdh;
    }

    /**
     * 获取接待人名称
     *
     * @return jdrmc - 接待人名称
     */
    public String getJdrmc() {
        return jdrmc;
    }

    /**
     * 设置接待人名称
     *
     * @param jdrmc 接待人名称
     */
    public void setJdrmc(String jdrmc) {
        this.jdrmc = jdrmc;
    }

    /**
     * @return sfzy
     */
    public String getSfzy() {
        return sfzy;
    }

    /**
     * @param sfzy
     */
    public void setSfzy(String sfzy) {
        this.sfzy = sfzy;
    }

    /**
     * 获取人员类型
     *
     * @return rylx - 人员类型
     */
    public String getRylx() {
        return rylx;
    }

    /**
     * 设置人员类型
     *
     * @param rylx 人员类型
     */
    public void setRylx(String rylx) {
        this.rylx = rylx;
    }

    /**
     * 获取人员状态
     *
     * @return rystate - 人员状态
     */
    public String getRystate() {
        return rystate;
    }

    /**
     * 设置人员状态
     *
     * @param rystate 人员状态
     */
    public void setRystate(String rystate) {
        this.rystate = rystate;
    }

    /**
     * 获取人员受害形式
     *
     * @return ryshxs - 人员受害形式
     */
    public String getRyshxs() {
        return ryshxs;
    }

    /**
     * 设置人员受害形式
     *
     * @param ryshxs 人员受害形式
     */
    public void setRyshxs(String ryshxs) {
        this.ryshxs = ryshxs;
    }

    /**
     * 获取人员受害程度
     *
     * @return rsshcd - 人员受害程度
     */
    public String getRsshcd() {
        return rsshcd;
    }

    /**
     * 设置人员受害程度
     *
     * @param rsshcd 人员受害程度
     */
    public void setRsshcd(String rsshcd) {
        this.rsshcd = rsshcd;
    }

    /**
     * @return sazz
     */
    public String getSazz() {
        return sazz;
    }

    /**
     * @param sazz
     */
    public void setSazz(String sazz) {
        this.sazz = sazz;
    }

    /**
     * @return ssje
     */
    public String getSsje() {
        return ssje;
    }

    /**
     * @param ssje
     */
    public void setSsje(String ssje) {
        this.ssje = ssje;
    }

    /**
     * @return srwhss
     */
    public String getSrwhss() {
        return srwhss;
    }

    /**
     * @param srwhss
     */
    public void setSrwhss(String srwhss) {
        this.srwhss = srwhss;
    }

    /**
     * @return qfqk
     */
    public String getQfqk() {
        return qfqk;
    }

    /**
     * @param qfqk
     */
    public void setQfqk(String qfqk) {
        this.qfqk = qfqk;
    }

    /**
     * @return swqk
     */
    public String getSwqk() {
        return swqk;
    }

    /**
     * @param swqk
     */
    public void setSwqk(String swqk) {
        this.swqk = swqk;
    }

    /**
     * 获取备注
     *
     * @return bz - 备注
     */
    public String getBz() {
        return bz;
    }

    /**
     * 设置备注
     *
     * @param bz 备注
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * @return ryly
     */
    public String getRyly() {
        return ryly;
    }

    /**
     * @param ryly
     */
    public void setRyly(String ryly) {
        this.ryly = ryly;
    }

    /**
     * @return ztid
     */
    public String getZtid() {
        return ztid;
    }

    /**
     * @param ztid
     */
    public void setZtid(String ztid) {
        this.ztid = ztid;
    }

    /**
     * @return lrdw
     */
    public String getLrdw() {
        return lrdw;
    }

    /**
     * @param lrdw
     */
    public void setLrdw(String lrdw) {
        this.lrdw = lrdw;
    }

    /**
     * @return lrsj
     */
    public String getLrsj() {
        return lrsj;
    }

    /**
     * @param lrsj
     */
    public void setLrsj(String lrsj) {
        this.lrsj = lrsj;
    }

    /**
     * @return lrr
     */
    public String getLrr() {
        return lrr;
    }

    /**
     * @param lrr
     */
    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    /**
     * @return departmentcode
     */
    public String getDepartmentcode() {
        return departmentcode;
    }

    /**
     * @param departmentcode
     */
    public void setDepartmentcode(String departmentcode) {
        this.departmentcode = departmentcode;
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * @return createdtime
     */
    public String getCreatedtime() {
        return createdtime;
    }

    /**
     * @param createdtime
     */
    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * @return securitygrade
     */
    public String getSecuritygrade() {
        return securitygrade;
    }

    /**
     * @param securitygrade
     */
    public void setSecuritygrade(String securitygrade) {
        this.securitygrade = securitygrade;
    }

    /**
     * @return reservation01
     */
    public String getReservation01() {
        return reservation01;
    }

    /**
     * @param reservation01
     */
    public void setReservation01(String reservation01) {
        this.reservation01 = reservation01;
    }

    /**
     * @return reservation02
     */
    public String getReservation02() {
        return reservation02;
    }

    /**
     * @param reservation02
     */
    public void setReservation02(String reservation02) {
        this.reservation02 = reservation02;
    }

    /**
     * @return reservation03
     */
    public String getReservation03() {
        return reservation03;
    }

    /**
     * @param reservation03
     */
    public void setReservation03(String reservation03) {
        this.reservation03 = reservation03;
    }

    /**
     * @return reservation04
     */
    public String getReservation04() {
        return reservation04;
    }

    /**
     * @param reservation04
     */
    public void setReservation04(String reservation04) {
        this.reservation04 = reservation04;
    }

    /**
     * @return reservation05
     */
    public String getReservation05() {
        return reservation05;
    }

    /**
     * @param reservation05
     */
    public void setReservation05(String reservation05) {
        this.reservation05 = reservation05;
    }

    /**
     * @return reservation06
     */
    public String getReservation06() {
        return reservation06;
    }

    /**
     * @param reservation06
     */
    public void setReservation06(String reservation06) {
        this.reservation06 = reservation06;
    }

    /**
     * @return reservation07
     */
    public String getReservation07() {
        return reservation07;
    }

    /**
     * @param reservation07
     */
    public void setReservation07(String reservation07) {
        this.reservation07 = reservation07;
    }

    /**
     * @return reservation08
     */
    public String getReservation08() {
        return reservation08;
    }

    /**
     * @param reservation08
     */
    public void setReservation08(String reservation08) {
        this.reservation08 = reservation08;
    }

    /**
     * @return reservation09
     */
    public String getReservation09() {
        return reservation09;
    }

    /**
     * @param reservation09
     */
    public void setReservation09(String reservation09) {
        this.reservation09 = reservation09;
    }

    /**
     * @return reservation10
     */
    public String getReservation10() {
        return reservation10;
    }

    /**
     * @param reservation10
     */
    public void setReservation10(String reservation10) {
        this.reservation10 = reservation10;
    }

    /**
     * @return lastupdatedby
     */
    public String getLastupdatedby() {
        return lastupdatedby;
    }

    /**
     * @param lastupdatedby
     */
    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    /**
     * @return lastupdatedtime
     */
    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * @param lastupdatedtime
     */
    public void setLastupdatedtime(String lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * @return refreshtime
     */
    public String getRefreshtime() {
        return refreshtime;
    }

    /**
     * @param refreshtime
     */
    public void setRefreshtime(String refreshtime) {
        this.refreshtime = refreshtime;
    }

    /**
     * @return uploadflag
     */
    public String getUploadflag() {
        return uploadflag;
    }

    /**
     * @param uploadflag
     */
    public void setUploadflag(String uploadflag) {
        this.uploadflag = uploadflag;
    }

    /**
     * @return downloadflag
     */
    public String getDownloadflag() {
        return downloadflag;
    }

    /**
     * @param downloadflag
     */
    public void setDownloadflag(String downloadflag) {
        this.downloadflag = downloadflag;
    }

    /**
     * @return deleteflag
     */
    public String getDeleteflag() {
        return deleteflag;
    }

    /**
     * @param deleteflag
     */
    public void setDeleteflag(String deleteflag) {
        this.deleteflag = deleteflag;
    }

    /**
     * @return reservation11
     */
    public String getReservation11() {
        return reservation11;
    }

    /**
     * @param reservation11
     */
    public void setReservation11(String reservation11) {
        this.reservation11 = reservation11;
    }

    /**
     * @return reservation12
     */
    public String getReservation12() {
        return reservation12;
    }

    /**
     * @param reservation12
     */
    public void setReservation12(String reservation12) {
        this.reservation12 = reservation12;
    }

    /**
     * @return reservation13
     */
    public String getReservation13() {
        return reservation13;
    }

    /**
     * @param reservation13
     */
    public void setReservation13(String reservation13) {
        this.reservation13 = reservation13;
    }

    /**
     * @return reservation14
     */
    public String getReservation14() {
        return reservation14;
    }

    /**
     * @param reservation14
     */
    public void setReservation14(String reservation14) {
        this.reservation14 = reservation14;
    }

    /**
     * @return reservation15
     */
    public String getReservation15() {
        return reservation15;
    }

    /**
     * @param reservation15
     */
    public void setReservation15(String reservation15) {
        this.reservation15 = reservation15;
    }

    /**
     * @return reservation16
     */
    public String getReservation16() {
        return reservation16;
    }

    /**
     * @param reservation16
     */
    public void setReservation16(String reservation16) {
        this.reservation16 = reservation16;
    }

    /**
     * @return reservation17
     */
    public String getReservation17() {
        return reservation17;
    }

    /**
     * @param reservation17
     */
    public void setReservation17(String reservation17) {
        this.reservation17 = reservation17;
    }

    /**
     * @return reservation18
     */
    public String getReservation18() {
        return reservation18;
    }

    /**
     * @param reservation18
     */
    public void setReservation18(String reservation18) {
        this.reservation18 = reservation18;
    }

    /**
     * @return reservation19
     */
    public String getReservation19() {
        return reservation19;
    }

    /**
     * @param reservation19
     */
    public void setReservation19(String reservation19) {
        this.reservation19 = reservation19;
    }

    /**
     * @return reservation20
     */
    public String getReservation20() {
        return reservation20;
    }

    /**
     * @param reservation20
     */
    public void setReservation20(String reservation20) {
        this.reservation20 = reservation20;
    }

    /**
     * @return datastate
     */
    public String getDatastate() {
        return datastate;
    }

    /**
     * @param datastate
     */
    public void setDatastate(String datastate) {
        this.datastate = datastate;
    }

    /**
     * @return datacheck
     */
    public String getDatacheck() {
        return datacheck;
    }

    /**
     * @param datacheck
     */
    public void setDatacheck(String datacheck) {
        this.datacheck = datacheck;
    }

    /**
     * @return queryid
     */
    public String getQueryid() {
        return queryid;
    }

    /**
     * @param queryid
     */
    public void setQueryid(String queryid) {
        this.queryid = queryid;
    }

    /**
     * 获取入库登记时间
     *
     * @return rkdjsj - 入库登记时间
     */
    public String getRkdjsj() {
        return rkdjsj;
    }

    /**
     * 设置入库登记时间
     *
     * @param rkdjsj 入库登记时间
     */
    public void setRkdjsj(String rkdjsj) {
        this.rkdjsj = rkdjsj;
    }

    /**
     * 获取入库修改时间
     *
     * @return rkgxsj - 入库修改时间
     */
    public String getRkgxsj() {
        return rkgxsj;
    }

    /**
     * 设置入库修改时间
     *
     * @param rkgxsj 入库修改时间
     */
    public void setRkgxsj(String rkgxsj) {
        this.rkgxsj = rkgxsj;
    }

	public String getAjbh() {
		return ajbh;
	}

	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}
    
}
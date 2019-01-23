package com.hyzs.cidyth.module.base.entity;

import javax.persistence.*;

@Table(name = "t_asj_wp")
public class CasesGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * 物品编号
     */
    private String wpbh;

    /**
     * 案件编号
     */
    private String ajbh;

    private String syzbh;

    /**
     * 物品名称
     */
    private String wpmc;

    /**
     * 物品性质
     */
    private String wpxz;

    /**
     * 物品状态
     */
    private String wpzt;

    /**
     * 物品类别
     */
    private String wplb;

    /**
     * 物品特征
     */
    private String wptz;

    /**
     * 损失类型
     */
    private String sslx;

    /**
     * 损失时间
     */
    private String sssj;

    /**
     * 物品颜色
     */
    private String wpys;

    /**
     * 物品产地
     */
    private String wpcd;

    /**
     * 物品产品
     */
    private String wpcp;

    /**
     * 物品型号
     */
    private String wpxh;

    private String wpsy;

    private String wpzd;

    /**
     * 物品规格
     */
    private String wpgg;

    private String cpys;

    private String wpcs;

    /**
     * 物品数量
     */
    private String wpsl;

    /**
     * 计量单位
     */
    private String jldw;

    /**
     * 物品价值
     */
    private String wpjz;

    /**
     * 物品折扣
     */
    private String wpzk;

    /**
     * 物品失主
     */
    private String wpsz;

    /**
     * 失主地址
     */
    private String szdz;

    private String kysj;

    private String zhdd;

    private String zhry;

    private String wply;

    /**
     * 丢失人员
     */
    private String dsry;

    private String bgry;

    private String bgbh;

    private String wpcl;

    /**
     * 备注
     */
    private String bz;

    private String lrsj;

    private String lrr;

    private String departmentcode;

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

    private String reservation21;

    private String reservation22;

    private String reservation23;

    private String reservation24;

    private String reservation25;

    private String reservation26;

    private String reservation27;

    private String reservation28;

    private String reservation29;

    private String reservation30;

    private String tzms;

    private String whjz;

    private String ssjz;

    private String zjrq;

    private String zjdw;

    private String jdjg;

    private String jdrq;

    private String jddw;

    /**
     * 书证物证种类
     */
    private String szwzzl;

    /**
     * 证据类别
     */
    private String zjlb;

    private String reserver1;

    private String reserver2;

    private String reserver3;

    private String reserver4;

    private String reserver5;

    private String reserver6;

    private String reserver7;

    private String reserver8;

    private String reserver9;

    private String reserver10;

    private String reserver11;

    private String reserver12;

    private String reserver13;

    private String reserver14;

    private String reserver15;

    private String reserver16;

    private String reserver17;

    private String reserver18;

    private String reserver19;

    private String reserver20;

    private String reserver21;

    private String reserver22;

    private String reserver23;

    private String reserver24;

    private String reserver25;

    private String reserver26;

    private String reserver27;

    private String reserver28;

    private String reserver29;

    private String reserver30;

    private String reserver31;

    private String reserver32;

    private String reserver33;

    private String reserver34;

    private String reserver35;

    private String reserver36;

    private String reserver37;

    private String reserver38;

    private String reserver39;

    private String reserver40;

    private String reserver41;

    private String reserver42;

    private String reserver43;

    private String reserver44;

    private String reserver45;

    private String reserver46;

    private String reserver47;

    private String reserver48;

    private String reserver49;

    private String reserver50;

    private String reserver51;

    private String reserver52;

    private String reserver53;

    private String reserver54;

    private String reserver55;

    private String reserver56;

    private String reserver57;

    private String reserver58;

    private String reserver59;

    private String reserver60;

    private String reserver61;

    private String reserver62;

    private String reserver63;

    private String reserver64;

    private String reserver65;

    private String reserver66;

    private String reserver67;

    private String reserver68;

    private String reserver69;

    private String reserver70;

    private String reserver71;

    private String reserver72;

    private String reserver73;

    private String reserver74;

    private String reserver75;

    private String reserver76;

    private String reserver77;

    private String reserver78;

    private String reserver79;

    private String reserver80;

    private String datastate;

    private String datacheck;

    private String queryid;

    private String rkdjsj;

    private String rkgxsj;

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
     * 获取物品编号
     *
     * @return wpbh - 物品编号
     */
    public String getWpbh() {
        return wpbh;
    }

    /**
     * 设置物品编号
     *
     * @param wpbh 物品编号
     */
    public void setWpbh(String wpbh) {
        this.wpbh = wpbh;
    }

    /**
     * 获取案件编号
     *
     * @return ajbh - 案件编号
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
     * @return syzbh
     */
    public String getSyzbh() {
        return syzbh;
    }

    /**
     * @param syzbh
     */
    public void setSyzbh(String syzbh) {
        this.syzbh = syzbh;
    }

    /**
     * 获取物品名称
     *
     * @return wpmc - 物品名称
     */
    public String getWpmc() {
        return wpmc;
    }

    /**
     * 设置物品名称
     *
     * @param wpmc 物品名称
     */
    public void setWpmc(String wpmc) {
        this.wpmc = wpmc;
    }

    /**
     * 获取物品性质
     *
     * @return wpxz - 物品性质
     */
    public String getWpxz() {
        return wpxz;
    }

    /**
     * 设置物品性质
     *
     * @param wpxz 物品性质
     */
    public void setWpxz(String wpxz) {
        this.wpxz = wpxz;
    }

    /**
     * 获取物品状态
     *
     * @return wpzt - 物品状态
     */
    public String getWpzt() {
        return wpzt;
    }

    /**
     * 设置物品状态
     *
     * @param wpzt 物品状态
     */
    public void setWpzt(String wpzt) {
        this.wpzt = wpzt;
    }

    /**
     * 获取物品类别
     *
     * @return wplb - 物品类别
     */
    public String getWplb() {
        return wplb;
    }

    /**
     * 设置物品类别
     *
     * @param wplb 物品类别
     */
    public void setWplb(String wplb) {
        this.wplb = wplb;
    }

    /**
     * 获取物品特征
     *
     * @return wptz - 物品特征
     */
    public String getWptz() {
        return wptz;
    }

    /**
     * 设置物品特征
     *
     * @param wptz 物品特征
     */
    public void setWptz(String wptz) {
        this.wptz = wptz;
    }

    /**
     * 获取损失类型
     *
     * @return sslx - 损失类型
     */
    public String getSslx() {
        return sslx;
    }

    /**
     * 设置损失类型
     *
     * @param sslx 损失类型
     */
    public void setSslx(String sslx) {
        this.sslx = sslx;
    }

    /**
     * 获取损失时间
     *
     * @return sssj - 损失时间
     */
    public String getSssj() {
        return sssj;
    }

    /**
     * 设置损失时间
     *
     * @param sssj 损失时间
     */
    public void setSssj(String sssj) {
        this.sssj = sssj;
    }

    /**
     * 获取物品颜色
     *
     * @return wpys - 物品颜色
     */
    public String getWpys() {
        return wpys;
    }

    /**
     * 设置物品颜色
     *
     * @param wpys 物品颜色
     */
    public void setWpys(String wpys) {
        this.wpys = wpys;
    }

    /**
     * 获取物品产地
     *
     * @return wpcd - 物品产地
     */
    public String getWpcd() {
        return wpcd;
    }

    /**
     * 设置物品产地
     *
     * @param wpcd 物品产地
     */
    public void setWpcd(String wpcd) {
        this.wpcd = wpcd;
    }

    /**
     * 获取物品产品
     *
     * @return wpcp - 物品产品
     */
    public String getWpcp() {
        return wpcp;
    }

    /**
     * 设置物品产品
     *
     * @param wpcp 物品产品
     */
    public void setWpcp(String wpcp) {
        this.wpcp = wpcp;
    }

    /**
     * 获取物品型号
     *
     * @return wpxh - 物品型号
     */
    public String getWpxh() {
        return wpxh;
    }

    /**
     * 设置物品型号
     *
     * @param wpxh 物品型号
     */
    public void setWpxh(String wpxh) {
        this.wpxh = wpxh;
    }

    /**
     * @return wpsy
     */
    public String getWpsy() {
        return wpsy;
    }

    /**
     * @param wpsy
     */
    public void setWpsy(String wpsy) {
        this.wpsy = wpsy;
    }

    /**
     * @return wpzd
     */
    public String getWpzd() {
        return wpzd;
    }

    /**
     * @param wpzd
     */
    public void setWpzd(String wpzd) {
        this.wpzd = wpzd;
    }

    /**
     * 获取物品规格
     *
     * @return wpgg - 物品规格
     */
    public String getWpgg() {
        return wpgg;
    }

    /**
     * 设置物品规格
     *
     * @param wpgg 物品规格
     */
    public void setWpgg(String wpgg) {
        this.wpgg = wpgg;
    }

    /**
     * @return cpys
     */
    public String getCpys() {
        return cpys;
    }

    /**
     * @param cpys
     */
    public void setCpys(String cpys) {
        this.cpys = cpys;
    }

    /**
     * @return wpcs
     */
    public String getWpcs() {
        return wpcs;
    }

    /**
     * @param wpcs
     */
    public void setWpcs(String wpcs) {
        this.wpcs = wpcs;
    }

    /**
     * @return wpsl
     */
    public String getWpsl() {
        return wpsl;
    }

    /**
     * @param wpsl
     */
    public void setWpsl(String wpsl) {
        this.wpsl = wpsl;
    }

    /**
     * 获取计量单位
     *
     * @return jldw - 计量单位
     */
    public String getJldw() {
        return jldw;
    }

    /**
     * 设置计量单位
     *
     * @param jldw 计量单位
     */
    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    /**
     * 获取物品价值
     *
     * @return wpjz - 物品价值
     */
    public String getWpjz() {
        return wpjz;
    }

    /**
     * 设置物品价值
     *
     * @param wpjz 物品价值
     */
    public void setWpjz(String wpjz) {
        this.wpjz = wpjz;
    }

    /**
     * 获取物品折扣
     *
     * @return wpzk - 物品折扣
     */
    public String getWpzk() {
        return wpzk;
    }

    /**
     * 设置物品折扣
     *
     * @param wpzk 物品折扣
     */
    public void setWpzk(String wpzk) {
        this.wpzk = wpzk;
    }

    /**
     * 获取物品失主
     *
     * @return wpsz - 物品失主
     */
    public String getWpsz() {
        return wpsz;
    }

    /**
     * 设置物品失主
     *
     * @param wpsz 物品失主
     */
    public void setWpsz(String wpsz) {
        this.wpsz = wpsz;
    }

    /**
     * 获取失主地址
     *
     * @return szdz - 失主地址
     */
    public String getSzdz() {
        return szdz;
    }

    /**
     * 设置失主地址
     *
     * @param szdz 失主地址
     */
    public void setSzdz(String szdz) {
        this.szdz = szdz;
    }

    /**
     * @return kysj
     */
    public String getKysj() {
        return kysj;
    }

    /**
     * @param kysj
     */
    public void setKysj(String kysj) {
        this.kysj = kysj;
    }

    /**
     * @return zhdd
     */
    public String getZhdd() {
        return zhdd;
    }

    /**
     * @param zhdd
     */
    public void setZhdd(String zhdd) {
        this.zhdd = zhdd;
    }

    /**
     * @return zhry
     */
    public String getZhry() {
        return zhry;
    }

    /**
     * @param zhry
     */
    public void setZhry(String zhry) {
        this.zhry = zhry;
    }

    /**
     * @return wply
     */
    public String getWply() {
        return wply;
    }

    /**
     * @param wply
     */
    public void setWply(String wply) {
        this.wply = wply;
    }

    /**
     * 获取丢失人员
     *
     * @return dsry - 丢失人员
     */
    public String getDsry() {
        return dsry;
    }

    /**
     * 设置丢失人员
     *
     * @param dsry 丢失人员
     */
    public void setDsry(String dsry) {
        this.dsry = dsry;
    }

    /**
     * @return bgry
     */
    public String getBgry() {
        return bgry;
    }

    /**
     * @param bgry
     */
    public void setBgry(String bgry) {
        this.bgry = bgry;
    }

    /**
     * @return bgbh
     */
    public String getBgbh() {
        return bgbh;
    }

    /**
     * @param bgbh
     */
    public void setBgbh(String bgbh) {
        this.bgbh = bgbh;
    }

    /**
     * @return wpcl
     */
    public String getWpcl() {
        return wpcl;
    }

    /**
     * @param wpcl
     */
    public void setWpcl(String wpcl) {
        this.wpcl = wpcl;
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
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
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
     * @return reservation21
     */
    public String getReservation21() {
        return reservation21;
    }

    /**
     * @param reservation21
     */
    public void setReservation21(String reservation21) {
        this.reservation21 = reservation21;
    }

    /**
     * @return reservation22
     */
    public String getReservation22() {
        return reservation22;
    }

    /**
     * @param reservation22
     */
    public void setReservation22(String reservation22) {
        this.reservation22 = reservation22;
    }

    /**
     * @return reservation23
     */
    public String getReservation23() {
        return reservation23;
    }

    /**
     * @param reservation23
     */
    public void setReservation23(String reservation23) {
        this.reservation23 = reservation23;
    }

    /**
     * @return reservation24
     */
    public String getReservation24() {
        return reservation24;
    }

    /**
     * @param reservation24
     */
    public void setReservation24(String reservation24) {
        this.reservation24 = reservation24;
    }

    /**
     * @return reservation25
     */
    public String getReservation25() {
        return reservation25;
    }

    /**
     * @param reservation25
     */
    public void setReservation25(String reservation25) {
        this.reservation25 = reservation25;
    }

    /**
     * @return reservation26
     */
    public String getReservation26() {
        return reservation26;
    }

    /**
     * @param reservation26
     */
    public void setReservation26(String reservation26) {
        this.reservation26 = reservation26;
    }

    /**
     * @return reservation27
     */
    public String getReservation27() {
        return reservation27;
    }

    /**
     * @param reservation27
     */
    public void setReservation27(String reservation27) {
        this.reservation27 = reservation27;
    }

    /**
     * @return reservation28
     */
    public String getReservation28() {
        return reservation28;
    }

    /**
     * @param reservation28
     */
    public void setReservation28(String reservation28) {
        this.reservation28 = reservation28;
    }

    /**
     * @return reservation29
     */
    public String getReservation29() {
        return reservation29;
    }

    /**
     * @param reservation29
     */
    public void setReservation29(String reservation29) {
        this.reservation29 = reservation29;
    }

    /**
     * @return reservation30
     */
    public String getReservation30() {
        return reservation30;
    }

    /**
     * @param reservation30
     */
    public void setReservation30(String reservation30) {
        this.reservation30 = reservation30;
    }

    /**
     * @return tzms
     */
    public String getTzms() {
        return tzms;
    }

    /**
     * @param tzms
     */
    public void setTzms(String tzms) {
        this.tzms = tzms;
    }

    /**
     * @return whjz
     */
    public String getWhjz() {
        return whjz;
    }

    /**
     * @param whjz
     */
    public void setWhjz(String whjz) {
        this.whjz = whjz;
    }

    /**
     * @return ssjz
     */
    public String getSsjz() {
        return ssjz;
    }

    /**
     * @param ssjz
     */
    public void setSsjz(String ssjz) {
        this.ssjz = ssjz;
    }

    /**
     * @return zjrq
     */
    public String getZjrq() {
        return zjrq;
    }

    /**
     * @param zjrq
     */
    public void setZjrq(String zjrq) {
        this.zjrq = zjrq;
    }

    /**
     * @return zjdw
     */
    public String getZjdw() {
        return zjdw;
    }

    /**
     * @param zjdw
     */
    public void setZjdw(String zjdw) {
        this.zjdw = zjdw;
    }

    /**
     * @return jdjg
     */
    public String getJdjg() {
        return jdjg;
    }

    /**
     * @param jdjg
     */
    public void setJdjg(String jdjg) {
        this.jdjg = jdjg;
    }

    /**
     * @return jdrq
     */
    public String getJdrq() {
        return jdrq;
    }

    /**
     * @param jdrq
     */
    public void setJdrq(String jdrq) {
        this.jdrq = jdrq;
    }

    /**
     * @return jddw
     */
    public String getJddw() {
        return jddw;
    }

    /**
     * @param jddw
     */
    public void setJddw(String jddw) {
        this.jddw = jddw;
    }

    /**
     * 获取书证物证种类
     *
     * @return szwzzl - 书证物证种类
     */
    public String getSzwzzl() {
        return szwzzl;
    }

    /**
     * 设置书证物证种类
     *
     * @param szwzzl 书证物证种类
     */
    public void setSzwzzl(String szwzzl) {
        this.szwzzl = szwzzl;
    }

    /**
     * 获取证据类别
     *
     * @return zjlb - 证据类别
     */
    public String getZjlb() {
        return zjlb;
    }

    /**
     * 设置证据类别
     *
     * @param zjlb 证据类别
     */
    public void setZjlb(String zjlb) {
        this.zjlb = zjlb;
    }

    /**
     * @return reserver1
     */
    public String getReserver1() {
        return reserver1;
    }

    /**
     * @param reserver1
     */
    public void setReserver1(String reserver1) {
        this.reserver1 = reserver1;
    }

    /**
     * @return reserver2
     */
    public String getReserver2() {
        return reserver2;
    }

    /**
     * @param reserver2
     */
    public void setReserver2(String reserver2) {
        this.reserver2 = reserver2;
    }

    /**
     * @return reserver3
     */
    public String getReserver3() {
        return reserver3;
    }

    /**
     * @param reserver3
     */
    public void setReserver3(String reserver3) {
        this.reserver3 = reserver3;
    }

    /**
     * @return reserver4
     */
    public String getReserver4() {
        return reserver4;
    }

    /**
     * @param reserver4
     */
    public void setReserver4(String reserver4) {
        this.reserver4 = reserver4;
    }

    /**
     * @return reserver5
     */
    public String getReserver5() {
        return reserver5;
    }

    /**
     * @param reserver5
     */
    public void setReserver5(String reserver5) {
        this.reserver5 = reserver5;
    }

    /**
     * @return reserver6
     */
    public String getReserver6() {
        return reserver6;
    }

    /**
     * @param reserver6
     */
    public void setReserver6(String reserver6) {
        this.reserver6 = reserver6;
    }

    /**
     * @return reserver7
     */
    public String getReserver7() {
        return reserver7;
    }

    /**
     * @param reserver7
     */
    public void setReserver7(String reserver7) {
        this.reserver7 = reserver7;
    }

    /**
     * @return reserver8
     */
    public String getReserver8() {
        return reserver8;
    }

    /**
     * @param reserver8
     */
    public void setReserver8(String reserver8) {
        this.reserver8 = reserver8;
    }

    /**
     * @return reserver9
     */
    public String getReserver9() {
        return reserver9;
    }

    /**
     * @param reserver9
     */
    public void setReserver9(String reserver9) {
        this.reserver9 = reserver9;
    }

    /**
     * @return reserver10
     */
    public String getReserver10() {
        return reserver10;
    }

    /**
     * @param reserver10
     */
    public void setReserver10(String reserver10) {
        this.reserver10 = reserver10;
    }

    /**
     * @return reserver11
     */
    public String getReserver11() {
        return reserver11;
    }

    /**
     * @param reserver11
     */
    public void setReserver11(String reserver11) {
        this.reserver11 = reserver11;
    }

    /**
     * @return reserver12
     */
    public String getReserver12() {
        return reserver12;
    }

    /**
     * @param reserver12
     */
    public void setReserver12(String reserver12) {
        this.reserver12 = reserver12;
    }

    /**
     * @return reserver13
     */
    public String getReserver13() {
        return reserver13;
    }

    /**
     * @param reserver13
     */
    public void setReserver13(String reserver13) {
        this.reserver13 = reserver13;
    }

    /**
     * @return reserver14
     */
    public String getReserver14() {
        return reserver14;
    }

    /**
     * @param reserver14
     */
    public void setReserver14(String reserver14) {
        this.reserver14 = reserver14;
    }

    /**
     * @return reserver15
     */
    public String getReserver15() {
        return reserver15;
    }

    /**
     * @param reserver15
     */
    public void setReserver15(String reserver15) {
        this.reserver15 = reserver15;
    }

    /**
     * @return reserver16
     */
    public String getReserver16() {
        return reserver16;
    }

    /**
     * @param reserver16
     */
    public void setReserver16(String reserver16) {
        this.reserver16 = reserver16;
    }

    /**
     * @return reserver17
     */
    public String getReserver17() {
        return reserver17;
    }

    /**
     * @param reserver17
     */
    public void setReserver17(String reserver17) {
        this.reserver17 = reserver17;
    }

    /**
     * @return reserver18
     */
    public String getReserver18() {
        return reserver18;
    }

    /**
     * @param reserver18
     */
    public void setReserver18(String reserver18) {
        this.reserver18 = reserver18;
    }

    /**
     * @return reserver19
     */
    public String getReserver19() {
        return reserver19;
    }

    /**
     * @param reserver19
     */
    public void setReserver19(String reserver19) {
        this.reserver19 = reserver19;
    }

    /**
     * @return reserver20
     */
    public String getReserver20() {
        return reserver20;
    }

    /**
     * @param reserver20
     */
    public void setReserver20(String reserver20) {
        this.reserver20 = reserver20;
    }

    /**
     * @return reserver21
     */
    public String getReserver21() {
        return reserver21;
    }

    /**
     * @param reserver21
     */
    public void setReserver21(String reserver21) {
        this.reserver21 = reserver21;
    }

    /**
     * @return reserver22
     */
    public String getReserver22() {
        return reserver22;
    }

    /**
     * @param reserver22
     */
    public void setReserver22(String reserver22) {
        this.reserver22 = reserver22;
    }

    /**
     * @return reserver23
     */
    public String getReserver23() {
        return reserver23;
    }

    /**
     * @param reserver23
     */
    public void setReserver23(String reserver23) {
        this.reserver23 = reserver23;
    }

    /**
     * @return reserver24
     */
    public String getReserver24() {
        return reserver24;
    }

    /**
     * @param reserver24
     */
    public void setReserver24(String reserver24) {
        this.reserver24 = reserver24;
    }

    /**
     * @return reserver25
     */
    public String getReserver25() {
        return reserver25;
    }

    /**
     * @param reserver25
     */
    public void setReserver25(String reserver25) {
        this.reserver25 = reserver25;
    }

    /**
     * @return reserver26
     */
    public String getReserver26() {
        return reserver26;
    }

    /**
     * @param reserver26
     */
    public void setReserver26(String reserver26) {
        this.reserver26 = reserver26;
    }

    /**
     * @return reserver27
     */
    public String getReserver27() {
        return reserver27;
    }

    /**
     * @param reserver27
     */
    public void setReserver27(String reserver27) {
        this.reserver27 = reserver27;
    }

    /**
     * @return reserver28
     */
    public String getReserver28() {
        return reserver28;
    }

    /**
     * @param reserver28
     */
    public void setReserver28(String reserver28) {
        this.reserver28 = reserver28;
    }

    /**
     * @return reserver29
     */
    public String getReserver29() {
        return reserver29;
    }

    /**
     * @param reserver29
     */
    public void setReserver29(String reserver29) {
        this.reserver29 = reserver29;
    }

    /**
     * @return reserver30
     */
    public String getReserver30() {
        return reserver30;
    }

    /**
     * @param reserver30
     */
    public void setReserver30(String reserver30) {
        this.reserver30 = reserver30;
    }

    /**
     * @return reserver31
     */
    public String getReserver31() {
        return reserver31;
    }

    /**
     * @param reserver31
     */
    public void setReserver31(String reserver31) {
        this.reserver31 = reserver31;
    }

    /**
     * @return reserver32
     */
    public String getReserver32() {
        return reserver32;
    }

    /**
     * @param reserver32
     */
    public void setReserver32(String reserver32) {
        this.reserver32 = reserver32;
    }

    /**
     * @return reserver33
     */
    public String getReserver33() {
        return reserver33;
    }

    /**
     * @param reserver33
     */
    public void setReserver33(String reserver33) {
        this.reserver33 = reserver33;
    }

    /**
     * @return reserver34
     */
    public String getReserver34() {
        return reserver34;
    }

    /**
     * @param reserver34
     */
    public void setReserver34(String reserver34) {
        this.reserver34 = reserver34;
    }

    /**
     * @return reserver35
     */
    public String getReserver35() {
        return reserver35;
    }

    /**
     * @param reserver35
     */
    public void setReserver35(String reserver35) {
        this.reserver35 = reserver35;
    }

    /**
     * @return reserver36
     */
    public String getReserver36() {
        return reserver36;
    }

    /**
     * @param reserver36
     */
    public void setReserver36(String reserver36) {
        this.reserver36 = reserver36;
    }

    /**
     * @return reserver37
     */
    public String getReserver37() {
        return reserver37;
    }

    /**
     * @param reserver37
     */
    public void setReserver37(String reserver37) {
        this.reserver37 = reserver37;
    }

    /**
     * @return reserver38
     */
    public String getReserver38() {
        return reserver38;
    }

    /**
     * @param reserver38
     */
    public void setReserver38(String reserver38) {
        this.reserver38 = reserver38;
    }

    /**
     * @return reserver39
     */
    public String getReserver39() {
        return reserver39;
    }

    /**
     * @param reserver39
     */
    public void setReserver39(String reserver39) {
        this.reserver39 = reserver39;
    }

    /**
     * @return reserver40
     */
    public String getReserver40() {
        return reserver40;
    }

    /**
     * @param reserver40
     */
    public void setReserver40(String reserver40) {
        this.reserver40 = reserver40;
    }

    /**
     * @return reserver41
     */
    public String getReserver41() {
        return reserver41;
    }

    /**
     * @param reserver41
     */
    public void setReserver41(String reserver41) {
        this.reserver41 = reserver41;
    }

    /**
     * @return reserver42
     */
    public String getReserver42() {
        return reserver42;
    }

    /**
     * @param reserver42
     */
    public void setReserver42(String reserver42) {
        this.reserver42 = reserver42;
    }

    /**
     * @return reserver43
     */
    public String getReserver43() {
        return reserver43;
    }

    /**
     * @param reserver43
     */
    public void setReserver43(String reserver43) {
        this.reserver43 = reserver43;
    }

    /**
     * @return reserver44
     */
    public String getReserver44() {
        return reserver44;
    }

    /**
     * @param reserver44
     */
    public void setReserver44(String reserver44) {
        this.reserver44 = reserver44;
    }

    /**
     * @return reserver45
     */
    public String getReserver45() {
        return reserver45;
    }

    /**
     * @param reserver45
     */
    public void setReserver45(String reserver45) {
        this.reserver45 = reserver45;
    }

    /**
     * @return reserver46
     */
    public String getReserver46() {
        return reserver46;
    }

    /**
     * @param reserver46
     */
    public void setReserver46(String reserver46) {
        this.reserver46 = reserver46;
    }

    /**
     * @return reserver47
     */
    public String getReserver47() {
        return reserver47;
    }

    /**
     * @param reserver47
     */
    public void setReserver47(String reserver47) {
        this.reserver47 = reserver47;
    }

    /**
     * @return reserver48
     */
    public String getReserver48() {
        return reserver48;
    }

    /**
     * @param reserver48
     */
    public void setReserver48(String reserver48) {
        this.reserver48 = reserver48;
    }

    /**
     * @return reserver49
     */
    public String getReserver49() {
        return reserver49;
    }

    /**
     * @param reserver49
     */
    public void setReserver49(String reserver49) {
        this.reserver49 = reserver49;
    }

    /**
     * @return reserver50
     */
    public String getReserver50() {
        return reserver50;
    }

    /**
     * @param reserver50
     */
    public void setReserver50(String reserver50) {
        this.reserver50 = reserver50;
    }

    /**
     * @return reserver51
     */
    public String getReserver51() {
        return reserver51;
    }

    /**
     * @param reserver51
     */
    public void setReserver51(String reserver51) {
        this.reserver51 = reserver51;
    }

    /**
     * @return reserver52
     */
    public String getReserver52() {
        return reserver52;
    }

    /**
     * @param reserver52
     */
    public void setReserver52(String reserver52) {
        this.reserver52 = reserver52;
    }

    /**
     * @return reserver53
     */
    public String getReserver53() {
        return reserver53;
    }

    /**
     * @param reserver53
     */
    public void setReserver53(String reserver53) {
        this.reserver53 = reserver53;
    }

    /**
     * @return reserver54
     */
    public String getReserver54() {
        return reserver54;
    }

    /**
     * @param reserver54
     */
    public void setReserver54(String reserver54) {
        this.reserver54 = reserver54;
    }

    /**
     * @return reserver55
     */
    public String getReserver55() {
        return reserver55;
    }

    /**
     * @param reserver55
     */
    public void setReserver55(String reserver55) {
        this.reserver55 = reserver55;
    }

    /**
     * @return reserver56
     */
    public String getReserver56() {
        return reserver56;
    }

    /**
     * @param reserver56
     */
    public void setReserver56(String reserver56) {
        this.reserver56 = reserver56;
    }

    /**
     * @return reserver57
     */
    public String getReserver57() {
        return reserver57;
    }

    /**
     * @param reserver57
     */
    public void setReserver57(String reserver57) {
        this.reserver57 = reserver57;
    }

    /**
     * @return reserver58
     */
    public String getReserver58() {
        return reserver58;
    }

    /**
     * @param reserver58
     */
    public void setReserver58(String reserver58) {
        this.reserver58 = reserver58;
    }

    /**
     * @return reserver59
     */
    public String getReserver59() {
        return reserver59;
    }

    /**
     * @param reserver59
     */
    public void setReserver59(String reserver59) {
        this.reserver59 = reserver59;
    }

    /**
     * @return reserver60
     */
    public String getReserver60() {
        return reserver60;
    }

    /**
     * @param reserver60
     */
    public void setReserver60(String reserver60) {
        this.reserver60 = reserver60;
    }

    /**
     * @return reserver61
     */
    public String getReserver61() {
        return reserver61;
    }

    /**
     * @param reserver61
     */
    public void setReserver61(String reserver61) {
        this.reserver61 = reserver61;
    }

    /**
     * @return reserver62
     */
    public String getReserver62() {
        return reserver62;
    }

    /**
     * @param reserver62
     */
    public void setReserver62(String reserver62) {
        this.reserver62 = reserver62;
    }

    /**
     * @return reserver63
     */
    public String getReserver63() {
        return reserver63;
    }

    /**
     * @param reserver63
     */
    public void setReserver63(String reserver63) {
        this.reserver63 = reserver63;
    }

    /**
     * @return reserver64
     */
    public String getReserver64() {
        return reserver64;
    }

    /**
     * @param reserver64
     */
    public void setReserver64(String reserver64) {
        this.reserver64 = reserver64;
    }

    /**
     * @return reserver65
     */
    public String getReserver65() {
        return reserver65;
    }

    /**
     * @param reserver65
     */
    public void setReserver65(String reserver65) {
        this.reserver65 = reserver65;
    }

    /**
     * @return reserver66
     */
    public String getReserver66() {
        return reserver66;
    }

    /**
     * @param reserver66
     */
    public void setReserver66(String reserver66) {
        this.reserver66 = reserver66;
    }

    /**
     * @return reserver67
     */
    public String getReserver67() {
        return reserver67;
    }

    /**
     * @param reserver67
     */
    public void setReserver67(String reserver67) {
        this.reserver67 = reserver67;
    }

    /**
     * @return reserver68
     */
    public String getReserver68() {
        return reserver68;
    }

    /**
     * @param reserver68
     */
    public void setReserver68(String reserver68) {
        this.reserver68 = reserver68;
    }

    /**
     * @return reserver69
     */
    public String getReserver69() {
        return reserver69;
    }

    /**
     * @param reserver69
     */
    public void setReserver69(String reserver69) {
        this.reserver69 = reserver69;
    }

    /**
     * @return reserver70
     */
    public String getReserver70() {
        return reserver70;
    }

    /**
     * @param reserver70
     */
    public void setReserver70(String reserver70) {
        this.reserver70 = reserver70;
    }

    /**
     * @return reserver71
     */
    public String getReserver71() {
        return reserver71;
    }

    /**
     * @param reserver71
     */
    public void setReserver71(String reserver71) {
        this.reserver71 = reserver71;
    }

    /**
     * @return reserver72
     */
    public String getReserver72() {
        return reserver72;
    }

    /**
     * @param reserver72
     */
    public void setReserver72(String reserver72) {
        this.reserver72 = reserver72;
    }

    /**
     * @return reserver73
     */
    public String getReserver73() {
        return reserver73;
    }

    /**
     * @param reserver73
     */
    public void setReserver73(String reserver73) {
        this.reserver73 = reserver73;
    }

    /**
     * @return reserver74
     */
    public String getReserver74() {
        return reserver74;
    }

    /**
     * @param reserver74
     */
    public void setReserver74(String reserver74) {
        this.reserver74 = reserver74;
    }

    /**
     * @return reserver75
     */
    public String getReserver75() {
        return reserver75;
    }

    /**
     * @param reserver75
     */
    public void setReserver75(String reserver75) {
        this.reserver75 = reserver75;
    }

    /**
     * @return reserver76
     */
    public String getReserver76() {
        return reserver76;
    }

    /**
     * @param reserver76
     */
    public void setReserver76(String reserver76) {
        this.reserver76 = reserver76;
    }

    /**
     * @return reserver77
     */
    public String getReserver77() {
        return reserver77;
    }

    /**
     * @param reserver77
     */
    public void setReserver77(String reserver77) {
        this.reserver77 = reserver77;
    }

    /**
     * @return reserver78
     */
    public String getReserver78() {
        return reserver78;
    }

    /**
     * @param reserver78
     */
    public void setReserver78(String reserver78) {
        this.reserver78 = reserver78;
    }

    /**
     * @return reserver79
     */
    public String getReserver79() {
        return reserver79;
    }

    /**
     * @param reserver79
     */
    public void setReserver79(String reserver79) {
        this.reserver79 = reserver79;
    }

    /**
     * @return reserver80
     */
    public String getReserver80() {
        return reserver80;
    }

    /**
     * @param reserver80
     */
    public void setReserver80(String reserver80) {
        this.reserver80 = reserver80;
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
     * @return rkdjsj
     */
    public String getRkdjsj() {
        return rkdjsj;
    }

    /**
     * @param rkdjsj
     */
    public void setRkdjsj(String rkdjsj) {
        this.rkdjsj = rkdjsj;
    }

    /**
     * @return rkgxsj
     */
    public String getRkgxsj() {
        return rkgxsj;
    }

    /**
     * @param rkgxsj
     */
    public void setRkgxsj(String rkgxsj) {
        this.rkgxsj = rkgxsj;
    }
}
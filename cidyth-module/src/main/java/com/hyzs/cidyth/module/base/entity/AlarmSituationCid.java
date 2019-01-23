package com.hyzs.cidyth.module.base.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "v_asj_jq")
public class AlarmSituationCid {

    private Integer id;

    /**
     * 警综系统ID
     */
    private String systemid;

    /**
     * 警情编号
     */
    private String ajbh;

    /**
     * 警情名称
     */
    private String ajmc;

    /**
     * 受理接警单位
     */
    private String sljjdw;

    /**
     * 受理接警人员
     */
    private String sljjry;

    /**
     * 受理报警受理号
     */
    @Column(name = "sl_bjslh")
    private String slBjslh;

    /**
     * 警情类别
     */
    private String jqlb;

    /**
     * 案别
     */
    private String ab;

    /**
     * 受理接警时间
     */
    private Date sljjsj;

    /**
     * 受理发现日期
     */
    private Date slfxrq;

    /**
     * 发案时间开始
     */
    private Date fasjcz;

    /**
     * 发案时间结束
     */
    private Date fasjzz;

    /**
     * 受理接警方式
     */
    @Column(name = "sl_jjfs")
    private String slJjfs;

    /**
     * 一审时间
     */
    private Date yssj;

    /**
     * 一审原因
     */
    private String ysyy;

    /**
     * 一审单位编号
     */
    private String ysdw;

    /**
     * 一审单位承办人
     */
    private String ysdwcbr;

    /**
     * 一审承办人电话
     */
    private String yscbrdh;

    /**
     * 发案地点区县
     */
    @Column(name = "fadd_qx")
    private String faddQx;

    /**
     * 发案地点街道
     */
    @Column(name = "fadd_jd")
    private String faddJd;

    /**
     * 案件所属警区
     */
    private String ajssjq;

    /**
     * 发案地点
     */
    private String fadd;

    /**
     * 所属社区
     */
    private String sssq;

    /**
     * 死亡人数
     */
    private Integer swrs;

    /**
     * 受伤人数
     */
    private Integer ssrs;

    /**
     * 损失价值
     */
    private Integer ssjz;

    private Integer sazz;

    @Column(name = "sl_zhrs")
    private Integer slZhrs;

    @Column(name = "sl_jzrs")
    private Integer slJzrs;

    @Column(name = "sl_jjfnrs")
    private Integer slJjfnrs;

    @Column(name = "sl_jjetrs")
    private Integer slJjetrs;

    /**
     * 受理出警人次
     */
    @Column(name = "sl_cjrc")
    private Integer slCjrc;

    @Column(name = "sl_cjcl")
    private Integer slCjcl;

    @Column(name = "sl_cjcz")
    private Integer slCjcz;

    @Column(name = "sl_cjhk")
    private Integer slCjhk;

    /**
     * 受理领导签字
     */
    @Column(name = "sl_ldqz")
    private String slLdqz;

    /**
     * 受理处理结果
     */
    @Column(name = "sl_cljg")
    private String slCljg;

    /**
     * 受理受理时间
     */
    @Column(name = "sl_slsj")
    private Date slSlsj;

    /**
     * 受理受理人姓名
     */
    @Column(name = "sl_slrxm")
    private String slSlrxm;

    /**
     * 受理接收单位
     */
    private String sljsdw;

    /**
     * 受理录入人
     */
    @Column(name = "sl_lrr")
    private String slLrr;

    /**
     * 受理录入时间
     */
    @Column(name = "sl_lrsj")
    private Date slLrsj;

    /**
     * 发现形式
     */
    private String fxxs;

    private String departmentcode;

    private String creator;

    private Date createdtime;

    private String securitygrade;

    private String reservation01;

    private String reservation02;

    private String reservation03;

    private String reservation04;

    private String reservation05;

    private String reservation06;

    private String reservation08;

    private String reservation09;

    private String reservation10;

    private String lastupdatedby;

    private Date lastupdatedtime;

    private Date refreshtime;

    private String uploadflag;

    private String downloadflag;

    private String deleteflag;

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

    private Date reserver14;

    private String reserver15;

    private String reserver16;

    private String reserver17;

    private Date reserver20;

    private Date reserver21;

    private Integer reserver51;

    private Integer reserver52;

    private Integer reserver53;

    private Integer reserver54;

    private Integer reserver55;

    private Integer reserver56;

    private Integer reserver57;

    private Integer reserver58;

    private Integer reserver59;

    private Integer reserver60;

    private Date reserver61;

    private Date reserver62;

    private Date reserver63;

    private Date reserver64;

    private Date reserver65;

    private Date reserver66;

    private Date reserver67;

    private Date reserver68;

    private Date reserver69;

    private Date reserver70;

    private String datastate;

    private String datacheck;

    private String queryid;

    private Date reservation40;

    private Date reservation39;

    private Date pjsj;

    private Date ddxcsj;

    private String jqzbxx;

    private Date reservation37;

    private Date reservation38;

    private String flmjid;

    private String flmjdw;

    private String section;

    /**
     * 报警来源
     */
    private String bjly;

    /**
     * 报警地点
     */
    private String bjdd;

    /**
     * 报警电话
     */
    private String bjdh;

    /**
     * 涉案人数
     */
    private Integer sars;

    /**
     * 出警单位
     */
    private String cjdw;

    /**
     * 出警时间
     */
    private Date cjsj;

    /**
     * 到达时间
     */
    private Date ddsj;

    private String tjjf;

    private String xckz;

    private String cljy;

    @Column(name = "wdw_bary")
    private String wdwBary;

    private String hbjqh;

    private String reservation21;

    private String reservation22;

    private Date reservation23;

    private Date reservation24;

    private Date reservation25;

    private Date reservation56;

    private Date reservation57;

    private Date reservation58;

    private Date reservation59;

    private Date reservation60;

    private Date rkdjsj;

    private Date rkgxsj;

    /**
     * 警情来源（1、从警综提取，2、手动录入案件）
     */
    @Column(name = "aj_from")
    private Byte ajFrom;

    /**
     * 录入人员
     */
    private String lrry;

    /**
     * 录入人员名称
     */
    private String lrrymc;

    /**
     * 录入时间
     */
    private Date lrsj;

    /**
     * 主要警情
     */
    private String zyaq;

    /**
     * 受理案件处理情况
     */
    @Column(name = "sl_ajclqk")
    private String slAjclqk;

    /**
     * 受理出警人员
     */
    @Column(name = "sl_cjry")
    private String slCjry;

    private String reservation07;

    /**
     * 备注
     */
    private String bz;

    private String reserver18;

    private String reserver19;

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

    private String reservation32;

    private String reservation31;

    private String reservation18;

    private String reservation17;

    private String reservation16;

    private String reservation15;

    private String reservation14;

    private String reservation13;

    private String reservation12;

    private String reservation11;

    private String reservation19;

    private String reservation20;

    private String reservation33;

    private String reservation34;

    private String reservation35;

    private String reservation36;

    private String cjjl;

    @Column(name = "bdw_bary")
    private String bdwBary;

    private String reservation41;

    private String reservation42;

    private String reservation43;

    private String reservation44;

    private String reservation45;

    private String reservation46;

    private String reservation47;

    private String reservation48;

    private String reservation49;

    private String reservation50;

    private String reservation51;

    private String reservation52;

    private String reservation53;

    private String reservation54;

    private String reservation55;

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
     * 获取警综系统ID
     *
     * @return systemid - 警综系统ID
     */
    public String getSystemid() {
        return systemid;
    }

    /**
     * 设置警综系统ID
     *
     * @param systemid 警综系统ID
     */
    public void setSystemid(String systemid) {
        this.systemid = systemid;
    }

    /**
     * 获取警情编号
     *
     * @return ajbh - 警情编号
     */
    public String getAjbh() {
        return ajbh;
    }

    /**
     * 设置警情编号
     *
     * @param ajbh 警情编号
     */
    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

    /**
     * 获取警情名称
     *
     * @return ajmc - 警情名称
     */
    public String getAjmc() {
        return ajmc;
    }

    /**
     * 设置警情名称
     *
     * @param ajmc 警情名称
     */
    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }

    /**
     * 获取受理接警单位
     *
     * @return sljjdw - 受理接警单位
     */
    public String getSljjdw() {
        return sljjdw;
    }

    /**
     * 设置受理接警单位
     *
     * @param sljjdw 受理接警单位
     */
    public void setSljjdw(String sljjdw) {
        this.sljjdw = sljjdw;
    }

    /**
     * 获取受理接警人员
     *
     * @return sljjry - 受理接警人员
     */
    public String getSljjry() {
        return sljjry;
    }

    /**
     * 设置受理接警人员
     *
     * @param sljjry 受理接警人员
     */
    public void setSljjry(String sljjry) {
        this.sljjry = sljjry;
    }

    /**
     * 获取受理报警受理号
     *
     * @return sl_bjslh - 受理报警受理号
     */
    public String getSlBjslh() {
        return slBjslh;
    }

    /**
     * 设置受理报警受理号
     *
     * @param slBjslh 受理报警受理号
     */
    public void setSlBjslh(String slBjslh) {
        this.slBjslh = slBjslh;
    }

    /**
     * 获取警情类别
     *
     * @return jqlb - 警情类别
     */
    public String getJqlb() {
        return jqlb;
    }

    /**
     * 设置警情类别
     *
     * @param jqlb 警情类别
     */
    public void setJqlb(String jqlb) {
        this.jqlb = jqlb;
    }

    /**
     * 获取案别
     *
     * @return ab - 案别
     */
    public String getAb() {
        return ab;
    }

    /**
     * 设置案别
     *
     * @param ab 案别
     */
    public void setAb(String ab) {
        this.ab = ab;
    }

    /**
     * 获取受理接警时间
     *
     * @return sljjsj - 受理接警时间
     */
    public Date getSljjsj() {
        return sljjsj;
    }

    /**
     * 设置受理接警时间
     *
     * @param sljjsj 受理接警时间
     */
    public void setSljjsj(Date sljjsj) {
        this.sljjsj = sljjsj;
    }

    /**
     * 获取受理发现日期
     *
     * @return slfxrq - 受理发现日期
     */
    public Date getSlfxrq() {
        return slfxrq;
    }

    /**
     * 设置受理发现日期
     *
     * @param slfxrq 受理发现日期
     */
    public void setSlfxrq(Date slfxrq) {
        this.slfxrq = slfxrq;
    }

    /**
     * 获取发案时间开始
     *
     * @return fasjcz - 发案时间开始
     */
    public Date getFasjcz() {
        return fasjcz;
    }

    /**
     * 设置发案时间开始
     *
     * @param fasjcz 发案时间开始
     */
    public void setFasjcz(Date fasjcz) {
        this.fasjcz = fasjcz;
    }

    /**
     * 获取发案时间结束
     *
     * @return fasjzz - 发案时间结束
     */
    public Date getFasjzz() {
        return fasjzz;
    }

    /**
     * 设置发案时间结束
     *
     * @param fasjzz 发案时间结束
     */
    public void setFasjzz(Date fasjzz) {
        this.fasjzz = fasjzz;
    }

    /**
     * 获取受理接警方式
     *
     * @return sl_jjfs - 受理接警方式
     */
    public String getSlJjfs() {
        return slJjfs;
    }

    /**
     * 设置受理接警方式
     *
     * @param slJjfs 受理接警方式
     */
    public void setSlJjfs(String slJjfs) {
        this.slJjfs = slJjfs;
    }

    /**
     * 获取一审时间
     *
     * @return yssj - 一审时间
     */
    public Date getYssj() {
        return yssj;
    }

    /**
     * 设置一审时间
     *
     * @param yssj 一审时间
     */
    public void setYssj(Date yssj) {
        this.yssj = yssj;
    }

    /**
     * 获取一审原因
     *
     * @return ysyy - 一审原因
     */
    public String getYsyy() {
        return ysyy;
    }

    /**
     * 设置一审原因
     *
     * @param ysyy 一审原因
     */
    public void setYsyy(String ysyy) {
        this.ysyy = ysyy;
    }

    /**
     * 获取一审单位编号
     *
     * @return ysdw - 一审单位编号
     */
    public String getYsdw() {
        return ysdw;
    }

    /**
     * 设置一审单位编号
     *
     * @param ysdw 一审单位编号
     */
    public void setYsdw(String ysdw) {
        this.ysdw = ysdw;
    }

    /**
     * 获取一审单位承办人
     *
     * @return ysdwcbr - 一审单位承办人
     */
    public String getYsdwcbr() {
        return ysdwcbr;
    }

    /**
     * 设置一审单位承办人
     *
     * @param ysdwcbr 一审单位承办人
     */
    public void setYsdwcbr(String ysdwcbr) {
        this.ysdwcbr = ysdwcbr;
    }

    /**
     * 获取一审承办人电话
     *
     * @return yscbrdh - 一审承办人电话
     */
    public String getYscbrdh() {
        return yscbrdh;
    }

    /**
     * 设置一审承办人电话
     *
     * @param yscbrdh 一审承办人电话
     */
    public void setYscbrdh(String yscbrdh) {
        this.yscbrdh = yscbrdh;
    }

    /**
     * 获取发案地点区县
     *
     * @return fadd_qx - 发案地点区县
     */
    public String getFaddQx() {
        return faddQx;
    }

    /**
     * 设置发案地点区县
     *
     * @param faddQx 发案地点区县
     */
    public void setFaddQx(String faddQx) {
        this.faddQx = faddQx;
    }

    /**
     * 获取发案地点街道
     *
     * @return fadd_jd - 发案地点街道
     */
    public String getFaddJd() {
        return faddJd;
    }

    /**
     * 设置发案地点街道
     *
     * @param faddJd 发案地点街道
     */
    public void setFaddJd(String faddJd) {
        this.faddJd = faddJd;
    }

    /**
     * 获取案件所属警区
     *
     * @return ajssjq - 案件所属警区
     */
    public String getAjssjq() {
        return ajssjq;
    }

    /**
     * 设置案件所属警区
     *
     * @param ajssjq 案件所属警区
     */
    public void setAjssjq(String ajssjq) {
        this.ajssjq = ajssjq;
    }

    /**
     * 获取发案地点
     *
     * @return fadd - 发案地点
     */
    public String getFadd() {
        return fadd;
    }

    /**
     * 设置发案地点
     *
     * @param fadd 发案地点
     */
    public void setFadd(String fadd) {
        this.fadd = fadd;
    }

    /**
     * 获取所属社区
     *
     * @return sssq - 所属社区
     */
    public String getSssq() {
        return sssq;
    }

    /**
     * 设置所属社区
     *
     * @param sssq 所属社区
     */
    public void setSssq(String sssq) {
        this.sssq = sssq;
    }

    /**
     * 获取死亡人数
     *
     * @return swrs - 死亡人数
     */
    public Integer getSwrs() {
        return swrs;
    }

    /**
     * 设置死亡人数
     *
     * @param swrs 死亡人数
     */
    public void setSwrs(Integer swrs) {
        this.swrs = swrs;
    }

    /**
     * 获取受伤人数
     *
     * @return ssrs - 受伤人数
     */
    public Integer getSsrs() {
        return ssrs;
    }

    /**
     * 设置受伤人数
     *
     * @param ssrs 受伤人数
     */
    public void setSsrs(Integer ssrs) {
        this.ssrs = ssrs;
    }

    /**
     * 获取损失价值
     *
     * @return ssjz - 损失价值
     */
    public Integer getSsjz() {
        return ssjz;
    }

    /**
     * 设置损失价值
     *
     * @param ssjz 损失价值
     */
    public void setSsjz(Integer ssjz) {
        this.ssjz = ssjz;
    }

    /**
     * @return sazz
     */
    public Integer getSazz() {
        return sazz;
    }

    /**
     * @param sazz
     */
    public void setSazz(Integer sazz) {
        this.sazz = sazz;
    }

    /**
     * @return sl_zhrs
     */
    public Integer getSlZhrs() {
        return slZhrs;
    }

    /**
     * @param slZhrs
     */
    public void setSlZhrs(Integer slZhrs) {
        this.slZhrs = slZhrs;
    }

    /**
     * @return sl_jzrs
     */
    public Integer getSlJzrs() {
        return slJzrs;
    }

    /**
     * @param slJzrs
     */
    public void setSlJzrs(Integer slJzrs) {
        this.slJzrs = slJzrs;
    }

    /**
     * @return sl_jjfnrs
     */
    public Integer getSlJjfnrs() {
        return slJjfnrs;
    }

    /**
     * @param slJjfnrs
     */
    public void setSlJjfnrs(Integer slJjfnrs) {
        this.slJjfnrs = slJjfnrs;
    }

    /**
     * @return sl_jjetrs
     */
    public Integer getSlJjetrs() {
        return slJjetrs;
    }

    /**
     * @param slJjetrs
     */
    public void setSlJjetrs(Integer slJjetrs) {
        this.slJjetrs = slJjetrs;
    }

    /**
     * 获取受理出警人次
     *
     * @return sl_cjrc - 受理出警人次
     */
    public Integer getSlCjrc() {
        return slCjrc;
    }

    /**
     * 设置受理出警人次
     *
     * @param slCjrc 受理出警人次
     */
    public void setSlCjrc(Integer slCjrc) {
        this.slCjrc = slCjrc;
    }

    /**
     * @return sl_cjcl
     */
    public Integer getSlCjcl() {
        return slCjcl;
    }

    /**
     * @param slCjcl
     */
    public void setSlCjcl(Integer slCjcl) {
        this.slCjcl = slCjcl;
    }

    /**
     * @return sl_cjcz
     */
    public Integer getSlCjcz() {
        return slCjcz;
    }

    /**
     * @param slCjcz
     */
    public void setSlCjcz(Integer slCjcz) {
        this.slCjcz = slCjcz;
    }

    /**
     * @return sl_cjhk
     */
    public Integer getSlCjhk() {
        return slCjhk;
    }

    /**
     * @param slCjhk
     */
    public void setSlCjhk(Integer slCjhk) {
        this.slCjhk = slCjhk;
    }

    /**
     * 获取受理领导签字
     *
     * @return sl_ldqz - 受理领导签字
     */
    public String getSlLdqz() {
        return slLdqz;
    }

    /**
     * 设置受理领导签字
     *
     * @param slLdqz 受理领导签字
     */
    public void setSlLdqz(String slLdqz) {
        this.slLdqz = slLdqz;
    }

    /**
     * 获取受理处理结果
     *
     * @return sl_cljg - 受理处理结果
     */
    public String getSlCljg() {
        return slCljg;
    }

    /**
     * 设置受理处理结果
     *
     * @param slCljg 受理处理结果
     */
    public void setSlCljg(String slCljg) {
        this.slCljg = slCljg;
    }

    /**
     * 获取受理受理时间
     *
     * @return sl_slsj - 受理受理时间
     */
    public Date getSlSlsj() {
        return slSlsj;
    }

    /**
     * 设置受理受理时间
     *
     * @param slSlsj 受理受理时间
     */
    public void setSlSlsj(Date slSlsj) {
        this.slSlsj = slSlsj;
    }

    /**
     * 获取受理受理人姓名
     *
     * @return sl_slrxm - 受理受理人姓名
     */
    public String getSlSlrxm() {
        return slSlrxm;
    }

    /**
     * 设置受理受理人姓名
     *
     * @param slSlrxm 受理受理人姓名
     */
    public void setSlSlrxm(String slSlrxm) {
        this.slSlrxm = slSlrxm;
    }

    /**
     * 获取受理接收单位
     *
     * @return sljsdw - 受理接收单位
     */
    public String getSljsdw() {
        return sljsdw;
    }

    /**
     * 设置受理接收单位
     *
     * @param sljsdw 受理接收单位
     */
    public void setSljsdw(String sljsdw) {
        this.sljsdw = sljsdw;
    }

    /**
     * 获取受理录入人
     *
     * @return sl_lrr - 受理录入人
     */
    public String getSlLrr() {
        return slLrr;
    }

    /**
     * 设置受理录入人
     *
     * @param slLrr 受理录入人
     */
    public void setSlLrr(String slLrr) {
        this.slLrr = slLrr;
    }

    /**
     * 获取受理录入时间
     *
     * @return sl_lrsj - 受理录入时间
     */
    public Date getSlLrsj() {
        return slLrsj;
    }

    /**
     * 设置受理录入时间
     *
     * @param slLrsj 受理录入时间
     */
    public void setSlLrsj(Date slLrsj) {
        this.slLrsj = slLrsj;
    }

    /**
     * 获取发现形式
     *
     * @return fxxs - 发现形式
     */
    public String getFxxs() {
        return fxxs;
    }

    /**
     * 设置发现形式
     *
     * @param fxxs 发现形式
     */
    public void setFxxs(String fxxs) {
        this.fxxs = fxxs;
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
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * @param createdtime
     */
    public void setCreatedtime(Date createdtime) {
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
    public Date getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * @param lastupdatedtime
     */
    public void setLastupdatedtime(Date lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * @return refreshtime
     */
    public Date getRefreshtime() {
        return refreshtime;
    }

    /**
     * @param refreshtime
     */
    public void setRefreshtime(Date refreshtime) {
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
    public Date getReserver14() {
        return reserver14;
    }

    /**
     * @param reserver14
     */
    public void setReserver14(Date reserver14) {
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
     * @return reserver20
     */
    public Date getReserver20() {
        return reserver20;
    }

    /**
     * @param reserver20
     */
    public void setReserver20(Date reserver20) {
        this.reserver20 = reserver20;
    }

    /**
     * @return reserver21
     */
    public Date getReserver21() {
        return reserver21;
    }

    /**
     * @param reserver21
     */
    public void setReserver21(Date reserver21) {
        this.reserver21 = reserver21;
    }

    /**
     * @return reserver51
     */
    public Integer getReserver51() {
        return reserver51;
    }

    /**
     * @param reserver51
     */
    public void setReserver51(Integer reserver51) {
        this.reserver51 = reserver51;
    }

    /**
     * @return reserver52
     */
    public Integer getReserver52() {
        return reserver52;
    }

    /**
     * @param reserver52
     */
    public void setReserver52(Integer reserver52) {
        this.reserver52 = reserver52;
    }

    /**
     * @return reserver53
     */
    public Integer getReserver53() {
        return reserver53;
    }

    /**
     * @param reserver53
     */
    public void setReserver53(Integer reserver53) {
        this.reserver53 = reserver53;
    }

    /**
     * @return reserver54
     */
    public Integer getReserver54() {
        return reserver54;
    }

    /**
     * @param reserver54
     */
    public void setReserver54(Integer reserver54) {
        this.reserver54 = reserver54;
    }

    /**
     * @return reserver55
     */
    public Integer getReserver55() {
        return reserver55;
    }

    /**
     * @param reserver55
     */
    public void setReserver55(Integer reserver55) {
        this.reserver55 = reserver55;
    }

    /**
     * @return reserver56
     */
    public Integer getReserver56() {
        return reserver56;
    }

    /**
     * @param reserver56
     */
    public void setReserver56(Integer reserver56) {
        this.reserver56 = reserver56;
    }

    /**
     * @return reserver57
     */
    public Integer getReserver57() {
        return reserver57;
    }

    /**
     * @param reserver57
     */
    public void setReserver57(Integer reserver57) {
        this.reserver57 = reserver57;
    }

    /**
     * @return reserver58
     */
    public Integer getReserver58() {
        return reserver58;
    }

    /**
     * @param reserver58
     */
    public void setReserver58(Integer reserver58) {
        this.reserver58 = reserver58;
    }

    /**
     * @return reserver59
     */
    public Integer getReserver59() {
        return reserver59;
    }

    /**
     * @param reserver59
     */
    public void setReserver59(Integer reserver59) {
        this.reserver59 = reserver59;
    }

    /**
     * @return reserver60
     */
    public Integer getReserver60() {
        return reserver60;
    }

    /**
     * @param reserver60
     */
    public void setReserver60(Integer reserver60) {
        this.reserver60 = reserver60;
    }

    /**
     * @return reserver61
     */
    public Date getReserver61() {
        return reserver61;
    }

    /**
     * @param reserver61
     */
    public void setReserver61(Date reserver61) {
        this.reserver61 = reserver61;
    }

    /**
     * @return reserver62
     */
    public Date getReserver62() {
        return reserver62;
    }

    /**
     * @param reserver62
     */
    public void setReserver62(Date reserver62) {
        this.reserver62 = reserver62;
    }

    /**
     * @return reserver63
     */
    public Date getReserver63() {
        return reserver63;
    }

    /**
     * @param reserver63
     */
    public void setReserver63(Date reserver63) {
        this.reserver63 = reserver63;
    }

    /**
     * @return reserver64
     */
    public Date getReserver64() {
        return reserver64;
    }

    /**
     * @param reserver64
     */
    public void setReserver64(Date reserver64) {
        this.reserver64 = reserver64;
    }

    /**
     * @return reserver65
     */
    public Date getReserver65() {
        return reserver65;
    }

    /**
     * @param reserver65
     */
    public void setReserver65(Date reserver65) {
        this.reserver65 = reserver65;
    }

    /**
     * @return reserver66
     */
    public Date getReserver66() {
        return reserver66;
    }

    /**
     * @param reserver66
     */
    public void setReserver66(Date reserver66) {
        this.reserver66 = reserver66;
    }

    /**
     * @return reserver67
     */
    public Date getReserver67() {
        return reserver67;
    }

    /**
     * @param reserver67
     */
    public void setReserver67(Date reserver67) {
        this.reserver67 = reserver67;
    }

    /**
     * @return reserver68
     */
    public Date getReserver68() {
        return reserver68;
    }

    /**
     * @param reserver68
     */
    public void setReserver68(Date reserver68) {
        this.reserver68 = reserver68;
    }

    /**
     * @return reserver69
     */
    public Date getReserver69() {
        return reserver69;
    }

    /**
     * @param reserver69
     */
    public void setReserver69(Date reserver69) {
        this.reserver69 = reserver69;
    }

    /**
     * @return reserver70
     */
    public Date getReserver70() {
        return reserver70;
    }

    /**
     * @param reserver70
     */
    public void setReserver70(Date reserver70) {
        this.reserver70 = reserver70;
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
     * @return reservation40
     */
    public Date getReservation40() {
        return reservation40;
    }

    /**
     * @param reservation40
     */
    public void setReservation40(Date reservation40) {
        this.reservation40 = reservation40;
    }

    /**
     * @return reservation39
     */
    public Date getReservation39() {
        return reservation39;
    }

    /**
     * @param reservation39
     */
    public void setReservation39(Date reservation39) {
        this.reservation39 = reservation39;
    }

    /**
     * @return pjsj
     */
    public Date getPjsj() {
        return pjsj;
    }

    /**
     * @param pjsj
     */
    public void setPjsj(Date pjsj) {
        this.pjsj = pjsj;
    }

    /**
     * @return ddxcsj
     */
    public Date getDdxcsj() {
        return ddxcsj;
    }

    /**
     * @param ddxcsj
     */
    public void setDdxcsj(Date ddxcsj) {
        this.ddxcsj = ddxcsj;
    }

    /**
     * @return jqzbxx
     */
    public String getJqzbxx() {
        return jqzbxx;
    }

    /**
     * @param jqzbxx
     */
    public void setJqzbxx(String jqzbxx) {
        this.jqzbxx = jqzbxx;
    }

    /**
     * @return reservation37
     */
    public Date getReservation37() {
        return reservation37;
    }

    /**
     * @param reservation37
     */
    public void setReservation37(Date reservation37) {
        this.reservation37 = reservation37;
    }

    /**
     * @return reservation38
     */
    public Date getReservation38() {
        return reservation38;
    }

    /**
     * @param reservation38
     */
    public void setReservation38(Date reservation38) {
        this.reservation38 = reservation38;
    }

    /**
     * @return flmjid
     */
    public String getFlmjid() {
        return flmjid;
    }

    /**
     * @param flmjid
     */
    public void setFlmjid(String flmjid) {
        this.flmjid = flmjid;
    }

    /**
     * @return flmjdw
     */
    public String getFlmjdw() {
        return flmjdw;
    }

    /**
     * @param flmjdw
     */
    public void setFlmjdw(String flmjdw) {
        this.flmjdw = flmjdw;
    }

    /**
     * @return section
     */
    public String getSection() {
        return section;
    }

    /**
     * @param section
     */
    public void setSection(String section) {
        this.section = section;
    }

    /**
     * 获取报警来源
     *
     * @return bjly - 报警来源
     */
    public String getBjly() {
        return bjly;
    }

    /**
     * 设置报警来源
     *
     * @param bjly 报警来源
     */
    public void setBjly(String bjly) {
        this.bjly = bjly;
    }

    /**
     * 获取报警地点
     *
     * @return bjdd - 报警地点
     */
    public String getBjdd() {
        return bjdd;
    }

    /**
     * 设置报警地点
     *
     * @param bjdd 报警地点
     */
    public void setBjdd(String bjdd) {
        this.bjdd = bjdd;
    }

    /**
     * 获取报警电话
     *
     * @return bjdh - 报警电话
     */
    public String getBjdh() {
        return bjdh;
    }

    /**
     * 设置报警电话
     *
     * @param bjdh 报警电话
     */
    public void setBjdh(String bjdh) {
        this.bjdh = bjdh;
    }

    /**
     * 获取涉案人数
     *
     * @return sars - 涉案人数
     */
    public Integer getSars() {
        return sars;
    }

    /**
     * 设置涉案人数
     *
     * @param sars 涉案人数
     */
    public void setSars(Integer sars) {
        this.sars = sars;
    }

    /**
     * 获取出警单位
     *
     * @return cjdw - 出警单位
     */
    public String getCjdw() {
        return cjdw;
    }

    /**
     * 设置出警单位
     *
     * @param cjdw 出警单位
     */
    public void setCjdw(String cjdw) {
        this.cjdw = cjdw;
    }

    /**
     * 获取出警时间
     *
     * @return cjsj - 出警时间
     */
    public Date getCjsj() {
        return cjsj;
    }

    /**
     * 设置出警时间
     *
     * @param cjsj 出警时间
     */
    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    /**
     * 获取到达时间
     *
     * @return ddsj - 到达时间
     */
    public Date getDdsj() {
        return ddsj;
    }

    /**
     * 设置到达时间
     *
     * @param ddsj 到达时间
     */
    public void setDdsj(Date ddsj) {
        this.ddsj = ddsj;
    }

    /**
     * @return tjjf
     */
    public String getTjjf() {
        return tjjf;
    }

    /**
     * @param tjjf
     */
    public void setTjjf(String tjjf) {
        this.tjjf = tjjf;
    }

    /**
     * @return xckz
     */
    public String getXckz() {
        return xckz;
    }

    /**
     * @param xckz
     */
    public void setXckz(String xckz) {
        this.xckz = xckz;
    }

    /**
     * @return cljy
     */
    public String getCljy() {
        return cljy;
    }

    /**
     * @param cljy
     */
    public void setCljy(String cljy) {
        this.cljy = cljy;
    }

    /**
     * @return wdw_bary
     */
    public String getWdwBary() {
        return wdwBary;
    }

    /**
     * @param wdwBary
     */
    public void setWdwBary(String wdwBary) {
        this.wdwBary = wdwBary;
    }

    /**
     * @return hbjqh
     */
    public String getHbjqh() {
        return hbjqh;
    }

    /**
     * @param hbjqh
     */
    public void setHbjqh(String hbjqh) {
        this.hbjqh = hbjqh;
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
    public Date getReservation23() {
        return reservation23;
    }

    /**
     * @param reservation23
     */
    public void setReservation23(Date reservation23) {
        this.reservation23 = reservation23;
    }

    /**
     * @return reservation24
     */
    public Date getReservation24() {
        return reservation24;
    }

    /**
     * @param reservation24
     */
    public void setReservation24(Date reservation24) {
        this.reservation24 = reservation24;
    }

    /**
     * @return reservation25
     */
    public Date getReservation25() {
        return reservation25;
    }

    /**
     * @param reservation25
     */
    public void setReservation25(Date reservation25) {
        this.reservation25 = reservation25;
    }

    /**
     * @return reservation56
     */
    public Date getReservation56() {
        return reservation56;
    }

    /**
     * @param reservation56
     */
    public void setReservation56(Date reservation56) {
        this.reservation56 = reservation56;
    }

    /**
     * @return reservation57
     */
    public Date getReservation57() {
        return reservation57;
    }

    /**
     * @param reservation57
     */
    public void setReservation57(Date reservation57) {
        this.reservation57 = reservation57;
    }

    /**
     * @return reservation58
     */
    public Date getReservation58() {
        return reservation58;
    }

    /**
     * @param reservation58
     */
    public void setReservation58(Date reservation58) {
        this.reservation58 = reservation58;
    }

    /**
     * @return reservation59
     */
    public Date getReservation59() {
        return reservation59;
    }

    /**
     * @param reservation59
     */
    public void setReservation59(Date reservation59) {
        this.reservation59 = reservation59;
    }

    /**
     * @return reservation60
     */
    public Date getReservation60() {
        return reservation60;
    }

    /**
     * @param reservation60
     */
    public void setReservation60(Date reservation60) {
        this.reservation60 = reservation60;
    }

    /**
     * @return rkdjsj
     */
    public Date getRkdjsj() {
        return rkdjsj;
    }

    /**
     * @param rkdjsj
     */
    public void setRkdjsj(Date rkdjsj) {
        this.rkdjsj = rkdjsj;
    }

    /**
     * @return rkgxsj
     */
    public Date getRkgxsj() {
        return rkgxsj;
    }

    /**
     * @param rkgxsj
     */
    public void setRkgxsj(Date rkgxsj) {
        this.rkgxsj = rkgxsj;
    }

    /**
     * 获取警情来源（1、从警综提取，2、手动录入案件）
     *
     * @return aj_from - 警情来源（1、从警综提取，2、手动录入案件）
     */
    public Byte getAjFrom() {
        return ajFrom;
    }

    /**
     * 设置警情来源（1、从警综提取，2、手动录入案件）
     *
     * @param ajFrom 警情来源（1、从警综提取，2、手动录入案件）
     */
    public void setAjFrom(Byte ajFrom) {
        this.ajFrom = ajFrom;
    }

    /**
     * 获取录入人员
     *
     * @return lrry - 录入人员
     */
    public String getLrry() {
        return lrry;
    }

    /**
     * 设置录入人员
     *
     * @param lrry 录入人员
     */
    public void setLrry(String lrry) {
        this.lrry = lrry;
    }

    /**
     * 获取录入人员名称
     *
     * @return lrrymc - 录入人员名称
     */
    public String getLrrymc() {
        return lrrymc;
    }

    /**
     * 设置录入人员名称
     *
     * @param lrrymc 录入人员名称
     */
    public void setLrrymc(String lrrymc) {
        this.lrrymc = lrrymc;
    }

    /**
     * 获取录入时间
     *
     * @return lrsj - 录入时间
     */
    public Date getLrsj() {
        return lrsj;
    }

    /**
     * 设置录入时间
     *
     * @param lrsj 录入时间
     */
    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    /**
     * 获取主要警情
     *
     * @return zyaq - 主要警情
     */
    public String getZyaq() {
        return zyaq;
    }

    /**
     * 设置主要警情
     *
     * @param zyaq 主要警情
     */
    public void setZyaq(String zyaq) {
        this.zyaq = zyaq;
    }

    /**
     * 获取受理案件处理情况
     *
     * @return sl_ajclqk - 受理案件处理情况
     */
    public String getSlAjclqk() {
        return slAjclqk;
    }

    /**
     * 设置受理案件处理情况
     *
     * @param slAjclqk 受理案件处理情况
     */
    public void setSlAjclqk(String slAjclqk) {
        this.slAjclqk = slAjclqk;
    }

    /**
     * 获取受理出警人员
     *
     * @return sl_cjry - 受理出警人员
     */
    public String getSlCjry() {
        return slCjry;
    }

    /**
     * 设置受理出警人员
     *
     * @param slCjry 受理出警人员
     */
    public void setSlCjry(String slCjry) {
        this.slCjry = slCjry;
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
     * @return reservation32
     */
    public String getReservation32() {
        return reservation32;
    }

    /**
     * @param reservation32
     */
    public void setReservation32(String reservation32) {
        this.reservation32 = reservation32;
    }

    /**
     * @return reservation31
     */
    public String getReservation31() {
        return reservation31;
    }

    /**
     * @param reservation31
     */
    public void setReservation31(String reservation31) {
        this.reservation31 = reservation31;
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
     * @return reservation33
     */
    public String getReservation33() {
        return reservation33;
    }

    /**
     * @param reservation33
     */
    public void setReservation33(String reservation33) {
        this.reservation33 = reservation33;
    }

    /**
     * @return reservation34
     */
    public String getReservation34() {
        return reservation34;
    }

    /**
     * @param reservation34
     */
    public void setReservation34(String reservation34) {
        this.reservation34 = reservation34;
    }

    /**
     * @return reservation35
     */
    public String getReservation35() {
        return reservation35;
    }

    /**
     * @param reservation35
     */
    public void setReservation35(String reservation35) {
        this.reservation35 = reservation35;
    }

    /**
     * @return reservation36
     */
    public String getReservation36() {
        return reservation36;
    }

    /**
     * @param reservation36
     */
    public void setReservation36(String reservation36) {
        this.reservation36 = reservation36;
    }

    /**
     * @return cjjl
     */
    public String getCjjl() {
        return cjjl;
    }

    /**
     * @param cjjl
     */
    public void setCjjl(String cjjl) {
        this.cjjl = cjjl;
    }

    /**
     * @return bdw_bary
     */
    public String getBdwBary() {
        return bdwBary;
    }

    /**
     * @param bdwBary
     */
    public void setBdwBary(String bdwBary) {
        this.bdwBary = bdwBary;
    }

    /**
     * @return reservation41
     */
    public String getReservation41() {
        return reservation41;
    }

    /**
     * @param reservation41
     */
    public void setReservation41(String reservation41) {
        this.reservation41 = reservation41;
    }

    /**
     * @return reservation42
     */
    public String getReservation42() {
        return reservation42;
    }

    /**
     * @param reservation42
     */
    public void setReservation42(String reservation42) {
        this.reservation42 = reservation42;
    }

    /**
     * @return reservation43
     */
    public String getReservation43() {
        return reservation43;
    }

    /**
     * @param reservation43
     */
    public void setReservation43(String reservation43) {
        this.reservation43 = reservation43;
    }

    /**
     * @return reservation44
     */
    public String getReservation44() {
        return reservation44;
    }

    /**
     * @param reservation44
     */
    public void setReservation44(String reservation44) {
        this.reservation44 = reservation44;
    }

    /**
     * @return reservation45
     */
    public String getReservation45() {
        return reservation45;
    }

    /**
     * @param reservation45
     */
    public void setReservation45(String reservation45) {
        this.reservation45 = reservation45;
    }

    /**
     * @return reservation46
     */
    public String getReservation46() {
        return reservation46;
    }

    /**
     * @param reservation46
     */
    public void setReservation46(String reservation46) {
        this.reservation46 = reservation46;
    }

    /**
     * @return reservation47
     */
    public String getReservation47() {
        return reservation47;
    }

    /**
     * @param reservation47
     */
    public void setReservation47(String reservation47) {
        this.reservation47 = reservation47;
    }

    /**
     * @return reservation48
     */
    public String getReservation48() {
        return reservation48;
    }

    /**
     * @param reservation48
     */
    public void setReservation48(String reservation48) {
        this.reservation48 = reservation48;
    }

    /**
     * @return reservation49
     */
    public String getReservation49() {
        return reservation49;
    }

    /**
     * @param reservation49
     */
    public void setReservation49(String reservation49) {
        this.reservation49 = reservation49;
    }

    /**
     * @return reservation50
     */
    public String getReservation50() {
        return reservation50;
    }

    /**
     * @param reservation50
     */
    public void setReservation50(String reservation50) {
        this.reservation50 = reservation50;
    }

    /**
     * @return reservation51
     */
    public String getReservation51() {
        return reservation51;
    }

    /**
     * @param reservation51
     */
    public void setReservation51(String reservation51) {
        this.reservation51 = reservation51;
    }

    /**
     * @return reservation52
     */
    public String getReservation52() {
        return reservation52;
    }

    /**
     * @param reservation52
     */
    public void setReservation52(String reservation52) {
        this.reservation52 = reservation52;
    }

    /**
     * @return reservation53
     */
    public String getReservation53() {
        return reservation53;
    }

    /**
     * @param reservation53
     */
    public void setReservation53(String reservation53) {
        this.reservation53 = reservation53;
    }

    /**
     * @return reservation54
     */
    public String getReservation54() {
        return reservation54;
    }

    /**
     * @param reservation54
     */
    public void setReservation54(String reservation54) {
        this.reservation54 = reservation54;
    }

    /**
     * @return reservation55
     */
    public String getReservation55() {
        return reservation55;
    }

    /**
     * @param reservation55
     */
    public void setReservation55(String reservation55) {
        this.reservation55 = reservation55;
    }
}
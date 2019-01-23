package com.hyzs.cidyth.module.base.entity;

import java.util.Date;
import javax.persistence.*;
/**
 * 案件
 * @author derrick
 *
 */
@Table(name = "t_asj_aj")
public class Cases {
    /**
     * ����
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * �������
     */
    private String ajbh;

    /**
     * ��λ
     */
    private String sljsdw;

    /**
     * ����״̬
     */
    private String ajstate;

    /**
     * ����״̬
     */
    private String zazt;

    @Column(name = "sl_bjslh")
    private String slBjslh;

    /**
     * �Ӿ���ʽ
     */
    @Column(name = "sl_jjfs")
    private String slJjfs;

    /**
     * ��������
     */
    private String ajlx;

    /**
     * ����
     */
    private String ab;

    /**
     * ר����ʶ
     */
    private String zabz;

    /**
     * ��������
     */
    private String ajmc;

    private String ajwh;

    /**
     * ����Ӿ�ʱ��
     */
    private Date sljjsj;

    /**
     * ����������
     */
    private Date slfxrq;

    private Date fasjcz;

    private Date fasjzz;

    /**
     * �����ص�����
     */
    @Column(name = "fadd_qx")
    private String faddQx;

    /**
     * �����ص�ֵ�
     */
    @Column(name = "fadd_jd")
    private String faddJd;

    /**
     * ������������
     */
    private String ajssjq;

    /**
     * �����ص�
     */
    private String fadd;

    /**
     * ��������
     */
    private String sssq;

    /**
     * ��������
     */
    private String fady;

    /**
     * ��������
     */
    private String slfacs;

    /**
     * ������ʽ
     */
    private String fxxs;

    /**
     * ����Σ���̶�
     */
    private String ajwhcd;

    private String blyy;

    private String zyaq;

    private String xzsj;

    private String xzcs;

    private String xzdx;

    private String xzwp;

    private String zagj;

    private String xzbw;

    private String zars;

    private String sdtd;

    private String swrs;

    private String ssrs;

    private String ssjz;

    private String sazz;

    private Date lasj;

    private Date pasj;

    @Column(name = "ja_jasj")
    private Date jaJasj;

    @Column(name = "xa_xasj")
    private Date xaXasj;

    private Date yssj;

    private String sljjdw;

    @Column(name = "sl_lrr")
    private String slLrr;

    @Column(name = "sl_lrsj")
    private Date slLrsj;

    private String sljjry;

    @Column(name = "sl_slrxm")
    private String slSlrxm;

    @Column(name = "sl_slsj")
    private Date slSlsj;

    private String slfaqh;

    private String ladw;

    private String ajlary;

    private String zbdw;

    private String ajzbry;

    private String ajxbry;

    private String ajbarp;
    /**
     * 本地案件状态
     */
    private String bdajstate;

    @Column(name = "la_lrr")
    private String laLrr;

    @Column(name = "la_lrsj")
    private Date laLrsj;

    @Column(name = "la_pzr")
    private String laPzr;

    @Column(name = "la_pzsj")
    private Date laPzsj;

    @Column(name = "la_zhxgr")
    private String laZhxgr;

    @Column(name = "la_zhxgsj")
    private Date laZhxgsj;

    @Column(name = "la_psstate")
    private String laPsstate;

    private String dbxx;

    private String ajly;

    private String bz;

    private String departmentcode;

    private String creator;

    private Date createdtime;

    private String securitygrade;

    private Date lastupdatedtime;

    private Date refreshtime;

    private String uploadflag;

    private String downloadflag;

    private String deleteflag;

    private String ysdw;

    private String yscbr;

    private String ysdwdh;

    private Date yssasj;

    private Date ystcsj;

    private String whssjz;

    private String dbjb;

    private String jtajly;

    private String fzztlx;

    private String sfsw;

    private String sjgjdq;

    private Date yishensj;

    private Date essj;

    @Column(name = "ysdw_new")
    private String ysdwNew;

    @Column(name = "ysdwdh_new")
    private String ysdwdhNew;

    @Column(name = "yscbr_new")
    private String yscbrNew;

    @Column(name = "yssj_new")
    private Date yssjNew;

    private String datastate;

    private String datacheck;

    private String queryid;

    private String jzbh;
    /**
     * 审核状态
     */
    private Integer shzt;
    /**
     * 是否串并案
     */
    private Integer sfcba;
    /**
     * 串并案编号
     */
    private String cbabh;

    /**
     * 案件来源类型（1、警综提取，2、手动录入）
     */
    @Column(name = "aj_from")
    private Integer ajFrom;

    /**
     * ���Ǽ�ʱ��
     */
    private Date rkdjsj;

    /**
     * ������ʱ��
     */
    private Date rkgxsj;

    /**
     * ��ע˵��
     */
    private String smbz;

    /**
     * ¼����Ա
     */
    private String lrry;

    /**
     * ¼��ʱ��
     */
    private Date lrsj;

    /**
     * �޸���Ա
     */
    private String xgry;

    /**
     * �޸���Ա
     */
    private Date xgsj;

    /**
     * 修改人员名称
     */
    private String lrrymc;

    /**
     * 修改人员名称
     */
    private String xgrymc;

    /**
     * 本地案件状态备注
     */
    private String bdajstatebz;

    /**
     * 思维导图上坐标
     */
    @Column(name = "mind_top")
    private String mindTop;

    /**
     * 思维导图左坐标
     */
    @Column(name = "mind_left")
    private String mindLeft;
    /**
     * 审核人员
     */
    @Column(name = "check_person")
    private String checkPerson;
    /**
     * 审核状态
     */
    @Column(name = "check_status")
    private Integer checkStatus;
    /**
     * 审核结果
     */
    @Column(name = "check_result")
    private String checkResult;
    /**
     * ��ȡ����
     *
     * @return id - ����
     */
    public Integer getId() {
        return id;
    }

    /**
     * ��������
     *
     * @param id ����
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * ��ȡ�������
     *
     * @return ajbh - �������
     */
    public String getAjbh() {
        return ajbh;
    }

    /**
     * ���ð������
     *
     * @param ajbh �������
     */
    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

    /**
     * ��ȡ��λ
     *
     * @return sljsdw - ��λ
     */
    public String getSljsdw() {
        return sljsdw;
    }

    /**
     * ���õ�λ
     *
     * @param sljsdw ��λ
     */
    public void setSljsdw(String sljsdw) {
        this.sljsdw = sljsdw;
    }

    /**
     * ��ȡ����״̬
     *
     * @return ajstate - ����״̬
     */
    public String getAjstate() {
        return ajstate;
    }

    /**
     * ���ð���״̬
     *
     * @param ajstate ����״̬
     */
    public void setAjstate(String ajstate) {
        this.ajstate = ajstate;
    }

    /**
     * ��ȡ����״̬
     *
     * @return zazt - ����״̬
     */
    public String getZazt() {
        return zazt;
    }

    /**
     * ��������״̬
     *
     * @param zazt ����״̬
     */
    public void setZazt(String zazt) {
        this.zazt = zazt;
    }

    /**
     * @return sl_bjslh
     */
    public String getSlBjslh() {
        return slBjslh;
    }

    /**
     * @param slBjslh
     */
    public void setSlBjslh(String slBjslh) {
        this.slBjslh = slBjslh;
    }

    /**
     * ��ȡ�Ӿ���ʽ
     *
     * @return sl_jjfs - �Ӿ���ʽ
     */
    public String getSlJjfs() {
        return slJjfs;
    }

    /**
     * ���ýӾ���ʽ
     *
     * @param slJjfs �Ӿ���ʽ
     */
    public void setSlJjfs(String slJjfs) {
        this.slJjfs = slJjfs;
    }

    /**
     * ��ȡ��������
     *
     * @return ajlx - ��������
     */
    public String getAjlx() {
        return ajlx;
    }

    /**
     * ���ð�������
     *
     * @param ajlx ��������
     */
    public void setAjlx(String ajlx) {
        this.ajlx = ajlx;
    }

    /**
     * ��ȡ����
     *
     * @return ab - ����
     */
    public String getAb() {
        return ab;
    }

    /**
     * ���ð���
     *
     * @param ab ����
     */
    public void setAb(String ab) {
        this.ab = ab;
    }

    /**
     * ��ȡר����ʶ
     *
     * @return zabz - ר����ʶ
     */
    public String getZabz() {
        return zabz;
    }

    /**
     * ����ר����ʶ
     *
     * @param zabz ר����ʶ
     */
    public void setZabz(String zabz) {
        this.zabz = zabz;
    }

    /**
     * ��ȡ��������
     *
     * @return ajmc - ��������
     */
    public String getAjmc() {
        return ajmc;
    }

    /**
     * ���ð�������
     *
     * @param ajmc ��������
     */
    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }

    /**
     * @return ajwh
     */
    public String getAjwh() {
        return ajwh;
    }

    /**
     * @param ajwh
     */
    public void setAjwh(String ajwh) {
        this.ajwh = ajwh;
    }

    /**
     * ��ȡ����Ӿ�ʱ��
     *
     * @return sljjsj - ����Ӿ�ʱ��
     */
    public Date getSljjsj() {
        return sljjsj;
    }

    /**
     * ��������Ӿ�ʱ��
     *
     * @param sljjsj ����Ӿ�ʱ��
     */
    public void setSljjsj(Date sljjsj) {
        this.sljjsj = sljjsj;
    }

    /**
     * ��ȡ����������
     *
     * @return slfxrq - ����������
     */
    public Date getSlfxrq() {
        return slfxrq;
    }

    /**
     * ��������������
     *
     * @param slfxrq ����������
     */
    public void setSlfxrq(Date slfxrq) {
        this.slfxrq = slfxrq;
    }

    /**
     * @return fasjcz
     */
    public Date getFasjcz() {
        return fasjcz;
    }

    /**
     * @param fasjcz
     */
    public void setFasjcz(Date fasjcz) {
        this.fasjcz = fasjcz;
    }

    /**
     * @return fasjzz
     */
    public Date getFasjzz() {
        return fasjzz;
    }

    /**
     * @param fasjzz
     */
    public void setFasjzz(Date fasjzz) {
        this.fasjzz = fasjzz;
    }

    /**
     * ��ȡ�����ص�����
     *
     * @return fadd_qx - �����ص�����
     */
    public String getFaddQx() {
        return faddQx;
    }

    /**
     * ���÷����ص�����
     *
     * @param faddQx �����ص�����
     */
    public void setFaddQx(String faddQx) {
        this.faddQx = faddQx;
    }

    /**
     * ��ȡ�����ص�ֵ�
     *
     * @return fadd_jd - �����ص�ֵ�
     */
    public String getFaddJd() {
        return faddJd;
    }

    /**
     * ���÷����ص�ֵ�
     *
     * @param faddJd �����ص�ֵ�
     */
    public void setFaddJd(String faddJd) {
        this.faddJd = faddJd;
    }

    /**
     * ��ȡ������������
     *
     * @return ajssjq - ������������
     */
    public String getAjssjq() {
        return ajssjq;
    }

    /**
     * ���ð�����������
     *
     * @param ajssjq ������������
     */
    public void setAjssjq(String ajssjq) {
        this.ajssjq = ajssjq;
    }

    /**
     * ��ȡ�����ص�
     *
     * @return fadd - �����ص�
     */
    public String getFadd() {
        return fadd;
    }

    /**
     * ���÷����ص�
     *
     * @param fadd �����ص�
     */
    public void setFadd(String fadd) {
        this.fadd = fadd;
    }

    /**
     * ��ȡ��������
     *
     * @return sssq - ��������
     */
    public String getSssq() {
        return sssq;
    }

    /**
     * ������������
     *
     * @param sssq ��������
     */
    public void setSssq(String sssq) {
        this.sssq = sssq;
    }

    /**
     * ��ȡ��������
     *
     * @return fady - ��������
     */
    public String getFady() {
        return fady;
    }

    /**
     * ���÷�������
     *
     * @param fady ��������
     */
    public void setFady(String fady) {
        this.fady = fady;
    }

    /**
     * ��ȡ��������
     *
     * @return slfacs - ��������
     */
    public String getSlfacs() {
        return slfacs;
    }

    /**
     * ���÷�������
     *
     * @param slfacs ��������
     */
    public void setSlfacs(String slfacs) {
        this.slfacs = slfacs;
    }

    /**
     * ��ȡ������ʽ
     *
     * @return fxxs - ������ʽ
     */
    public String getFxxs() {
        return fxxs;
    }

    /**
     * ���÷�����ʽ
     *
     * @param fxxs ������ʽ
     */
    public void setFxxs(String fxxs) {
        this.fxxs = fxxs;
    }

    /**
     * ��ȡ����Σ���̶�
     *
     * @return ajwhcd - ����Σ���̶�
     */
    public String getAjwhcd() {
        return ajwhcd;
    }

    /**
     * ���ð���Σ���̶�
     *
     * @param ajwhcd ����Σ���̶�
     */
    public void setAjwhcd(String ajwhcd) {
        this.ajwhcd = ajwhcd;
    }

    /**
     * @return blyy
     */
    public String getBlyy() {
        return blyy;
    }

    /**
     * @param blyy
     */
    public void setBlyy(String blyy) {
        this.blyy = blyy;
    }

    /**
     * @return zyaq
     */
    public String getZyaq() {
        return zyaq;
    }

    /**
     * @param zyaq
     */
    public void setZyaq(String zyaq) {
        this.zyaq = zyaq;
    }

    /**
     * @return xzsj
     */
    public String getXzsj() {
        return xzsj;
    }

    /**
     * @param xzsj
     */
    public void setXzsj(String xzsj) {
        this.xzsj = xzsj;
    }

    /**
     * @return xzcs
     */
    public String getXzcs() {
        return xzcs;
    }

    /**
     * @param xzcs
     */
    public void setXzcs(String xzcs) {
        this.xzcs = xzcs;
    }

    /**
     * @return xzdx
     */
    public String getXzdx() {
        return xzdx;
    }

    /**
     * @param xzdx
     */
    public void setXzdx(String xzdx) {
        this.xzdx = xzdx;
    }

    /**
     * @return xzwp
     */
    public String getXzwp() {
        return xzwp;
    }

    /**
     * @param xzwp
     */
    public void setXzwp(String xzwp) {
        this.xzwp = xzwp;
    }

    /**
     * @return zagj
     */
    public String getZagj() {
        return zagj;
    }

    /**
     * @param zagj
     */
    public void setZagj(String zagj) {
        this.zagj = zagj;
    }

    /**
     * @return xzbw
     */
    public String getXzbw() {
        return xzbw;
    }

    /**
     * @param xzbw
     */
    public void setXzbw(String xzbw) {
        this.xzbw = xzbw;
    }

    /**
     * @return zars
     */
    public String getZars() {
        return zars;
    }

    /**
     * @param zars
     */
    public void setZars(String zars) {
        this.zars = zars;
    }

    /**
     * @return sdtd
     */
    public String getSdtd() {
        return sdtd;
    }

    /**
     * @param sdtd
     */
    public void setSdtd(String sdtd) {
        this.sdtd = sdtd;
    }

    /**
     * @return swrs
     */
    public String getSwrs() {
        return swrs;
    }

    /**
     * @param swrs
     */
    public void setSwrs(String swrs) {
        this.swrs = swrs;
    }

    /**
     * @return ssrs
     */
    public String getSsrs() {
        return ssrs;
    }

    /**
     * @param ssrs
     */
    public void setSsrs(String ssrs) {
        this.ssrs = ssrs;
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
     * @return lasj
     */
    public Date getLasj() {
        return lasj;
    }

    /**
     * @param lasj
     */
    public void setLasj(Date lasj) {
        this.lasj = lasj;
    }

    /**
     * @return pasj
     */
    public Date getPasj() {
        return pasj;
    }

    /**
     * @param pasj
     */
    public void setPasj(Date pasj) {
        this.pasj = pasj;
    }

    /**
     * @return ja_jasj
     */
    public Date getJaJasj() {
        return jaJasj;
    }

    /**
     * @param jaJasj
     */
    public void setJaJasj(Date jaJasj) {
        this.jaJasj = jaJasj;
    }

    /**
     * @return xa_xasj
     */
    public Date getXaXasj() {
        return xaXasj;
    }

    /**
     * @param xaXasj
     */
    public void setXaXasj(Date xaXasj) {
        this.xaXasj = xaXasj;
    }

    /**
     * @return yssj
     */
    public Date getYssj() {
        return yssj;
    }

    /**
     * @param yssj
     */
    public void setYssj(Date yssj) {
        this.yssj = yssj;
    }

    /**
     * @return sljjdw
     */
    public String getSljjdw() {
        return sljjdw;
    }

    /**
     * @param sljjdw
     */
    public void setSljjdw(String sljjdw) {
        this.sljjdw = sljjdw;
    }

    /**
     * @return sl_lrr
     */
    public String getSlLrr() {
        return slLrr;
    }

    /**
     * @param slLrr
     */
    public void setSlLrr(String slLrr) {
        this.slLrr = slLrr;
    }

    /**
     * @return sl_lrsj
     */
    public Date getSlLrsj() {
        return slLrsj;
    }

    /**
     * @param slLrsj
     */
    public void setSlLrsj(Date slLrsj) {
        this.slLrsj = slLrsj;
    }

    /**
     * @return sljjry
     */
    public String getSljjry() {
        return sljjry;
    }

    /**
     * @param sljjry
     */
    public void setSljjry(String sljjry) {
        this.sljjry = sljjry;
    }

    /**
     * @return sl_slrxm
     */
    public String getSlSlrxm() {
        return slSlrxm;
    }

    /**
     * @param slSlrxm
     */
    public void setSlSlrxm(String slSlrxm) {
        this.slSlrxm = slSlrxm;
    }

    /**
     * @return sl_slsj
     */
    public Date getSlSlsj() {
        return slSlsj;
    }

    /**
     * @param slSlsj
     */
    public void setSlSlsj(Date slSlsj) {
        this.slSlsj = slSlsj;
    }

    /**
     * @return slfaqh
     */
    public String getSlfaqh() {
        return slfaqh;
    }

    /**
     * @param slfaqh
     */
    public void setSlfaqh(String slfaqh) {
        this.slfaqh = slfaqh;
    }

    /**
     * @return ladw
     */
    public String getLadw() {
        return ladw;
    }

    /**
     * @param ladw
     */
    public void setLadw(String ladw) {
        this.ladw = ladw;
    }

    /**
     * @return ajlary
     */
    public String getAjlary() {
        return ajlary;
    }

    /**
     * @param ajlary
     */
    public void setAjlary(String ajlary) {
        this.ajlary = ajlary;
    }

    /**
     * @return zbdw
     */
    public String getZbdw() {
        return zbdw;
    }

    /**
     * @param zbdw
     */
    public void setZbdw(String zbdw) {
        this.zbdw = zbdw;
    }

    /**
     * @return ajzbry
     */
    public String getAjzbry() {
        return ajzbry;
    }

    /**
     * @param ajzbry
     */
    public void setAjzbry(String ajzbry) {
        this.ajzbry = ajzbry;
    }

    /**
     * @return ajxbry
     */
    public String getAjxbry() {
        return ajxbry;
    }

    /**
     * @param ajxbry
     */
    public void setAjxbry(String ajxbry) {
        this.ajxbry = ajxbry;
    }

    /**
     * @return ajbarp
     */
    public String getAjbarp() {
        return ajbarp;
    }

    /**
     * @param ajbarp
     */
    public void setAjbarp(String ajbarp) {
        this.ajbarp = ajbarp;
    }

    /**
     * @return la_lrr
     */
    public String getLaLrr() {
        return laLrr;
    }

    /**
     * @param laLrr
     */
    public void setLaLrr(String laLrr) {
        this.laLrr = laLrr;
    }

    /**
     * @return la_lrsj
     */
    public Date getLaLrsj() {
        return laLrsj;
    }

    /**
     * @param laLrsj
     */
    public void setLaLrsj(Date laLrsj) {
        this.laLrsj = laLrsj;
    }

    /**
     * @return la_pzr
     */
    public String getLaPzr() {
        return laPzr;
    }

    /**
     * @param laPzr
     */
    public void setLaPzr(String laPzr) {
        this.laPzr = laPzr;
    }

    /**
     * @return la_pzsj
     */
    public Date getLaPzsj() {
        return laPzsj;
    }

    /**
     * @param laPzsj
     */
    public void setLaPzsj(Date laPzsj) {
        this.laPzsj = laPzsj;
    }

    /**
     * @return la_zhxgr
     */
    public String getLaZhxgr() {
        return laZhxgr;
    }

    /**
     * @param laZhxgr
     */
    public void setLaZhxgr(String laZhxgr) {
        this.laZhxgr = laZhxgr;
    }

    /**
     * @return la_zhxgsj
     */
    public Date getLaZhxgsj() {
        return laZhxgsj;
    }

    /**
     * @param laZhxgsj
     */
    public void setLaZhxgsj(Date laZhxgsj) {
        this.laZhxgsj = laZhxgsj;
    }

    /**
     * @return la_psstate
     */
    public String getLaPsstate() {
        return laPsstate;
    }

    /**
     * @param laPsstate
     */
    public void setLaPsstate(String laPsstate) {
        this.laPsstate = laPsstate;
    }

    /**
     * @return dbxx
     */
    public String getDbxx() {
        return dbxx;
    }

    /**
     * @param dbxx
     */
    public void setDbxx(String dbxx) {
        this.dbxx = dbxx;
    }

    /**
     * @return ajly
     */
    public String getAjly() {
        return ajly;
    }

    /**
     * @param ajly
     */
    public void setAjly(String ajly) {
        this.ajly = ajly;
    }

    /**
     * @return bz
     */
    public String getBz() {
        return bz;
    }

    /**
     * @param bz
     */
    public void setBz(String bz) {
        this.bz = bz;
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
     * @return ysdw
     */
    public String getYsdw() {
        return ysdw;
    }

    /**
     * @param ysdw
     */
    public void setYsdw(String ysdw) {
        this.ysdw = ysdw;
    }

    /**
     * @return yscbr
     */
    public String getYscbr() {
        return yscbr;
    }

    /**
     * @param yscbr
     */
    public void setYscbr(String yscbr) {
        this.yscbr = yscbr;
    }

    /**
     * @return ysdwdh
     */
    public String getYsdwdh() {
        return ysdwdh;
    }

    /**
     * @param ysdwdh
     */
    public void setYsdwdh(String ysdwdh) {
        this.ysdwdh = ysdwdh;
    }

    /**
     * @return yssasj
     */
    public Date getYssasj() {
        return yssasj;
    }

    /**
     * @param yssasj
     */
    public void setYssasj(Date yssasj) {
        this.yssasj = yssasj;
    }

    /**
     * @return ystcsj
     */
    public Date getYstcsj() {
        return ystcsj;
    }

    /**
     * @param ystcsj
     */
    public void setYstcsj(Date ystcsj) {
        this.ystcsj = ystcsj;
    }

    /**
     * @return whssjz
     */
    public String getWhssjz() {
        return whssjz;
    }

    /**
     * @param whssjz
     */
    public void setWhssjz(String whssjz) {
        this.whssjz = whssjz;
    }

    /**
     * @return dbjb
     */
    public String getDbjb() {
        return dbjb;
    }

    /**
     * @param dbjb
     */
    public void setDbjb(String dbjb) {
        this.dbjb = dbjb;
    }

    /**
     * @return jtajly
     */
    public String getJtajly() {
        return jtajly;
    }

    /**
     * @param jtajly
     */
    public void setJtajly(String jtajly) {
        this.jtajly = jtajly;
    }

    /**
     * @return fzztlx
     */
    public String getFzztlx() {
        return fzztlx;
    }

    /**
     * @param fzztlx
     */
    public void setFzztlx(String fzztlx) {
        this.fzztlx = fzztlx;
    }

    /**
     * @return sfsw
     */
    public String getSfsw() {
        return sfsw;
    }

    /**
     * @param sfsw
     */
    public void setSfsw(String sfsw) {
        this.sfsw = sfsw;
    }

    /**
     * @return sjgjdq
     */
    public String getSjgjdq() {
        return sjgjdq;
    }

    /**
     * @param sjgjdq
     */
    public void setSjgjdq(String sjgjdq) {
        this.sjgjdq = sjgjdq;
    }

    /**
     * @return yishensj
     */
    public Date getYishensj() {
        return yishensj;
    }

    /**
     * @param yishensj
     */
    public void setYishensj(Date yishensj) {
        this.yishensj = yishensj;
    }

    /**
     * @return essj
     */
    public Date getEssj() {
        return essj;
    }

    /**
     * @param essj
     */
    public void setEssj(Date essj) {
        this.essj = essj;
    }

    /**
     * @return ysdw_new
     */
    public String getYsdwNew() {
        return ysdwNew;
    }

    /**
     * @param ysdwNew
     */
    public void setYsdwNew(String ysdwNew) {
        this.ysdwNew = ysdwNew;
    }

    /**
     * @return ysdwdh_new
     */
    public String getYsdwdhNew() {
        return ysdwdhNew;
    }

    /**
     * @param ysdwdhNew
     */
    public void setYsdwdhNew(String ysdwdhNew) {
        this.ysdwdhNew = ysdwdhNew;
    }

    /**
     * @return yscbr_new
     */
    public String getYscbrNew() {
        return yscbrNew;
    }

    /**
     * @param yscbrNew
     */
    public void setYscbrNew(String yscbrNew) {
        this.yscbrNew = yscbrNew;
    }

    /**
     * @return yssj_new
     */
    public Date getYssjNew() {
        return yssjNew;
    }

    /**
     * @param yssjNew
     */
    public void setYssjNew(Date yssjNew) {
        this.yssjNew = yssjNew;
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
     * @return jzbh
     */
    public String getJzbh() {
        return jzbh;
    }

    /**
     * @param jzbh
     */
    public void setJzbh(String jzbh) {
        this.jzbh = jzbh;
    }

    /**
     * ��ȡ���Ǽ�ʱ��
     *
     * @return rkdjsj - ���Ǽ�ʱ��
     */
    public Date getRkdjsj() {
        return rkdjsj;
    }

    /**
     * �������Ǽ�ʱ��
     *
     * @param rkdjsj ���Ǽ�ʱ��
     */
    public void setRkdjsj(Date rkdjsj) {
        this.rkdjsj = rkdjsj;
    }

    /**
     * ��ȡ������ʱ��
     *
     * @return rkgxsj - ������ʱ��
     */
    public Date getRkgxsj() {
        return rkgxsj;
    }

    /**
     * ����������ʱ��
     *
     * @param rkgxsj ������ʱ��
     */
    public void setRkgxsj(Date rkgxsj) {
        this.rkgxsj = rkgxsj;
    }

    /**
     * ��ȡ��ע˵��
     *
     * @return smbz - ��ע˵��
     */
    public String getSmbz() {
        return smbz;
    }

    /**
     * ���ñ�ע˵��
     *
     * @param smbz ��ע˵��
     */
    public void setSmbz(String smbz) {
        this.smbz = smbz;
    }

    /**
     * ��ȡ¼����Ա
     *
     * @return lrry - ¼����Ա
     */
    public String getLrry() {
        return lrry;
    }

    /**
     * ����¼����Ա
     *
     * @param lrry ¼����Ա
     */
    public void setLrry(String lrry) {
        this.lrry = lrry;
    }

    /**
     * ��ȡ¼��ʱ��
     *
     * @return lrsj - ¼��ʱ��
     */
    public Date getLrsj() {
        return lrsj;
    }

    /**
     * ����¼��ʱ��
     *
     * @param lrsj ¼��ʱ��
     */
    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    /**
     * ��ȡ�޸���Ա
     *
     * @return xgry - �޸���Ա
     */
    public String getXgry() {
        return xgry;
    }

    /**
     * �����޸���Ա
     *
     * @param xgry �޸���Ա
     */
    public void setXgry(String xgry) {
        this.xgry = xgry;
    }

    /**
     * ��ȡ�޸���Ա
     *
     * @return xgsj - �޸���Ա
     */
    public Date getXgsj() {
        return xgsj;
    }

    /**
     * �����޸���Ա
     *
     * @param xgsj �޸���Ա
     */
    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public Integer getShzt() {
        return shzt;
    }

    public void setShzt(Integer shzt) {
        this.shzt = shzt;
    }

    public Integer getSfcba() {
        return sfcba;
    }

    public void setSfcba(Integer sfcba) {
        this.sfcba = sfcba;
    }

    public String getCbabh() {
        return cbabh;
    }

    public void setCbabh(String cbabh) {
        this.cbabh = cbabh;
    }

    public Integer getAjFrom() {
        return ajFrom;
    }

    public void setAjFrom(Integer ajFrom) {
        this.ajFrom = ajFrom;
    }

    public String getLrrymc() {
        return lrrymc;
    }

    public void setLrrymc(String lrrymc) {
        this.lrrymc = lrrymc;
    }

    public String getXgrymc() {
        return xgrymc;
    }

    public void setXgrymc(String xgrymc) {
        this.xgrymc = xgrymc;
    }

    public String getBdajstate() {
        return bdajstate;
    }

    public void setBdajstate(String bdajstate) {
        this.bdajstate = bdajstate;
    }

    public String getBdajstatebz() {
        return bdajstatebz;
    }

    public void setBdajstatebz(String bdajstatebz) {
        this.bdajstatebz = bdajstatebz;
    }

    public String getMindTop() {
        return mindTop;
    }

    public void setMindTop(String mindTop) {
        this.mindTop = mindTop;
    }

    public String getMindLeft() {
        return mindLeft;
    }

    public void setMindLeft(String mindLeft) {
        this.mindLeft = mindLeft;
    }

    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }
}
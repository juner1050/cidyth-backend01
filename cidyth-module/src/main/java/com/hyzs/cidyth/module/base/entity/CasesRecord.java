package com.hyzs.cidyth.module.base.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 案件笔录
 * @author derrick
 *
 */
@Table(name = "t_asj_bl")
public class CasesRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;

    /**
     * 警综的笔录库是sytemid对应本地库systemid字段
     * 考拉的笔录库是id对应本地库systemid字段
     */
	private String systemid;

    private String ajbh;

    @Column(name = "ser_no")
    private String serNo;

    private String name;

    /**
     * 笔录来源
     */
    private String blly;

    /**
     * 人员类型
     */
    private String rylx;

    private String type;

    @Column(name = "scen_systemid")
    private String scenSystemid;

    private String target;

    private String fj;

    private String body;

    private Date starttime;

    private Date endtime;

    private String jldd;

    @Column(name = "creator_name")
    private String creatorName;

    @Column(name = "creator_dept")
    private String creatorDept;

    private String recorder;

    @Column(name = "recorder_dept")
    private String recorderDept;

    @Column(name = "target_xm")
    private String targetXm;

    @Column(name = "target_cym")
    private String targetCym;

    @Column(name = "target_xb")
    private String targetXb;

    @Column(name = "target_csrq")
    private String targetCsrq;

    @Column(name = "target_whcd")
    private String targetWhcd;

    @Column(name = "target_mz")
    private String targetMz;

    @Column(name = "target_hjszd")
    private String targetHjszd;

    @Column(name = "target_xzz")
    private String targetXzz;

    @Column(name = "target_zjzl")
    private String targetZjzl;

    @Column(name = "target_zjhm")
    private String targetZjhm;

    @Column(name = "target_gzdw")
    private String targetGzdw;

    @Column(name = "target_lxdh")
    private String targetLxdh;

    @Column(name = "target_arrivaltime")
    private String targetArrivaltime;

    @Column(name = "target_lefttime")
    private String targetLefttime;

    @Column(name = "target_bm")
    private String targetBm;

    @Column(name = "target_ywm")
    private String targetYwm;

    @Column(name = "target_gj")
    private String targetGj;

    @Column(name = "target_ch")
    private String targetCh;

    @Column(name = "target_jg")
    private String targetJg;

    @Column(name = "target_zzmm")
    private String targetZzmm;

    @Column(name = "target_jtqk")
    private String targetJtqk;

    @Column(name = "target_shjl")
    private String targetShjl;

    @Column(name = "target_sfsgcf")
    private String targetSfsgcf;

    @Column(name = "target_sflyrs")
    private String targetSflyrs;

    @Column(name = "target_sfpbfyry")
    private String targetSfpbfyry;

    @Column(name = "trans_xm")
    private String transXm;

    @Column(name = "trans_zy")
    private String transZy;

    @Column(name = "trans_gzdw")
    private String transGzdw;

    @Column(name = "trans_xzz")
    private String transXzz;

    private String kcdd;

    private String jcz;

    private String kcryms;

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

    private String reservation31;

    private String reservation32;

    private String reservation33;

    private String reservation34;

    private String reservation35;

    private String reservation36;

    private String reservation37;

    private String reservation38;

    private String reservation39;

    private String reservation40;

    private String departmentcode;

    private String creator;

    private String createdtime;

    private String lastupdatedby;

    private String lastupdatedtime;

    private String uploadflag;

    private String downloadflag;

    private String deleteflag;

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
     * @return ajbh
     */
    public String getAjbh() {
        return ajbh;
    }

    /**
     * @param ajbh
     */
    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

    /**
     * @return ser_no
     */
    public String getSerNo() {
        return serNo;
    }

    /**
     * @param serNo
     */
    public void setSerNo(String serNo) {
        this.serNo = serNo;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return scen_systemid
     */
    public String getScenSystemid() {
        return scenSystemid;
    }

    /**
     * @param scenSystemid
     */
    public void setScenSystemid(String scenSystemid) {
        this.scenSystemid = scenSystemid;
    }

    /**
     * @return target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * @return fj
     */
    public String getFj() {
        return fj;
    }

    /**
     * @param fj
     */
    public void setFj(String fj) {
        this.fj = fj;
    }

    /**
     * @return body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return starttime
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * @param starttime
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * @return endtime
     */
    public Date getEndtime() {
        return endtime;
    }

    /**
     * @param endtime
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * @return jldd
     */
    public String getJldd() {
        return jldd;
    }

    /**
     * @param jldd
     */
    public void setJldd(String jldd) {
        this.jldd = jldd;
    }

    /**
     * @return creator_name
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * @param creatorName
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * @return creator_dept
     */
    public String getCreatorDept() {
        return creatorDept;
    }

    /**
     * @param creatorDept
     */
    public void setCreatorDept(String creatorDept) {
        this.creatorDept = creatorDept;
    }

    /**
     * @return recorder
     */
    public String getRecorder() {
        return recorder;
    }

    /**
     * @param recorder
     */
    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    /**
     * @return recorder_dept
     */
    public String getRecorderDept() {
        return recorderDept;
    }

    /**
     * @param recorderDept
     */
    public void setRecorderDept(String recorderDept) {
        this.recorderDept = recorderDept;
    }

    /**
     * @return target_xm
     */
    public String getTargetXm() {
        return targetXm;
    }

    /**
     * @param targetXm
     */
    public void setTargetXm(String targetXm) {
        this.targetXm = targetXm;
    }

    /**
     * @return target_cym
     */
    public String getTargetCym() {
        return targetCym;
    }

    /**
     * @param targetCym
     */
    public void setTargetCym(String targetCym) {
        this.targetCym = targetCym;
    }

    /**
     * @return target_xb
     */
    public String getTargetXb() {
        return targetXb;
    }

    /**
     * @param targetXb
     */
    public void setTargetXb(String targetXb) {
        this.targetXb = targetXb;
    }

    /**
     * @return target_csrq
     */
    public String getTargetCsrq() {
        return targetCsrq;
    }

    /**
     * @param targetCsrq
     */
    public void setTargetCsrq(String targetCsrq) {
        this.targetCsrq = targetCsrq;
    }

    /**
     * @return target_whcd
     */
    public String getTargetWhcd() {
        return targetWhcd;
    }

    /**
     * @param targetWhcd
     */
    public void setTargetWhcd(String targetWhcd) {
        this.targetWhcd = targetWhcd;
    }

    /**
     * @return target_mz
     */
    public String getTargetMz() {
        return targetMz;
    }

    /**
     * @param targetMz
     */
    public void setTargetMz(String targetMz) {
        this.targetMz = targetMz;
    }

    /**
     * @return target_hjszd
     */
    public String getTargetHjszd() {
        return targetHjszd;
    }

    /**
     * @param targetHjszd
     */
    public void setTargetHjszd(String targetHjszd) {
        this.targetHjszd = targetHjszd;
    }

    /**
     * @return target_xzz
     */
    public String getTargetXzz() {
        return targetXzz;
    }

    /**
     * @param targetXzz
     */
    public void setTargetXzz(String targetXzz) {
        this.targetXzz = targetXzz;
    }

    /**
     * @return target_zjzl
     */
    public String getTargetZjzl() {
        return targetZjzl;
    }

    /**
     * @param targetZjzl
     */
    public void setTargetZjzl(String targetZjzl) {
        this.targetZjzl = targetZjzl;
    }

    /**
     * @return target_zjhm
     */
    public String getTargetZjhm() {
        return targetZjhm;
    }

    /**
     * @param targetZjhm
     */
    public void setTargetZjhm(String targetZjhm) {
        this.targetZjhm = targetZjhm;
    }

    /**
     * @return target_gzdw
     */
    public String getTargetGzdw() {
        return targetGzdw;
    }

    /**
     * @param targetGzdw
     */
    public void setTargetGzdw(String targetGzdw) {
        this.targetGzdw = targetGzdw;
    }

    /**
     * @return target_lxdh
     */
    public String getTargetLxdh() {
        return targetLxdh;
    }

    /**
     * @param targetLxdh
     */
    public void setTargetLxdh(String targetLxdh) {
        this.targetLxdh = targetLxdh;
    }

    /**
     * @return target_arrivaltime
     */
    public String getTargetArrivaltime() {
        return targetArrivaltime;
    }

    /**
     * @param targetArrivaltime
     */
    public void setTargetArrivaltime(String targetArrivaltime) {
        this.targetArrivaltime = targetArrivaltime;
    }

    /**
     * @return target_lefttime
     */
    public String getTargetLefttime() {
        return targetLefttime;
    }

    /**
     * @param targetLefttime
     */
    public void setTargetLefttime(String targetLefttime) {
        this.targetLefttime = targetLefttime;
    }

    /**
     * @return target_bm
     */
    public String getTargetBm() {
        return targetBm;
    }

    /**
     * @param targetBm
     */
    public void setTargetBm(String targetBm) {
        this.targetBm = targetBm;
    }

    /**
     * @return target_ywm
     */
    public String getTargetYwm() {
        return targetYwm;
    }

    /**
     * @param targetYwm
     */
    public void setTargetYwm(String targetYwm) {
        this.targetYwm = targetYwm;
    }

    /**
     * @return target_gj
     */
    public String getTargetGj() {
        return targetGj;
    }

    /**
     * @param targetGj
     */
    public void setTargetGj(String targetGj) {
        this.targetGj = targetGj;
    }

    /**
     * @return target_ch
     */
    public String getTargetCh() {
        return targetCh;
    }

    /**
     * @param targetCh
     */
    public void setTargetCh(String targetCh) {
        this.targetCh = targetCh;
    }

    /**
     * @return target_jg
     */
    public String getTargetJg() {
        return targetJg;
    }

    /**
     * @param targetJg
     */
    public void setTargetJg(String targetJg) {
        this.targetJg = targetJg;
    }

    /**
     * @return target_zzmm
     */
    public String getTargetZzmm() {
        return targetZzmm;
    }

    /**
     * @param targetZzmm
     */
    public void setTargetZzmm(String targetZzmm) {
        this.targetZzmm = targetZzmm;
    }

    /**
     * @return target_jtqk
     */
    public String getTargetJtqk() {
        return targetJtqk;
    }

    /**
     * @param targetJtqk
     */
    public void setTargetJtqk(String targetJtqk) {
        this.targetJtqk = targetJtqk;
    }

    /**
     * @return target_shjl
     */
    public String getTargetShjl() {
        return targetShjl;
    }

    /**
     * @param targetShjl
     */
    public void setTargetShjl(String targetShjl) {
        this.targetShjl = targetShjl;
    }

    /**
     * @return target_sfsgcf
     */
    public String getTargetSfsgcf() {
        return targetSfsgcf;
    }

    /**
     * @param targetSfsgcf
     */
    public void setTargetSfsgcf(String targetSfsgcf) {
        this.targetSfsgcf = targetSfsgcf;
    }

    /**
     * @return target_sflyrs
     */
    public String getTargetSflyrs() {
        return targetSflyrs;
    }

    /**
     * @param targetSflyrs
     */
    public void setTargetSflyrs(String targetSflyrs) {
        this.targetSflyrs = targetSflyrs;
    }

    /**
     * @return target_sfpbfyry
     */
    public String getTargetSfpbfyry() {
        return targetSfpbfyry;
    }

    /**
     * @param targetSfpbfyry
     */
    public void setTargetSfpbfyry(String targetSfpbfyry) {
        this.targetSfpbfyry = targetSfpbfyry;
    }

    /**
     * @return trans_xm
     */
    public String getTransXm() {
        return transXm;
    }

    /**
     * @param transXm
     */
    public void setTransXm(String transXm) {
        this.transXm = transXm;
    }

    /**
     * @return trans_zy
     */
    public String getTransZy() {
        return transZy;
    }

    /**
     * @param transZy
     */
    public void setTransZy(String transZy) {
        this.transZy = transZy;
    }

    /**
     * @return trans_gzdw
     */
    public String getTransGzdw() {
        return transGzdw;
    }

    /**
     * @param transGzdw
     */
    public void setTransGzdw(String transGzdw) {
        this.transGzdw = transGzdw;
    }

    /**
     * @return trans_xzz
     */
    public String getTransXzz() {
        return transXzz;
    }

    /**
     * @param transXzz
     */
    public void setTransXzz(String transXzz) {
        this.transXzz = transXzz;
    }

    /**
     * @return kcdd
     */
    public String getKcdd() {
        return kcdd;
    }

    /**
     * @param kcdd
     */
    public void setKcdd(String kcdd) {
        this.kcdd = kcdd;
    }

    /**
     * @return jcz
     */
    public String getJcz() {
        return jcz;
    }

    /**
     * @param jcz
     */
    public void setJcz(String jcz) {
        this.jcz = jcz;
    }

    /**
     * @return kcryms
     */
    public String getKcryms() {
        return kcryms;
    }

    /**
     * @param kcryms
     */
    public void setKcryms(String kcryms) {
        this.kcryms = kcryms;
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
     * @return reservation37
     */
    public String getReservation37() {
        return reservation37;
    }

    /**
     * @param reservation37
     */
    public void setReservation37(String reservation37) {
        this.reservation37 = reservation37;
    }

    /**
     * @return reservation38
     */
    public String getReservation38() {
        return reservation38;
    }

    /**
     * @param reservation38
     */
    public void setReservation38(String reservation38) {
        this.reservation38 = reservation38;
    }

    /**
     * @return reservation39
     */
    public String getReservation39() {
        return reservation39;
    }

    /**
     * @param reservation39
     */
    public void setReservation39(String reservation39) {
        this.reservation39 = reservation39;
    }

    /**
     * @return reservation40
     */
    public String getReservation40() {
        return reservation40;
    }

    /**
     * @param reservation40
     */
    public void setReservation40(String reservation40) {
        this.reservation40 = reservation40;
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

    public String getBlly() {
        return blly;
    }

    public void setBlly(String blly) {
        this.blly = blly;
    }

    public String getRylx() {
        return rylx;
    }

    public void setRylx(String rylx) {
        this.rylx = rylx;
    }

    public String getSystemid() {
        return systemid;
    }

    public void setSystemid(String systemid) {
        this.systemid = systemid;
    }
}
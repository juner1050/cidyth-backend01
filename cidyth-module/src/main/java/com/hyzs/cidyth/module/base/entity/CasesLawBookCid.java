package com.hyzs.cidyth.module.base.entity;

import javax.persistence.*;

@Table(name = "v_asj_ws")
public class CasesLawBookCid {

    /**
     * 文书唯一号
     */
    private String writid;

    /**
     * 案件编号
     */
    private String ajbh;

    /**
     * 案件名称
     */
    private String ajmc;

    /**
     * 案件文号
     */
    private String ajwh;

    /**
     * 案件类型
     */
    private String ajlx;

    /**
     * 未知
     */
    private String writefor;

    /**
     * 对象编号
     */
    private String dxbh;

    /**
     * 文书类型（字典值）
     */
    private String wscode;

    /**
     * 文书名称
     */
    private String wsmc;

    /**
     * 文书word
     */
    private String wordofwrit;

    /**
     * 文书年份
     */
    private String yearofwrit;

    /**
     * 文书标识
     */
    private String markofwrit;

    /**
     * 文书word2
     */
    private String wordofwrit2;

    /**
     * 文书年份2
     */
    private String yearofwrit2;

    /**
     * 文书标识2
     */
    private String markofwrit2;

    /**
     * 文书状态
     */
    private String state;

    /**
     * 文书id
     */
    private String superwritid;

    /**
     * 开始发生时间
     */
    private String beginhappentime;

    /**
     * 结束发生时间
     */
    private String endhappentime;

    /**
     * 文书内容
     */
    private String transact;

    /**
     * 提交记录
     */
    private String submitnote;

    /**
     * 法律名称
     */
    private String lawname;

    /**
     * 法律子项（外检值）
     */
    private String itemoflaw;

    /**
     * 法律概要
     */
    private String analysisinlaw;

    /**
     * 文书返回日期
     */
    private String returnwritdate;

    /**
     * 未知
     */
    private String bookoffile;

    /**
     * 未知
     */
    private String pageoffile;

    /**
     * 未知
     */
    private String pageofreinforce;

    /**
     * 结语
     */
    private String epilog;

    /**
     * 主要审判侦查员
     */
    private String transactprimarytout;

    /**
     * 审判单位
     */
    private String transactunit;

    /**
     * ba单位联系电话
     */
    private String badwlxdh;

    /**
     * 提交时间
     */
    private String submittime;

    /**
     * 确认记录（字典值）
     */
    private String confirmnote;

    /**
     * 文书当前环节
     */
    private String wsdqhj;

    /**
     * 文书当前状态
     */
    private String wsdqzt;

    /**
     * 确认人
     */
    private String confirmbyperson;

    /**
     * 确认单位
     */
    private String confirmbyunit;

    /**
     * 确认时间
     */
    private String confirmtime;

    /**
     * 写入时间
     */
    private String writetime;

    /**
     * 写入人员
     */
    private String writeby;

    /**
     * 接收单位
     */
    private String receiveunit;

    /**
     * 主体
     */
    private String tosomebody;

    /**
     * 接收人员
     */
    private String receiveby;

    /**
     * 接收时间
     */
    private String receivetime;

    /**
     * 旧简要理由
     */
    private String oldbriefreason;

    /**
     * 简要理由
     */
    private String briefreason;

    /**
     * 详细理由
     */
    private String detailreason;

    /**
     * 单位名称
     */
    private String dwmc;

    /**
     * 详细地址描述
     */
    private String xxdzms;

    /**
     * 法人代表 
     */
    private String frdb;

    /**
     * 姓名
     */
    private String zwxm;

    /**
     * 未知
     */
    private String cym;

    /**
     * 未知
     */
    private String byname;

    /**
     * 性别
     */
    private String sex;

    /**
     * 英文姓
     */
    private String ywx;

    /**
     * 英文名
     */
    private String ywm;

    /**
     * 民族
     */
    private String nation;

    /**
     * 国籍
     */
    private String nationlity;

    /**
     * 籍贯
     */
    private String nativeplace;

    /**
     * 年龄
     */
    private String age;

    /**
     * 出生开始日期
     */
    private String birthdayinbegin;

    /**
     * 出生结束日期
     */
    private String birthdayinend;

    /**
     * 证件类型
     */
    private String cardtype;

    /**
     * 证件号码
     */
    private String zjhm;

    /**
     * 婚姻状况
     */
    private String hyzk;

    /**
     * 学历
     */
    private String culture;

    /**
     * 未知
     */
    private String sf;

    /**
     * 未知
     */
    private String specialdegree;

    /**
     * 职业
     */
    private String profession;

    /**
     * 未知
     */
    private String jkzh;

    /**
     * 未知
     */
    private String politicalparty;

    /**
     * 工作地址
     */
    private String workin;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 注册地址
     */
    private String addressofregister;

    /**
     * 现住地址
     */
    private String xzdz;

    /**
     * 类型
     */
    private String resume;

    /**
     * 关系类型
     */
    private String gxlx;

    /**
     * 姓名2
     */
    private String xm2;

    /**
     * 性别2
     */
    private String xb2;

    /**
     * 年龄2
     */
    private String age2;

    /**
     * 出生日期2
     */
    private String csrq2;

    /**
     * 单位2
     */
    private String dw2;

    /**
     * 职业2
     */
    private String zy2;

    /**
     * 详细住址
     */
    private String xxzz;

    /**
     * 联系方式2
     */
    private String lxfs2;

    /**
     * 未知
     */
    private String csid;

    /**
     * 未知
     */
    private String compellentstemp;

    /**
     * 未知
     */
    private String termofcompellentstemp;

    /**
     * 未知
     */
    private String cellofterm;

    /**
     * 未知
     */
    private String beginperformtime;

    /**
     * 未知
     */
    private String endperformtime;

    /**
     * 未知
     */
    private String cpaturetime;

    /**
     * 未知
     */
    private String performby;

    /**
     * 未知
     */
    private String delaylengthofterm;

    /**
     * 未知
     */
    private String cellofdelaylength;

    /**
     * 未知
     */
    private String delaytotime;

    /**
     * 未知
     */
    private String sfzc;

    /**
     * 银行
     */
    private String bank;

    /**
     * 账号
     */
    private String account;

    /**
     * 金额
     */
    private String money;

    /**
     * 原金额
     */
    private String oldmoney;

    /**
     * 取保候审类型
     */
    private String qbhslx;

    /**
     * 未知
     */
    private String zxdxjy;

    /**
     * 未知
     */
    private String zxdxxx;

    /**
     * 未知
     */
    private String zxddmc;

    /**
     * 未知
     */
    private String zxddxz;

    /**
     * 未知
     */
    private String zddddd;

    /**
     * 未知
     */
    private String zdddsj;

    /**
     * 未知
     */
    private String tqjcsj;

    /**
     * 未知
     */
    private String assistantunit;

    /**
     * 未知
     */
    private String assistantunitinfact;

    /**
     * 未知
     */
    private String note;

    /**
     * 未知
     */
    private String departmentcode;

    /**
     * 未知
     */
    private String creator;

    /**
     * 未知
     */
    private String createdtime;

    /**
     * 未知
     */
    private String securitygrade;

    /**
     * 未知
     */
    private String reservation01;

    /**
     * 未知
     */
    private String reservation02;

    /**
     * 未知
     */
    private String reservation03;

    /**
     * 未知
     */
    private String reservation04;

    /**
     * 未知
     */
    private String reservation05;

    /**
     * 未知
     */
    private String reservation06;

    /**
     * 未知
     */
    private String reservation07;

    /**
     * 未知
     */
    private String reservation08;

    /**
     * 未知
     */
    private String reservation09;

    /**
     * 未知
     */
    private String reservation10;

    /**
     * 未知
     */
    private String lastupdatedby;

    /**
     * 未知
     */
    private String lastupdatedtime;

    /**
     * 未知
     */
    private String refreshtime;

    /**
     * 未知
     */
    private String uploadflag;

    /**
     * 未知
     */
    private String downloadflag;

    /**
     * 未知
     */
    private String deleteflag;

    /**
     * 未知
     */
    private String maxprintnum1;

    /**
     * 未知
     */
    private String maxprintnum2;

    /**
     * 未知
     */
    private String maxprintnum3;

    /**
     * 未知
     */
    private String maxprintnum4;

    /**
     * 未知
     */
    private String reservation11;

    /**
     * 未知
     */
    private String reservation12;

    /**
     * 未知
     */
    private String reservation13;

    /**
     * 未知
     */
    private String reservation14;

    /**
     * 未知
     */
    private String reservation15;

    /**
     * 未知
     */
    private String reservation16;

    /**
     * 未知
     */
    private String reservation17;

    /**
     * 未知
     */
    private String reservation18;

    /**
     * 未知
     */
    private String reservation19;

    /**
     * 未知
     */
    private String reservation20;

    /**
     * 未知
     */
    private String transactprimarytoutid;

    /**
     * 未知
     */
    private String confirmbypersonid;

    /**
     * 未知
     */
    private String confirmbyunitid;

    /**
     * 未知
     */
    private String writebyid;

    /**
     * 未知
     */
    private String reservation21;

    /**
     * 未知
     */
    private String reservation22;

    /**
     * 未知
     */
    private String reservation23;

    /**
     * 未知
     */
    private String reservation24;

    /**
     * 未知
     */
    private String reservation25;

    /**
     * 未知
     */
    private String reservation26;

    /**
     * 未知
     */
    private String reservation27;

    /**
     * 未知
     */
    private String reservation28;

    /**
     * 未知
     */
    private String reservation29;

    /**
     * 未知
     */
    private String reservation30;

    /**
     * 未知
     */
    private String reservation31;

    /**
     * 未知
     */
    private String reservation32;

    /**
     * 未知
     */
    @Column(name = "ssws1_printdate")
    private String ssws1Printdate;

    /**
     * 未知
     */
    @Column(name = "ssws2_printdate")
    private String ssws2Printdate;

    /**
     * 未知
     */
    @Column(name = "ssws3_printdate")
    private String ssws3Printdate;

    /**
     * 未知
     */
    @Column(name = "ssws4_printdate")
    private String ssws4Printdate;

    /**
     * 未知
     */
    private String wscqjb;

    /**
     * 入库登记时间
     */
    private String rkdjsj;

    /**
     * 入库修改时间
     */
    private String rkgxsj;

    /**
     * 案件概要
     */
    private String analysiscase;

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
     * 获取案件名称
     *
     * @return ajmc - 案件名称
     */
    public String getAjmc() {
        return ajmc;
    }

    /**
     * 设置案件名称
     *
     * @param ajmc 案件名称
     */
    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }

    /**
     * 获取案件文号
     *
     * @return ajwh - 案件文号
     */
    public String getAjwh() {
        return ajwh;
    }

    /**
     * 设置案件文号
     *
     * @param ajwh 案件文号
     */
    public void setAjwh(String ajwh) {
        this.ajwh = ajwh;
    }

    /**
     * 获取案件类型
     *
     * @return ajlx - 案件类型
     */
    public String getAjlx() {
        return ajlx;
    }

    /**
     * 设置案件类型
     *
     * @param ajlx 案件类型
     */
    public void setAjlx(String ajlx) {
        this.ajlx = ajlx;
    }

    /**
     * 获取未知
     *
     * @return writefor - 未知
     */
    public String getWritefor() {
        return writefor;
    }

    /**
     * 设置未知
     *
     * @param writefor 未知
     */
    public void setWritefor(String writefor) {
        this.writefor = writefor;
    }

    /**
     * 获取对象编号
     *
     * @return dxbh - 对象编号
     */
    public String getDxbh() {
        return dxbh;
    }

    /**
     * 设置对象编号
     *
     * @param dxbh 对象编号
     */
    public void setDxbh(String dxbh) {
        this.dxbh = dxbh;
    }

    /**
     * 获取文书类型（字典值）
     *
     * @return wscode - 文书类型（字典值）
     */
    public String getWscode() {
        return wscode;
    }

    /**
     * 设置文书类型（字典值）
     *
     * @param wscode 文书类型（字典值）
     */
    public void setWscode(String wscode) {
        this.wscode = wscode;
    }

    /**
     * 获取文书名称
     *
     * @return wsmc - 文书名称
     */
    public String getWsmc() {
        return wsmc;
    }

    /**
     * 设置文书名称
     *
     * @param wsmc 文书名称
     */
    public void setWsmc(String wsmc) {
        this.wsmc = wsmc;
    }

    /**
     * 获取文书word
     *
     * @return wordofwrit - 文书word
     */
    public String getWordofwrit() {
        return wordofwrit;
    }

    /**
     * 设置文书word
     *
     * @param wordofwrit 文书word
     */
    public void setWordofwrit(String wordofwrit) {
        this.wordofwrit = wordofwrit;
    }

    /**
     * 获取文书年份
     *
     * @return yearofwrit - 文书年份
     */
    public String getYearofwrit() {
        return yearofwrit;
    }

    /**
     * 设置文书年份
     *
     * @param yearofwrit 文书年份
     */
    public void setYearofwrit(String yearofwrit) {
        this.yearofwrit = yearofwrit;
    }

    /**
     * 获取文书标识
     *
     * @return markofwrit - 文书标识
     */
    public String getMarkofwrit() {
        return markofwrit;
    }

    /**
     * 设置文书标识
     *
     * @param markofwrit 文书标识
     */
    public void setMarkofwrit(String markofwrit) {
        this.markofwrit = markofwrit;
    }

    /**
     * 获取文书word2
     *
     * @return wordofwrit2 - 文书word2
     */
    public String getWordofwrit2() {
        return wordofwrit2;
    }

    /**
     * 设置文书word2
     *
     * @param wordofwrit2 文书word2
     */
    public void setWordofwrit2(String wordofwrit2) {
        this.wordofwrit2 = wordofwrit2;
    }

    /**
     * 获取文书年份2
     *
     * @return yearofwrit2 - 文书年份2
     */
    public String getYearofwrit2() {
        return yearofwrit2;
    }

    /**
     * 设置文书年份2
     *
     * @param yearofwrit2 文书年份2
     */
    public void setYearofwrit2(String yearofwrit2) {
        this.yearofwrit2 = yearofwrit2;
    }

    /**
     * 获取文书标识2
     *
     * @return markofwrit2 - 文书标识2
     */
    public String getMarkofwrit2() {
        return markofwrit2;
    }

    /**
     * 设置文书标识2
     *
     * @param markofwrit2 文书标识2
     */
    public void setMarkofwrit2(String markofwrit2) {
        this.markofwrit2 = markofwrit2;
    }

    /**
     * 获取文书状态
     *
     * @return state - 文书状态
     */
    public String getState() {
        return state;
    }

    /**
     * 设置文书状态
     *
     * @param state 文书状态
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取文书id
     *
     * @return superwritid - 文书id
     */
    public String getSuperwritid() {
        return superwritid;
    }

    /**
     * 设置文书id
     *
     * @param superwritid 文书id
     */
    public void setSuperwritid(String superwritid) {
        this.superwritid = superwritid;
    }

    /**
     * 获取开始发生时间
     *
     * @return beginhappentime - 开始发生时间
     */
    public String getBeginhappentime() {
        return beginhappentime;
    }

    /**
     * 设置开始发生时间
     *
     * @param beginhappentime 开始发生时间
     */
    public void setBeginhappentime(String beginhappentime) {
        this.beginhappentime = beginhappentime;
    }

    /**
     * 获取结束发生时间
     *
     * @return endhappentime - 结束发生时间
     */
    public String getEndhappentime() {
        return endhappentime;
    }

    /**
     * 设置结束发生时间
     *
     * @param endhappentime 结束发生时间
     */
    public void setEndhappentime(String endhappentime) {
        this.endhappentime = endhappentime;
    }

    /**
     * 获取文书内容
     *
     * @return transact - 文书内容
     */
    public String getTransact() {
        return transact;
    }

    /**
     * 设置文书内容
     *
     * @param transact 文书内容
     */
    public void setTransact(String transact) {
        this.transact = transact;
    }

    /**
     * 获取提交记录
     *
     * @return submitnote - 提交记录
     */
    public String getSubmitnote() {
        return submitnote;
    }

    /**
     * 设置提交记录
     *
     * @param submitnote 提交记录
     */
    public void setSubmitnote(String submitnote) {
        this.submitnote = submitnote;
    }

    /**
     * 获取法律名称
     *
     * @return lawname - 法律名称
     */
    public String getLawname() {
        return lawname;
    }

    /**
     * 设置法律名称
     *
     * @param lawname 法律名称
     */
    public void setLawname(String lawname) {
        this.lawname = lawname;
    }

    /**
     * 获取法律子项（外检值）
     *
     * @return itemoflaw - 法律子项（外检值）
     */
    public String getItemoflaw() {
        return itemoflaw;
    }

    /**
     * 设置法律子项（外检值）
     *
     * @param itemoflaw 法律子项（外检值）
     */
    public void setItemoflaw(String itemoflaw) {
        this.itemoflaw = itemoflaw;
    }

    /**
     * 获取法律概要
     *
     * @return analysisinlaw - 法律概要
     */
    public String getAnalysisinlaw() {
        return analysisinlaw;
    }

    /**
     * 设置法律概要
     *
     * @param analysisinlaw 法律概要
     */
    public void setAnalysisinlaw(String analysisinlaw) {
        this.analysisinlaw = analysisinlaw;
    }

    /**
     * 获取文书返回日期
     *
     * @return returnwritdate - 文书返回日期
     */
    public String getReturnwritdate() {
        return returnwritdate;
    }

    /**
     * 设置文书返回日期
     *
     * @param returnwritdate 文书返回日期
     */
    public void setReturnwritdate(String returnwritdate) {
        this.returnwritdate = returnwritdate;
    }

    /**
     * 获取未知
     *
     * @return bookoffile - 未知
     */
    public String getBookoffile() {
        return bookoffile;
    }

    /**
     * 设置未知
     *
     * @param bookoffile 未知
     */
    public void setBookoffile(String bookoffile) {
        this.bookoffile = bookoffile;
    }

    /**
     * 获取未知
     *
     * @return pageoffile - 未知
     */
    public String getPageoffile() {
        return pageoffile;
    }

    /**
     * 设置未知
     *
     * @param pageoffile 未知
     */
    public void setPageoffile(String pageoffile) {
        this.pageoffile = pageoffile;
    }

    /**
     * 获取未知
     *
     * @return pageofreinforce - 未知
     */
    public String getPageofreinforce() {
        return pageofreinforce;
    }

    /**
     * 设置未知
     *
     * @param pageofreinforce 未知
     */
    public void setPageofreinforce(String pageofreinforce) {
        this.pageofreinforce = pageofreinforce;
    }

    /**
     * 获取结语
     *
     * @return epilog - 结语
     */
    public String getEpilog() {
        return epilog;
    }

    /**
     * 设置结语
     *
     * @param epilog 结语
     */
    public void setEpilog(String epilog) {
        this.epilog = epilog;
    }

    /**
     * 获取主要审判侦查员
     *
     * @return transactprimarytout - 主要审判侦查员
     */
    public String getTransactprimarytout() {
        return transactprimarytout;
    }

    /**
     * 设置主要审判侦查员
     *
     * @param transactprimarytout 主要审判侦查员
     */
    public void setTransactprimarytout(String transactprimarytout) {
        this.transactprimarytout = transactprimarytout;
    }

    /**
     * 获取审判单位
     *
     * @return transactunit - 审判单位
     */
    public String getTransactunit() {
        return transactunit;
    }

    /**
     * 设置审判单位
     *
     * @param transactunit 审判单位
     */
    public void setTransactunit(String transactunit) {
        this.transactunit = transactunit;
    }

    /**
     * 获取ba单位联系电话
     *
     * @return badwlxdh - ba单位联系电话
     */
    public String getBadwlxdh() {
        return badwlxdh;
    }

    /**
     * 设置ba单位联系电话
     *
     * @param badwlxdh ba单位联系电话
     */
    public void setBadwlxdh(String badwlxdh) {
        this.badwlxdh = badwlxdh;
    }

    /**
     * 获取提交时间
     *
     * @return submittime - 提交时间
     */
    public String getSubmittime() {
        return submittime;
    }

    /**
     * 设置提交时间
     *
     * @param submittime 提交时间
     */
    public void setSubmittime(String submittime) {
        this.submittime = submittime;
    }

    /**
     * 获取确认记录（字典值）
     *
     * @return confirmnote - 确认记录（字典值）
     */
    public String getConfirmnote() {
        return confirmnote;
    }

    /**
     * 设置确认记录（字典值）
     *
     * @param confirmnote 确认记录（字典值）
     */
    public void setConfirmnote(String confirmnote) {
        this.confirmnote = confirmnote;
    }

    /**
     * 获取文书当前环节
     *
     * @return wsdqhj - 文书当前环节
     */
    public String getWsdqhj() {
        return wsdqhj;
    }

    /**
     * 设置文书当前环节
     *
     * @param wsdqhj 文书当前环节
     */
    public void setWsdqhj(String wsdqhj) {
        this.wsdqhj = wsdqhj;
    }

    /**
     * 获取文书当前状态
     *
     * @return wsdqzt - 文书当前状态
     */
    public String getWsdqzt() {
        return wsdqzt;
    }

    /**
     * 设置文书当前状态
     *
     * @param wsdqzt 文书当前状态
     */
    public void setWsdqzt(String wsdqzt) {
        this.wsdqzt = wsdqzt;
    }

    /**
     * 获取确认人
     *
     * @return confirmbyperson - 确认人
     */
    public String getConfirmbyperson() {
        return confirmbyperson;
    }

    /**
     * 设置确认人
     *
     * @param confirmbyperson 确认人
     */
    public void setConfirmbyperson(String confirmbyperson) {
        this.confirmbyperson = confirmbyperson;
    }

    /**
     * 获取确认单位
     *
     * @return confirmbyunit - 确认单位
     */
    public String getConfirmbyunit() {
        return confirmbyunit;
    }

    /**
     * 设置确认单位
     *
     * @param confirmbyunit 确认单位
     */
    public void setConfirmbyunit(String confirmbyunit) {
        this.confirmbyunit = confirmbyunit;
    }

    /**
     * 获取确认时间
     *
     * @return confirmtime - 确认时间
     */
    public String getConfirmtime() {
        return confirmtime;
    }

    /**
     * 设置确认时间
     *
     * @param confirmtime 确认时间
     */
    public void setConfirmtime(String confirmtime) {
        this.confirmtime = confirmtime;
    }

    /**
     * 获取写入时间
     *
     * @return writetime - 写入时间
     */
    public String getWritetime() {
        return writetime;
    }

    /**
     * 设置写入时间
     *
     * @param writetime 写入时间
     */
    public void setWritetime(String writetime) {
        this.writetime = writetime;
    }

    /**
     * 获取写入人员
     *
     * @return writeby - 写入人员
     */
    public String getWriteby() {
        return writeby;
    }

    /**
     * 设置写入人员
     *
     * @param writeby 写入人员
     */
    public void setWriteby(String writeby) {
        this.writeby = writeby;
    }

    /**
     * 获取接收单位
     *
     * @return receiveunit - 接收单位
     */
    public String getReceiveunit() {
        return receiveunit;
    }

    /**
     * 设置接收单位
     *
     * @param receiveunit 接收单位
     */
    public void setReceiveunit(String receiveunit) {
        this.receiveunit = receiveunit;
    }

    /**
     * 获取主体
     *
     * @return tosomebody - 主体
     */
    public String getTosomebody() {
        return tosomebody;
    }

    /**
     * 设置主体
     *
     * @param tosomebody 主体
     */
    public void setTosomebody(String tosomebody) {
        this.tosomebody = tosomebody;
    }

    /**
     * 获取接收人员
     *
     * @return receiveby - 接收人员
     */
    public String getReceiveby() {
        return receiveby;
    }

    /**
     * 设置接收人员
     *
     * @param receiveby 接收人员
     */
    public void setReceiveby(String receiveby) {
        this.receiveby = receiveby;
    }

    /**
     * 获取接收时间
     *
     * @return receivetime - 接收时间
     */
    public String getReceivetime() {
        return receivetime;
    }

    /**
     * 设置接收时间
     *
     * @param receivetime 接收时间
     */
    public void setReceivetime(String receivetime) {
        this.receivetime = receivetime;
    }

    /**
     * 获取旧简要理由
     *
     * @return oldbriefreason - 旧简要理由
     */
    public String getOldbriefreason() {
        return oldbriefreason;
    }

    /**
     * 设置旧简要理由
     *
     * @param oldbriefreason 旧简要理由
     */
    public void setOldbriefreason(String oldbriefreason) {
        this.oldbriefreason = oldbriefreason;
    }

    /**
     * 获取简要理由
     *
     * @return briefreason - 简要理由
     */
    public String getBriefreason() {
        return briefreason;
    }

    /**
     * 设置简要理由
     *
     * @param briefreason 简要理由
     */
    public void setBriefreason(String briefreason) {
        this.briefreason = briefreason;
    }

    /**
     * 获取详细理由
     *
     * @return detailreason - 详细理由
     */
    public String getDetailreason() {
        return detailreason;
    }

    /**
     * 设置详细理由
     *
     * @param detailreason 详细理由
     */
    public void setDetailreason(String detailreason) {
        this.detailreason = detailreason;
    }

    /**
     * 获取单位名称
     *
     * @return dwmc - 单位名称
     */
    public String getDwmc() {
        return dwmc;
    }

    /**
     * 设置单位名称
     *
     * @param dwmc 单位名称
     */
    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    /**
     * 获取详细地址描述
     *
     * @return xxdzms - 详细地址描述
     */
    public String getXxdzms() {
        return xxdzms;
    }

    /**
     * 设置详细地址描述
     *
     * @param xxdzms 详细地址描述
     */
    public void setXxdzms(String xxdzms) {
        this.xxdzms = xxdzms;
    }

    /**
     * 获取法人代表 
     *
     * @return frdb - 法人代表 
     */
    public String getFrdb() {
        return frdb;
    }

    /**
     * 设置法人代表 
     *
     * @param frdb 法人代表 
     */
    public void setFrdb(String frdb) {
        this.frdb = frdb;
    }

    /**
     * 获取姓名
     *
     * @return zwxm - 姓名
     */
    public String getZwxm() {
        return zwxm;
    }

    /**
     * 设置姓名
     *
     * @param zwxm 姓名
     */
    public void setZwxm(String zwxm) {
        this.zwxm = zwxm;
    }

    /**
     * 获取未知
     *
     * @return cym - 未知
     */
    public String getCym() {
        return cym;
    }

    /**
     * 设置未知
     *
     * @param cym 未知
     */
    public void setCym(String cym) {
        this.cym = cym;
    }

    /**
     * 获取未知
     *
     * @return byname - 未知
     */
    public String getByname() {
        return byname;
    }

    /**
     * 设置未知
     *
     * @param byname 未知
     */
    public void setByname(String byname) {
        this.byname = byname;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
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
     * 获取民族
     *
     * @return nation - 民族
     */
    public String getNation() {
        return nation;
    }

    /**
     * 设置民族
     *
     * @param nation 民族
     */
    public void setNation(String nation) {
        this.nation = nation;
    }

    /**
     * 获取国籍
     *
     * @return nationlity - 国籍
     */
    public String getNationlity() {
        return nationlity;
    }

    /**
     * 设置国籍
     *
     * @param nationlity 国籍
     */
    public void setNationlity(String nationlity) {
        this.nationlity = nationlity;
    }

    /**
     * 获取籍贯
     *
     * @return nativeplace - 籍贯
     */
    public String getNativeplace() {
        return nativeplace;
    }

    /**
     * 设置籍贯
     *
     * @param nativeplace 籍贯
     */
    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public String getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * 获取出生开始日期
     *
     * @return birthdayinbegin - 出生开始日期
     */
    public String getBirthdayinbegin() {
        return birthdayinbegin;
    }

    /**
     * 设置出生开始日期
     *
     * @param birthdayinbegin 出生开始日期
     */
    public void setBirthdayinbegin(String birthdayinbegin) {
        this.birthdayinbegin = birthdayinbegin;
    }

    /**
     * 获取出生结束日期
     *
     * @return birthdayinend - 出生结束日期
     */
    public String getBirthdayinend() {
        return birthdayinend;
    }

    /**
     * 设置出生结束日期
     *
     * @param birthdayinend 出生结束日期
     */
    public void setBirthdayinend(String birthdayinend) {
        this.birthdayinend = birthdayinend;
    }

    /**
     * 获取证件类型
     *
     * @return cardtype - 证件类型
     */
    public String getCardtype() {
        return cardtype;
    }

    /**
     * 设置证件类型
     *
     * @param cardtype 证件类型
     */
    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
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
     * 获取婚姻状况
     *
     * @return hyzk - 婚姻状况
     */
    public String getHyzk() {
        return hyzk;
    }

    /**
     * 设置婚姻状况
     *
     * @param hyzk 婚姻状况
     */
    public void setHyzk(String hyzk) {
        this.hyzk = hyzk;
    }

    /**
     * 获取学历
     *
     * @return culture - 学历
     */
    public String getCulture() {
        return culture;
    }

    /**
     * 设置学历
     *
     * @param culture 学历
     */
    public void setCulture(String culture) {
        this.culture = culture;
    }

    /**
     * 获取未知
     *
     * @return sf - 未知
     */
    public String getSf() {
        return sf;
    }

    /**
     * 设置未知
     *
     * @param sf 未知
     */
    public void setSf(String sf) {
        this.sf = sf;
    }

    /**
     * 获取未知
     *
     * @return specialdegree - 未知
     */
    public String getSpecialdegree() {
        return specialdegree;
    }

    /**
     * 设置未知
     *
     * @param specialdegree 未知
     */
    public void setSpecialdegree(String specialdegree) {
        this.specialdegree = specialdegree;
    }

    /**
     * 获取职业
     *
     * @return profession - 职业
     */
    public String getProfession() {
        return profession;
    }

    /**
     * 设置职业
     *
     * @param profession 职业
     */
    public void setProfession(String profession) {
        this.profession = profession;
    }

    /**
     * 获取未知
     *
     * @return jkzh - 未知
     */
    public String getJkzh() {
        return jkzh;
    }

    /**
     * 设置未知
     *
     * @param jkzh 未知
     */
    public void setJkzh(String jkzh) {
        this.jkzh = jkzh;
    }

    /**
     * 获取未知
     *
     * @return politicalparty - 未知
     */
    public String getPoliticalparty() {
        return politicalparty;
    }

    /**
     * 设置未知
     *
     * @param politicalparty 未知
     */
    public void setPoliticalparty(String politicalparty) {
        this.politicalparty = politicalparty;
    }

    /**
     * 获取工作地址
     *
     * @return workin - 工作地址
     */
    public String getWorkin() {
        return workin;
    }

    /**
     * 设置工作地址
     *
     * @param workin 工作地址
     */
    public void setWorkin(String workin) {
        this.workin = workin;
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
     * 获取注册地址
     *
     * @return addressofregister - 注册地址
     */
    public String getAddressofregister() {
        return addressofregister;
    }

    /**
     * 设置注册地址
     *
     * @param addressofregister 注册地址
     */
    public void setAddressofregister(String addressofregister) {
        this.addressofregister = addressofregister;
    }

    /**
     * 获取现住地址
     *
     * @return xzdz - 现住地址
     */
    public String getXzdz() {
        return xzdz;
    }

    /**
     * 设置现住地址
     *
     * @param xzdz 现住地址
     */
    public void setXzdz(String xzdz) {
        this.xzdz = xzdz;
    }

    /**
     * 获取类型
     *
     * @return resume - 类型
     */
    public String getResume() {
        return resume;
    }

    /**
     * 设置类型
     *
     * @param resume 类型
     */
    public void setResume(String resume) {
        this.resume = resume;
    }

    /**
     * 获取关系类型
     *
     * @return gxlx - 关系类型
     */
    public String getGxlx() {
        return gxlx;
    }

    /**
     * 设置关系类型
     *
     * @param gxlx 关系类型
     */
    public void setGxlx(String gxlx) {
        this.gxlx = gxlx;
    }

    /**
     * 获取姓名2
     *
     * @return xm2 - 姓名2
     */
    public String getXm2() {
        return xm2;
    }

    /**
     * 设置姓名2
     *
     * @param xm2 姓名2
     */
    public void setXm2(String xm2) {
        this.xm2 = xm2;
    }

    /**
     * 获取性别2
     *
     * @return xb2 - 性别2
     */
    public String getXb2() {
        return xb2;
    }

    /**
     * 设置性别2
     *
     * @param xb2 性别2
     */
    public void setXb2(String xb2) {
        this.xb2 = xb2;
    }

    /**
     * 获取年龄2
     *
     * @return age2 - 年龄2
     */
    public String getAge2() {
        return age2;
    }

    /**
     * 设置年龄2
     *
     * @param age2 年龄2
     */
    public void setAge2(String age2) {
        this.age2 = age2;
    }

    /**
     * 获取出生日期2
     *
     * @return csrq2 - 出生日期2
     */
    public String getCsrq2() {
        return csrq2;
    }

    /**
     * 设置出生日期2
     *
     * @param csrq2 出生日期2
     */
    public void setCsrq2(String csrq2) {
        this.csrq2 = csrq2;
    }

    /**
     * 获取单位2
     *
     * @return dw2 - 单位2
     */
    public String getDw2() {
        return dw2;
    }

    /**
     * 设置单位2
     *
     * @param dw2 单位2
     */
    public void setDw2(String dw2) {
        this.dw2 = dw2;
    }

    /**
     * 获取职业2
     *
     * @return zy2 - 职业2
     */
    public String getZy2() {
        return zy2;
    }

    /**
     * 设置职业2
     *
     * @param zy2 职业2
     */
    public void setZy2(String zy2) {
        this.zy2 = zy2;
    }

    /**
     * 获取详细住址
     *
     * @return xxzz - 详细住址
     */
    public String getXxzz() {
        return xxzz;
    }

    /**
     * 设置详细住址
     *
     * @param xxzz 详细住址
     */
    public void setXxzz(String xxzz) {
        this.xxzz = xxzz;
    }

    /**
     * 获取联系方式2
     *
     * @return lxfs2 - 联系方式2
     */
    public String getLxfs2() {
        return lxfs2;
    }

    /**
     * 设置联系方式2
     *
     * @param lxfs2 联系方式2
     */
    public void setLxfs2(String lxfs2) {
        this.lxfs2 = lxfs2;
    }

    /**
     * 获取未知
     *
     * @return csid - 未知
     */
    public String getCsid() {
        return csid;
    }

    /**
     * 设置未知
     *
     * @param csid 未知
     */
    public void setCsid(String csid) {
        this.csid = csid;
    }

    /**
     * 获取未知
     *
     * @return compellentstemp - 未知
     */
    public String getCompellentstemp() {
        return compellentstemp;
    }

    /**
     * 设置未知
     *
     * @param compellentstemp 未知
     */
    public void setCompellentstemp(String compellentstemp) {
        this.compellentstemp = compellentstemp;
    }

    /**
     * 获取未知
     *
     * @return termofcompellentstemp - 未知
     */
    public String getTermofcompellentstemp() {
        return termofcompellentstemp;
    }

    /**
     * 设置未知
     *
     * @param termofcompellentstemp 未知
     */
    public void setTermofcompellentstemp(String termofcompellentstemp) {
        this.termofcompellentstemp = termofcompellentstemp;
    }

    /**
     * 获取未知
     *
     * @return cellofterm - 未知
     */
    public String getCellofterm() {
        return cellofterm;
    }

    /**
     * 设置未知
     *
     * @param cellofterm 未知
     */
    public void setCellofterm(String cellofterm) {
        this.cellofterm = cellofterm;
    }

    /**
     * 获取未知
     *
     * @return beginperformtime - 未知
     */
    public String getBeginperformtime() {
        return beginperformtime;
    }

    /**
     * 设置未知
     *
     * @param beginperformtime 未知
     */
    public void setBeginperformtime(String beginperformtime) {
        this.beginperformtime = beginperformtime;
    }

    /**
     * 获取未知
     *
     * @return endperformtime - 未知
     */
    public String getEndperformtime() {
        return endperformtime;
    }

    /**
     * 设置未知
     *
     * @param endperformtime 未知
     */
    public void setEndperformtime(String endperformtime) {
        this.endperformtime = endperformtime;
    }

    /**
     * 获取未知
     *
     * @return cpaturetime - 未知
     */
    public String getCpaturetime() {
        return cpaturetime;
    }

    /**
     * 设置未知
     *
     * @param cpaturetime 未知
     */
    public void setCpaturetime(String cpaturetime) {
        this.cpaturetime = cpaturetime;
    }

    /**
     * 获取未知
     *
     * @return performby - 未知
     */
    public String getPerformby() {
        return performby;
    }

    /**
     * 设置未知
     *
     * @param performby 未知
     */
    public void setPerformby(String performby) {
        this.performby = performby;
    }

    /**
     * 获取未知
     *
     * @return delaylengthofterm - 未知
     */
    public String getDelaylengthofterm() {
        return delaylengthofterm;
    }

    /**
     * 设置未知
     *
     * @param delaylengthofterm 未知
     */
    public void setDelaylengthofterm(String delaylengthofterm) {
        this.delaylengthofterm = delaylengthofterm;
    }

    /**
     * 获取未知
     *
     * @return cellofdelaylength - 未知
     */
    public String getCellofdelaylength() {
        return cellofdelaylength;
    }

    /**
     * 设置未知
     *
     * @param cellofdelaylength 未知
     */
    public void setCellofdelaylength(String cellofdelaylength) {
        this.cellofdelaylength = cellofdelaylength;
    }

    /**
     * 获取未知
     *
     * @return delaytotime - 未知
     */
    public String getDelaytotime() {
        return delaytotime;
    }

    /**
     * 设置未知
     *
     * @param delaytotime 未知
     */
    public void setDelaytotime(String delaytotime) {
        this.delaytotime = delaytotime;
    }

    /**
     * 获取未知
     *
     * @return sfzc - 未知
     */
    public String getSfzc() {
        return sfzc;
    }

    /**
     * 设置未知
     *
     * @param sfzc 未知
     */
    public void setSfzc(String sfzc) {
        this.sfzc = sfzc;
    }

    /**
     * 获取银行
     *
     * @return bank - 银行
     */
    public String getBank() {
        return bank;
    }

    /**
     * 设置银行
     *
     * @param bank 银行
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * 获取账号
     *
     * @return account - 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     *
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取金额
     *
     * @return money - 金额
     */
    public String getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
     */
    public void setMoney(String money) {
        this.money = money;
    }

    /**
     * 获取原金额
     *
     * @return oldmoney - 原金额
     */
    public String getOldmoney() {
        return oldmoney;
    }

    /**
     * 设置原金额
     *
     * @param oldmoney 原金额
     */
    public void setOldmoney(String oldmoney) {
        this.oldmoney = oldmoney;
    }

    /**
     * 获取取保候审类型
     *
     * @return qbhslx - 取保候审类型
     */
    public String getQbhslx() {
        return qbhslx;
    }

    /**
     * 设置取保候审类型
     *
     * @param qbhslx 取保候审类型
     */
    public void setQbhslx(String qbhslx) {
        this.qbhslx = qbhslx;
    }

    /**
     * 获取未知
     *
     * @return zxdxjy - 未知
     */
    public String getZxdxjy() {
        return zxdxjy;
    }

    /**
     * 设置未知
     *
     * @param zxdxjy 未知
     */
    public void setZxdxjy(String zxdxjy) {
        this.zxdxjy = zxdxjy;
    }

    /**
     * 获取未知
     *
     * @return zxdxxx - 未知
     */
    public String getZxdxxx() {
        return zxdxxx;
    }

    /**
     * 设置未知
     *
     * @param zxdxxx 未知
     */
    public void setZxdxxx(String zxdxxx) {
        this.zxdxxx = zxdxxx;
    }

    /**
     * 获取未知
     *
     * @return zxddmc - 未知
     */
    public String getZxddmc() {
        return zxddmc;
    }

    /**
     * 设置未知
     *
     * @param zxddmc 未知
     */
    public void setZxddmc(String zxddmc) {
        this.zxddmc = zxddmc;
    }

    /**
     * 获取未知
     *
     * @return zxddxz - 未知
     */
    public String getZxddxz() {
        return zxddxz;
    }

    /**
     * 设置未知
     *
     * @param zxddxz 未知
     */
    public void setZxddxz(String zxddxz) {
        this.zxddxz = zxddxz;
    }

    /**
     * 获取未知
     *
     * @return zddddd - 未知
     */
    public String getZddddd() {
        return zddddd;
    }

    /**
     * 设置未知
     *
     * @param zddddd 未知
     */
    public void setZddddd(String zddddd) {
        this.zddddd = zddddd;
    }

    /**
     * 获取未知
     *
     * @return zdddsj - 未知
     */
    public String getZdddsj() {
        return zdddsj;
    }

    /**
     * 设置未知
     *
     * @param zdddsj 未知
     */
    public void setZdddsj(String zdddsj) {
        this.zdddsj = zdddsj;
    }

    /**
     * 获取未知
     *
     * @return tqjcsj - 未知
     */
    public String getTqjcsj() {
        return tqjcsj;
    }

    /**
     * 设置未知
     *
     * @param tqjcsj 未知
     */
    public void setTqjcsj(String tqjcsj) {
        this.tqjcsj = tqjcsj;
    }

    /**
     * 获取未知
     *
     * @return assistantunit - 未知
     */
    public String getAssistantunit() {
        return assistantunit;
    }

    /**
     * 设置未知
     *
     * @param assistantunit 未知
     */
    public void setAssistantunit(String assistantunit) {
        this.assistantunit = assistantunit;
    }

    /**
     * 获取未知
     *
     * @return assistantunitinfact - 未知
     */
    public String getAssistantunitinfact() {
        return assistantunitinfact;
    }

    /**
     * 设置未知
     *
     * @param assistantunitinfact 未知
     */
    public void setAssistantunitinfact(String assistantunitinfact) {
        this.assistantunitinfact = assistantunitinfact;
    }

    /**
     * 获取未知
     *
     * @return note - 未知
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置未知
     *
     * @param note 未知
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 获取未知
     *
     * @return departmentcode - 未知
     */
    public String getDepartmentcode() {
        return departmentcode;
    }

    /**
     * 设置未知
     *
     * @param departmentcode 未知
     */
    public void setDepartmentcode(String departmentcode) {
        this.departmentcode = departmentcode;
    }

    /**
     * 获取未知
     *
     * @return creator - 未知
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置未知
     *
     * @param creator 未知
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取未知
     *
     * @return createdtime - 未知
     */
    public String getCreatedtime() {
        return createdtime;
    }

    /**
     * 设置未知
     *
     * @param createdtime 未知
     */
    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * 获取未知
     *
     * @return securitygrade - 未知
     */
    public String getSecuritygrade() {
        return securitygrade;
    }

    /**
     * 设置未知
     *
     * @param securitygrade 未知
     */
    public void setSecuritygrade(String securitygrade) {
        this.securitygrade = securitygrade;
    }

    /**
     * 获取未知
     *
     * @return reservation01 - 未知
     */
    public String getReservation01() {
        return reservation01;
    }

    /**
     * 设置未知
     *
     * @param reservation01 未知
     */
    public void setReservation01(String reservation01) {
        this.reservation01 = reservation01;
    }

    /**
     * 获取未知
     *
     * @return reservation02 - 未知
     */
    public String getReservation02() {
        return reservation02;
    }

    /**
     * 设置未知
     *
     * @param reservation02 未知
     */
    public void setReservation02(String reservation02) {
        this.reservation02 = reservation02;
    }

    /**
     * 获取未知
     *
     * @return reservation03 - 未知
     */
    public String getReservation03() {
        return reservation03;
    }

    /**
     * 设置未知
     *
     * @param reservation03 未知
     */
    public void setReservation03(String reservation03) {
        this.reservation03 = reservation03;
    }

    /**
     * 获取未知
     *
     * @return reservation04 - 未知
     */
    public String getReservation04() {
        return reservation04;
    }

    /**
     * 设置未知
     *
     * @param reservation04 未知
     */
    public void setReservation04(String reservation04) {
        this.reservation04 = reservation04;
    }

    /**
     * 获取未知
     *
     * @return reservation05 - 未知
     */
    public String getReservation05() {
        return reservation05;
    }

    /**
     * 设置未知
     *
     * @param reservation05 未知
     */
    public void setReservation05(String reservation05) {
        this.reservation05 = reservation05;
    }

    /**
     * 获取未知
     *
     * @return reservation06 - 未知
     */
    public String getReservation06() {
        return reservation06;
    }

    /**
     * 设置未知
     *
     * @param reservation06 未知
     */
    public void setReservation06(String reservation06) {
        this.reservation06 = reservation06;
    }

    /**
     * 获取未知
     *
     * @return reservation07 - 未知
     */
    public String getReservation07() {
        return reservation07;
    }

    /**
     * 设置未知
     *
     * @param reservation07 未知
     */
    public void setReservation07(String reservation07) {
        this.reservation07 = reservation07;
    }

    /**
     * 获取未知
     *
     * @return reservation08 - 未知
     */
    public String getReservation08() {
        return reservation08;
    }

    /**
     * 设置未知
     *
     * @param reservation08 未知
     */
    public void setReservation08(String reservation08) {
        this.reservation08 = reservation08;
    }

    /**
     * 获取未知
     *
     * @return reservation09 - 未知
     */
    public String getReservation09() {
        return reservation09;
    }

    /**
     * 设置未知
     *
     * @param reservation09 未知
     */
    public void setReservation09(String reservation09) {
        this.reservation09 = reservation09;
    }

    /**
     * 获取未知
     *
     * @return reservation10 - 未知
     */
    public String getReservation10() {
        return reservation10;
    }

    /**
     * 设置未知
     *
     * @param reservation10 未知
     */
    public void setReservation10(String reservation10) {
        this.reservation10 = reservation10;
    }

    /**
     * 获取未知
     *
     * @return lastupdatedby - 未知
     */
    public String getLastupdatedby() {
        return lastupdatedby;
    }

    /**
     * 设置未知
     *
     * @param lastupdatedby 未知
     */
    public void setLastupdatedby(String lastupdatedby) {
        this.lastupdatedby = lastupdatedby;
    }

    /**
     * 获取未知
     *
     * @return lastupdatedtime - 未知
     */
    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    /**
     * 设置未知
     *
     * @param lastupdatedtime 未知
     */
    public void setLastupdatedtime(String lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    /**
     * 获取未知
     *
     * @return refreshtime - 未知
     */
    public String getRefreshtime() {
        return refreshtime;
    }

    /**
     * 设置未知
     *
     * @param refreshtime 未知
     */
    public void setRefreshtime(String refreshtime) {
        this.refreshtime = refreshtime;
    }

    /**
     * 获取未知
     *
     * @return uploadflag - 未知
     */
    public String getUploadflag() {
        return uploadflag;
    }

    /**
     * 设置未知
     *
     * @param uploadflag 未知
     */
    public void setUploadflag(String uploadflag) {
        this.uploadflag = uploadflag;
    }

    /**
     * 获取未知
     *
     * @return downloadflag - 未知
     */
    public String getDownloadflag() {
        return downloadflag;
    }

    /**
     * 设置未知
     *
     * @param downloadflag 未知
     */
    public void setDownloadflag(String downloadflag) {
        this.downloadflag = downloadflag;
    }

    /**
     * 获取未知
     *
     * @return deleteflag - 未知
     */
    public String getDeleteflag() {
        return deleteflag;
    }

    /**
     * 设置未知
     *
     * @param deleteflag 未知
     */
    public void setDeleteflag(String deleteflag) {
        this.deleteflag = deleteflag;
    }

    /**
     * 获取未知
     *
     * @return maxprintnum1 - 未知
     */
    public String getMaxprintnum1() {
        return maxprintnum1;
    }

    /**
     * 设置未知
     *
     * @param maxprintnum1 未知
     */
    public void setMaxprintnum1(String maxprintnum1) {
        this.maxprintnum1 = maxprintnum1;
    }

    /**
     * 获取未知
     *
     * @return maxprintnum2 - 未知
     */
    public String getMaxprintnum2() {
        return maxprintnum2;
    }

    /**
     * 设置未知
     *
     * @param maxprintnum2 未知
     */
    public void setMaxprintnum2(String maxprintnum2) {
        this.maxprintnum2 = maxprintnum2;
    }

    /**
     * 获取未知
     *
     * @return maxprintnum3 - 未知
     */
    public String getMaxprintnum3() {
        return maxprintnum3;
    }

    /**
     * 设置未知
     *
     * @param maxprintnum3 未知
     */
    public void setMaxprintnum3(String maxprintnum3) {
        this.maxprintnum3 = maxprintnum3;
    }

    /**
     * 获取未知
     *
     * @return maxprintnum4 - 未知
     */
    public String getMaxprintnum4() {
        return maxprintnum4;
    }

    /**
     * 设置未知
     *
     * @param maxprintnum4 未知
     */
    public void setMaxprintnum4(String maxprintnum4) {
        this.maxprintnum4 = maxprintnum4;
    }

    /**
     * 获取未知
     *
     * @return reservation11 - 未知
     */
    public String getReservation11() {
        return reservation11;
    }

    /**
     * 设置未知
     *
     * @param reservation11 未知
     */
    public void setReservation11(String reservation11) {
        this.reservation11 = reservation11;
    }

    /**
     * 获取未知
     *
     * @return reservation12 - 未知
     */
    public String getReservation12() {
        return reservation12;
    }

    /**
     * 设置未知
     *
     * @param reservation12 未知
     */
    public void setReservation12(String reservation12) {
        this.reservation12 = reservation12;
    }

    /**
     * 获取未知
     *
     * @return reservation13 - 未知
     */
    public String getReservation13() {
        return reservation13;
    }

    /**
     * 设置未知
     *
     * @param reservation13 未知
     */
    public void setReservation13(String reservation13) {
        this.reservation13 = reservation13;
    }

    /**
     * 获取未知
     *
     * @return reservation14 - 未知
     */
    public String getReservation14() {
        return reservation14;
    }

    /**
     * 设置未知
     *
     * @param reservation14 未知
     */
    public void setReservation14(String reservation14) {
        this.reservation14 = reservation14;
    }

    /**
     * 获取未知
     *
     * @return reservation15 - 未知
     */
    public String getReservation15() {
        return reservation15;
    }

    /**
     * 设置未知
     *
     * @param reservation15 未知
     */
    public void setReservation15(String reservation15) {
        this.reservation15 = reservation15;
    }

    /**
     * 获取未知
     *
     * @return reservation16 - 未知
     */
    public String getReservation16() {
        return reservation16;
    }

    /**
     * 设置未知
     *
     * @param reservation16 未知
     */
    public void setReservation16(String reservation16) {
        this.reservation16 = reservation16;
    }

    /**
     * 获取未知
     *
     * @return reservation17 - 未知
     */
    public String getReservation17() {
        return reservation17;
    }

    /**
     * 设置未知
     *
     * @param reservation17 未知
     */
    public void setReservation17(String reservation17) {
        this.reservation17 = reservation17;
    }

    /**
     * 获取未知
     *
     * @return reservation18 - 未知
     */
    public String getReservation18() {
        return reservation18;
    }

    /**
     * 设置未知
     *
     * @param reservation18 未知
     */
    public void setReservation18(String reservation18) {
        this.reservation18 = reservation18;
    }

    /**
     * 获取未知
     *
     * @return reservation19 - 未知
     */
    public String getReservation19() {
        return reservation19;
    }

    /**
     * 设置未知
     *
     * @param reservation19 未知
     */
    public void setReservation19(String reservation19) {
        this.reservation19 = reservation19;
    }

    /**
     * 获取未知
     *
     * @return reservation20 - 未知
     */
    public String getReservation20() {
        return reservation20;
    }

    /**
     * 设置未知
     *
     * @param reservation20 未知
     */
    public void setReservation20(String reservation20) {
        this.reservation20 = reservation20;
    }

    /**
     * 获取未知
     *
     * @return transactprimarytoutid - 未知
     */
    public String getTransactprimarytoutid() {
        return transactprimarytoutid;
    }

    /**
     * 设置未知
     *
     * @param transactprimarytoutid 未知
     */
    public void setTransactprimarytoutid(String transactprimarytoutid) {
        this.transactprimarytoutid = transactprimarytoutid;
    }

    /**
     * 获取未知
     *
     * @return confirmbypersonid - 未知
     */
    public String getConfirmbypersonid() {
        return confirmbypersonid;
    }

    /**
     * 设置未知
     *
     * @param confirmbypersonid 未知
     */
    public void setConfirmbypersonid(String confirmbypersonid) {
        this.confirmbypersonid = confirmbypersonid;
    }

    /**
     * 获取未知
     *
     * @return confirmbyunitid - 未知
     */
    public String getConfirmbyunitid() {
        return confirmbyunitid;
    }

    /**
     * 设置未知
     *
     * @param confirmbyunitid 未知
     */
    public void setConfirmbyunitid(String confirmbyunitid) {
        this.confirmbyunitid = confirmbyunitid;
    }

    /**
     * 获取未知
     *
     * @return writebyid - 未知
     */
    public String getWritebyid() {
        return writebyid;
    }

    /**
     * 设置未知
     *
     * @param writebyid 未知
     */
    public void setWritebyid(String writebyid) {
        this.writebyid = writebyid;
    }

    /**
     * 获取未知
     *
     * @return reservation21 - 未知
     */
    public String getReservation21() {
        return reservation21;
    }

    /**
     * 设置未知
     *
     * @param reservation21 未知
     */
    public void setReservation21(String reservation21) {
        this.reservation21 = reservation21;
    }

    /**
     * 获取未知
     *
     * @return reservation22 - 未知
     */
    public String getReservation22() {
        return reservation22;
    }

    /**
     * 设置未知
     *
     * @param reservation22 未知
     */
    public void setReservation22(String reservation22) {
        this.reservation22 = reservation22;
    }

    /**
     * 获取未知
     *
     * @return reservation23 - 未知
     */
    public String getReservation23() {
        return reservation23;
    }

    /**
     * 设置未知
     *
     * @param reservation23 未知
     */
    public void setReservation23(String reservation23) {
        this.reservation23 = reservation23;
    }

    /**
     * 获取未知
     *
     * @return reservation24 - 未知
     */
    public String getReservation24() {
        return reservation24;
    }

    /**
     * 设置未知
     *
     * @param reservation24 未知
     */
    public void setReservation24(String reservation24) {
        this.reservation24 = reservation24;
    }

    /**
     * 获取未知
     *
     * @return reservation25 - 未知
     */
    public String getReservation25() {
        return reservation25;
    }

    /**
     * 设置未知
     *
     * @param reservation25 未知
     */
    public void setReservation25(String reservation25) {
        this.reservation25 = reservation25;
    }

    /**
     * 获取未知
     *
     * @return reservation26 - 未知
     */
    public String getReservation26() {
        return reservation26;
    }

    /**
     * 设置未知
     *
     * @param reservation26 未知
     */
    public void setReservation26(String reservation26) {
        this.reservation26 = reservation26;
    }

    /**
     * 获取未知
     *
     * @return reservation27 - 未知
     */
    public String getReservation27() {
        return reservation27;
    }

    /**
     * 设置未知
     *
     * @param reservation27 未知
     */
    public void setReservation27(String reservation27) {
        this.reservation27 = reservation27;
    }

    /**
     * 获取未知
     *
     * @return reservation28 - 未知
     */
    public String getReservation28() {
        return reservation28;
    }

    /**
     * 设置未知
     *
     * @param reservation28 未知
     */
    public void setReservation28(String reservation28) {
        this.reservation28 = reservation28;
    }

    /**
     * 获取未知
     *
     * @return reservation29 - 未知
     */
    public String getReservation29() {
        return reservation29;
    }

    /**
     * 设置未知
     *
     * @param reservation29 未知
     */
    public void setReservation29(String reservation29) {
        this.reservation29 = reservation29;
    }

    /**
     * 获取未知
     *
     * @return reservation30 - 未知
     */
    public String getReservation30() {
        return reservation30;
    }

    /**
     * 设置未知
     *
     * @param reservation30 未知
     */
    public void setReservation30(String reservation30) {
        this.reservation30 = reservation30;
    }

    /**
     * 获取未知
     *
     * @return reservation31 - 未知
     */
    public String getReservation31() {
        return reservation31;
    }

    /**
     * 设置未知
     *
     * @param reservation31 未知
     */
    public void setReservation31(String reservation31) {
        this.reservation31 = reservation31;
    }

    /**
     * 获取未知
     *
     * @return reservation32 - 未知
     */
    public String getReservation32() {
        return reservation32;
    }

    /**
     * 设置未知
     *
     * @param reservation32 未知
     */
    public void setReservation32(String reservation32) {
        this.reservation32 = reservation32;
    }

    /**
     * 获取未知
     *
     * @return ssws1_printdate - 未知
     */
    public String getSsws1Printdate() {
        return ssws1Printdate;
    }

    /**
     * 设置未知
     *
     * @param ssws1Printdate 未知
     */
    public void setSsws1Printdate(String ssws1Printdate) {
        this.ssws1Printdate = ssws1Printdate;
    }

    /**
     * 获取未知
     *
     * @return ssws2_printdate - 未知
     */
    public String getSsws2Printdate() {
        return ssws2Printdate;
    }

    /**
     * 设置未知
     *
     * @param ssws2Printdate 未知
     */
    public void setSsws2Printdate(String ssws2Printdate) {
        this.ssws2Printdate = ssws2Printdate;
    }

    /**
     * 获取未知
     *
     * @return ssws3_printdate - 未知
     */
    public String getSsws3Printdate() {
        return ssws3Printdate;
    }

    /**
     * 设置未知
     *
     * @param ssws3Printdate 未知
     */
    public void setSsws3Printdate(String ssws3Printdate) {
        this.ssws3Printdate = ssws3Printdate;
    }

    /**
     * 获取未知
     *
     * @return ssws4_printdate - 未知
     */
    public String getSsws4Printdate() {
        return ssws4Printdate;
    }

    /**
     * 设置未知
     *
     * @param ssws4Printdate 未知
     */
    public void setSsws4Printdate(String ssws4Printdate) {
        this.ssws4Printdate = ssws4Printdate;
    }

    /**
     * 获取未知
     *
     * @return wscqjb - 未知
     */
    public String getWscqjb() {
        return wscqjb;
    }

    /**
     * 设置未知
     *
     * @param wscqjb 未知
     */
    public void setWscqjb(String wscqjb) {
        this.wscqjb = wscqjb;
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

    /**
     * 获取案件概要
     *
     * @return analysiscase - 案件概要
     */
    public String getAnalysiscase() {
        return analysiscase;
    }

    /**
     * 设置案件概要
     *
     * @param analysiscase 案件概要
     */
    public void setAnalysiscase(String analysiscase) {
        this.analysiscase = analysiscase;
    }

    public String getWritid() {
        return writid;
    }

    public void setWritid(String writid) {
        this.writid = writid;
    }
}
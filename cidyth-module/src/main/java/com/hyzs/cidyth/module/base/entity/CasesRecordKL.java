package com.hyzs.cidyth.module.base.entity;

/**
 * 考拉接口的电子笔录详情（带正文）
 * Created by 1 on 2018/5/31.
 */
public class CasesRecordKL {
    /**
     * 笔录ID
     */
    private String ID;
    /**
     * 人员类型
     */
    private String RYLX;
    /**
     * 案件编号
     */
    private String CASECODE;
    /**
     * 人员编号
     */
    private String JZRYBH;
    /**
     * 被问话人姓名
     */
    private String QUESTIONNAME;
    /**
     * 性别
     */
    private String SEX;
    /**
     * 现住址
     */
    private String PRESENTADDRESS;
    /**
     * 身份证号
     */
    private String CREDENTIALNO;
    /**
     * 笔录正文
     */
    private String NOTECONTENT;
    /**
     * 制作人警号
     */
    private String POLICENUMBER1;
    /**
     * 制作人姓名
     */
    private String QUESTIONER1;
    /**
     * 制作人单位
     *
     */
    private String QUESTIONER1WORKUNIT;
    /**
     * 开始时间
     */
    private String BEGINTIME;
    /**
     * 结束时间
     */
    private String ENDTIME;
    /**
     * 联系电话
     */
    private String PHONENUMBER;

    public String getRYLX() {
        return RYLX;
    }

    public void setRYLX(String RYLX) {
        this.RYLX = RYLX;
    }

    public String getCASECODE() {
        return CASECODE;
    }

    public void setCASECODE(String CASECODE) {
        this.CASECODE = CASECODE;
    }

    public String getJZRYBH() {
        return JZRYBH;
    }

    public void setJZRYBH(String JZRYBH) {
        this.JZRYBH = JZRYBH;
    }

    public String getQUESTIONNAME() {
        return QUESTIONNAME;
    }

    public void setQUESTIONNAME(String QUESTIONNAME) {
        this.QUESTIONNAME = QUESTIONNAME;
    }

    public String getSEX() {
        return SEX;
    }

    public void setSEX(String SEX) {
        this.SEX = SEX;
    }

    public String getPRESENTADDRESS() {
        return PRESENTADDRESS;
    }

    public void setPRESENTADDRESS(String PRESENTADDRESS) {
        this.PRESENTADDRESS = PRESENTADDRESS;
    }

    public String getCREDENTIALNO() {
        return CREDENTIALNO;
    }

    public void setCREDENTIALNO(String CREDENTIALNO) {
        this.CREDENTIALNO = CREDENTIALNO;
    }

    public String getNOTECONTENT() {
        return NOTECONTENT;
    }

    public void setNOTECONTENT(String NOTECONTENT) {
        this.NOTECONTENT = NOTECONTENT;
    }

    public String getPOLICENUMBER1() {
        return POLICENUMBER1;
    }

    public void setPOLICENUMBER1(String POLICENUMBER1) {
        this.POLICENUMBER1 = POLICENUMBER1;
    }

    public String getQUESTIONER1() {
        return QUESTIONER1;
    }

    public void setQUESTIONER1(String QUESTIONER1) {
        this.QUESTIONER1 = QUESTIONER1;
    }

    public String getQUESTIONER1WORKUNIT() {
        return QUESTIONER1WORKUNIT;
    }

    public void setQUESTIONER1WORKUNIT(String QUESTIONER1WORKUNIT) {
        this.QUESTIONER1WORKUNIT = QUESTIONER1WORKUNIT;
    }

    public String getBEGINTIME() {
        return BEGINTIME;
    }

    public void setBEGINTIME(String BEGINTIME) {
        this.BEGINTIME = BEGINTIME;
    }

    public String getENDTIME() {
        return ENDTIME;
    }

    public void setENDTIME(String ENDTIME) {
        this.ENDTIME = ENDTIME;
    }

    public String getPHONENUMBER() {
        return PHONENUMBER;
    }

    public void setPHONENUMBER(String PHONENUMBER) {
        this.PHONENUMBER = PHONENUMBER;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}

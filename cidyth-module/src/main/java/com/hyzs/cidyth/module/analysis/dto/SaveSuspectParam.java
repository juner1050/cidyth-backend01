package com.hyzs.cidyth.module.analysis.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by 1 on 2018/11/3.
 */
public class SaveSuspectParam {

    /**
     * 姓名
     */
    @NotBlank
    private String xm;
    /**
     * 性别（1、男，2、女）
     */
    @NotBlank
    private String xb;
    /**
     * 证件号码
     */
    @NotBlank
    private String zjhm;
    /**
     * 手机号码
     */
    @NotBlank
    private String lxdh;
    /**
     * 户籍地址
     */
    @NotBlank
    private String hjdz;
    /**
     * 详细地址
     */
    @NotBlank
    private String xxdzms;
    /**
     * 上传图片
     */
    private MultipartFile file;
    /**
     * 关联案件编号
     */
    /*@NotEmpty
    private List<String> relationCode;*/

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getHjdz() {
        return hjdz;
    }

    public void setHjdz(String hjdz) {
        this.hjdz = hjdz;
    }

    public String getXxdzms() {
        return xxdzms;
    }

    public void setXxdzms(String xxdzms) {
        this.xxdzms = xxdzms;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    /*public List<String> getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(List<String> relationCode) {
        this.relationCode = relationCode;
    }*/
}

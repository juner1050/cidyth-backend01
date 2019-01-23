package com.hyzs.cidyth.module.analysis.dto;

/**
 * Created by 1 on 2018/11/2.
 */

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 保存案件图片对象
 */
public class SaveCaseImageParam {

    /**
     * 案件编号
     */
    @NotBlank
    private String ajbh;

    /**
     * 图片集合
     */
    private List<MultipartFile> files;

    public String getAjbh() {
        return ajbh;
    }

    public void setAjbh(String ajbh) {
        this.ajbh = ajbh;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}

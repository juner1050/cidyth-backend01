package com.hyzs.cidyth.module.integral.vo;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by 1 on 2018/6/12.
 */
public class IntegralTitleSaveDTO {

    /**
     * 主键
     */
    @ApiModelProperty("id")
    private Integer id;
    /**
     * 头衔名称
     */
    @ApiModelProperty(value = "头衔名称", required = true)
    private String name;
    /**
     * 头衔头像
     */
    @ApiModelProperty("头衔头像")
    private MultipartFile avatar;
    /**
     * 上限人数
     */
    @ApiModelProperty(value = "上限人数", required = true)
    private Integer upperLimit;
    /**
     * 下限人数
     */
    @ApiModelProperty(value = "下限人数", required = true)
    private Integer lowerLimit;
    /**
     * 说明备注
     */
    @ApiModelProperty(value = "说明备注")
    private String smbz;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Integer getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Integer lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public String getSmbz() {
        return smbz;
    }

    public void setSmbz(String smbz) {
        this.smbz = smbz;
    }
}

package com.hyzs.cidyth.module.integral.vo;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by 1 on 2018/6/12.
 */
public class IntegralTitleAvatarDTO {

    /**
     * 主键
     */
    @ApiModelProperty("id")
    private Integer id;
    /**
     * 头衔头像
     */
    @ApiModelProperty("头衔头像")
    private MultipartFile avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }
}

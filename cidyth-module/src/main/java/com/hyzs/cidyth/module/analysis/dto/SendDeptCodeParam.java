package com.hyzs.cidyth.module.analysis.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 下发部门编号参数对象
 * Created by 1 on 2018/10/30.
 */
public class SendDeptCodeParam {

    /**
     * 线索编号
     */
    @NotBlank
    @ApiModelProperty(value = "线索编号")
    private String xsbh;

    /**
     * 部门编号
     */
    @NotBlank
    @ApiModelProperty(value = "部门编号")
    private String deptCode;

    public String getXsbh() {
        return xsbh;
    }

    public void setXsbh(String xsbh) {
        this.xsbh = xsbh;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}

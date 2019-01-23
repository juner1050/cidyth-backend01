package com.hyzs.cidyth.module.analysis.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * 指派人员列表对象
 * Created by 1 on 2018/10/30.
 */
public class AllocateListParam {

    /**
     * 线索编号
     */
    @NotBlank()
    @ApiModelProperty(value = "线索编号")
    private String xsbh;

    /**
     * 被指派人员
     */
    @NotEmpty
    @ApiModelProperty(value = "被指派人员")
    private List<String> persons;

    public List<String> getPersons() {
        return persons;
    }

    public void setPersons(List<String> persons) {
        this.persons = persons;
    }

    public String getXsbh() {
        return xsbh;
    }

    public void setXsbh(String xsbh) {
        this.xsbh = xsbh;
    }
}

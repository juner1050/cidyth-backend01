package com.hyzs.cidyth.module.analysis.dto;

import com.drew.lang.annotations.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by 1 on 2018/11/3.
 */
public class SaveSuspectParamBatch {

    @NotBlank
    private String xsbh;

    @Valid
    @NotEmpty
    private List<SaveSuspectParam> lsSaveSuspectParam;

    public String getXsbh() {
        return xsbh;
    }

    public void setXsbh(String xsbh) {
        this.xsbh = xsbh;
    }

    public List<SaveSuspectParam> getLsSaveSuspectParam() {
        return lsSaveSuspectParam;
    }

    public void setLsSaveSuspectParam(List<SaveSuspectParam> lsSaveSuspectParam) {
        this.lsSaveSuspectParam = lsSaveSuspectParam;
    }
}

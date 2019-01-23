package com.hyzs.cidyth.module.analysis.dto;

/**
 * Created by 1 on 2018/11/2.
 */

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

/**
 * 保存案件图片对象
 */
public class SaveCaseImageParamBatch {

    @Valid
    @NotEmpty
    private List<SaveCaseImageParam> lsSaveCaseImageParam;

    public List<SaveCaseImageParam> getLsSaveCaseImageParam() {
        return lsSaveCaseImageParam;
    }

    public void setLsSaveCaseImageParam(List<SaveCaseImageParam> lsSaveCaseImageParam) {
        this.lsSaveCaseImageParam = lsSaveCaseImageParam;
    }
}

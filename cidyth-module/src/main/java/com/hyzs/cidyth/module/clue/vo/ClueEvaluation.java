package com.hyzs.cidyth.module.clue.vo;

import com.hyzs.cidyth.module.integral.vo.IntegralRuleOption;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 线索评价对象，用于侦结案件时，选择的线索
 * Created by 1 on 2018/6/20.
 */
@ApiModel("ClueEvaluation：破案时线索评价")
public class ClueEvaluation {

    /**
     * 线索对象
     */
    @ApiModelProperty("线索对象")
    private List<ClueMain> lsClueMain;
    /**
     * 规则对象
     */
    @ApiModelProperty("规则对象")
    private List<IntegralRuleOption> lsIntegralRuleOption;

    public List<ClueMain> getLsClueMain() {
        return lsClueMain;
    }

    public void setLsClueMain(List<ClueMain> lsClueMain) {
        this.lsClueMain = lsClueMain;
    }

    public List<IntegralRuleOption> getLsIntegralRuleOption() {
        return lsIntegralRuleOption;
    }

    public void setLsIntegralRuleOption(List<IntegralRuleOption> lsIntegralRuleOption) {
        this.lsIntegralRuleOption = lsIntegralRuleOption;
    }
}

package com.hyzs.cidyth.module.clue.vo;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 线索
 * @author derrick
 *
 */
public class ClueAppraiseSave {

    /**
     * 线索id
     */
    @ApiModelProperty(value="线索id", required=true)
    private Integer clueId;
    /**
     * 规则类型
     */
    @ApiModelProperty(value="规则类型", required=true)
    private String ruleType;
    /**
     * 积分
     */
    @ApiModelProperty(value="积分", required=true)
    private Float score;

    public Integer getClueId() {
        return clueId;
    }

    public void setClueId(Integer clueId) {
        this.clueId = clueId;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
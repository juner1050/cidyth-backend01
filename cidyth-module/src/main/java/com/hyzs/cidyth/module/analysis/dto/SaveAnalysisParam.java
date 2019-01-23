package com.hyzs.cidyth.module.analysis.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 添加线索对象
 * Created by 1 on 2018/10/30.
 */
@ApiModel(value="参数对象")
public class SaveAnalysisParam {

    /**
     * 线索编号
     */
    @NotBlank()
    @ApiModelProperty(value = "线索编号", required = true)
    private String xsbh;

    /**
     * 线索名称
     */
    @NotBlank()
    @ApiModelProperty(value = "线索名称", required = true)
    private String xsmc;

    /**
     * 线索说明
     */
    @NotBlank()
    @ApiModelProperty(value = "线索说明", required = true)
    private String smbz;

    /**
     * 工作意见
     */
    @NotBlank()
    @ApiModelProperty(value = "工作意见", required = true)
    private String gzyj;

    /**
     * 研判时间
     */
    @NotBlank()
    @ApiModelProperty(value = "研判时间", required = true)
    private String ypsj;

    /**
     * 附件
     */
    @NotNull
    @ApiModelProperty(value = "附件", required = true)
    private MultipartFile file;

    public String getXsbh() {
        return xsbh;
    }

    public void setXsbh(String xsbh) {
        this.xsbh = xsbh;
    }

    public String getXsmc() {
        return xsmc;
    }

    public void setXsmc(String xsmc) {
        this.xsmc = xsmc;
    }

    public String getSmbz() {
        return smbz;
    }

    public void setSmbz(String smbz) {
        this.smbz = smbz;
    }

    public String getGzyj() {
        return gzyj;
    }

    public void setGzyj(String gzyj) {
        this.gzyj = gzyj;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getYpsj() {
        return ypsj;
    }

    public void setYpsj(String ypsj) {
        this.ypsj = ypsj;
    }
}

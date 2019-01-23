package com.hyzs.cidyth.module.attachment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 1 on 2018/6/13.
 */
@ApiModel("AttachmentUpload（上传文件对象）")
public class AttachmentUpload {

    /**
     * 上传文件
     */
    @ApiModelProperty("上传文件")
    private MultipartFile file;
    /**
     * 文件类型（1、审批文书；2、法律文书；3、证据材料；4、其他）
     */
    @ApiModelProperty("文件类型（1、审批文书；2、法律文书；3、证据材料；4、其他）")
    private Integer fileType;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }
}

package com.hyzs.cidyth.portal.controller.attachment;

import com.hyzs.cidyth.common.utils.FileUtil;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.attachment.entity.Attachment;
import com.hyzs.cidyth.module.attachment.service.AttachmentService;
import com.hyzs.cidyth.module.base.entity.SceneImageCid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags={"附件接口"})
@RestController
@RequestMapping("attachment")
public class    AttachmentController {
	
	private static final Logger logger = LoggerFactory.getLogger(AttachmentController.class);

	@Autowired
	private AttachmentService attachmentService;

    /**
     * 文件下载
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @return ResponseEntity
     */
    @GetMapping(value = "download")
    @ApiOperation(value = "文件下载", httpMethod = "GET", response = ResponseEntity.class, notes = "文件下载")
    public ResponseEntity<byte[]> download(Integer id){
        if(id == null){
            return null;
        }else{
            Attachment attachment = attachmentService.getAttachmentById(id);
            if(attachment == null) {
                return null;
            }
            try {
                return FileUtil.download(attachment.getFileId() + "." + attachment.getWjgs(), attachment.getFjmc());
            } catch (Exception e) {
                throw new ServiceException("下载失败：", e.getMessage());
            }
        }
    }

    /**
     * 读取图片流返回
     * @param req
     * @param res
     * @param xxid
     */
    @GetMapping(value = "getImage")
    @ApiOperation(value = "读取图片流返回", httpMethod = "GET", notes = "读取图片流返回")
    public void getImage(HttpServletRequest req, HttpServletResponse res, String fileId) {
        if (StringUtils.isNotBlank(fileId)) {
            attachmentService.readImage(res, fileId);
        }
    }
}

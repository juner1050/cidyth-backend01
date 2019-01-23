package com.hyzs.cidyth.module.attachment.service.impl;

import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.enums.FileTypeEnum;
import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import com.hyzs.cidyth.common.utils.FileUtil;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.attachment.dao.AttachmentMapper;
import com.hyzs.cidyth.module.attachment.entity.Attachment;
import com.hyzs.cidyth.module.attachment.service.AttachmentService;
import com.hyzs.cidyth.module.attachment.vo.AttachmentUpload;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.vo.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("localAttachmentService")
public class AttachmentServiceImpl implements AttachmentService {

	private static final Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);

	@Autowired
	private AttachmentMapper attachmentMapper;

	@Override
	public Map<String, Object> insert(MultipartFile file, String fileComment, String referenceId, String fjlx) {
		AttachmentUpload attachmentUpload = new AttachmentUpload();
		attachmentUpload.setFile(file);
		attachmentUpload.setFileType(Integer.valueOf(FileTypeEnum.FILE_OTHER.code()));
		return insert(Lists.newArrayList(attachmentUpload), fileComment, referenceId, fjlx).get(0);
	}

	@Override
	public List<Map<String, Object>> insert(List<AttachmentUpload> attachmentUpload, String fileComment, String referenceId, String fjlx) {
		User loginUser = UserUtil.getUser();
		List<Map<String, Object>> result = Lists.newArrayList();
		List<Attachment> lsAttachment = Lists.newArrayList();
		for(int i=0; i<attachmentUpload.size(); i++){
			// 获取文件对象
			Integer fileType = attachmentUpload.get(i).getFileType();
			// 获取文件对象
			MultipartFile files = attachmentUpload.get(i).getFile();
			// 获取文件名
			String fileName = files.getOriginalFilename();
			// 获取文件扩展名（如exe、doc）
			String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
			String fileId = "";
			try {
				fileId = FileUtil.upload(files);
			} catch (Exception e) {
				throw new ServiceException("文件上传失败！");
			}
			// 创建附件对象
			Attachment attachment = new Attachment();
			attachment.setReferenceId(referenceId);
			attachment.setFjlx(fjlx);
			attachment.setFjmc(files.getOriginalFilename());
			attachment.setWjdx(files.getSize());
			attachment.setFileType(fileType);
			attachment.setLrry(loginUser.getAccount());
			attachment.setLrsj(new Date());
			attachment.setLrrymc(loginUser.getName());
			attachment.setSmbz(fileComment);
			attachment.setWjgs(extName);
			attachment.setFileId(fileId);
			lsAttachment.add(attachment);
		}
		// 文件上传成功再把数据入库
		if(CollectionUtils.isNotEmpty(lsAttachment)){
			attachmentMapper.insertList(lsAttachment);
			lsAttachment.forEach(i -> result.add(AttachmentService.AttachmentMetaDataInfoWrapper.wrap(i)));
		}
		return result;
	}

	/**
	 * 新增附件
	 *
	 * @param attachment@return 返回上传成功的文件元信息,格式参见AttachmentMetaDataInfoWrapper.wrap()方法返回的结果
	 */
	@Override
	public Map<String, Object> insert(Attachment attachment) {
		attachmentMapper.insert(attachment);
		return AttachmentService.AttachmentMetaDataInfoWrapper.wrap(attachment);
	}

	@Override
	public List<Map<String, Object>> insert(List<Attachment> lsAttachment) {
		List<Map<String, Object>> lsResult = Lists.newArrayList();
		attachmentMapper.insertList(lsAttachment);
		lsAttachment.forEach(i -> lsResult.add(AttachmentService.AttachmentMetaDataInfoWrapper.wrap(i)));
		return lsResult;
	}

	@Override
	public List<Map<String, Object>> loadAttachment(String referenceId, TimeNodeEnum attachmentType) {
		if (StringUtils.isBlank(referenceId))
			throw new ServiceException("目标对象id不能空");
		String attachmentTypeName = (attachmentType == null ? null : attachmentType.name());
		logger.debug("attachment type: " + attachmentTypeName);
		List<Attachment> list = attachmentMapper.selectByReferenceId$AttachmentType(referenceId, attachmentTypeName);
		return AttachmentService.AttachmentMetaDataInfoWrapper.wrap(list);
	}

	@Override
	public Attachment getAttachmentById(int id) {
		return attachmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public Attachment getAttachmentByMaxId(Integer id) {
		return attachmentMapper.getAttachmentByMaxId(id);
	}

	/**
	 * 根据案件ID获取案件图片唯一编号集合
	 *
	 * @param referenceId
	 * @return
	 */
	@Override
	public List<String> listFileIdByReference(String referenceId, String fjlx) {
		List<String> lsResult = Lists.newArrayList();
		Attachment queryParam = new Attachment();
		queryParam.setReferenceId(referenceId);
		queryParam.setFjlx(fjlx);
		List<Attachment> lsAttachment = attachmentMapper.select(queryParam);
		lsAttachment.forEach(i -> lsResult.add(i.getFileId()));
		return lsResult;
	}

	@Override
	public void insertList(List<Attachment> lsAttachment) {
		attachmentMapper.insertList(lsAttachment);
	}

	/**
	 * 读取图片流返回
	 *
	 * @param fileId
	 * @return
	 */
	@Override
	public void readImage(HttpServletResponse res, String fileId) {
		Attachment queryParam = new Attachment();
		queryParam.setFileId(fileId);
		List<Attachment> lsAttachment = attachmentMapper.select(queryParam);
		if(CollectionUtils.isNotEmpty(lsAttachment)){
			String fileName = lsAttachment.get(0).getFileId() + "." + lsAttachment.get(0).getWjgs();
			try {
				TimeNodeEnum timeNodeEnum = TimeNodeEnum.ofName(lsAttachment.get(0).getFjlx());
				FileUtil.read(res, fileName, timeNodeEnum == null ? "" : timeNodeEnum.name().toString());
			} catch (Exception e) {
				throw new ServiceException("读取文件失败！", e);
			}
		}
	}
}

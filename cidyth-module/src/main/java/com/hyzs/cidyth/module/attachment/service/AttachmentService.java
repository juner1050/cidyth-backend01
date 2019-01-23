package com.hyzs.cidyth.module.attachment.service;

import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.enums.FileTypeEnum;
import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.module.attachment.entity.Attachment;
import com.hyzs.cidyth.module.attachment.vo.AttachmentUpload;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface AttachmentService {

	Map<String, Object> insert(MultipartFile file, String fileComment, String referenceId, String fjlx);

	List<Map<String, Object>> insert(List<AttachmentUpload> attachmentUpload, String fileComment, String referenceId, String fjlx);

	Map<String, Object> insert(Attachment attachment);

	List<Map<String, Object>> insert(List<Attachment> lsAttachment);

	/**
	 * 根据目标id和附件用途获取附件
	 * 
	 * @param referenceId
	 *            目标id(需求id、线索id、信息id、回复id...)
	 * @param attachmentType
	 *            附件用途:CASE(案件)、DEMAND(上传)、INFO(反馈线索)、UPLOAD_CLUE(反馈线索)、RETURN_CLUE(反馈线索)、REPLY(回复),参见com.hyzs.cidyth.common.enums.TableTypeEnum
	 *            枚举的name
	 * @return
	 */
	public List<Map<String, Object>> loadAttachment(String referenceId, TimeNodeEnum attachmentType);

	/**
	 * 附件信息包装类
	 * 
	 * @author jidw
	 *
	 */
	public static final class AttachmentMetaDataInfoWrapper {
		public static Map<String, Object> wrap(Attachment entity) {
			if (entity == null)
				return null;
			Map<String, Object> attachemt = new HashMap<String, Object>();
			attachemt.put("fileId", entity.getId());// 文件主键记录id
			attachemt.put("fileName", entity.getFjmc());// 原文件名称
			attachemt.put("fileSize", entity.getWjdx());// 文件大小
			attachemt.put("url", entity.getFileId());// 文件唯一标识或者url
			attachemt.put("usage", entity.getFjlx());// 附件用途
			attachemt.put("comment", entity.getSmbz());// 备注信息
			attachemt.put("createTime",
					entity.getLrsj() == null ? null : DateUtil.formatDate(entity.getLrsj(), DateUtil.Y_M_D_H_M_S));// 上传时间
			attachemt.put("createUserId", entity.getLrry());// 上传操作人用户id
			attachemt.put("createUserName", entity.getLrrymc());// 上传操作人用户名称
			attachemt.put("fileType", FileTypeEnum.ofCode(String.valueOf(entity.getFileType())) == null ? "" : FileTypeEnum.ofCode(String.valueOf(entity.getFileType())).descp());// 文件类型（1、审批文书；2、法律文书；3、证据材料；4、其他）
			return attachemt;
		}

		public static List<Map<String, Object>> wrap(List<Attachment> entityList) {
			List<Map<String, Object>> attachments = null;
			if (CollectionUtils.isNotEmpty(entityList)) {
				attachments = Lists.newArrayList();
				for (Attachment entity : entityList) {
					Map<String, Object> attachment = wrap(entity);
					if (attachment != null) {
						attachments.add(attachment);
					}
				}
			}
			return attachments;
		}
	}

	void insertList(List<Attachment> lsAttachment);

	/**
	 * 根据fileId获取对象
	 * @param id
	 * @return
	 */
	public Attachment getAttachmentById(int id);

	/**
	 * 根据reference_id获取id最大的记录（reference_id相同的所有记录的最后一条记录）
	 * @param id
	 * @return
	 */
	public Attachment getAttachmentByMaxId(Integer id);

	/**
	 * 根据案件Id获取附件Id集合
	 * @param referenceId
	 * @return
	 */
	List<String> listFileIdByReference(String referenceId, String fjlx);

	/**
	 * 读取图片流返回
	 * @param fileId
	 * @return
	 */
	void readImage(HttpServletResponse res, String fileId);
}

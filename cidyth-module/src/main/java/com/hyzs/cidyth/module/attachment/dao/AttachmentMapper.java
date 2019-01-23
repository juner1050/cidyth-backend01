package com.hyzs.cidyth.module.attachment.dao;

import java.util.List;

import com.hyzs.psd.gafa.daf.mybatis.tk.common.MySqlMapper;
import org.apache.ibatis.annotations.Param;

import com.hyzs.cidyth.module.attachment.entity.Attachment;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface AttachmentMapper extends Mapper<Attachment>, MySqlMapper<Attachment> {
	/**
	 * /** 根据目标id和附件用途获取附件
	 * 
	 * @param referenceId
	 *            目标id(需求id、线索id、信息id、回复id...)
	 * @param attachmentType
	 *            附件用途:CASE(案件)、DEMAND(上传)、INFO(反馈线索)、UPLOAD_CLUE(反馈线索)、RETURN_CLUE(反馈线索)、REPLY(回复),参见com.hyzs.cidyth.common.enums.TableTypeEnum
	 *            枚举的name
	 * @return
	 */
	List<Attachment> selectByReferenceId$AttachmentType(@Param("referenceId") String referenceId, @Param("attachmentType")  String attachmentType);

	/**
	 * 根据reference_id获取id最大的记录（reference_id相同的所有记录的最后一条记录）
	 * @param id
	 * @return
	 */
	Attachment getAttachmentByMaxId(Integer id);
}
package com.hyzs.cidyth.module.reply.dao;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hyzs.cidyth.module.reply.entity.Reply;
import com.hyzs.psd.gafa.daf.mybatis.tex.DataSourceName;
import com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper;

@DataSourceName(name = "masterSqlSessionFactory")
public interface ReplyMapper extends Mapper<Reply> {
	/**
	 * 根据引用目标
	 * 
	 * @param referenceId
	 * @param replyTypes
	 * @return
	 */
	List<Reply> selectByReferenceId$ReplyTypes(@Param("referenceIdList") Collection<String> referenceIdList,
			@Param("replyTypes") Collection<String> replyTypes);
}
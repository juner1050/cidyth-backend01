package com.hyzs.cidyth.module.reply.service;

import com.hyzs.cidyth.module.reply.vo.Replies;

/**
 * 回复服务
 * 
 * @author jidw
 *
 */
public interface ReplyService {
	/**
	 * 保存回复
	 * 
	 * @param reply
	 * @return 记录id
	 * @throws Exception 
	 */
	String saveReply(Replies reply) throws Exception;
	/**
	 * 根据回复记录id获取该条回复
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Replies getReplyById(String id)throws Exception;
}

package com.hyzs.cidyth.module.reply.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.hyzs.cidyth.common.enums.ReplyTypeEnum;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.module.reply.entity.Reply;

/**
 * 正对消息的互动
 * 
 * @author jidw
 *
 */
public class InfoInteractionContent extends InteractionContent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2893674353912222029L;

	private Collection<ImmutablePair<Reply, List<Map<String, Object>>>> replyList;// 所有的回复、针对回复的回复和各自的附件映射

	public void setReplyList(Collection<ImmutablePair<Reply, List<Map<String, Object>>>> replyList) {
		this.replyList = replyList;
	}

	@Override
	public List<Map<String, Object>> getContentList() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if (CollectionUtils.isNotEmpty(this.replyList)) {
			Collection<Map<String, Object>> data = Collections2.transform(this.replyList,
					new Function<ImmutablePair<Reply, List<Map<String, Object>>>, Map<String, Object>>() {
						@Override
						public Map<String, Object> apply(ImmutablePair<Reply, List<Map<String, Object>>> input) {
							Reply reply = input.getLeft();
							List<Map<String, Object>> attachments = input.getRight();
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("replyId", reply.getId());// 回复
							map.put("fromDepId", reply.getFsdw());// 发起方单位编号
							map.put("fromDeptName", reply.getFsdwmc());// 发起方单位名称
							map.put("fromUserId", reply.getFsry());// 发起人警号
							map.put("fromUserName", reply.getFsryxm());// 发起人姓名
							map.put(getInteractionTimeFieldName(), reply.getFsrq() == null ? null
									: DateUtil.formatDate(reply.getFsrq(), getInteractionTimeFormat()));// 日期
							map.put("content", reply.getFsnr());// 内容
							map.put("attachment", attachments);// 附件
							map.put("toDepId", reply.getHfdw());// 接收方单位编号
							map.put("toDeptName", reply.getHfdwmc());// 接收方单位名称
							map.put("toUserId", reply.getHfry());// 接收人警号
							map.put("toUserName", reply.getHfryxm());// 接收人姓名
							/*if (ReplyTypeEnum.DEMAND.name().equals(reply.getXxlx())) {
								// 针对需求的回复
								map.put("type", ReplyTypeEnum.DEMAND.name());
							} else if (ReplyTypeEnum.RESP.name().equals(reply.getXxlx())) {
								// 对某一条回复的内容进行的回复
								map.put("type", ReplyTypeEnum.RESP.name());
							} else if (ReplyTypeEnum.CLUE.name().equals(reply.getXxlx()))  {
								//针对线索的回复
								map.put("type", ReplyTypeEnum.CLUE.name());
							} else if (ReplyTypeEnum.INFO.name().equals(reply.getXxlx())) {
								//针对信息的回复
								map.put("type", ReplyTypeEnum.INFO.name());
							}*/
							if (ReplyTypeEnum.DEMAND.name().equals(reply.getXxlx())) {
								// 针对需求的回复
								map.put("type", ReplyTypeEnum.RESP.name());
							} else if (ReplyTypeEnum.RESP.name().equals(reply.getXxlx())) {
								// 对某一条回复的内容进行的回复
								map.put("type", ReplyTypeEnum.RESP.name());
							} else if (ReplyTypeEnum.CLUE.name().equals(reply.getXxlx()))  {
								//针对线索的回复
								map.put("type", ReplyTypeEnum.RESP.name());
							} else if (ReplyTypeEnum.INFO.name().equals(reply.getXxlx())) {
								//针对信息的回复
								map.put("type", ReplyTypeEnum.RESP.name());
							}
							return map;
						}
					});
			if (CollectionUtils.isNotEmpty(data)) {
				result.addAll(data);
			}
		}
		if (CollectionUtils.isNotEmpty(result)) {
			Collections.sort(result, new Comparator<Map<String, Object>>() {
				@Override
				public int compare(Map<String, Object> map1, Map<String, Object> map2) {
					return ASC(map1, map2);// 整体按时间进行升序排序
				}
			});
		}
		return result;
	}
}

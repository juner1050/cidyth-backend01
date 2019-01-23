package com.hyzs.cidyth.module.reply.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.hyzs.cidyth.common.enums.ReplyTypeEnum;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.module.appraise.vo.Appraise;
import com.hyzs.cidyth.module.clue.entity.Clue;
import com.hyzs.cidyth.module.reply.entity.Reply;
/**
 * 针对需求的互动
 * @author jidw
 *
 */
public class DemandInteractionContent extends InteractionContent {
	private static final Logger logger = LoggerFactory.getLogger(DemandInteractionContent.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 3055960607098913610L;
	private Collection<ImmutablePair<Clue, List<Map<String, Object>>>> clueList;// 所有线索和各自的附件列表的映射
	private static Map<Integer, Appraise> clueAppraiseMap;//线索对应的评价;
	private Collection<ImmutablePair<Reply, List<Map<String, Object>>>> replyList;// 所有的回复、针对回复的回复和各自的附件映射

	public void setClueList(List<ImmutablePair<Clue, List<Map<String, Object>>>> clueList) {
		this.clueList = clueList;
	}

	public void setClueAppraiseList(List<ImmutablePair<Clue, Appraise>> clueAppraiseList) {
		if(CollectionUtils.isNotEmpty(clueAppraiseList)){
			this.clueAppraiseMap = Maps.newHashMap();
			for(ImmutablePair<Clue, Appraise> pair:clueAppraiseList){
				if(pair.getLeft()!=null){
					this.clueAppraiseMap.put(pair.getLeft().getId(), pair.getRight());
				}
			}
		}
	}

	public void setReplyList(Collection<ImmutablePair<Reply, List<Map<String, Object>>>> replyList) {
		this.replyList = replyList;
	}
	
	public static Map<String, Object> convertClue(String topMessageIdentifer,Clue clue,List<Map<String, Object>> attachments){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", clue.getId());// 回复
		map.put("fromDepId", clue.getFkdwbh());// 发起方单位编号
		map.put("fromDeptName", clue.getFkdw());// 发起方单位名称
		map.put("fromUserId", clue.getFkry());// 发起人警号
		map.put("fromUserName", clue.getLrrymc());// 发起人姓名
		map.put("date", clue.getFkrq() == null ? null
				: DateUtil.formatDate(clue.getFkrq(), DateUtil.Y_M_D_H_M_S));// 日期
		map.put("content", clue.getXsnr());// 内容
		map.put("attachment", attachments);// 附件
		map.put("toDepId", null);// 接收方单位编号
		map.put("toDeptName", null);// 接收方单位名称
		map.put("toUserId", null);// 接收人警号
		map.put("toUserName", null);// 接收人姓名
		map.put("approveCount", clue.getApproveCount());// 点赞数量
		map.put("type", ReplyTypeEnum.CLUE.name());// 线索
		map.put("topMessageIdentifer", topMessageIdentifer);// 顶层消息标识符
		
		if(clueAppraiseMap!=null){
			Appraise appraise = clueAppraiseMap.get(clue.getId());
			if(appraise!=null){
				map.put("pffz", appraise.getPffz());//评分分值
			}else{
				map.put("pffz", null);
			}
		}
		return map;
	}
	
	public static Map<String, Object> convertReply(String topMessageIdentifer,Reply reply,List<Map<String, Object>> attachments){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", reply.getId());// 回复
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
		}
		map.put("topMessageIdentifer", topMessageIdentifer);// 顶层消息标识符
		return map;
	}
	@Override
	public List<Map<String, Object>> getContentList() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if (CollectionUtils.isNotEmpty(this.clueList)) {
			Collection<Map<String, Object>> data = Collections2.transform(this.clueList,
					new Function<ImmutablePair<Clue, List<Map<String, Object>>>, Map<String, Object>>() {
						@Override
						public Map<String, Object> apply(ImmutablePair<Clue, List<Map<String, Object>>> input) {
							Clue clue = input.getLeft();
							List<Map<String, Object>> attachments = input.getRight();
							return convertClue(null,clue,attachments);
						}
					});
			if (CollectionUtils.isNotEmpty(data)) {
				result.addAll(data);
			}
		}
		if (CollectionUtils.isNotEmpty(this.replyList)) {
			Collection<Map<String, Object>> data = Collections2.transform(this.replyList,
					new Function<ImmutablePair<Reply, List<Map<String, Object>>>, Map<String, Object>>() {
						@Override
						public Map<String, Object> apply(ImmutablePair<Reply, List<Map<String, Object>>> input) {
							Reply reply = input.getLeft();
							List<Map<String, Object>> attachments = input.getRight();
							return convertReply(null,reply,attachments);
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

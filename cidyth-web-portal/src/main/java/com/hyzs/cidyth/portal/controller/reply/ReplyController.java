package com.hyzs.cidyth.portal.controller.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.enums.ReplyTypeEnum;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.interaction.service.InteractionService;
import com.hyzs.cidyth.module.reply.service.ReplyService;
import com.hyzs.cidyth.module.reply.vo.InteractionContent;
import com.hyzs.cidyth.module.reply.vo.Replies;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags={"回复接口"})
@RestController
@RequestMapping("/reply")
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	@Autowired
	private ReplyService replyService;
	@Autowired
	private InteractionService interactionService;//互动服务
	/**
	 * 发送回复
	 * 
	 * @param reply
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ApiOperation(value = "发送回复", httpMethod = "POST", response = Map.class, notes = "发送回复")
	public Map<String, Object> send(@RequestBody Replies reply) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", replyService.saveReply(reply));
		return result;
	}

	/**
	 * 根据id获取回复
	 * 
	 * @param id
	 *            回复id
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value = "/get/{id}", method = { RequestMethod.GET, RequestMethod.POST })
//	@ApiOperation(value = "根据id获取回复", httpMethod = "POST", response = Replies.class, notes = "根据id获取回复")
//	public Replies getReply(@PathVariable String id) throws Exception {
//		return replyService.getReplyById(id);
//	}
	
	@RequestMapping(value = "/getDemandInteractionContent", method = { RequestMethod.GET})
	@ApiOperation(value = "根据需求或者信息id和类型(只能为'DEMAND'或者'INFO',分别表示'需求'和'消息')查询互动内容", httpMethod = "GET", response = List.class, notes = "根据id获取回复")
	public List<Map<String, Object>> getDemandInteractionContent(@RequestParam("referenceId") String referenceId,@RequestParam("type") String type){
		InteractionContent content = null;
		if(StringUtils.isBlank(type)){
			throw new ServiceException("类型参数不能为空!");
		}
		if(ReplyTypeEnum.DEMAND.name().equals(type)){
			content = interactionService.loadInteractionBodyForDemand(Lists.newArrayList(referenceId));
		}
		if(ReplyTypeEnum.INFO.name().equals(type)){
			content = interactionService.loadInteractionBodyForInfo(Lists.newArrayList(referenceId));
		}
		return content == null?null:content.getContentList();
	}
}

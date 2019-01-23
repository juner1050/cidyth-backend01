package com.hyzs.cidyth.app.controller.reply;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hyzs.cidyth.module.reply.service.ReplyService;
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
}

package com.hyzs.cidyth.portal.controller.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enterprisedt.util.debug.Logger;
import com.hyzs.cidyth.module.notice.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "全局通知" })
@RestController
@RequestMapping("notice")
public class NoticeController {

	private static final Logger logger = Logger.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;

	/**
	 * 加载当前用户未读通知
	 * 
	 * @return
	 */
	@RequestMapping(value = "loadNotReadNotice", method = RequestMethod.GET)
	@ApiOperation(value = "加载当前用户未读通知", httpMethod = "GET", response = List.class, notes = "加载当前用户未读通知")
	public List loadNotReadNotice() {
		return noticeService.loadNotRead();
	}

	/**
	 * 读通知
	 * 
	 * @param noticeId
	 */
	@RequestMapping(value = "readNotice", method = RequestMethod.GET)
	@ApiOperation(value = "读取通知", httpMethod = "GET", response = Void.class, notes = "读取通知")
	public void list(
			@ApiParam(required = true, name = "noticeId", value = "通知id字符串,多个通知id用逗号拼接") @RequestParam(required = true) String noticeId) {
		noticeService.read(noticeId);
	}

}

package com.hyzs.cidyth.module.notice.service.impl;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.notice.dao.NoticeMapper;
import com.hyzs.cidyth.module.notice.service.NoticeService;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.vo.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);
	@Autowired
	private NoticeMapper noticeMapper;


	public NoticeServiceImpl() {

	}

	@Override
	public List loadNotRead() {
		// 获取用户
		User user = UserUtil.getUser();
		if (user == null || StringUtils.isBlank(user.getAccount())) {
			throw new ServiceException("无法获取当前登录用户!");
		}
		return noticeMapper.selectNotRead(user.getAccount());
	}

	@Override
	public void read(String ids) {
		// 获取用户
		User user = UserUtil.getUser();
		if (user == null || StringUtils.isBlank(user.getAccount())) {
			throw new ServiceException("无法获取当前登录用户!");
		}
		if (StringUtils.isNotBlank(ids)) {
			List<String> idList = Lists.newArrayList(Splitter.on(",").split(ids));
			if (CollectionUtils.isNotEmpty(idList)) {
				int read = noticeMapper.readBatch(idList, user.getAccount());
				logger.info("User [" + user.getAccount() + "] has read {} notice.", read);
			}
		}
	}
}

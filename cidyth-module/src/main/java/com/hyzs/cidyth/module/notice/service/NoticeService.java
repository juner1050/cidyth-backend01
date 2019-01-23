package com.hyzs.cidyth.module.notice.service;

import java.util.List;

/**
 * 站内信
 * @author derrick
 *
 */
public interface NoticeService {
	/**
	 * 加载所有没有读过的
	 * @return
	 */
	public List loadNotRead();
	/**
	 * 读取通知
	 */
	public void read(String ids);
}

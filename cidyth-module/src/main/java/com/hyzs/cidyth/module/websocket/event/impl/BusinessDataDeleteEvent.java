package com.hyzs.cidyth.module.websocket.event.impl;

import com.hyzs.cidyth.module.websocket.event.BussinessDataEvent;

/**
 * 业务数据删除
 * 
 * @author derrick
 *
 */
public final class BusinessDataDeleteEvent extends BussinessDataEvent {

	public BusinessDataDeleteEvent(Object eventSource, Object data) {
		super(eventSource, data);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6112119474875167360L;

	

}

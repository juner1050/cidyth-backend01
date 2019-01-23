package com.hyzs.cidyth.module.websocket.event.impl;

import com.hyzs.cidyth.module.websocket.event.BussinessDataEvent;
/**
 * 业务数据生成
 * @author derrick
 *
 */
public final class BusinessDataCreateEvent extends BussinessDataEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5316175372147436934L;

	public BusinessDataCreateEvent(Object eventSource, Object data) {
		super(eventSource, data);
		
	}
}

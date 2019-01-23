package com.hyzs.cidyth.module.websocket.event.impl;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.hyzs.cidyth.module.websocket.event.BussinessDataEvent;

/**
 * 业务数据修改
 * 
 * @author derrick
 *
 */
public final class BusinessDataChangeEvent extends BussinessDataEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8333739347295071506L;

	public BusinessDataChangeEvent(Object eventSource, ImmutablePair<Object, Object> data) {
		super(eventSource, data);
	}

}

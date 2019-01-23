package com.hyzs.cidyth.module.websocket.event;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.context.ApplicationEvent;

import com.hyzs.cidyth.module.websocket.event.impl.BusinessDataChangeEvent;
import com.hyzs.cidyth.module.websocket.event.impl.BusinessDataCreateEvent;
import com.hyzs.cidyth.module.websocket.event.impl.BusinessDataDeleteEvent;

/**
 * 抽象的业务数据事件
 * 
 * @author derrick
 *
 */
public abstract class BussinessDataEvent extends ApplicationEvent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7754978326211013418L;

	private final Object eventSource;// 事件源对象
	private Object businessData;// 业务数据

	public BussinessDataEvent(Object eventSource) {
		super(eventSource);
		this.eventSource = eventSource;
	}

	public BussinessDataEvent(Object eventSource, Object data) {
		super(eventSource);
		this.eventSource = eventSource;
		this.businessData = data;
	}

	public Object getEventSource() {
		return eventSource;
	}

	public Object getBusinessData() {
		return businessData;
	}

	public static BusinessDataCreateEvent create(Object eventSource, Object data) {
		return new BusinessDataCreateEvent(eventSource, data);
	}
	public static BusinessDataChangeEvent change(Object eventSource, Object oldData,Object newData) {
		return new BusinessDataChangeEvent(eventSource, ImmutablePair.<Object, Object>of(oldData,newData));
	}
	public static BusinessDataDeleteEvent delete(Object eventSource, Object data) {
		return new BusinessDataDeleteEvent(eventSource, data);
	}
}

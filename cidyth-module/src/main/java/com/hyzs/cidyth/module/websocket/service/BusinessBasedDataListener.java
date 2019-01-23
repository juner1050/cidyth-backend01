package com.hyzs.cidyth.module.websocket.service;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.ApplicationListener;

import com.hyzs.cidyth.module.websocket.event.BussinessDataEvent;
public interface BusinessBasedDataListener extends ApplicationListener<BussinessDataEvent> {
	public static final String USER_INFO_KEY = "User";

	@Override
	public void onApplicationEvent(BussinessDataEvent event);
	/**
	 * 事件类型判断
	 * @author derrick
	 *
	 */
	public static class AccaptableEventValidator {
		/**
		 * 判断目标事件源是否是当前关注的事件源
		 * @param targetEnventSource 目标事件源
		 * @param accaptableEventSourceTypes 可接受的事件源类型范围
		 * @return 
		 */
		public static boolean isAcceptableEventSource(Object targetEnventSource, Set<Class<?>> accaptableEventSourceTypes) {
			if (targetEnventSource != null && CollectionUtils.isNotEmpty(accaptableEventSourceTypes)) {
				for (Class clazz : accaptableEventSourceTypes) {
					if (targetEnventSource.getClass().isAssignableFrom(clazz)) {
						return true;
					}
				}
			}
			return false;
		}
		/**
		 * 判断目标事件是否是当前可接受的事件
		 * @param targetEvent 目标事件
		 * @param accaptableEventTypes 可接受的事件类型范围
		 * @return
		 */
		public static boolean isAcceptableEvent(BussinessDataEvent targetEvent,
				Set<Class<? extends BussinessDataEvent>> accaptableEventTypes) {
			if (targetEvent != null && CollectionUtils.isNotEmpty(accaptableEventTypes)) {
				for (Class<? extends BussinessDataEvent> clazz : accaptableEventTypes) {
					if (targetEvent.getClass().isAssignableFrom(clazz)) {
						return true;
					}
				}
			}
			return false;
		}
	}
}

package com.hyzs.cidyth.module.websocket.event.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.hyzs.cidyth.common.config.MultiThreadTaskConfig;
@Component
public class BussinessDataEventPublisher implements ApplicationContextAware {
	private static final Logger logger = LoggerFactory.getLogger(BussinessDataEventPublisher.class);
	private static ApplicationContext beanfactory;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.beanfactory = applicationContext;
	}
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public static void publishEvent(ApplicationEvent event) {
		if (beanfactory != null){
			beanfactory.publishEvent(event);
			logger.debug("Event has been published...");
		}else{
			logger.error("Event publish unsuccessful.'beanfactory' is null.");
		}
	}
}

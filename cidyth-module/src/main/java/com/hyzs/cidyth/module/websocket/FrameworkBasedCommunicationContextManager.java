package com.hyzs.cidyth.module.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component("frameworkBasedCommunicationContextManager")
public class FrameworkBasedCommunicationContextManager implements BeanPostProcessor {
	private static final Logger logger = LoggerFactory.getLogger(FrameworkBasedCommunicationContextManager.class);
	private static Map<String, MessageContentValidator> messageContentValidatorContainer = new HashMap<String, MessageContentValidator>();

	/**
	 * 获取消息验证器
	 * 
	 * @param validatorKey
	 * @return
	 */
	public static MessageContentValidator getMessageContentValidator(String validatorKey) {
		return messageContentValidatorContainer.get(validatorKey);
	}

	/**
	 * 注册消息验证器
	 * 
	 * @param validator
	 */
	public void registerMessageContentValidator(MessageContentValidator validator) {
		if (validator != null) {
			messageContentValidatorContainer.put(
					StringUtils.isBlank(validator.name()) ? validator.getClass().getCanonicalName() : validator.name(),
					validator);
		}
	}

	/**
	 * 清空消息验证器
	 */
	public void clearMessageContentValidator() {
		messageContentValidatorContainer.clear();
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		List<Class<?>> list = new ArrayList<Class<?>>() {
			{
				addAll(ClassUtils.getAllInterfaces(bean.getClass()));
				addAll(ClassUtils.getAllSuperclasses(bean.getClass()));
			}
		};
		if (isTypeMached(list, MessageContentValidator.class)) {
			logger.debug("target reply content validator ==> " + bean.getClass().getCanonicalName());
			registerMessageContentValidator((MessageContentValidator) bean);
		}
		return bean;
	}

	private boolean isTypeMached(List<Class<?>> sourceTypes, Class tergetTypes) {
		if (CollectionUtils.isNotEmpty(sourceTypes) && sourceTypes.contains(tergetTypes)) {
			return true;
		}
		return false;
	}
}

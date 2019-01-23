package com.hyzs.cidyth.common.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hyzs.cidyth.common.helper.ContextUserHelper;
/**
 * 全局servlet 组件配置
 * @author derrick
 *
 */
@Configuration
public class GlobalServletComponentConfig {
	@Bean
	public ServletListenerRegistrationBean<ContextUserHelper> servletContextListenerBean() {
		ServletListenerRegistrationBean<ContextUserHelper> listenerBean = new ServletListenerRegistrationBean<ContextUserHelper>(
				new ContextUserHelper());
		return listenerBean;
	}
}

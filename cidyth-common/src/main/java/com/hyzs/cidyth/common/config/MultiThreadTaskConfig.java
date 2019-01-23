package com.hyzs.cidyth.common.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Multitask executor config
 * 
 * @author derrick
 *
 *         usage: use the
 *         annotation @Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)<br/>
 *         apply on the method that you want to execute asynchronously
 */
@Configuration
@EnableAsync
public class MultiThreadTaskConfig {
	public static final String MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME = "multiThreadTaskExecutor";

	@Bean(value = MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public Executor multiThreadTaskExecutor() {
		Executor executor = new ScheduledThreadPoolExecutor(8, new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}
}

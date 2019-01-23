package com.hyzs.cidyth.common.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.hyzs.cidyth.common.config.MultiThreadTaskConfig;

@Configuration
@EnableScheduling
@AutoConfigureAfter(MultiThreadTaskConfig.class)
public class ScheduledConfig implements SchedulingConfigurer {

	@Autowired(required = true)
	@Qualifier(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	private Executor executor;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(executor);
	}

}

package com.hyzs.cidyth;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication(scanBasePackages = { "com.hyzs" })
@EnableScheduling
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
@EnableCaching
public class Application extends SpringBootServletInitializer {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
	}

	public static void main(String[] args) {
		SpringApplication sa = new SpringApplication();

		ApplicationContext ctx = sa.run(Application.class, args);
		System.out.println("=======================cidyth-web-portal has been started.===========================");
		System.out.println("=======================cidyth-web-portal has been started测试01.===========================");

	}
}

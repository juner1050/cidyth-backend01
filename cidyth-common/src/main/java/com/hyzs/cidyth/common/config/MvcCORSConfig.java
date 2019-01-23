package com.hyzs.cidyth.common.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 * 跨域配置
 * @author jidw
 *
 */
@Configuration
public class MvcCORSConfig{
	/**
	 * 跨域过滤器
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean getCORSfilterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
		filterRegistration.setFilter(new CORSFilter());
		filterRegistration.addUrlPatterns("/*");
		filterRegistration.setName("CORSFilter");
		return filterRegistration;
	}
}

class CORSFilter extends OncePerRequestFilter{

	private final static String P3P_REQUEST_HEADER_VALUE = "CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"";

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
		//response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods",
				"PUT,POST,GET,DELETE,OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"X-Requested-With,Content-Type");
		response.setHeader("P3P", P3P_REQUEST_HEADER_VALUE);
		filterChain.doFilter(req, res);
	}
}
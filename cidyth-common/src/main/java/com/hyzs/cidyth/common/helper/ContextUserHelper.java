package com.hyzs.cidyth.common.helper;

import java.io.File;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 上下文用户信息辅助工具类
 * 
 * @author derrick
 *
 */
public final class ContextUserHelper extends RequestContextListener
		implements HttpSessionListener, ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(ContextUserHelper.class);
	public static final String CURRENT_LOGIN_USER = com.hyzs.cidyth.common.utils.Constant.CONTEXT_LOGIN_USER;
	public static String APPLICATION_ROOT_REAL_PATH = null;
	/**
	 * 获取当前环境信息
	 * 
	 * @return
	 */
	public static Locale getLocale() {
		return LocaleContextHolder.getLocale();
	}

	/**
	 * 存储当前登录用户
	 */
	public static void setLoginUser(Object user) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		if (request != null) {
			HttpSession session = request.getSession();
			if (session == null) {
				session = request.getSession();
			}
			session.setAttribute(CURRENT_LOGIN_USER, user);
		}
	}

	/**
	 * 获取当前登录用户
	 * 
	 * @return
	 */
	public static Object getLoginUser() {
		Object user = null;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		if (request != null) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				user = session.getAttribute(CURRENT_LOGIN_USER);
			}
		}
		return user;
	}
	/**
	 * 获取项目所在文件系统中的真实路径
	 * @return
	 */
	public static String getApplicationRootRealPath(){
		return APPLICATION_ROOT_REAL_PATH;
	}
	
	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		if (session != null) {
			session.removeAttribute(CURRENT_LOGIN_USER);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();
		if (ctx != null) {
			APPLICATION_ROOT_REAL_PATH = ctx.getRealPath(File.separator);
			logger.debug("APPLICATION_ROOT_REAL_PATH : " + APPLICATION_ROOT_REAL_PATH);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}

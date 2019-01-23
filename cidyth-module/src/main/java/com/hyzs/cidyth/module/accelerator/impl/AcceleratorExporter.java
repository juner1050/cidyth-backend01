package com.hyzs.cidyth.module.accelerator.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.hyzs.cidyth.module.accelerator.QueryAcceleratorPrepare;

@Configuration
@Component
public class AcceleratorExporter implements ApplicationContextAware {
	private static final Logger logger = LoggerFactory.getLogger(AcceleratorExporter.class);

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new ManualQueryAcceleratorServlet(), "/queryAccelerator/prepare");
	}

	public class ManualQueryAcceleratorServlet extends HttpServlet {
		private static final String PARAMETER_NAME = "name";
		/**
		 * 
		 */
		private static final long serialVersionUID = -8587421330112371885L;

		private String wrapExceptionHtmlBody(String msg) {
			return StringUtils.isNotBlank(msg) ? "<font color='red'>" + msg + "</font>" : "";
		}

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<meta content='text/html; charset=utf-8' http-equiv='Content-Type'>");
			out.println("<title>Init ManualQueryAccelerator</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<center style='font-size:25px;vertical-align:middle;padding-top:100px;'>");
			StringBuilder responseContent = new StringBuilder();
			String queryAcceleratorName = req.getParameter(PARAMETER_NAME);
			if (StringUtils.isNotBlank(queryAcceleratorName)) {
				if (applicationContext != null) {
					try {
						QueryAcceleratorPrepare bean = (QueryAcceleratorPrepare) applicationContext
								.getBean(queryAcceleratorName, QueryAcceleratorPrepare.class);
						if (bean != null) {
							bean.prepare();
							responseContent.append("Manual Init QueryAccelerator[" + queryAcceleratorName + "]...");
							logger.info(responseContent.toString());
						} else {
							String notFound = "Not Found QueryAccelerator instance named [" + queryAcceleratorName
									+ "]";
							responseContent.append(wrapExceptionHtmlBody(notFound));
							logger.error(notFound);
						}
					} catch (Exception e) {
						String exp = e.getMessage();
						logger.error("Failed to Init QueryAccelerator[" + queryAcceleratorName + "],{}", e);
						responseContent.append(wrapExceptionHtmlBody("Failed to Init QueryAccelerator[" + queryAcceleratorName + "],"+exp));
					}
				} else {
					responseContent.append(wrapExceptionHtmlBody(
							"application context is not ready to get QueryAccelerator instance named ["
									+ queryAcceleratorName + "]"));
				}
			}else{
				responseContent.append(wrapExceptionHtmlBody(
						"parameter named ["
								+ PARAMETER_NAME + "] and is Value are requried."));
			}
			out.print(responseContent);
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			out.flush();
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	private ApplicationContext applicationContext;
}

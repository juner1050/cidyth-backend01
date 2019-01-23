
package com.hyzs.cidyth.core.druid;

import com.alibaba.druid.filter.logging.LogFilter;
import com.alibaba.druid.filter.stat.StatFilter;

/**
 * druid datasource filters
 * 
 * @author derrick
 *
 */
public class ProxyFilterFactory {
	/**
	 * 日志
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static LogFilter logFilter(Class<? extends LogFilter> logClass)
			throws InstantiationException, IllegalAccessException {
		logClass = (logClass == null ? com.alibaba.druid.filter.logging.Slf4jLogFilter.class : logClass);
		LogFilter logFilter= logClass.newInstance();
		logFilter.setResultSetLogEnabled(false);
		return logFilter;
	}

	public static LogFilter logFilter() throws InstantiationException, IllegalAccessException {
		return logFilter(null);
	}

	/**
	 * 统计
	 * 
	 * @return
	 */
	public static StatFilter statFilter() {
		return new StatFilter();
	}
}

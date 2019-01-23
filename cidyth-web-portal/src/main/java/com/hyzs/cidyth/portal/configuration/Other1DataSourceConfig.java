package com.hyzs.cidyth.portal.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.filter.logging.LogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.hyzs.cidyth.core.druid.ProxyFilterFactory;
import com.hyzs.psd.gafa.daf.mybatis.tex.MapperScannerConfigurer;
/**
 * other1数据源配置
 * 
 * @author acer1
 */
@Configuration
@EnableConfigurationProperties(PageHelperProperties.class)
public class Other1DataSourceConfig {

	// 精确到 other1 目录，以便跟其他数据源隔离
	static final String PACKAGE = "com.hyzs.cidyth.module.*.dao";
	static final String MAPPER_LOCATION = "classpath:mapper/other1/*.xml";
	static final String SESSION_FACTORY_BEAN_NAME = "other1SqlSessionFactory";

	@Bean(name = "other1DataSource")
	@ConfigurationProperties(prefix = "other1.datasource")
	public DataSource other1DataSource() throws InstantiationException, IllegalAccessException {
		DruidDataSource dataSource=new DruidDataSource();
		LogFilter logFilter = ProxyFilterFactory.logFilter();
		StatFilter statFilter = ProxyFilterFactory.statFilter();
		statFilter.setLogSlowSql(true);
		statFilter.setSlowSqlMillis(8000);
		dataSource.setProxyFilters(Lists.newArrayList(logFilter,statFilter));
		return dataSource;
	}

	@Bean(name = "other1TransactionManager")
	public DataSourceTransactionManager other1TransactionManager(
			@Qualifier("other1DataSource") DataSource other1DataSource) {
		return new DataSourceTransactionManager(other1DataSource);
	}

	/**
	 * TODO:不同的数据源可能有不同的分页配置,不能使用同一个前缀pageHelper注入
	 * 
	 * @param other1DataSource
	 * @param pageProperties
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "other1SqlSessionFactory")
	public SqlSessionFactory other1SqlSessionFactory(@Qualifier("other1DataSource") DataSource other1DataSource,
			@Autowired PageHelperProperties pageProperties) throws Exception {
		// 分页插件
		PageHelper pageInterceptor = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("reasonable", String.valueOf(pageProperties.isReasonable()));
		properties.setProperty("supportMethodsArguments", String.valueOf(pageProperties.isSupportMethodsArguments()));
		properties.setProperty("returnPageInfo", pageProperties.getReturnPageInfo());
		properties.setProperty("params", pageProperties.getParams());

		pageInterceptor.setProperties(properties);

		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(other1DataSource);
		sessionFactory.setPlugins(new Interceptor[] { pageInterceptor });
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(Other1DataSourceConfig.MAPPER_LOCATION));
		return sessionFactory.getObject();
	}

	@Bean(name = "other1MapperScannerConfigurer")
	public MapperScannerConfigurer other1MapperScannerConfigurer() {
		Properties properties = new Properties();
		properties.put("mappers", "com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper");

		MapperScannerConfigurer msc = new MapperScannerConfigurer();
		msc.setSqlSessionFactoryBeanName(Other1DataSourceConfig.SESSION_FACTORY_BEAN_NAME);
		msc.getMapperHelper().setProperties(properties);
		msc.setSqlSessionFactoryBeanName("other1SqlSessionFactory");
		msc.setBasePackage(Other1DataSourceConfig.PACKAGE);

		return msc;
	}
}

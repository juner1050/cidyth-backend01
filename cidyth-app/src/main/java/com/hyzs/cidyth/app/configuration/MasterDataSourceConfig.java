package com.hyzs.cidyth.app.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
 * 主数据源的配置
 *
 * @author acer
 */
@Configuration
@EnableConfigurationProperties(PageHelperProperties.class)
public class MasterDataSourceConfig {
    private Logger logger = LoggerFactory.getLogger(MasterDataSourceConfig.class);
	
	// 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.hyzs.cidyth.module.*.dao";
    static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";
    static final String SESSION_FACTORY_BEAN_NAME="masterSqlSessionFactory";

	@Primary
	@Bean(name = "masterDataSource")
	@ConfigurationProperties(prefix="master.datasource")
	public DataSource masterDataSource() throws InstantiationException, IllegalAccessException {
		DruidDataSource dataSource= new DruidDataSource();
		LogFilter logFilter = ProxyFilterFactory.logFilter();
		StatFilter statFilter = ProxyFilterFactory.statFilter();
		statFilter.setLogSlowSql(true);
		statFilter.setSlowSqlMillis(5000);
		dataSource.setProxyFilters(Lists.newArrayList(logFilter,statFilter));
		return dataSource;
	}

	@Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDataSource") DataSource masterDataSource) {
        return new DataSourceTransactionManager(masterDataSource);
    }

    /**
     *  TODO:不同的数据源可能有不同的分页配置,不能使用同一个前缀pageHelper注入
     * @param masterDataSource
     * @param pageProperties
     * @return
     * @throws Exception
     */
	@Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource,
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
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setPlugins(new Interceptor[] { pageInterceptor });
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MasterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name="masterMapperScannerConfigurer")
    public MapperScannerConfigurer masterMapperScannerConfigurer(){
        Properties properties=new Properties();
        properties.put("mappers","com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper");

        MapperScannerConfigurer msc=new MapperScannerConfigurer();
        msc.setSqlSessionFactoryBeanName(MasterDataSourceConfig.SESSION_FACTORY_BEAN_NAME);
        msc.getMapperHelper().setProperties(properties);
        msc.setSqlSessionFactoryBeanName("masterSqlSessionFactory");
        msc.setBasePackage(MasterDataSourceConfig.PACKAGE);

        return msc;
    }

}






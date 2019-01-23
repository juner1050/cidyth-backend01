package com.hyzs.cidyth.portal.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
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

    @Bean
    public ServletRegistrationBean druidStatViewServlet(){
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");

        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        //servletRegistrationBean.addInitParameter("deny","192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
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
        properties.put("mappers","com.hyzs.psd.gafa.daf.mybatis.tk.common.Mapper,com.hyzs.psd.gafa.daf.mybatis.tk.common.MySqlMapper");

        MapperScannerConfigurer msc=new MapperScannerConfigurer();
        msc.setSqlSessionFactoryBeanName(MasterDataSourceConfig.SESSION_FACTORY_BEAN_NAME);
        msc.getMapperHelper().setProperties(properties);
        msc.setSqlSessionFactoryBeanName("masterSqlSessionFactory");
        msc.setBasePackage(MasterDataSourceConfig.PACKAGE);

        return msc;
    }

}






package com.zepal.seckill.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource(value= {"classpath:db.properties"},encoding="utf-8")
public class DBConfig {

	@Value("${seckill.datasource.url}")
	private String dataSource_url;
	
	@Value("${seckill.datasource.driver}")
	private String dataSource_driver;
	
	@Value("${seckill.datasource.username}")
	private String dataSource_username;
	
	@Value("${seckill.datasource.password}")
	private String dataSource_password;
	
	@Value("${seckill.datasource.initialPoolSize}")
	private String initialPoolSize;
	
	@Value("${seckill.datasource.maxPoolSize}")
	private String maxPoolSize;
	
	@Value("${seckill.datasource.minPoolSize}")
	private String minPoolSize;
	
	@Value("${seckill.datasource.maxIdleTime}")
	private String maxIdleTime;
	
	@Value("${seckill.datasource.checkoutTime}")
	private String checkoutTime;
	
	@Value("${seckill.datasource.acquireRetryAttempts}")
	private String acquireRetryAttempts;
	
	@Bean
	public DataSource initDataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setJdbcUrl(dataSource_url);
		dataSource.setDriverClass(dataSource_driver);
		dataSource.setUser(dataSource_username);
		dataSource.setPassword(dataSource_password);
		/* ------------------------------------- */
		//关闭连接后不自动提交
		dataSource.setAutoCommitOnClose(false);
		dataSource.setInitialPoolSize(Integer.valueOf(initialPoolSize));
		dataSource.setMinPoolSize(Integer.valueOf(minPoolSize));
		dataSource.setMaxPoolSize(Integer.valueOf(maxPoolSize));
		dataSource.setMaxIdleTime(Integer.valueOf(maxIdleTime));
		dataSource.setCheckoutTimeout(Integer.valueOf(checkoutTime));
		dataSource.setAcquireRetryAttempts(Integer.valueOf(acquireRetryAttempts));
		return dataSource;
	}
}

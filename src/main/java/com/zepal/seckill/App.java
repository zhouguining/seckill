package com.zepal.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *  <p>关闭数据源自动配置
 * */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})//启动注解
@MapperScan("com.zepal.seckill.dao")//dao层接口,还可以在mybatis配置文件中指定
@EnableTransactionManagement//开启事务
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

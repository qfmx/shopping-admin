package com.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@MapperScan("com.springcloud.dao")
public class SpringCloudOrdersProvider {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringCloudOrdersProvider.class, args);
	}

}
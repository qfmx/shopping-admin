package com.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
/**
 * 商品微服务的入口类
 * @author Ya
 *
 */
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.springcloud.dao") //设置Mybatis接口所在的位置
public class SpringCloudGoodsProvider {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringCloudGoodsProvider.class, args);
	}
}

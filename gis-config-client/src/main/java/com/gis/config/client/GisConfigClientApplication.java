package com.gis.config.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * 主程序入口类
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://gisnci.com">Hongyu Jiang</a>
 */
@SpringBootApplication
@EnableDiscoveryClient // 在注册中心发现服务
@EnableAutoConfiguration(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class GisConfigClientApplication {

	private  static Logger logger = LoggerFactory.getLogger(GisConfigClientApplication.class);
	/**
	 * main函数参数定义
	 * 0	客户端类型(_clientType),如mswss
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(GisConfigClientApplication.class, args);
		logger.info("[GisConfigClient微服务] 已启动");
	}

}

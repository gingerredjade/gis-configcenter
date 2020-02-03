package com.gis.config.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 应用启动入口类
 * 	设置扫描了所有包
 * 	支持定时任务
 *
 * 	@EnableDiscoveryClient表明它是服务注册组件的客户端（对ZK、Eureka适用）
 * 	@EnableEurekaClient只对Eureka适用
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://126.com">Hongyu Jiang</a>
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages = {"com.gis.config.server"})
@EnableAutoConfiguration(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class GisConfigServerApplication {
	private  static Logger logger = LoggerFactory.getLogger(GisConfigServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GisConfigServerApplication.class, args);
		logger.info("[GisConfigServer微服务] 已启动");
	}

}

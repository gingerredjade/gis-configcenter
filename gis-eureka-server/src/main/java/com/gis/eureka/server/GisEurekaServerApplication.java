package com.gis.eureka.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 主程序入口类
 * 	@SpringBootApplication表示是Spring Boot应用
 * 	包含一个main()方法,且通过SpringApplication类的run()方法运行该类
 * 	@EnableEurekaServer表明它是一个服务注册组件的服务端
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://gisnci.com">Hongyu Jiang</a>
 */
@SpringBootApplication
@EnableEurekaServer
@EnableAutoConfiguration(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class GisEurekaServerApplication {
	private  static Logger logger = LoggerFactory.getLogger(GisEurekaServerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(GisEurekaServerApplication.class, args);
		logger.info("[GisEurekaServer微服务] 已启动");
	}
}

package com.gis.config.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.web.ManagementContextConfiguration;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.annotation.Bean;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

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

	public static void main(String[] args) {
		SpringApplication.run(GisConfigClientApplication.class, args);
		logger.info("[GisConfigClient微服务] 已启动");
	}

}

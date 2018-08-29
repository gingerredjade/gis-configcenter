package com.gis.config.serverui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 应用启动入口类
 * 	设置扫描了所有包
 * 	支持定时任务
 *
 * 	@EnableDiscoveryClient表明它是服务注册组件的客户端（对ZK、Eureka适用）
 * 	@EnableEurekaClient只对Eureka适用
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://gisnci.com">Hongyu Jiang</a>
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GisConfigServerUiApplication {

    private  static Logger logger = LoggerFactory.getLogger(GisConfigServerUiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GisConfigServerUiApplication.class, args);
        logger.info("[GisConfigServerUi微服务] 已启动");
	}
}

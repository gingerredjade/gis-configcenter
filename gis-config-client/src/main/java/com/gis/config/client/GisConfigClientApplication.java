package com.gis.config.client;

import com.gis.config.client.common.ConfigManager;
import com.gis.config.client.service.mswss.MswssSvcConfigManager;
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

	private static final String CLIENT_MSWSS = "mswss";

	/**
	 * main函数参数定义
	 * 0	客户端类型(_clientType),如mswss
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(GisConfigClientApplication.class, args);
		logger.info("[GisConfigClient微服务] 已启动");

		// TODO.. 设置启动参数 启动成功后，根据不同参数执行不同客户端修改功能

		// 解析参数
		String _clientType = "";

		/**
		 * 修改MSWSS客户端的配置信息
		 */
		if (args.length == 0) {
			logger.warn("no args, 'clientType' is null, nothing config to update.");
			return;
		} else if (args.length == 1) {
			_clientType = args[0];
		} else {
			System.err.println("参数有误，请按要求填写所需的1个参数[clientType] ");
			return;
		}

		if (_clientType.equalsIgnoreCase(CLIENT_MSWSS)) {
			ConfigManager cm = new MswssSvcConfigManager();
			try {
				cm.confUpdate(_clientType);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("修改MSWSS Client配置文件信息失败：" + e.getMessage());
			}
		} else {
			// TODO.. 适配其他微服务客户端
		}


	}

}

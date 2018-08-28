package com.gis.config.clientrefresh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroserviceConfigClientRefreshApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceConfigClientRefreshApplication.class, args);
		System.out.println("【【【【【【 ConfigClientRefreshBus微服务 】】】】】】已启动.");
	}
}

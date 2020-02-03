package com.gis.config.client.controller.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello Controller.
 *
 * 	@RefreshScope
 * 
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://126.com">Hongyu Jiang</a>
 */
@Api(value = "HelloController",description = "Hello测试相关的API")
@RestController
@RefreshScope // 使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中
@RequestMapping("/testhello")
public class HelloController {

	@ApiOperation(value="客户端hello测试",notes="第一个hello测试api<br />换行test")
	@GetMapping("/hello")
	public String hello() {
		return "Hello World!Spring Cloud Config Client";
	}

	// 从对应的配置中心找到文件并把属性注入到value值中
	@Value("${redis.server.port}")
	private String redis_server_port;

	@Value("${redis.server.requirepass}")
	private String redis_server_pass;

	@ApiOperation(value="redis服务器端口",notes="Redis服务器端访问端口<br />")
	@GetMapping("/redis_server_port")
	public String redis_server_port() {
		return redis_server_port;
	}

	@ApiOperation(value="redis服务器密码",notes="Redis服务器端访问密码")
	@GetMapping("/redis_server_pass")
	public String redis_server_pass() {
		return redis_server_pass;
	}

}

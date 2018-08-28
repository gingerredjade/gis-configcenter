package com.gis.eureka.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Hello Controller.
 * 	@RestController表示它具备发布REST API的能力，
 * 		还能将每个REST API的返回值自动序列化为JSON格式。
 * 
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://gisnci.com">Hongyu Jiang</a>
 */
@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello World!gis eureka server!";
	}
}

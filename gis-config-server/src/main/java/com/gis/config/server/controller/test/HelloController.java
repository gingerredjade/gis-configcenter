package com.gis.config.server.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello Controller.
 * 
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://126.com">Hongyu Jiang</a>
 */
@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello() {
	return "Hello World! Spring Cloud Config Server!!!";
	}

}

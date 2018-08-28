package com.gis.config.server.controller.test;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;

/**
 * Hello Controller.
 * 
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://gisnci.com">Hongyu Jiang</a>
 */
@RestController
public class HelloController {

	@GetMapping("/hello")
	public String hello() {
	return "Hello World! Spring Cloud Config Server!!!";
	}

}

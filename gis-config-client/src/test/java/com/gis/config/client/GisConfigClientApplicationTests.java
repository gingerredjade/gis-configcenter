package com.gis.config.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GisConfigClientApplicationTests {

	/**
	 * 这个配置文件不是在应用本身而是在云端的
	 */
	@Value("${redis.server.ip}")
	private String redis_server_ip;

	@Value("${redis.server.port}")
	private String redis_server_port;

	@Value("${redis.server.requirepass}")
	private String redis_server_requirepass;

	@Test
	public void contextLoads() {
		// 写断言判断督导的内容是否是"waylau.com"
		//assertEquals("waylau.com", auther);
		System.out.println(redis_server_ip);
		System.out.println(redis_server_port);
		System.out.println(redis_server_requirepass);
	}

}

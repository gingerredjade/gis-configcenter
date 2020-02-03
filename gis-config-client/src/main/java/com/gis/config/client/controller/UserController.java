package com.gis.config.client.controller;

import com.gis.config.client.model.test.User;
import com.gis.config.client.service.test.TestInterface;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

/**
 * 用户 Controller
 *
 * Spring Boot默认按照包顺序注入，要在service注入后创建Controller
 * Spring Boot不需要传统的xml配置扫描包，只需要添加注解@ComponentScan(basePackages={“com.gis.config.client.service”})
 *
 * @Api 用在类上说明该类的作用
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://126.com">Hongyu Jiang</a>
 */
@Api(value = "UserController",description = "用户相关的API")
@RestController
@EnableAutoConfiguration
@RequestMapping("usertest")
@EnableConfigurationProperties(User.class)
@ComponentScan(basePackages = {"com.gis.config.client.service"})
public class UserController implements UserContIntf {

    // 日志
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TestInterface testInterface;

    // 通过构造器注入
    private final User user;

    @Autowired
    public UserController(User user) {
        this.user = user;
    }

    @Override
    public User user() {
        return user;
    }

    @Override
    public User getUser() {
        // 打印日志
        logger.info("TestBootController getUser info......");
        logger.error("TestBootController getUser error......");

        return testInterface.testGetUser();
    }

    @Override
    public User putUser(User user) {
        return testInterface.testPutUser(user);
    }
}

package com.gis.config.client.controller;

import com.gis.config.client.model.test.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * UserController对应的接口类
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://126.com">Hongyu Jiang</a>
 */
public interface UserContIntf {

    @ApiOperation(value = "获取默认构造用户",notes = "获取默认构造用户",
            httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping("/user")
    public User user();

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息",
            httpMethod = "GET",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping("/get")
    @ResponseBody
    public User getUser();

    @ApiOperation(value = "选择添加User", notes = "利用User实体进行参数选择性的插入<br />")
    @ApiImplicitParam(name = "user", value = "用户实体User", required = true, dataType = "User")
    @RequestMapping(value = "/insertUser", method = RequestMethod.PUT)
    @ResponseBody
    public default User putUser(@RequestBody User user) {
        return null;
    }
}

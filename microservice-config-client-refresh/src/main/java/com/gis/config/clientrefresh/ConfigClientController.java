package com.gis.config.clientrefresh;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${redis.server.port}")
    private String redis_server_port;

    @GetMapping("/redis_server_port")
    public String redis_server_port() {
        return redis_server_port;
    }

}

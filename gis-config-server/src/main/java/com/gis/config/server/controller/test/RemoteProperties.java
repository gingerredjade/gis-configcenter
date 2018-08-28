package com.gis.config.server.controller.test;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * @Configuration 表明这是一个配置类
 * @ConfigurationProperties(prefix = "remote", ignoreUnknownFields = false) 该注解用于绑定属性。
 *      prefix用来选择属性的前缀，也就是在remote.properties文件中的“remote”，ignoreUnknownFields是用来告诉SpringBoot在有属性不能匹配到声明的域时抛出异常。
 * @PropertySource("classpath:config/remote.properties") 配置文件路径
 * @Data 这个是一个lombok注解，用于生成getter&setter方法，详情请查阅lombok相关资料
 * @Component 标识为Bean
 *
 * Data注解有问题，还不能读到配置里的数据
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://gisnci.com">Hongyu Jiang</a>
 */

//@Configuration
//@ConfigurationProperties(prefix = "mswss-config", ignoreUnknownFields = false)
//@PropertySource("classpath:properties/mswss-146-prod.properties")
//@Component
public class RemoteProperties {

    private String auther;
    private String version;

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

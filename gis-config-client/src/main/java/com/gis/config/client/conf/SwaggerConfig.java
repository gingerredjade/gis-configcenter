package com.gis.config.client.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 * 实际上，为了形成一个完整的api文档，需要添加的注解常常很多，若是都写在同一个文件里就会显得臃肿
 * 我们常常会写一个接口文件，将注解都放在接口文件中，然后再用一个实体类来实现控制器，算是实现配置和逻辑分离了吧。
 *
 *  @EnableSwagger2用来启动Swagger支持，表示这是一个Spring Swagger的配置文件
 *
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://gisnci.com">Hongyu Jiang</a>
 */
@Configuration      //必须存在
@EnableSwagger2     //必须存在
//@ComponentScan(basePackages = {"com.gis.config.client"}) //必须存在 扫描的API Controller package name 也可以直接扫描class (basePackageClasses)
public class SwaggerConfig extends WebMvcConfigurationSupport {

    /**
     * UI页面显示信息
     */
    private static String apiTitle = "GIS Config Center Client";
    private static String apiDesc = "Spring Boot中使用Swagger2构建APIs";
    private static String apiVersion = "1.0.0";
    private static String basePackage = "com.gis.config.client";

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket accessToken() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("GIS APIs")                 // 定义组
                .select()                               // 选择哪些路径和api会生成document
                //.apis(RequestHandlerSelectors.any())  // 对所有api进行监控
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())             // 对所有路径进行监控
                //.paths(regex("/api/.*"))              // 拦截的接口路径
                .build() // 创建
                .apiInfo(apiInfo());                    // 配置说明
    }

    /**
     * Spring中名字并不重要，重要的是它返回一个Docket类，DocumentationType.SWAGGER_2作为Docket构造方法的参数，指定了所用的swagger版本2.0，官网上已经在预告3.0版本了。
     * 而之后的apiInfo则是调用接下来的apiInfo函数，来创建Docket的信息。
     * apiInfo函数采用ApiInfoBuilder来创建ApiInfo类
     * @return
     */
    private static ApiInfo apiInfo() {
        //Contact contact = new Contact("gis", "http://www.gis.com/configcenter/", "gis@126.com");
        return new ApiInfoBuilder()
                .title(apiTitle + "-API")                            // 标题
                .description(apiDesc) // 描述
                .termsOfServiceUrl("https://spring.io/")           //
                //.contact(contact)                                  // 联系
                //.license("gis 1.0")                                // 开源协议
                //.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE") // 地址
                .version(apiVersion)                                 // 版本
                .build();
    }

    /**
     * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。
     * 需要重新指定静态资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}

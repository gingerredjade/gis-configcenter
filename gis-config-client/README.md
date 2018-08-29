# Spring Cloud Config Client高可用
- 启动多个实例即可

````
1. Edit Configurations
2. 添加相同的Spring Boot启动类
3. 为每个启动类设置不同的端口
   -Dserver.port=9001
````

- 启动多个实例
- 重启应用服务
- 自动负载均衡访问多个客户端实例

# 组件功能支持说明
## 1. 处理http/json 请求
## 2. 日志记录 
- Spring Boot自带日志，直接在Spring Boot中添加日志
- 生成日志文件，添加Spring Boot默认支持的logback作为标准日志输出

```
- 日志按天记录，自动生成当天的记录文件 
- 日志分级存储（info,error）
```

## 3. 定时任务
- Spring Boot定时任务的启动和配置要简单很多，只需要增加一个注解EnableScheduling即可

## 4. 服务注册发现
- 当服务启动时，自动将其信息注册到服务注册表中，如每个服务的IP与端口。

## 5. swagger 
http://localhost:8080/swagger-ui.html
http://localhost:8080/v2/api-docs可以看到之前配置的诸多信息。注入description，version，title等。并且确实有TestController的信息。

## 6. 配置信息手动刷新
curl -X POST http://localhost:8080/actuator/refresh 



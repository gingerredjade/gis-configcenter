# 一、框架搭建环境
````
- 编译器：IDEA 2017.2.5
- Maven：3.3.9
- JDK：1.8.0_144
- 系统：Win 10
````

# 二、开发框架
Spring Boot 2.0（2.0.4）

```
1. 新增特性
2. 代码重构
3. 配置变更
```

```
http://spring.io
https://github.com/spring-projects/spring-boot/wiki可查看发布日志
https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Configuration-Changelog查看配置的改变
```

# 三、maven使用办法
## 1. 拷贝maven仓库至本地环境（默认仓库位置/自定义位置）
## 2. 准备maven环境
- 本机安装maven环境

```
配置maven中仓库的位置（默认/自定义配置）
```
- 本机可以不用安装maven环境(Linux中方法同)

```
(1)将maven压缩包放到某个目录下"E:/apache-maven-3.5.2-bin.zip"
(2)编辑项目中的.mvn/wrapper/maven-wrapper.properties
(3)distributionUrl设为第二步设置的目录"E:/apache-maven-3.5.2-bin.zip"
```

# 四、配置中心gis-configcenter组件结构
- gis-eureka-server
- gis-config-server、gis-config-server-ui
- gis-config-client
- microservice-config-client-refresh(仅测试消息总线用)

## 1. 配置中心服务组件启动顺序
项目源码使用模块编程，client端注册到了eureka服务端，所以启动项目应该也要启动eureka的服务端。
然后再启动config服务端，最后启动config-client端，启动config-client要使用profile方法启动。
1. gis-eureka-server
2. gis-config-server、gis-config-server-ui
3. gis-config-client

## 2. 端口
eureka-server:8761
config-server:7070
config-server-ui:7071
config-client:8080

测试：
1. http://localhost:8761
2. 查看服务的配置信息

```
- http://localhost:7070/mswss-146/dev
- http://localhost:7070/mswss-146-dev.properties
- http://localhost:7070/mswss-146-dev.json
- http://localhost:7070/mswss-146-dev.yml

- http://localhost:7070/mswss-157/prod
- http://localhost:7070/mswss-157-prod.properties
- http://localhost:7070/mswss-157-prod.json
- http://localhost:7070/mswss-157-prod.yml
```

3. http://localhost:8080/testhello/redis_server_port
4. 修改配置-->/bus-refresh
5. http://localhost:8080/testhello/redis_server_port已获取最新配置信息


# 五、build - 打包
## 1. IDEA界面
## 2. 命令行
```
1 进入项目
2 mvn clean package或mvn clean install
```

# 六、run - 启动、停止
## 1. 启动
将jar包上传到指定服务器上，采用 java -jar *.jar 的方式  

linux下可以通过nohup或者supervisor(推荐）进行启动，启动命令中建议加入spring.profiles.active参数，指定使用生产环境的配置，该配置可以application-prod.properties中指定

使用prod模式时，日志文件会自动输出到当前目录的logs文件中，可通过```tail -100f logs/gis-frame.log```进行查看

- nohup java -jar target/xxxx.jar > /dev/null 2>&1 &
- nohup java -jar -Dspring.profiles.active=prod target/xxxx.jar > console.file 2>&1 &
## 2. 停止
- ps -ef|grep eureka
- kill进程以停止服务

# 七、配置文件约定
配置文件使用本地存储模式，且独立于配置中心服务端，以支持配置中心服务端能自动读取本地仓库最新的配置信息供客户端获取使用。

使用模式
1. 镜像方式（一般使用该方式）
- 1.1 准备配置文件数据
- 1.2 固定配置文件本地仓库路径为/var/config-center/config-repo
- 1.3 配置中心服务端application.properties文件中spring.cloud.config.server.native.search-locations属性指定该路径
- 1.4 镜像制作
    * 1.4.1 创建配置配置文件本地仓库路径
    - 1.4.2 添加配置文件到本地仓库
    - 1.4.3 添加配置中心服务端
    * 1.4.4 启动配置中心服务端
- 1.5 交付配置中心服务端Docker镜像

2. Linux环境
- 2.1 准备配置文件数据
- 2.2 固定配置文件本地仓库路径为/var/config-center/config-repo
- 2.3 配置中心服务端application.properties文件中spring.cloud.config.server.native.search-locations属性指定该路径
- 2.4 自行检测、创建本地仓库，并上传配置文件信息
- 2.5 启动配置中心服务端

3. Windows环境
- 3.1 准备配置文件数据
- 3.2 自定义配置文件本地仓库路径，并上传配置文件信息
- 3.3 配置中心服务端application.properties文件中spring.cloud.config.server.native.search-locations属性指定该路径
- 2.5 启动配置中心服务端


当Config Server启动后，如果配置发生了修改，微服务也应该实现配置的刷新。
# 八、动态刷新Config Client配置
## 1. rabbitmq安装
最方便的是运行rabbitmq镜像实例。参照WebGIS Blog。
```
0. docker pull rabbitmq:3.7.7-management
1. 准备镜像:rabbitmq:3.7.7-management.tar
2. 运行容器:docker run -d --name rabbitmq -p  5671:5671 -p 5672:5672 -p 4369:4369 -p 25672:25672 -p 15671:15671 -p 15672:15672 reg.gisnci.com/mirrors-opensource/rabbitmq:3.7.7-management
3. 容器启动之后就可以访问web 管理端了——http://宿主机IP:15672，默认创建了一个 guest 用户，密码也是 guest。
目前使用K8S集群失败，使用56.213上的独立docker部署：
1.进入56.213
2.docker container start 8931bb67f095
3.访问http://192.168.56.213:15672
```

## 2. 单点手动动态刷新Config Client配置（/actuator/refresh）
给客户端发送执行刷新的命令：
```
- 使用Postman发送POST请求：http://localhost:8080/actuator/refresh
- 使用终端发送POST请求：curl -X POST http://localhost:8080/actuator/refresh
```

刷新后客户端再获取就是更新后的数据了。

## 3. Config Client配置信息实时刷新-Spring Cloud Bus（/actuator/bus-refresh）
使用rabbitmq消息中间件产品来增强刷新机制，解决多台配置客户端自动刷新配置问题。

Spring Cloud Bus做配置更新步骤如下：
- 在服务端出发POST请求给/actuator/bus-refresh
- Config Server端接收到请求并发送给Spring Cloud Bus
- Spring Cloud Bus接到消息并通知给其它客户端
- 其它客户端接收到通知，请求Server端获取最新配置
- 全部客户端均获取到最新的配置

```
安装erlang、rabbitmq（Windows测试：Erlang 19.3，rabbitmq 3.7.7）

guest guest账户访问rabbit：http://localhost:15672/

使Config Server端支持/actuator/bus-refresh即可
- 增添依赖 
- 增加配置

- 使用Postman向服务端发送POST请求：http://localhost:7070/actuator/bus-refresh
- 使用终端向服务端发送POST请求：curl -X POST http://localhost:7070/actuator/bus-refresh
```


## 4. 测试
```
http://192.168.55.111:8761/ 查看注册中心
http://192.168.55.111:7070/mswss-146/dev    查看mswss-146-dev.properties配置
http://192.168.55.111:8080/testhello/redis_server_port  获取客户端1的某配置
http://192.168.55.111:8081/redis_server_port    获取客户端2的某配置
修改配置信息
http://localhost:7070/actuator/bus-refresh 发送POST消息
http://192.168.55.111:8080/testhello/redis_server_port  获取客户端1的更新的某配置
http://192.168.55.111:8081/redis_server_port    获取客户端2的更新的某配置
```


# 九、健康状态检测

给客户端发送执行健康状态检测的命令：
```
- 使用Postman发送GET请求：http://localhost:8080/actuator/health
- 使用终端发送GET请求：curl -X GET http://localhost:8080/actuator/health
```


# 十、知识点记录
## 10.1 Config Client的Controller编写规则
每个Controller类和其内部的方法都要编写详细的API说明，且要做到配置和逻辑分离。interface+implements，注解和注释写在interface中。
可参考gis-config-client.

## 10.2 Spring Cloud Config简介
- Config Server可横向扩展、集中式的配置服务器，集中管理应用程序各个环境下的配置；
- Config Client是Config Server的客户端，用于操作存储在Config Server中的配置属性；

Spring Cloud Config分服务端和客户端，服务端负责将git/svn/本地中存储的配置文件发布成REST接口，客户端可以从服务端REST接口获取配置。
但客户端并不能主动感知到配置的变化，从而主动去获取新的配置。
Spring Cloud已经给我们提供了客户端主动获取新的配置信息的解决方案，每个客户端通过POST方法触发各自的/refresh。

本地仓库是指将所有的配置文件统一写在Config Server工程目录下。
config server暴露Http API接口，config client通过调用config server的Http API接口来读取配置文件


## 10.3 注册中心
```
分布式系统中，服务注册中心是最重要的基础部分
示例：注册中心、A、微服务B：
微服务B启动的时候向注册中心进行注册；
A是如何拿到B的信息呢？两种方法：
1.  客户端发现（由A发起）
    A直接找注册中心，注册中心把它上面所有B服务的信息告诉A
    A从注册中心提供的众多可用的B中通过负载均衡机制（轮询、Hash）挑出一个
    再通过IP地址找到B
    Eureka采用的是该方式    
2.  服务端发现（代理）
    代理帮A从众多B中挑选一个出来，然后A再去找B。
    如下技术采用的是该方式：
    - Nginx
    - Dubbo
    - Kubernetes（集群中的每个节点都运行一个代理来实现服务发现的功能）
```

## 10.4 微服务的特点：异构
- 不同语言
- 不同类型的数据库

## 10.5 目前流行的通信方式
- REST（Spring Cloud的服务调用方式）
- RPC
- Node.js的eureka-js-client
Eureka、支持将非Java语言实现的服务纳入到自己的服务治理体系中，只需要其他语言实现eureka的客户端程序。

## 10.6 使用Swagger自动生成REST API文档
### 10.6.1 接口文档
遇到如下场景需要提供接口文档
- 前后端分离
- 第三方合作

### 10.6.2 简介

```
https://swagger.io/
Swagger是一个规范和完整的框架，用于生成、描述、调用和可视化RESTful风格的Web服务。http://swagger.io/
springfox的前身是swagger-springmvc，是一个开源的API doc框架，可以将我们的Controller的方法以文档的形式展现，基于Swagger。
http://springfox.github.io/springfox/

Swagger Editor：用于编辑REST API文档.支持编辑Swagger API规范yaml文档描述API，并可实时预览API
Swagger Codegen：用于生成REST API文档.一个模板驱动引擎，通过分析用户Swagger资源声明以各种语言生成客户端SDK或Server端桩代码，从而让开发团队能够更好的关注API的实现和调用，提高开发效率
Swagger UI：用于查看REST API文档。API在线文档生成和测试的工具，可显示API描述，并且支持调用API进行测试及验证

服务端启动后，能通过Swagger UI在浏览器中查看已发布的REST API文档。

注：Spring Cloud Config Server不能集成Swagger，否则swagger ui的资源加载不了，只能集成在客户端。
```


## 10.7 Web开发——Spring Boot对静态资源的处理
### 10.7.1 使用SpringBoot

```
1）、创建SpringBoot应用，选中我们需要的模块；

2）、SpringBoot已经默认将这些场景配置好了，只需要在配置文件中指定少量配置就可以运行起来

3）、自己编写业务代码；
```


**自动配置原理？**

这个场景SpringBoot帮我们配置了什么？能不能修改？能修改哪些配置？能不能扩展？xxx


```
xxxxAutoConfiguration：帮我们给容器中自动配置组件；
xxxxProperties:配置类来封装配置文件的内容；
```


### 10.7.2 SpringBoot对静态资源的映射规则；
==1）、所有 /webjars/** ，都去 classpath:/META-INF/resources/webjars/ 找资源；==

	webjars：以jar包的方式引入静态资源；

http://www.webjars.org/

localhost:8080/webjars/jquery/3.3.1/jquery.js


```
<!--引入jquery-webjar-->在访问的时候只需要写webjars下面资源的名称即可
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>jquery</artifactId>
			<version>3.3.1</version>
		</dependency>
```


==2）、"/**" 访问当前项目的任何资源，都去（静态资源的文件夹）找映射


```
"classpath:/META-INF/resources/", 
"classpath:/resources/",
"classpath:/static/", 
"classpath:/public/" 
"/"：当前项目的根路径
```

localhost:8080/abc ===  去静态资源文件夹里面找abc

==3）、欢迎页； 静态资源文件夹下的所有index.html页面；被"/**"映射；

	localhost:8080/   找index页面

==4）、所有的 **/favicon.ico  都是在静态资源文件下找；


### 10.7.3 自定义静态资源路径
配置静态文件夹路径，如配成类路径下的hello和gis文件夹**：spring.resources.static-locations=classpath:/hello/,classpath:/gis
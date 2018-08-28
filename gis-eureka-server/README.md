# Spring Cloud Eureka
- 基于Netflix Eureka做了二次封装
- 两个组件组成：
    - Eureka Server注册中心
    - Eureka Client服务注册
    - 供服务注册的服务端，客户端是用来简化与服务端的交互。作为轮询负载均衡器，并提供服务的故障切换支持。

- 支持心跳检测、健康检查、负载均衡等功能
- 监控界面：http://localhost:8761/

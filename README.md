# vic-tech
  一个测试项目，用来学习研究基于spring-cloud的java分布式架构。<br />
  使用gradle构建，有配置gradle wapper，默认版本gradle-4.2-rc-2<br />
  spring-cloud版本 -> Dalston.SR4  <br />
  springboot版本 -> 1.5.6.RELEASE  <br />
  
  邮件： jerryhanm@foxmail.com 有问题欢迎交流

## 已实现或正在进行中功能 ↓

    * 服务发现
    * 外部配置（统一配置），可在启动时动态设置配置项，达到运维与研发分离的目的
    * Oauth2安全验证（sso）
    * logstash日志管理
    * 国际化
    * 统一错误管理（对外返回有意义的统一格式的错误信息）


## 计划中功能 ↓
    
    * docker容器
    * DDD事件驱动模型
    * Spark数据分析
    * 还没想到，想到再加 :-)


## 服务组成 ↓

### discovery-service

  服务发现(Eureka)，所有服务在这里注册，相当于项目的总线，服务间的交互就靠他了。
  
    * 微服务在交互时不再需要IP，PORT 知道服务名字即可
    * 负载均衡(Ribbon 使用默认策略，就是轮询)

### config-service
  配置文件中心(这里我采用提供公共配置文件，当然是支持单个服务拥有自己专属的配置文件)

### oauth2-service
  鉴权服务，spring-security 与 oauth2 实现。
  
    * 实现JWT和Redis方式存储Token， 通过配置文件选择
    * client存在Mongodb，user由user-service维护
    * 只实现了用户-权限2级策略(有时间再来搞用户-角色-权限的三级权限策略)

### webapp-gateway
  给web前端提供的api网关； 这里没有使用zuul而是用代码转发， 理由如下：
    
    * nginx比zuul更加高效和方便应用
    * api网关有个职责是对数据结构的优化 <br />
    例如：<br />
    user-service提供了User的信息，web前端需要展示所有的字段给用户， 但是mobile前端只需要展示少量的字段(节约流量)；<br />
    我目前的解决办法是提供webapp-gateway与mobile-gateway来规范返回值，并且为不同的客户端提供相应的专属API。

### user-service
  用户服务，使用MongoDB数据库，学习下MongoDB。

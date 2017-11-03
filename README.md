# vic-tech_iot

  一个测试项目，用来学习研究基于spring-cloud的java分布式架构。<br />
  功能以物联网相关业务为基础，大致包括
  * 设备网关 -> 与硬件设备交互，至少实现TCP（MQTT协议），消息队列（kafka），http方式
  * 设备管理平台 -> 管理网站（前后端完全分离，后台为微服务提供RESTFul的api，前台为AngularJS + Bootstarp响应式网站）
  
  使用gradle构建，有配置gradle wapper，默认版本gradle-4.2-rc-2<br />
  spring-cloud版本 -> Dalston.SR4  <br />
  springboot版本 -> 1.5.6.RELEASE  <br />
  
  ****
	
|Author|韩珉 (Jerry)|
|---|---
|E-mail|han-min@hotmail.com 

  ****

## 已实现或正在进行中功能

    * 服务发现
    * 外部配置（统一配置），可在启动时动态设置配置项，达到运维与研发分离的目的
    * Oauth2安全验证（sso）
    * logstash日志管理
    * 国际化
    * 统一错误管理（对外返回有意义的统一格式的错误信息）


## 计划中功能
    
    * docker容器
    * DDD事件驱动模型
    * Spark数据分析
    * 还没想到，想到再加 :-)

  ****

## iot-components 组件
  组件，一些公共的封装或者特殊工具类。

### iot-common

  公共组件，由于条件限制，没有maven本地库，所以只好在项目中直接引用，方便开发。
  
    * 包括错误消息实体（确保每个服务的错误消息格式统一化，前端才好根据错误进行业务逻辑，提高容错性）
    * Entity和controller的底层公共参数
    * exception
    * swagger和i18n的相关配置
    * http返回参数的格式化工具（确保每个服务的返回格式统一化，api网关才好做统一的返回处理）

  ****
  
## iot-micoservices 微服务
  微服务，提供具备原子性功能的服务，为业务逻辑的实现提供支持。

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
    * 除了oauth2本身（认证服务器）和api网关（资源服务器）需要鉴权，其他服务均是开放的，不需要鉴权
        （我是这么想的，其他服务都是不会暴露到外网的，所以不算资源服务器；至于安全问题，应该交由更专业的运维来保证）
        （我觉得微服务的设计应该具备原子性，单一功能且无状态化，而具体的业务逻辑都由api网关进行组装）

### user-service
  用户服务，使用MongoDB数据库，学习下MongoDB。

  ****

## iot-gateway 网关服务
  对外网关服务，也是微服务，不过网关的维度是业务驱动，有怎样的业务逻辑需求就需要特定的网关支持，不具备原子性。

### webapp-manager-gateway
  给web管理网站提供的api网关； 这里没有使用zuul而是用代码转发， 理由如下：
    
    * nginx比zuul更加高效和方便应用
    * api网关有个职责是对数据结构的优化
    例如：
    user-service提供了User的信息，web前端需要展示所有的字段给用户， 但是mobile前端只需要展示少量的字段(节约流量)；
    我目前的解决办法是提供webapp-gateway与mobile-gateway来规范返回值，并且为不同的客户端提供相应的专属API。
    
    *实现了国际化，但是这里的国际化并不是为了提示，只是想把内容写进配置文件中，顺带实现了国际化

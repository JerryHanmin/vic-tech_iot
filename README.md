# vic-tech
  一个测试项目, 用来学习研究基于spring-cloud的java分布式架构.
  使用Gradle构建, spring-cloud版本 -> Dalston.SR4  springboot版本 -> 1.5.6.RELEASE

## 服务组成及功能

#### discovery-service

  服务发现(Eureka), 所有服务在这里注册, 相当于项目的总线, 服务间的交互就靠他了.
  
  * 微服务在交互时不再需要IP,PORT 知道服务名字即可
  * 负载均衡(Ribbon 使用默认策略,就是轮询)

#### config-service
  配置文件中心(这里我采用提供公共配置文件, 当然是支持单个服务拥有自己专属的配置文件)

#### oauth2-service
  鉴权服务
  
  * 实现JWT和REDIS方式存储Token, 通过配置文件选择
  * client与account存在Mysql
  * 只实现了用户-权限2级策略(有时间再来搞用户-角色-权限的三级权限策略)

#### webapp-gateway
  给web前端提供的api网关; 这里没有使用zuul而是用代码转发, 理由如下:
  * nginx比zuul更加高效和方便应用
  * api网关有个职责是对数据结构的优化, <br />
    例如底层的user-service提供了User的信息, web前端可能需要所有的字段展示给用户; 但是mobile前端可能只需要少量的字段来显示(节约流量);<br />
    这时候我目前的解决办法是提供webapp-gateway与mobile-gateway来规范返回值, 并且为不同的客户端提供相应的专属API.

#### user-service
  用户服务

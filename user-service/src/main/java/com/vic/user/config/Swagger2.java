package com.vic.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    /**
     * 创建api 基本信息
     */
    @Bean
    public Docket createRestApi() {
        //构建全局参数 Authorization, Oauth2安全令牌, 除登陆外, 其他的api均需使用
//        ParameterBuilder builder = new ParameterBuilder();
//        builder.name("Authorization").description("Oauth2认证令牌").defaultValue("Bearer").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //增加参数
//                .globalOperationParameters(Collections.singletonList(builder.build()))
                .select()
                // 扫描该包下的所有需要在Swagger中展示的API，@ApiIgnore注解标注的除外
                .apis(RequestHandlerSelectors.basePackage("org.springframework.security.oauth2.provider.endpoint"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {// 创建API的基本信息，这些信息会在Swagger UI中进行显示
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")// API 标题
                .description("Oauth2 安全认证服务")// API描述
                .contact("hanmin")// 联系人
                .version("1.0")// 版本号
                .build();
    }
}

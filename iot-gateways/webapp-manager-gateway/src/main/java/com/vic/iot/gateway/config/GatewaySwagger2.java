package com.vic.iot.gateway.config;

import com.vic.iot.common.config.Swagger2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class GatewaySwagger2 extends Swagger2 {

    @Autowired
    private Environment environment;

    /**
     * 创建api 基本信息
     */
    @Override
    @Bean
    public Docket createRestApi() {
        //构建全局参数 Authorization, Oauth2安全令牌, 除登陆外, 其他的api均需使用
        ParameterBuilder builder = new ParameterBuilder();
        builder.name("Authorization").description("Oauth2认证令牌").defaultValue("Bearer").modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //增加参数
                .globalOperationParameters(Collections.singletonList(builder.build()))
                .select()
                // 扫描该包下的所有需要在Swagger中展示的API，@ApiIgnore注解标注的除外
                .apis(RequestHandlerSelectors.basePackage(environment.getProperty("service.swagger.base_package")))
                .paths(PathSelectors.any())
                .build();
    }
}

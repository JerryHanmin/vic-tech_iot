package com.vic.iot.common.config;

import com.vic.iot.common.properties.ServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {


    @Autowired
    private ServiceProperties serviceProperties;

    /**
     * 创建api 基本信息
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 扫描该包下的所有需要在Swagger中展示的API，@ApiIgnore注解标注的除外
                .apis(RequestHandlerSelectors.basePackage(serviceProperties.getSwagger().getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    protected ApiInfo apiInfo() {// 创建API的基本信息，这些信息会在Swagger UI中进行显示
        return new ApiInfoBuilder()
                .title(serviceProperties.getSwagger().getTitle())// API 标题
                .description(serviceProperties.getSwagger().getDescription())// API描述
                .contact(new Contact(serviceProperties.getSwagger().getContact(), serviceProperties.getSwagger().getContactUrl(), serviceProperties.getSwagger().getContactEmail()))// 联系人
                .version(serviceProperties.getSwagger().getVersion())// 版本号
                .build();
    }
}

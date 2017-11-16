package com.vic.iot.cache;

import com.vic.iot.banner.BuddhaBanner;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.vic.iot"})
@EnableEurekaClient
public class DataCacheApp {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder()
                .banner(new BuddhaBanner())
                .bannerMode(Banner.Mode.LOG)
                .sources(DataCacheApp.class)
                .run(args);
    }

}

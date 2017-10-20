package com.vic.user;

import com.vic.user.entity.User;
import com.vic.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class UserApp {


    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(UserApp.class, args);
    }


    @Autowired
    UserRepository userRepository;

    @Autowired
    public void init() {
        userRepository.deleteAll();

        User user = new User();

        user.setAccount("admin");
        user.setAge(18);
        user.setName("admin");

        userRepository.save(user);
    }
}

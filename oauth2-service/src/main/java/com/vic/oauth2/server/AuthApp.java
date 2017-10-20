package com.vic.oauth2.server;

import com.vic.oauth2.server.entity.Account;
import com.vic.oauth2.server.entity.OauthClientDetails;
import com.vic.oauth2.server.properties.ServiceProperties;
import com.vic.oauth2.server.repository.AccountRepository;
import com.vic.oauth2.server.repository.OauthClientsDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootApplication()
@EnableEurekaClient
@EnableConfigurationProperties(ServiceProperties.class)
@ComponentScan("com.vic.oauth2.server")
public class AuthApp {

    public static void main(String[] args) {
        SpringApplication.run(AuthApp.class, args);
    }

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OauthClientsDetailsRepository oauthClientsDetailsRepository;

    @Autowired
    public void init() {
        accountRepository.deleteAll();

        Account account = new Account();
        account.setAccountId(UUID.randomUUID().toString());
        account.setPassword("admin");
        account.setUsername("admin");
        account.setAuthorities(new String[]{"ROLE_USER", "ROLE_CLIENT", "ROLE_ADMIN"});
        account.setEnabled(true);
        account.setAccountNonExpired(true);
        account.setCredentialsNonExpired(true);
        account.setAccountNonLocked(true);
        accountRepository.save(account);


        oauthClientsDetailsRepository.deleteAll();

        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        oauthClientDetails.setClientId("webapp");
        oauthClientDetails.setClientSecret("webapp");
        oauthClientDetails.setResourceIds("oauth2-resource");
        oauthClientDetails.setScope("read,write");
        oauthClientDetails.setGrantTypes("authorization_code,password,refresh_token");
        oauthClientDetails.setAccsssTokenValidity(Long.toString(TimeUnit.MINUTES.toSeconds(10)));
        oauthClientDetails.setRefreshTokenValidity(Long.toString(TimeUnit.DAYS.toSeconds(10)));

        oauthClientsDetailsRepository.save(oauthClientDetails);
    }
}

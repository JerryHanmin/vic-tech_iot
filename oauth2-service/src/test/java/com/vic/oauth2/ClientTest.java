package com.vic.oauth2;

import com.vic.oauth2.server.AuthApp;
import com.vic.oauth2.server.entity.OauthClientDetails;
import com.vic.oauth2.server.repository.OauthClientsDetailsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientTest {

    @Autowired
    OauthClientsDetailsRepository clientsDetailsRepository;

    @Test
    public void testCreateDefaultClient() {
        clientsDetailsRepository.deleteAll();

        OauthClientDetails clientDetails = new OauthClientDetails();
        clientDetails.setClientId("webapp");
        clientDetails.setClientSecret("webapp");
        clientDetails.setAccessTokenValiditySeconds(600);
        clientDetails.setRefreshTokenValiditySeconds(6000);

        List<String> list = new ArrayList<>();
        list.add("authorization_code");
        list.add("password");
        list.add("refresh_token");

        clientDetails.setAuthorizedGrantTypes(list);

        list = new ArrayList<>();
        list.add("web-resource");
        list.add("mobile-resource");

        clientDetails.setResourceIds(list);

        list = new ArrayList<>();
        list.add("read");
        list.add("write");

        clientDetails.setScope(list);

        clientsDetailsRepository.save(clientDetails);
    }
}

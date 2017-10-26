package com.vic.iot.oauth2.server.service;

import com.vic.iot.oauth2.server.properties.ServiceProperties;
import com.vic.iot.oauth2.server.repository.OauthClientsDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class BaseService {

    @Autowired
    protected ServiceProperties serviceProperties;
    @Autowired
    protected RestTemplate restTemplate;
    @Autowired
    protected OauthClientsDetailsRepository oauthClientsDetailsRepository;
}

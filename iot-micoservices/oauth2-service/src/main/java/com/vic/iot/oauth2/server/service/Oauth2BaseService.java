package com.vic.iot.oauth2.server.service;

import com.vic.iot.common.service.BaseService;
import com.vic.iot.oauth2.server.properties.OauthServiceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Oauth2BaseService extends BaseService{

    @Autowired
    protected OauthServiceProperties serviceProperties;
}

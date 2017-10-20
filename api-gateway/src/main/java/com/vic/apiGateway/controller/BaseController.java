package com.vic.apiGateway.controller;

import com.vic.apiGateway.properties.ServiceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class BaseController {

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected ServiceProperties serviceProperties;
}

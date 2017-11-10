package com.vic.iot.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@Service
public class BaseService {
    @Autowired
    protected RestTemplate restTemplate;
    @Autowired
    protected AsyncRestTemplate asyncRestTemplate;
}

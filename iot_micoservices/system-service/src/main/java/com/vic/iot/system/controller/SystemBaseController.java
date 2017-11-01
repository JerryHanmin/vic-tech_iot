package com.vic.iot.system.controller;

import com.vic.iot.common.controller.BaseController;
import com.vic.iot.system.repository.OauthClientsDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class SystemBaseController extends BaseController {
    @Autowired
    protected OauthClientsDetailsRepository oauthClientsDetailsRepository;
}

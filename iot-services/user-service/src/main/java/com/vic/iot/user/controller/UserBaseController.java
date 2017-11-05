package com.vic.iot.user.controller;

import com.vic.iot.common.controller.BaseController;
import com.vic.iot.user.properties.UserServiceProperties;
import com.vic.iot.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserBaseController extends BaseController {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected UserServiceProperties userServiceProperties;

}

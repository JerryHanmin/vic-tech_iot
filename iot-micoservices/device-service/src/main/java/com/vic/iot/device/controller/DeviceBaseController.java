package com.vic.iot.device.controller;

import com.vic.iot.common.controller.BaseController;
import com.vic.iot.device.properties.DeviceServiceProperties;
import com.vic.iot.device.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceBaseController extends BaseController {

    @Autowired
    protected DeviceRepository deviceRepository;

    @Autowired
    protected MessageSource messageSource;

    @Autowired
    protected DeviceServiceProperties deviceServiceProperties;
}

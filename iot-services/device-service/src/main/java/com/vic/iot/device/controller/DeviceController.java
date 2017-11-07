package com.vic.iot.device.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "设备管理")
@RequestMapping("/devices")
public class DeviceController extends DeviceBaseController {

}

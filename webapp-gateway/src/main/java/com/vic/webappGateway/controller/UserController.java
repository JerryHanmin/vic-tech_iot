package com.vic.webappGateway.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "用户控制")
public class UserController extends BaseController {

    @ApiOperation(value = "查询所有用户信息")
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object findUsers() {
        String api = String.format(serviceProperties.getUser().getPrefix() + serviceProperties.getUser().getUsers());
        ResponseEntity<Object> response = restTemplate.getForEntity(api, Object.class);

        log.debug(response.getBody().toString());
        return response.getBody();
    }

    @ApiOperation(value = "查询超级用户信息(测试api, 没有这个角色, 应该返回403错误, 权限不足)")
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    @RequestMapping(value = "/superuser", method = RequestMethod.GET)
    public Object findSuperUsers() {
        return "super user";
    }
}

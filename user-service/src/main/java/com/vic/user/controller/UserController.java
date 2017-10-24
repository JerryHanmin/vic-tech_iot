package com.vic.user.controller;

import com.vic.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags = "账号管理")
public class UserController extends BaseController {
    @ApiOperation(value = "注册账号")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public HttpEntity<?> register(@RequestBody User user) {
        if (null != userRepository.findByLoginnameOrMobile(user.getAccount(), user.getMobile())) {
            return new ResponseEntity<>(errorMessage("oauth2.account.register.existed"), HttpStatus.BAD_REQUEST);
        }

        User createUser = new User();
        BeanUtils.copyProperties(user, createUser);

        return new ResponseEntity<>(userRepository.save(createUser), HttpStatus.CREATED);
    }

    @ApiOperation(value = "按条件查询用户(登录名 或 手机号)")
    @RequestMapping(value = "/by/{param}", method = RequestMethod.GET)
    public HttpEntity<?> findUserBy(@PathVariable String param) {
        return new ResponseEntity<>(userRepository.findByLoginnameOrMobile(param, param), HttpStatus.OK);
    }

    @ApiOperation(value = "按条件查询用户(登录名 或 用户ID 或 手机号)")
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<?> findAllUser(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        return new ResponseEntity<>(userRepository.findAll(pageable), HttpStatus.OK);
    }
}

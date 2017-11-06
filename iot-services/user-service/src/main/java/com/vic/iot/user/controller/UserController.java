package com.vic.iot.user.controller;

import com.vic.iot.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "账号管理")
public class UserController extends UserBaseController {
    @ApiOperation(value = "注册账号", code = 201)
    @ApiResponses({@ApiResponse(code = 412, message = "前置条件未达成,用户已经存在不能注册")})
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public HttpEntity<?> register(@RequestBody User user) {
        if (null != userRepository.findByAccountOrMobile(user.getAccount(), user.getMobile())) {
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }

        User createUser = new User();
        BeanUtils.copyProperties(user, createUser);

        return new ResponseEntity<>(userRepository.save(createUser), HttpStatus.CREATED);
    }

    @ApiOperation(value = "按条件查询用户(登录名 或 手机号)")
    @RequestMapping(value = "/by/{param}", method = RequestMethod.GET)
    public HttpEntity<?> findUserBy(@PathVariable String param) {
        return new ResponseEntity<>(userRepository.findByAccountOrMobile(param, param), HttpStatus.OK);
    }

    @ApiOperation(value = "查询所有用户")
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<?> findAllUser(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        return new ResponseEntity<>(pagedResources(userRepository.findAll(pageable)), HttpStatus.OK);
    }
}

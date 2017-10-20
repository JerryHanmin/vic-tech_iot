package com.vic.webappGateway.controller;


import com.vic.webappGateway.model.AccessToken;
import com.vic.webappGateway.model.Login;
import com.vic.webappGateway.utils.OauthUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "权限控制")
public class AuthController extends BaseController {

    @ApiOperation(value = "登陆, 获取access_token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AccessToken login(@RequestBody Login login) {
        String api = String.format(serviceProperties.getOauth2().getPrefix() + serviceProperties.getOauth2().getAccessTokenApi(), login.getAccount(), login.getPassword());
        ResponseEntity<AccessToken> response = restTemplate.postForEntity(api, new HttpEntity(OauthUtils.buildAouthHeader(serviceProperties.getOauth2().getClientId(), serviceProperties.getOauth2().getClientSecret())), AccessToken.class);

        log.debug(response.getBody().toString());
        return response.getBody();
    }

}

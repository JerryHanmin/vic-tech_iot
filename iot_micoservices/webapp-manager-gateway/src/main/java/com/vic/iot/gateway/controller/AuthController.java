package com.vic.iot.gateway.controller;


import com.vic.iot.gateway.MyResponseErrorHandler;
import com.vic.iot.gateway.model.AccessToken;
import com.vic.iot.gateway.model.request.LoginRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = "权限控制")
public class AuthController extends GatewayBaseController {

    @ApiOperation(value = "登陆, 获取access_token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@Validated @RequestBody LoginRequest login, BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<>(errorReponse("auth.login.error", result.getAllErrors(), null, LocaleContextHolder.getLocale()), HttpStatus.BAD_REQUEST);
        }

        String api = String.format(gatewayServiceProperties.getOauth2().getPrefix() + gatewayServiceProperties.getOauth2().getAccessTokenApi(), login.getAccount(), login.getPassword());

        restTemplate.setErrorHandler(new MyResponseErrorHandler());

        try {
            ResponseEntity<AccessToken> response = restTemplate.postForEntity(api, new HttpEntity(aouthHeader(gatewayServiceProperties.getOauth2().getClientId(), gatewayServiceProperties.getOauth2().getClientSecret())), AccessToken.class);

            log.debug(response.getBody().toString());
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);

        } catch (Exception e) {
            log.error("oauth2.getAccessTokenApi.error : " + e.getLocalizedMessage());

            MyResponseErrorHandler errorHandler = (MyResponseErrorHandler) restTemplate.getErrorHandler();
            return new ResponseEntity<>(errorReponse("auth.login.error", errorMessage("oauth2.getAccessTokenApi.error", errorHandler.getResponseBody()), null, LocaleContextHolder.getLocale()), errorHandler.getHttpStatus());
        }

    }

}

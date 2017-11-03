package com.vic.iot.gateway.controller;

import com.vic.iot.common.controller.BaseController;
import com.vic.iot.gateway.properties.GatewayServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GatewayBaseController extends BaseController {

    @Autowired
    protected GatewayServiceProperties gatewayServiceProperties;

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<?> handleInvalidTokenException(Exception e) throws Exception {
        return new ResponseEntity<>(errorReponse("auth.invalid_token", null, LocaleContextHolder.getLocale()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(Exception e) throws Exception {
        return new ResponseEntity<>(errorReponse("auth.permission_denied", null, LocaleContextHolder.getLocale()), HttpStatus.FORBIDDEN);
    }
}

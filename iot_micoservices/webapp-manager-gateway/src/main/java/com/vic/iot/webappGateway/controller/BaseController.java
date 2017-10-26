package com.vic.iot.webappGateway.controller;

import com.vic.iot.webappGateway.model.ErrorMessage;
import com.vic.iot.webappGateway.model.response.ErrorReponse;
import com.vic.iot.webappGateway.properties.ServiceProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@RestController
@Slf4j
public class BaseController {

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected ServiceProperties serviceProperties;

    @Autowired
    protected MessageSource messageSource;

    protected MultiValueMap<String, String> aouthHeader(String clientId, String clientSecret) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> auth = new ArrayList<>();
        String plainCreds = clientId + ":" + clientSecret;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        auth.add("Basic " + base64Creds);
        headers.put("Authorization", auth);
        return headers;
    }

    protected ErrorMessage errorMessage(String error, String description) {
        return new ErrorMessage(error, description);
    }

    protected ErrorMessage errorMessage(String code, Object[] args, Locale locale) {
        return new ErrorMessage(code, messageSource.getMessage(code, args, locale));
    }

    protected ErrorReponse errorReponse(String code, Object[] args, Locale locale) {
        return new ErrorReponse(code, messageSource.getMessage(code, args, locale));
    }

    protected ErrorReponse errorReponse(String error, ErrorMessage errorMessage, Object[] args, Locale locale) {
        ErrorReponse errorReponse = new ErrorReponse(error, messageSource.getMessage(error, args, locale));
        errorReponse.setMessages(Collections.singletonList(errorMessage));

        return errorReponse;
    }

    public ErrorReponse errorReponse(String error, List<ObjectError> errors, Object[] args, Locale locale) {
        List<ErrorMessage> messages = new ArrayList<>();
        for (ObjectError objectError : errors) {
            messages.add(new ErrorMessage(objectError.getCode(), objectError.getDefaultMessage()));
        }
        ErrorReponse errorReponse = new ErrorReponse(error, messageSource.getMessage(error, args, locale));
        errorReponse.setMessages(messages);

        return errorReponse;
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<?> handleInvalidTokenException(Exception e) throws Exception {
        return new ResponseEntity<>(errorReponse("auth.invalid_token", null, LocaleContextHolder.getLocale()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(Exception e) throws Exception {
        return new ResponseEntity<>(errorReponse("auth.permission_denied", null, LocaleContextHolder.getLocale()), HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception e) throws Exception {
        return new ResponseEntity<>(errorReponse("system.error", errorMessage(e.getMessage(), e.toString()), null, LocaleContextHolder.getLocale()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

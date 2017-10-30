package com.vic.iot.common.controller;

import com.google.common.collect.Lists;
import com.vic.iot.common.model.ErrorMessage;
import com.vic.iot.common.model.ErrorReponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedResources;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@RestController
@Slf4j
public class BaseController {

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected AsyncRestTemplate asyncRestTemplate;

    @Autowired
    protected MessageSource messageSource;

    protected MultiValueMap<String, String> aouthHeader(String clientId, String clientSecret) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        List<String> auth = Lists.newArrayList();
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
        List<ErrorMessage> messages = Lists.newArrayList();
        for (ObjectError objectError : errors) {
            messages.add(new ErrorMessage(objectError.getCode(), objectError.getDefaultMessage()));
        }
        ErrorReponse errorReponse = new ErrorReponse(error, messageSource.getMessage(error, args, locale));
        errorReponse.setMessages(messages);

        return errorReponse;
    }

    protected PagedResources pagedResources(Page<?> page) {
        return new PagedResources<>(page.getContent(),
                new PagedResources.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages()));
    }

}

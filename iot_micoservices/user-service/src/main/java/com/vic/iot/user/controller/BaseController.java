package com.vic.iot.user.controller;

import com.vic.iot.user.ErrorMessage;
import com.vic.iot.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class BaseController {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected MessageSource messageSource;

    protected ErrorMessage errorMessage(String code, Locale locale) {
        return new ErrorMessage(code, messageSource.getMessage(code, null, locale));
    }
}

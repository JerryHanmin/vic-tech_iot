package com.vic.user.controller;

import com.vic.user.ErrorMessage;
import com.vic.user.repository.UserRepository;
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

    protected ErrorMessage errorMessage(String code) {
        return new ErrorMessage(code, messageSource.getMessage(code, null, Locale.CHINESE));
    }
}

package com.vic.iot.device.controller;

import com.vic.iot.device.ErrorReponse;
import com.vic.iot.device.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class BaseController {

    @Autowired
    protected DeviceRepository deviceRepository;

    @Autowired
    protected MessageSource messageSource;

    protected ErrorReponse errorReponse(String code, Object[] args, Locale locale) {
        return new ErrorReponse(code, messageSource.getMessage(code, args, locale));
    }

    protected PagedResources pagedResources(Page<?> page) {
        return new PagedResources<>(page.getContent(),
                new PagedResources.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages()));
    }
}

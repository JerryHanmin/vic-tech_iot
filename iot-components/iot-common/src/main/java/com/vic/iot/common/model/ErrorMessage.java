package com.vic.iot.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ErrorMessage {

    public ErrorMessage() {
    }

    public ErrorMessage(String code, String description) {
        this.code = code;
        this.description = description;
    }


    private String code;
    private String description;
}

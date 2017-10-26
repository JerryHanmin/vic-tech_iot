package com.vic.iot.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
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

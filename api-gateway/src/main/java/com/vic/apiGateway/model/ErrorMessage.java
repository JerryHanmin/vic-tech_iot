package com.vic.apiGateway.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ErrorMessage {

    public ErrorMessage() {
    }

    public ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }


    private String code;
    private String message;
}

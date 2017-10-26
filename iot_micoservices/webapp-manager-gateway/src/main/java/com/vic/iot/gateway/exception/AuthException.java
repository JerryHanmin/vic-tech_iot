package com.vic.iot.gateway.exception;

import lombok.Data;

@Data
public class AuthException extends RuntimeException {
    private String code;
    private String msg;

    public AuthException() {
        super();
    }

    public AuthException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public AuthException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
}

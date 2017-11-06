package com.vic.iot.common.exception;

import lombok.Data;

@Data
public class IotException extends RuntimeException {
    private String code;
    private String msg;

    public IotException() {
        super();
    }

    public IotException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public IotException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
}

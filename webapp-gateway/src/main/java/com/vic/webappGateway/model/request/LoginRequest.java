package com.vic.webappGateway.model.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginRequest {
    private String account;
    private String password;
}

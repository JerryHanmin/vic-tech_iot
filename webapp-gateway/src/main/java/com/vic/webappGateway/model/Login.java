package com.vic.webappGateway.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Login {
    private String account;
    private String password;
}

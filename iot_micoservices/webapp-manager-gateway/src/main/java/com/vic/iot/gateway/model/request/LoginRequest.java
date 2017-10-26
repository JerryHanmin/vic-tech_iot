package com.vic.iot.gateway.model.request;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@ToString
public class LoginRequest {
    @NotEmpty(message = "{login.account.empty}")
    private String account;
    @NotEmpty(message = "{login.password.empty}")
    private String password;
}

package com.vic.iot.gateway.model.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserRequest {
    private String loginname;
    private String username;
    private String password;

    private Integer age;
    private String mobile;
    private String email;

}

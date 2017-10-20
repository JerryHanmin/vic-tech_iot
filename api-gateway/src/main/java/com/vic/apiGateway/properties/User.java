package com.vic.apiGateway.properties;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private String prefix = "http://user-service/users";
    private String users = "";
}

package com.vic.iot.oauth2.server.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GrantedAuthority implements org.springframework.security.core.GrantedAuthority {
    private String authority;
}

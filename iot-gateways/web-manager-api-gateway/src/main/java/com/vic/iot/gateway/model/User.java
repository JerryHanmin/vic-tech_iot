package com.vic.iot.gateway.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String userId = UUID.randomUUID().toString();
    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    private Integer age;
    private String mobile;
    private String email;

    private String createBy;
    private String modifyBy;

}

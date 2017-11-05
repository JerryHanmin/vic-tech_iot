package com.vic.iot.oauth2.server.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Data
@ToString
public class User implements UserDetails {
    private String userId;
    private String account;
    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}

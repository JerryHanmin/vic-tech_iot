package com.vic.iot.user.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Set;
import java.util.UUID;

@Data
@ToString
public class User extends BaseEntity {
    @Id
    private String userId = UUID.randomUUID().toString();
    private String account;
    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private Integer age;
    private String mobile;
    private String email;

}

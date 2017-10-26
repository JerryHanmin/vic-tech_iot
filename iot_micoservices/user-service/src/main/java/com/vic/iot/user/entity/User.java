package com.vic.iot.user.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;
import java.util.UUID;

@Data
@ToString
@Document(collection = "user")
public class User extends BaseEntity {
    @Id
    @Field("user_id")
    private String userId = UUID.randomUUID().toString();
    private String account;
    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;
    @Field("account_non_expired")
    private boolean accountNonExpired;
    @Field("account_non_locked")
    private boolean accountNonLocked;
    @Field("credentials_non_expired")
    private boolean credentialsNonExpired;
    private boolean enabled;

    private Integer age;
    private String mobile;
    private String email;

}

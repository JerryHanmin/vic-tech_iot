package com.vic.iot.user.entity;

import com.vic.iot.common.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@ToString
@Document(collection = "user")
public class User extends BaseEntity {
    @Id
    private String userId = UUID.randomUUID().toString();
    private String account;
    private String username;
    private String password;
    private String[] roles;
    @Transient
    private String[] authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private Integer age;
    private String mobile;
    private String email;

}

package com.vic.iot.gateway.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vic.iot.gateway.model.GrantedAuthority;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {
    private String userId;
    private String username;
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpirede = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    private Integer age;
    private String mobile;
    private String email;

    private String createBy;
    private String modifyBy;
    private Long createTime;
    private Long modifyTime;

}

package com.vic.webappGateway.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GrantedAuthority {

    public GrantedAuthority() {
    }

    public GrantedAuthority(String authority) {
        this.authority = authority;
    }

    private String authority;
}

package com.vic.iot.oauth2.server.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;


@Data
public class OauthClientDetails extends BaseClientDetails {
    @Id
    private String clientId;
}

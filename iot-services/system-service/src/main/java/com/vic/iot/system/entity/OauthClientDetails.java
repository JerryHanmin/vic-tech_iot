package com.vic.iot.system.entity;

import com.vic.iot.common.entity.BaseEntity;
import com.vic.iot.system.model.GrantedAuthority;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;
import java.util.Set;


@Data
@Document(collection = "oauth_client_details")
public class OauthClientDetails extends BaseEntity {
    @Id
    private String clientId;

    private String clientSecret;

    private Set<String> scope = Collections.emptySet();

    private Set<String> resourceIds = Collections.emptySet();

    private Set<String> authorizedGrantTypes = Collections.emptySet();

    private Set<String> registeredRedirectUris = Collections.emptySet();

    private Set<String> autoApproveScopes = Collections.emptySet();

    private List<GrantedAuthority> authorities = Collections.emptyList();

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

}

package com.vic.iot.gateway.security;

import com.vic.iot.common.ResponseErrorHandler;
import com.vic.iot.gateway.properties.GatewayServiceProperties;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Map;


/**
 * 重写RemoteTokenServices , 原本的RemoteTokenServices 没有异常处理, 一旦出现异常全是500错误
 */
@Component
@Slf4j
public class MyRemoteTokenServices implements ResourceServerTokenServices {

    @Setter
    private String tokenName = "token";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GatewayServiceProperties serviceProperties;

    @Setter
    private AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();


    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add(tokenName, accessToken);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", getAuthorizationHeader(serviceProperties.getOauth2().getClientId(), serviceProperties.getOauth2().getClientSecret()));
        Map<String, Object> map = postForMap(serviceProperties.getOauth2().getPrefix() + serviceProperties.getOauth2().getCheckTokenApi(), formData, headers);

        if (map.containsKey("code")) {
            log.debug("check_token returned code: " + map.get("code"));
            throw new InvalidTokenException(accessToken);
        }

        Assert.state(map.containsKey("client_id"), "Client id must be present in response from auth server");
        return tokenConverter.extractAuthentication(map);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        throw new UnsupportedOperationException("Not supported: read access token");
    }

    private String getAuthorizationHeader(String clientId, String clientSecret) {

        if (clientId == null || clientSecret == null) {
            log.warn("Null Client ID or Client Secret detected. Endpoint that requires authentication will reject request with 401 code.");
        }

        String creds = String.format("%s:%s", clientId, clientSecret);
        try {
            return "Basic " + new String(Base64.encode(creds.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Could not convert String");
        }
    }

    private Map<String, Object> postForMap(String path, MultiValueMap<String, String> formData, HttpHeaders headers) throws AuthenticationException, InvalidTokenException {
        if (headers.getContentType() == null) {
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        }
        //处理异常情况
        restTemplate.setErrorHandler(new ResponseErrorHandler());
        try {

            @SuppressWarnings("rawtypes")
            Map map = restTemplate.exchange(path, HttpMethod.POST, new HttpEntity<>(formData, headers), Map.class).getBody();

            @SuppressWarnings("unchecked")
            Map<String, Object> result = map;
            return result;

        } catch (Exception e) {
            ResponseErrorHandler errorHandler = (ResponseErrorHandler) restTemplate.getErrorHandler();
            JSONObject reponse = JSONObject.fromObject(errorHandler.getResponseBody());

            //通常这里都是check_token ,如果异常那就是InvalidTokenException
            if (!StringUtils.isEmpty(errorHandler.getResponseBody()) && reponse.containsKey("errorDescription"))
                throw new InvalidTokenException(reponse.getString("errorDescription"));

            else throw new InvalidTokenException("Token validation does not pass !");
        }


    }
}

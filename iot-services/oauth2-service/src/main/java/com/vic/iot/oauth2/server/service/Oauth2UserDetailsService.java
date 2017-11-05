/**
 *
 */
package com.vic.iot.oauth2.server.service;

import com.vic.iot.oauth2.server.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Oauth2UserDetailsService extends Oauth2BaseService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /* 查询用户信息 */
        String api = String.format(serviceProperties.getUser().getPrefix() + serviceProperties.getUser().getFindUser(), username);
        User user = restTemplate.getForEntity(api, User.class).getBody();
        if (user != null)
            return user;
        else
            throw new UsernameNotFoundException("用户[" + username + "]不存在");

    }

}

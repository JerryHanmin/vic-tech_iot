/**
 *
 */
package com.vic.oauth2.server.user;

import com.vic.oauth2.server.entity.Account;
import com.vic.oauth2.server.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("oauth2UserDetailsService")
public class Oauth2UserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /* 查询用户信息 */
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            return new User(account.getUsername(), account.getPassword(),
                    account.isEnabled(), account.isAccountNonExpired(), account.isCredentialsNonExpired(), account.isAccountNonLocked(), AuthorityUtils.createAuthorityList(account.getAuthorities()));
        } else {
            throw new UsernameNotFoundException("用户[" + username + "]不存在");
        }
    }

}

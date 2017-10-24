package com.vic.user;

import com.vic.user.entity.GrantedAuthority;
import com.vic.user.entity.User;
import com.vic.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testCreateDefaultUser() {
        userRepository.deleteAll();

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setAccount("admin");
        user.setPassword("admin");
        user.setMobile("18100001111");
        user.setUsername("Admin");
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);


        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new GrantedAuthority("ROLE_ADMIN"));
        authorities.add(new GrantedAuthority("ROLE_USER"));

        user.setAuthorities(authorities);


        userRepository.save(user);
    }

    @Test
    public void testFindUser() {
        User user = userRepository.findByLoginnameOrMobile("hanmin", null);
        System.out.println(user);
    }
}

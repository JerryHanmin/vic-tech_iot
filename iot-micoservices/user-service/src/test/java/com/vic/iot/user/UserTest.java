package com.vic.iot.user;

import com.vic.iot.common.properties.ServiceProperties;
import com.vic.iot.user.entity.Authority;
import com.vic.iot.user.entity.User;
import com.vic.iot.user.properties.UserServiceProperties;
import com.vic.iot.user.repository.UserRepository;
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


        userRepository.save(user);
    }

    @Test
    public void testFindUser() {
        User user = userRepository.findByAccountOrMobile("hanmin", null);
        System.out.println(user);
    }
}

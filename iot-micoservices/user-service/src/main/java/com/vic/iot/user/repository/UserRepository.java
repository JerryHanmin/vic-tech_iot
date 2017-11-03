package com.vic.iot.user.repository;

import com.vic.iot.user.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 处理数据到mongodb数据库
 *
 * @author hanmin
 */
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    User findByAccountOrMobile(String account, String mobile);
}

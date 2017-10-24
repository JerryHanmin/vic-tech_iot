package com.vic.user.repository;

import com.vic.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 处理数据到mongodb数据库
 *
 * @author hanmin
 */
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    User findByLoginnameOrMobile(String loginname, String mobile);
}

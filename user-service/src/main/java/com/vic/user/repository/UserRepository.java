package com.vic.user.repository;

import com.vic.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 处理数据到mongodb数据库
 *
 * @author hanmin
 */
public interface UserRepository extends MongoRepository<User, String> {
}

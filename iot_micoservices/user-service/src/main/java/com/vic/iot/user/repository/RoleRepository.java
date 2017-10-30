package com.vic.iot.user.repository;

import com.vic.iot.user.entity.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 处理数据到mongodb数据库
 *
 * @author hanmin
 */
public interface RoleRepository extends PagingAndSortingRepository<Role, String> {
}

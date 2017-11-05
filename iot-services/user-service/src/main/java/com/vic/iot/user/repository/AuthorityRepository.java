package com.vic.iot.user.repository;

import com.vic.iot.user.entity.Authority;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 处理数据到mongodb数据库
 *
 * @author hanmin
 */
public interface AuthorityRepository extends PagingAndSortingRepository<Authority, String> {
}

package com.vic.oauth2.server.repository;


import com.vic.oauth2.server.entity.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account, String> {
    /**
     * @param name
     * @return
     */
    Account findByUsername(String name);
}

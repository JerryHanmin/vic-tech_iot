package com.vic.oauth2.server.repository;


import com.vic.oauth2.server.entity.OauthClientDetails;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OauthClientsDetailsRepository extends PagingAndSortingRepository<OauthClientDetails, String> {
}

package com.vic.oauth2.server.repository;


import com.vic.oauth2.server.entity.OauthClientDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OauthClientsDetailsRepository extends MongoRepository<OauthClientDetails, String> {
}

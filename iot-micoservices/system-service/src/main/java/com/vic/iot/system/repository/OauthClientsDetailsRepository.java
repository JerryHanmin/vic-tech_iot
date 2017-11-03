package com.vic.iot.system.repository;


import com.vic.iot.system.entity.OauthClientDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OauthClientsDetailsRepository extends MongoRepository<OauthClientDetails, String> {
}

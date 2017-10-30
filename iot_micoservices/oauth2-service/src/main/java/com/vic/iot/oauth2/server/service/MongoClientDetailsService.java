package com.vic.iot.oauth2.server.service;

import com.vic.iot.oauth2.server.entity.OauthClientDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MongoClientDetailsService extends BaseService implements ClientDetailsService, ClientRegistrationService {
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws NoSuchClientException {
        OauthClientDetails oauthClientDetails = oauthClientsDetailsRepository.findOne(clientId);
        if (null == oauthClientDetails)
            throw new NoSuchClientException("No client found with id = " + clientId);
        else
            return oauthClientDetails;
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        OauthClientDetails oauthClientDetails = new OauthClientDetails();
        BeanUtils.copyProperties(clientDetails, oauthClientDetails);
        oauthClientsDetailsRepository.save(oauthClientDetails);
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        OauthClientDetails oauthClientDetails = oauthClientsDetailsRepository.findOne(clientDetails.getClientId());
        if (null == oauthClientDetails)
            throw new NoSuchClientException("No client found with id = " + clientDetails.getClientId());
        else {
            oauthClientsDetailsRepository.save(oauthClientDetails);
        }
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        OauthClientDetails oauthClientDetails = oauthClientsDetailsRepository.findOne(clientId);
        if (null == oauthClientDetails)
            throw new NoSuchClientException("No client found with id = " + clientId);
        else {
            oauthClientDetails.setClientSecret(secret);
            oauthClientsDetailsRepository.save(oauthClientDetails);
        }
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        oauthClientsDetailsRepository.delete(clientId);
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        List<ClientDetails> clientDetails = Collections.emptyList();
        oauthClientsDetailsRepository.findAll().forEach(clientDetails::add);
        return clientDetails;
    }
}

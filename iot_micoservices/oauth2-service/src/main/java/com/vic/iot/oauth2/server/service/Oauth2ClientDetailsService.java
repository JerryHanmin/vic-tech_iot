package com.vic.iot.oauth2.server.service;

import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Oauth2ClientDetailsService extends Oauth2BaseService implements ClientDetailsService, ClientRegistrationService {
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws NoSuchClientException {
        return null;
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        return null;
    }
}

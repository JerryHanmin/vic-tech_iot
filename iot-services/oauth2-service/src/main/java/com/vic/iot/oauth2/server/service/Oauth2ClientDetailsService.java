package com.vic.iot.oauth2.server.service;

import com.vic.iot.common.ResponseErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class Oauth2ClientDetailsService extends Oauth2BaseService implements ClientDetailsService, ClientRegistrationService {
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws NoSuchClientException {
        String api = String.format(serviceProperties.getSystem().getPrefix() + serviceProperties.getSystem().getFindClientById(), clientId);
        ClientDetails clientDetails = restTemplate.getForEntity(api, ClientDetails.class).getBody();

        if (null == clientDetails)
            throw new NoSuchClientException("No client found with id = " + clientId);
        else
            return clientDetails;

    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        String api = serviceProperties.getSystem().getPrefix() + serviceProperties.getSystem().getClients();
        restTemplate.setErrorHandler(new ResponseErrorHandler());
        try {
            restTemplate.postForEntity(api, clientDetails, ClientDetails.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            ResponseErrorHandler errorHandler = (ResponseErrorHandler) restTemplate.getErrorHandler();
            if (errorHandler.getHttpStatus().equals(HttpStatus.PRECONDITION_FAILED))
                throw new ClientAlreadyExistsException("Client already exists: " + clientDetails.getClientId(), e);
        }

    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        String api = serviceProperties.getSystem().getPrefix() + serviceProperties.getSystem().getClients();
        restTemplate.setErrorHandler(new ResponseErrorHandler());
        try {
            restTemplate.put(api, clientDetails, ClientDetails.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            ResponseErrorHandler errorHandler = (ResponseErrorHandler) restTemplate.getErrorHandler();
            if (errorHandler.getHttpStatus().equals(HttpStatus.NOT_FOUND))
                throw new NoSuchClientException("No client found with id = " + clientDetails.getClientId());
        }
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId(clientId);
        clientDetails.setClientSecret(secret);

        String api = serviceProperties.getSystem().getPrefix() + serviceProperties.getSystem().getClients();
        restTemplate.setErrorHandler(new ResponseErrorHandler());

        try {
            restTemplate.patchForObject(api, clientDetails, ClientDetails.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            ResponseErrorHandler errorHandler = (ResponseErrorHandler) restTemplate.getErrorHandler();
            if (errorHandler.getHttpStatus().equals(HttpStatus.NOT_FOUND))
                throw new NoSuchClientException("No client found with id = " + clientDetails.getClientId());
        }

    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        String api = String.format(serviceProperties.getSystem().getPrefix() + serviceProperties.getSystem().getFindClientById(), clientId);
        restTemplate.setErrorHandler(new ResponseErrorHandler());

        try {
            restTemplate.delete(api, clientId);

        } catch (Exception e) {
            log.error(e.getMessage(), e);

            ResponseErrorHandler errorHandler = (ResponseErrorHandler) restTemplate.getErrorHandler();
            if (errorHandler.getHttpStatus().equals(HttpStatus.NOT_FOUND))
                throw new NoSuchClientException("No client found with id = " + clientId);
        }
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        String api = serviceProperties.getSystem().getPrefix() + serviceProperties.getSystem().getClients();
        restTemplate.setErrorHandler(new ResponseErrorHandler());

        return restTemplate.exchange(api, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<ClientDetails>>() {
        }).getBody();
    }
}

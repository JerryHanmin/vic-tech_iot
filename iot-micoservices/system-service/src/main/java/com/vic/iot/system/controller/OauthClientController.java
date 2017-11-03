package com.vic.iot.system.controller;

import com.vic.iot.system.entity.OauthClientDetails;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class OauthClientController extends SystemBaseController {
    @ApiOperation(value = "根据ID查询客户端")
    @RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
    public HttpEntity<?> loadClientByClientId(@PathVariable String clientId) {
        return new ResponseEntity<>(oauthClientsDetailsRepository.findOne(clientId), HttpStatus.OK);
    }

    @ApiOperation(value = "新增客户端")
    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<?> addClientDetails(@RequestBody OauthClientDetails clientDetails) {
        OauthClientDetails existClientDetails = oauthClientsDetailsRepository.findOne(clientDetails.getClientId());
        if (null == existClientDetails) {
            return new ResponseEntity<>(oauthClientsDetailsRepository.save(clientDetails), HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }

    @ApiOperation(value = "修改客户端")
    @RequestMapping(method = RequestMethod.PUT)
    public HttpEntity<?> updateClientDetails(@RequestBody OauthClientDetails clientDetails) {
        OauthClientDetails existClientDetails = oauthClientsDetailsRepository.findOne(clientDetails.getClientId());
        if (null == existClientDetails)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            BeanUtils.copyProperties(clientDetails, existClientDetails);
            return new ResponseEntity<>(oauthClientsDetailsRepository.save(existClientDetails), HttpStatus.OK);
        }
    }

    @ApiOperation(value = "修改客户端密码")
    @RequestMapping(value = "/{clientId}", method = RequestMethod.PUT)
    public HttpEntity<?> updateClientSecret(@PathVariable String clientId, @RequestBody String secret) {
        OauthClientDetails existClientDetails = oauthClientsDetailsRepository.findOne(clientId);
        if (null == existClientDetails)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            existClientDetails.setClientSecret(secret);
            oauthClientsDetailsRepository.save(existClientDetails);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "删除客户端")
    @RequestMapping(value = "/{clientId}", method = RequestMethod.DELETE)
    public HttpEntity<?> removeClientDetails(@PathVariable String clientId) {
        OauthClientDetails existClientDetails = oauthClientsDetailsRepository.findOne(clientId);
        if (null == existClientDetails)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            oauthClientsDetailsRepository.delete(existClientDetails);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "查询所有客户端")
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<?> listClientDetails() {
        return new ResponseEntity<>(oauthClientsDetailsRepository.findAll(), HttpStatus.OK);
    }
}

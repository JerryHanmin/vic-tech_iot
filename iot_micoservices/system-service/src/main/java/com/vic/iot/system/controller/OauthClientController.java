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
    @ApiOperation(value = "注册账号")
    @RequestMapping(value = "/{clientId}", method = RequestMethod.GET)
    public HttpEntity<?> loadClientByClientId(@PathVariable String clientId) {
        OauthClientDetails clientDetails = oauthClientsDetailsRepository.findOne(clientId);
        if (null != clientDetails)
            return new ResponseEntity<>(clientDetails, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "注册账号")
    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<?> addClientDetails(@RequestBody OauthClientDetails clientDetails) {
        OauthClientDetails existClientDetails = oauthClientsDetailsRepository.findOne(clientDetails.getClientId());
        if (null == clientDetails) {
            return new ResponseEntity<>(oauthClientsDetailsRepository.save(existClientDetails), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

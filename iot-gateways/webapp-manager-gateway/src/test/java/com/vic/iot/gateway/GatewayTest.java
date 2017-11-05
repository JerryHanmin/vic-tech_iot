package com.vic.iot.gateway;

import com.vic.iot.gateway.properties.GatewayServiceProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebappManagerGateway.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GatewayTest {

}

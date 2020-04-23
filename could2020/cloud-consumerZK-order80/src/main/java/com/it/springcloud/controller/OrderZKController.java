package com.it.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderZKController {
    @Autowired
    RestTemplate restTemplate;
    public static final String  SERVER_URL = "http://cloud-provider-payment";
    @GetMapping("/consumer/getServer")
    public String getServer(){
        return restTemplate.getForObject(SERVER_URL+"/payment/getServer",String.class);
    }
}

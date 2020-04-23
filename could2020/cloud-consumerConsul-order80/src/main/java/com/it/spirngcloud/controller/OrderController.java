package com.it.spirngcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    @Autowired
    RestTemplate restTemplate;
    public static String SERVER_prefix = "http://cloud-providerConsul-payment";
    @GetMapping("/consumer/getServer")
    public String getServer(){
        return  restTemplate.getForObject(SERVER_prefix+"/payment/consul",String.class);
    }
}

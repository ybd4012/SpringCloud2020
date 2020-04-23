package com.it.springcloud.controller;

import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private  String serverPort;

    @GetMapping("/payment/consul")
    public String getServer(){
        return serverPort+"\t"+ UUID.randomUUID().toString();
    }
}

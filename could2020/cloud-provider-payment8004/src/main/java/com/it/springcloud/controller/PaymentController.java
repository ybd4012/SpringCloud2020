package com.it.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {

    @GetMapping("/payment/getServer")
    public String getServer(){
        return "8004:"+ UUID.randomUUID().toString();
    }
}

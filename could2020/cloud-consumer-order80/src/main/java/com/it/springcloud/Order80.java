package com.it.springcloud;

import com.it.iruleconfig.IruleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
//@RibbonClient(name = "CLOUD-PROVIDER-PAYMENT",configuration = IruleConfig.class)
public class Order80 {
    public static void main(String[] args) {
        SpringApplication.run(Order80.class,args);
    }
}

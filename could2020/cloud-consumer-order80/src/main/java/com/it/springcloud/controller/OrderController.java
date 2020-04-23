package com.it.springcloud.controller;

import com.it.springcloud.component.MyLoadBalanced;
import com.it.springcloud.entities.CommonResult;
import com.it.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

//    private static final String PAYMENT_URL = "http://localhost:8001";
    private static final String PAYMENT_URL = "http://CLOUD-PROVIDER-PAYMENT";

    @Autowired
    private MyLoadBalanced myLoadBalanced;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    public OrderController() {
    }

    @GetMapping("/consumer/payment/add")
    public CommonResult<Payment> add(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/add", payment, CommonResult.class);
    }
    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/getPaymentById/"+id, CommonResult.class);
    }

    /**
     * 自定义 LoadBalanced 能够发起轮询
     * 不能直接通过
     *  return restTemplate.getForObject("url+"/payment/lb",String.class);发起请求服务
     * @return
     */
    @GetMapping("/consumer/payment/lb")
    public String myLB(){
        List<ServiceInstance> instanceList = discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT");
        ServiceInstance server = myLoadBalanced.getServer(instanceList);
        URI url = server.getUri();
        String id = server.getInstanceId();
        String port = String.valueOf(server.getPort());
        String serviceId = server.getServiceId();
        return restTemplate.getForObject("http://"+serviceId+"/payment/lb",String.class);
//        return url+"\t"+id+"\t"+"\t"+serviceId+"\t";
    }

}

package com.it.springcloud.controller;

import com.it.springcloud.entities.CommonResult;
import com.it.springcloud.entities.Payment;
import com.it.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payment/add")
    public CommonResult add(Payment payment){
        int resultCode = paymentService.add(payment);
        log.info("********************status:"+resultCode);
        if(resultCode>0){
            return new CommonResult(200,"添加到数据库成功",resultCode);
        }else{
            return new CommonResult(444,"添加到数据库失败",null);
        }
    }
    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment resultEntities = paymentService.getPaymentById(id);
        log.info("****************Wstatus:"+resultEntities);
        if(resultEntities!=null){
            return new CommonResult(200,"查询成功",resultEntities);
        }else{
            return new CommonResult(444,"查询失败",null);
        }
    }

}

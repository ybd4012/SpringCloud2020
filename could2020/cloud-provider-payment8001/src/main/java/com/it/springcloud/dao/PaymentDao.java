package com.it.springcloud.dao;

import com.it.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {

    public int add(Payment payment);

    public Payment getPaymentById(Long id);
}

package org.xfwang.springcloud.service;

import org.apache.ibatis.annotations.Param;
import org.xfwang.springcloud.entities.Payment;

public interface PaymentService {
    int create(Payment payment); //写

    Payment getPaymentById(@Param("id") Long id);  //读取
}

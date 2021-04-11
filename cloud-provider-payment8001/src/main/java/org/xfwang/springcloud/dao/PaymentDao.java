package org.xfwang.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.xfwang.springcloud.entities.Payment;

@Mapper
public interface PaymentDao {
    int create(Payment payment);

    Payment getPaymentById(Long id);
}

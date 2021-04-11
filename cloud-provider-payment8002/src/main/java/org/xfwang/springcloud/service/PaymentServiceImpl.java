package org.xfwang.springcloud.service;
import org.springframework.stereotype.Service;
import org.xfwang.springcloud.dao.PaymentDao;
import org.xfwang.springcloud.entities.Payment;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById( Long id){

        return paymentDao.getPaymentById(id);

    }
}

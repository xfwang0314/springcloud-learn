package org.xfwang.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.xfwang.springcloud.entities.CommonResult;
import org.xfwang.springcloud.entities.Payment;
import org.xfwang.springcloud.lb.LoadBalancer;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("payment {}",payment);
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create",payment, CommonResult.class);
    }
    @GetMapping(value = "/consumer/payment/get/{id}")
    public Object getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id,CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/getforEntity/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if(forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败!");
        }
    }

    @PostMapping(value = "/consumer/payment/createPayment")
    public CommonResult<Payment> create2(Payment payment){
        log.info("payment {}",payment);
        ResponseEntity<CommonResult> commonResultResponseEntity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        if(commonResultResponseEntity.getStatusCode().is2xxSuccessful()){
            return commonResultResponseEntity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败!");
        }
    }
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() <= 0){
            return "无提供的服务!";
        }
        return restTemplate.getForObject(this.loadBalancer.instances(instances).getUri() + "/payment/lb",String.class);
    }

    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin()
    {
        String result = restTemplate.getForObject("http://localhost:8001"+"/payment/zipkin/", String.class);
        return result;
    }
}
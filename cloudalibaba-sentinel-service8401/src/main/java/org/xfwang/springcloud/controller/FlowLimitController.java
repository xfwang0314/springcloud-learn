package org.xfwang.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() throws InterruptedException {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        System.out.println(Thread.currentThread().getName());
        return "------testB";
    }
    @GetMapping("/testD")
    public String testD()
    {
 //       try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
 //       System.out.println("testD 测试RT");

        System.out.println("testd 测试异常比例");
        int a = 10 /0;
        return "------testD";
    }

    @GetMapping("/testE")
    public String testE()
    {
        System.out.println("testE 测试异常数");
        int age = 10/0;
        return "------testE 测试异常数";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotkey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2,
                             @RequestParam(value = "p3",required = false)Integer p3){
        return "....testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, Integer p3,BlockException block){
        return "兜底方法.../(ㄒoㄒ)/~~" + block.getMessage();
    }


}

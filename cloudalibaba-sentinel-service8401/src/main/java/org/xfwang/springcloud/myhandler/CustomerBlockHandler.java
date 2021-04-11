package org.xfwang.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.xfwang.springcloud.entities.CommonResult;
import org.xfwang.springcloud.entities.Payment;

public class CustomerBlockHandler {
    public static CommonResult handlerException1(BlockException blockException){
        return new CommonResult(4444,"自定义限流兜底方法1",new Payment(2020L,"doudi1"));
    }
    public static CommonResult handlerException2(BlockException blockException){
        return new CommonResult(4444,"自定义限流兜底方法2",new Payment(2020L,"doudi2"));
    }
}

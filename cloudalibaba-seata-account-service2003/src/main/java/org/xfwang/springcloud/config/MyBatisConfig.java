package org.xfwang.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"org.xfwang.springcloud.dao"})
public class MyBatisConfig {

}
package com.atguigu.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Retryer myRetryer() {
        return Retryer.NEVER_RETRY;//不重试
//        return new Retryer.Default(100,1000,3);//重试策略，每隔1000毫秒重试一次，最多重试3次
    }

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}

package com.promptoven.paymentservice.global.config;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import feign.Request;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new ErrorDecoder.Default();
    }
    
    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(5000, TimeUnit.MILLISECONDS, 5000, TimeUnit.MILLISECONDS, true);
    }
}
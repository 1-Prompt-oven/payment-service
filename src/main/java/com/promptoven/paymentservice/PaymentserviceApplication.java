package com.promptoven.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.promptoven.paymentservice.member.payment.application.ProductFeignClient;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = ProductFeignClient.class)
public class PaymentserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentserviceApplication.class, args);
	}

}

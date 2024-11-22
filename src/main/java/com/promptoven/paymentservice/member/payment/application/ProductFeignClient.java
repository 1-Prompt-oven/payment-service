package com.promptoven.paymentservice.member.payment.application;

import com.promptoven.paymentservice.member.payment.dto.in.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8080/")
public interface ProductFeignClient {

    @GetMapping("/v1/payments/product/seller/{productUUID}")
    ProductResponseDto getProductByUuid(@PathVariable("productUUID") String productUUID);
}

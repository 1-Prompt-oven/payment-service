package com.promptoven.paymentservice.member.payment.application;

import com.promptoven.paymentservice.global.common.response.BaseResponse;
import com.promptoven.paymentservice.member.payment.dto.in.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "https://api.promptoven.shop")
public interface ProductFeignClient {

    @GetMapping("/v1/product/{productUUID}/seller")
    BaseResponse<ProductResponseDto> getProductByUuid(@PathVariable("productUUID") String productUUID);
}

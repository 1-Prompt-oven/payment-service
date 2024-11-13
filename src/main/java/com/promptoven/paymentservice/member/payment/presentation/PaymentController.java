package com.promptoven.paymentservice.member.payment.presentation;

import com.promptoven.paymentservice.global.common.response.BaseResponse;
import com.promptoven.paymentservice.member.payment.application.PaymentService;
import com.promptoven.paymentservice.member.payment.dto.in.PaymentCallbackRequestDto;
import com.promptoven.paymentservice.member.payment.vo.in.PaymentCallbackRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
@Tag(name = "결제 API", description = "결제 관련 API endpoints")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "결제 콜백", description = "결제 콜백")
    @PostMapping("/callback")
    public BaseResponse<Void> handlePaymentCallback(@RequestBody PaymentCallbackRequestVo requestVo) {

        PaymentCallbackRequestDto requestDto = PaymentCallbackRequestDto.toDto(requestVo);

        paymentService.processPaymentCallback(requestDto);

        return new BaseResponse<>();
    }

    @Operation(summary = "kafka 테스트", description = "kafka 테스트")
    @PostMapping("/test")
    public BaseResponse<Void> test(@RequestParam String memberUuid,
                                   @RequestParam List<String> productUuid) {
        paymentService.test(memberUuid, productUuid);
        return new BaseResponse<>();
    }
}

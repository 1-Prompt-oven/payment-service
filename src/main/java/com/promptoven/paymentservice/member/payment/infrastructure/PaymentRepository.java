package com.promptoven.paymentservice.member.payment.infrastructure;

import com.promptoven.paymentservice.common.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

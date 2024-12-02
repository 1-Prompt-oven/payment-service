package com.promptoven.paymentservice.member.payment.infrastructure;

import com.promptoven.paymentservice.common.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByMemberUuid(String memberUuid);
}

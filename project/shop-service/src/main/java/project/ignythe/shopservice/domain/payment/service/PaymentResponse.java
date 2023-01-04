package project.ignythe.shopservice.domain.payment.service;

import project.ignythe.shopservice.domain.payment.PaymentStatus;

import java.math.BigDecimal;

record PaymentResponse(Long paymentId,
                       String description,
                       BigDecimal amount,
                       PaymentStatus status) {
}

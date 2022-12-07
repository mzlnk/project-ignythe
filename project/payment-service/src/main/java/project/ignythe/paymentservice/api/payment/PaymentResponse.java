package project.ignythe.paymentservice.api.payment;

import project.ignythe.paymentservice.domain.payment.PaymentStatus;

import java.math.BigDecimal;

public record PaymentResponse(Long paymentId,
                              String description,
                              BigDecimal amount,
                              PaymentStatus status) {
}

package project.ignythe.paymentservice.api.payment;

import project.ignythe.paymentservice.domain.payment.PaymentStatus;

public record PaymentResponse(Long paymentId, PaymentStatus status) {
}

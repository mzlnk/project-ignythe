package project.ignythe.paymentservice.api.payment;

import project.ignythe.paymentservice.domain.payment.PaymentStatus;

record PaymentUpdateRequest(PaymentStatus newStatus) {
}

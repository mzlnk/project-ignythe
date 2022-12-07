package project.ignythe.paymentservice.api.payment;

import project.ignythe.paymentservice.domain.payment.Payment;

class PaymentMapper {

    PaymentMapper() {
    }

    PaymentResponse toPaymentResponse(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getDescription(),
                payment.getAmount(),
                payment.getStatus()
        );
    }

}

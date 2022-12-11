package project.ignythe.shopservice.domain.payment;

import io.vavr.control.Either;

public interface PaymentReader {

    Either<ReadPaymentFailed, ReadPaymentSucceeded> readPayment(Long paymentId);

    record ReadPaymentSucceeded(Payment payment) {
    }

    record ReadPaymentFailed(String message) {
    }

}

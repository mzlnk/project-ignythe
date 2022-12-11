package project.ignythe.shopservice.domain.payment;

import io.vavr.control.Either;

public interface PaymentWriter {

    Either<CreatePaymentFailed, CreatePaymentSucceeded> createPayment(PaymentCreateRequest createRequest);

    record CreatePaymentSucceeded(Payment payment) {
    }

    record CreatePaymentFailed(String message) {
    }

}

package project.ignythe.shopservice.domain.payment.service;

import io.vavr.control.Either;
import io.vavr.control.Try;
import project.ignythe.shopservice.domain.payment.Payment;
import project.ignythe.shopservice.domain.payment.PaymentReader;

class ServicePaymentReader implements PaymentReader {

    private final ServicePaymentClient paymentClient;

    ServicePaymentReader(ServicePaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @Override
    public Either<ReadPaymentFailed, ReadPaymentSucceeded> readPayment(Long paymentId) {
        return Try.of(() -> paymentClient.readPaymentById(paymentId))
                .toEither()
                .map(this::onSuccess)
                .mapLeft(this::onFail);
    }

    private ReadPaymentFailed onFail(Throwable fail) {
        return new ReadPaymentFailed(fail.getMessage());
    }

    private ReadPaymentSucceeded onSuccess(PaymentResponse response) {
        var payment = new Payment(response.paymentId(), response.description(), response.amount(), response.status());
        return new ReadPaymentSucceeded(payment);
    }

}

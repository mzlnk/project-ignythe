package project.ignythe.shopservice.domain.payment.service;

import io.vavr.control.Either;
import io.vavr.control.Try;
import project.ignythe.shopservice.domain.payment.Payment;
import project.ignythe.shopservice.domain.payment.PaymentCreateRequest;
import project.ignythe.shopservice.domain.payment.PaymentWriter;

class ServicePaymentWriter implements PaymentWriter {

    private final ServicePaymentClient paymentClient;

    ServicePaymentWriter(ServicePaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @Override
    public Either<CreatePaymentFailed, CreatePaymentSucceeded> createPayment(PaymentCreateRequest createRequest) {
        return Try.of(() -> paymentClient.createPayment(createRequest))
                .toEither()
                .mapLeft(this::onFail)
                .map(this::onSuccess);
    }

    private CreatePaymentFailed onFail(Throwable fail) {
        return new CreatePaymentFailed(fail.getMessage());
    }

    private CreatePaymentSucceeded onSuccess(PaymentResponse response) {
        var payment = new Payment(response.paymentId(), response.description(), response.amount(), response.status());
        return new CreatePaymentSucceeded(payment);
    }

}

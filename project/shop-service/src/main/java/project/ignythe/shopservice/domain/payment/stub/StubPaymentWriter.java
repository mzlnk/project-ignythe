package project.ignythe.shopservice.domain.payment.stub;

import io.vavr.control.Either;
import project.ignythe.shopservice.domain.payment.PaymentCreateRequest;
import project.ignythe.shopservice.domain.payment.PaymentWriter;

class StubPaymentWriter implements PaymentWriter {

    private final StubPaymentStorage paymentStorage;

    StubPaymentWriter(StubPaymentStorage paymentStorage) {
        this.paymentStorage = paymentStorage;
    }

    @Override
    public Either<CreatePaymentFailed, CreatePaymentSucceeded> createPayment(PaymentCreateRequest createRequest) {
        var createdPayment = paymentStorage.createPayment(createRequest);
        return Either.right(new CreatePaymentSucceeded(createdPayment));
    }

}

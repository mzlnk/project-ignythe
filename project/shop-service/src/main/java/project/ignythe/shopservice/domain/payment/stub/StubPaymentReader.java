package project.ignythe.shopservice.domain.payment.stub;

import io.vavr.control.Either;
import project.ignythe.shopservice.domain.payment.PaymentReader;

class StubPaymentReader implements PaymentReader {

    private final StubPaymentStorage paymentStorage;

    StubPaymentReader(StubPaymentStorage paymentStorage) {
        this.paymentStorage = paymentStorage;
    }

    @Override
    public Either<ReadPaymentFailed, ReadPaymentSucceeded> readPayment(Long paymentId) {
        return paymentStorage.findPaymentById(paymentId)
                .toEither(new ReadPaymentFailed("Payment with id '%s' not found".formatted(paymentId)))
                .map(ReadPaymentSucceeded::new);
    }

}

package project.ignythe.shopservice.domain.payment.stub;

import io.vavr.control.Option;
import project.ignythe.shopservice.domain.payment.Payment;
import project.ignythe.shopservice.domain.payment.PaymentCreateRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static project.ignythe.shopservice.domain.payment.PaymentStatus.PENDING;

class StubPaymentStorage {

    private final Map<Long, Payment> payments = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    Option<Payment> findPaymentById(Long paymentId) {
        return Option.of(payments.get(paymentId));
    }

    Payment createPayment(PaymentCreateRequest createRequest) {
        var paymentId = sequence.getAndIncrement();
        var payment = new Payment(paymentId, createRequest.description(), createRequest.amount(), PENDING);

        payments.put(paymentId, payment);
        return payment;
    }

}

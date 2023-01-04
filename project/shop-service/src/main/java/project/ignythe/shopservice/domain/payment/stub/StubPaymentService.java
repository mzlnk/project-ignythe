package project.ignythe.shopservice.domain.payment.stub;

import project.ignythe.shopservice.domain.payment.Payment;
import project.ignythe.shopservice.domain.payment.PaymentService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static project.ignythe.shopservice.domain.payment.PaymentOperations.PaymentCreateDetails;
import static project.ignythe.shopservice.domain.payment.PaymentStatus.PENDING;

class StubPaymentService implements PaymentService {

    private final Map<Long, Payment> payments = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(0L);

    @Override
    public Payment createPayment(PaymentCreateDetails createDetails) {
        var paymentId = sequence.getAndIncrement();
        var payment = new Payment(paymentId, createDetails.description(), createDetails.amount(), PENDING);

        this.payments.put(paymentId, payment);
        return payment;
    }

}

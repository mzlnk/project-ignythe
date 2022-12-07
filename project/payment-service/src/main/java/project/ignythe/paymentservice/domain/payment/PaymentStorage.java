package project.ignythe.paymentservice.domain.payment;

import static project.ignythe.paymentservice.domain.payment.PaymentNotFoundException.paymentNotFound;
import static project.ignythe.paymentservice.domain.payment.PaymentOperations.*;

public class PaymentStorage {

    private final PaymentRepository paymentRepository;

    PaymentStorage(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment get(PaymentGetDetails getDetails) {
        return paymentRepository.findById(getDetails.paymentId())
                .orElseThrow(paymentNotFound(getDetails.paymentId()));
    }

    public Payment create() {
        var newPayment = Payment.builder()
                .status(PaymentStatus.PENDING)
                .build();

        return paymentRepository.save(newPayment);
    }

    public Payment update(PaymentUpdateDetails updateDetails) {
        var payment = get(new PaymentGetDetails(updateDetails.paymentId()));

        payment.setStatus(updateDetails.newStatus());
        return paymentRepository.save(payment);
    }

}

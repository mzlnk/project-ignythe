package project.ignythe.paymentservice.domain.payment;

import java.util.function.Supplier;

public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException(Long paymentId) {
        super("Payment with id '%s' not found");
    }

    public static Supplier<PaymentNotFoundException> paymentNotFound(Long paymentId) {
        return () -> new PaymentNotFoundException(paymentId);
    }

}

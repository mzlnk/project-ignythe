package project.ignythe.shopservice.domain.payment;

import static project.ignythe.shopservice.domain.payment.PaymentReader.ReadPaymentSucceeded;
import static project.ignythe.shopservice.domain.payment.PaymentWriter.CreatePaymentSucceeded;

public class PaymentFacade {

    private final PaymentReader paymentReader;
    private final PaymentWriter paymentWriter;

    PaymentFacade(PaymentReader paymentReader, PaymentWriter paymentWriter) {
        this.paymentReader = paymentReader;
        this.paymentWriter = paymentWriter;
    }

    public Payment getPaymentById(Long paymentId) {
        return paymentReader.readPayment(paymentId)
                .map(ReadPaymentSucceeded::payment)
                .getOrElseThrow(fail -> new PaymentException(fail.message()));
    }

    public Payment createPayment(PaymentCreateRequest createRequest) {
        return paymentWriter.createPayment(createRequest)
                .map(CreatePaymentSucceeded::payment)
                .getOrElseThrow(fail -> new PaymentException(fail.message()));
    }

}

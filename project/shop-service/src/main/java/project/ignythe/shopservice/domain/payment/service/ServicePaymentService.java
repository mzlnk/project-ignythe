package project.ignythe.shopservice.domain.payment.service;

import project.ignythe.shopservice.domain.payment.Payment;
import project.ignythe.shopservice.domain.payment.PaymentService;

import static project.ignythe.shopservice.domain.payment.PaymentOperations.PaymentCreateDetails;

class ServicePaymentService implements PaymentService {

    private final ServicePaymentClient paymentClient;

    ServicePaymentService(ServicePaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @Override
    public Payment createPayment(PaymentCreateDetails createDetails) {
        var createRequest = new PaymentCreateRequest(createDetails.description(), createDetails.amount());
        return toPayment(paymentClient.createPayment(createRequest));
    }

    private static Payment toPayment(PaymentResponse response) {
        return new Payment(response.paymentId(), response.description(), response.amount(), response.status());
    }
}

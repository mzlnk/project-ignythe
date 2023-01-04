package project.ignythe.shopservice.domain.payment;

import static project.ignythe.shopservice.domain.payment.PaymentOperations.PaymentCreateDetails;

public interface PaymentService {

    Payment createPayment(PaymentCreateDetails createDetails);

}

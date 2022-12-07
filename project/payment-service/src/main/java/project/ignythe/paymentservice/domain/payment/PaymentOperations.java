package project.ignythe.paymentservice.domain.payment;

public interface PaymentOperations {

    record PaymentGetDetails(Long paymentId) {
    }

    record PaymentUpdateDetails(Long paymentId, PaymentStatus newStatus) {
    }


}

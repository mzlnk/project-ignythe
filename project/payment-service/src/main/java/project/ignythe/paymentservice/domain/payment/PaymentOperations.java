package project.ignythe.paymentservice.domain.payment;

import java.math.BigDecimal;

public interface PaymentOperations {

    record PaymentGetDetails(Long paymentId) {
    }

    record PaymentCreateDetails(String description, BigDecimal amount) {
    }

    record PaymentUpdateDetails(Long paymentId, PaymentStatus newStatus) {
    }


}

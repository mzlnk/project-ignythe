package project.ignythe.shopservice.domain.payment;

import java.math.BigDecimal;

public interface PaymentOperations {

    record PaymentCreateDetails(String description, BigDecimal amount) {
    }

}

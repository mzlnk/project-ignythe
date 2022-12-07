package project.ignythe.paymentservice.api.payment;

import java.math.BigDecimal;

record PaymentCreateRequest(String description,
                            BigDecimal amount) {
}

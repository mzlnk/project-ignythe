package project.ignythe.shopservice.domain.payment;

import java.math.BigDecimal;

public record Payment(Long paymentId,
                      String description,
                      BigDecimal amount,
                      PaymentStatus status) {
}

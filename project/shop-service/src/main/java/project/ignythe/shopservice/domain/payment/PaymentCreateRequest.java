package project.ignythe.shopservice.domain.payment;

import java.math.BigDecimal;

public record PaymentCreateRequest(String description,
                            BigDecimal amount) {
}
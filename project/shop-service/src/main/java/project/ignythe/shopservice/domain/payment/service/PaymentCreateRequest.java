package project.ignythe.shopservice.domain.payment.service;

import java.math.BigDecimal;

record PaymentCreateRequest(String description,
                            BigDecimal amount) {
}

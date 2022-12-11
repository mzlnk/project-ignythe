package project.ignythe.shopservice.domain.payment.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import project.ignythe.shopservice.domain.payment.PaymentStatus;

import java.math.BigDecimal;

record PaymentResponse(@JsonProperty("paymentId") Long paymentId,
                       @JsonProperty("description") String description,
                       @JsonProperty("amount") BigDecimal amount,
                       @JsonProperty("status") PaymentStatus status) {
}

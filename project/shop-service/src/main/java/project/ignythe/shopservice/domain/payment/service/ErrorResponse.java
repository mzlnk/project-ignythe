package project.ignythe.shopservice.domain.payment.service;

import com.fasterxml.jackson.annotation.JsonProperty;

record ErrorResponse(@JsonProperty("title") String title,
                     @JsonProperty("description") String description,
                     @JsonProperty("status") int status) {
}

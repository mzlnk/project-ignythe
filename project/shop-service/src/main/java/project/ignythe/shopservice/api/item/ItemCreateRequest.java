package project.ignythe.shopservice.api.item;

import project.ignythe.shopservice.domain.item.UnitType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public record ItemCreateRequest(@NotBlank String name,
                                String description,
                                @Positive BigDecimal unitPrice,
                                UnitType unitType) {
}

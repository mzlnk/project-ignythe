package project.ignythe.shopservice.domain.item;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public record ItemCreateDetails(@NotBlank String name,
                                String description,
                                @Positive BigDecimal unitPrice,
                                UnitType unitType) {
}


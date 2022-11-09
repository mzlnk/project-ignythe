package project.ignythe.shopservice.api.item;

import project.ignythe.shopservice.domain.item.UnitType;

import java.math.BigDecimal;

public record ItemCreateRequest(String name,
                                String description,
                                BigDecimal unitPrice,
                                UnitType unitType) {
}

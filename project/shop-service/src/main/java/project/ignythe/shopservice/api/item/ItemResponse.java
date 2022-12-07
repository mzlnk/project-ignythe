package project.ignythe.shopservice.api.item;

import project.ignythe.shopservice.domain.item.UnitType;

import java.math.BigDecimal;

record ItemResponse(Long id,
                           String name,
                           String description,
                           BigDecimal unitPrice,
                           UnitType unitType) {
}

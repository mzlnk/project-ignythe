package project.ignythe.shopservice.api.order;

import project.ignythe.shopservice.domain.item.UnitType;
import project.ignythe.shopservice.domain.order.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

record OrderResponse(Long orderId,
                            OrderStatus status,
                            BigDecimal totalAmount,
                            List<OrderItemDetails> items) {

    record OrderItemDetails(String name,
                                   String description,
                                   BigDecimal unitPrice,
                                   UnitType unitType,
                                   Long amount) {

    }

}

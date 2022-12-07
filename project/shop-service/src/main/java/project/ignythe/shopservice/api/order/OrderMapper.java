package project.ignythe.shopservice.api.order;

import project.ignythe.shopservice.domain.order.Order;
import project.ignythe.shopservice.domain.order.OrderItem;

import java.math.BigDecimal;

import static project.ignythe.shopservice.api.order.OrderResponse.*;

class OrderMapper {

    OrderMapper() {

    }

    OrderResponse toOrderResponse(Order order) {
        var items = order.getOrderItems().stream()
                .map(this::toOrderItemDetails)
                .toList();

        var totalAmount = items.stream()
                .reduce(
                        BigDecimal.ZERO,
                        (BigDecimal acc, OrderItemDetails orderItem) -> acc.add(orderItem.unitPrice().multiply(BigDecimal.valueOf(orderItem.amount()))),
                        BigDecimal::add
                );

        return new OrderResponse(
                order.getId(),
                order.getOrderStatus(),
                order.getPaymentId(),
                totalAmount,
                items
        );
    }

    private OrderItemDetails toOrderItemDetails(OrderItem orderItem) {
        return new OrderItemDetails(
                orderItem.getName(),
                orderItem.getDescription(),
                orderItem.getUnitPrice(),
                orderItem.getUnitType(),
                orderItem.getAmount()
        );
    }

}

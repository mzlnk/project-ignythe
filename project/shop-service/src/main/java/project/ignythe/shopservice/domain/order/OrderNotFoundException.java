package project.ignythe.shopservice.domain.order;

import java.util.function.Supplier;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long orderId) {
        super("Order with id '%s' not found".formatted(orderId));
    }

    public static Supplier<OrderNotFoundException> orderNotFound(Long orderId) {
        return () -> new OrderNotFoundException(orderId);
    }

}

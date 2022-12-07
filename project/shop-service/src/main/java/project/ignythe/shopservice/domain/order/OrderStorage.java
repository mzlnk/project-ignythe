package project.ignythe.shopservice.domain.order;

import project.ignythe.shopservice.domain.basket.Basket;
import project.ignythe.shopservice.domain.basket.BasketItem;
import project.ignythe.shopservice.domain.basket.BasketOperations;
import project.ignythe.shopservice.domain.basket.BasketStorage;

import java.util.stream.Collectors;

import static project.ignythe.shopservice.domain.basket.BasketOperations.*;
import static project.ignythe.shopservice.domain.order.OrderNotFoundException.orderNotFound;
import static project.ignythe.shopservice.domain.order.OrderOperations.*;
import static project.ignythe.shopservice.domain.order.OrderStatus.*;

public class OrderStorage {

    private final OrderRepository orderRepository;
    private final BasketStorage basketStorage;

    OrderStorage(OrderRepository orderRepository, BasketStorage basketStorage) {
        this.orderRepository = orderRepository;
        this.basketStorage = basketStorage;
    }

    public Order get(OrderGetDetails getDetails) {
        return orderRepository.findById(getDetails.orderId())
                .orElseThrow(orderNotFound(getDetails.orderId()));
    }

    public Order create(OrderCreateDetails createDetails) {
        Basket basket = basketStorage.getBasketById(new BasketGetDetails(createDetails.basketId()));

        var orderItems = basket.getBasketItems().stream()
                .map(this::toOrderItem)
                .collect(Collectors.toSet());

        var order = Order.builder()
                .orderStatus(WAITING_FOR_PAYMENT)
                .orderItems(orderItems)
                .build();

        return orderRepository.save(order);
    }

    public Order update(OrderUpdateDetails updateDetails) {
        var order = get(new OrderGetDetails(updateDetails.orderId()));

        order.setOrderStatus(updateDetails.orderStatus());
        return orderRepository.save(order);
    }



    private OrderItem toOrderItem(BasketItem basketItem) {
        var item = basketItem.getItem();
        return OrderItem.builder()
                .item(item)
                .name(item.getName())
                .description(item.getDescription())
                .unitPrice(item.getUnitPrice())
                .unitType(item.getUnitType())
                .amount(basketItem.getAmount())
                .build();
    }
}

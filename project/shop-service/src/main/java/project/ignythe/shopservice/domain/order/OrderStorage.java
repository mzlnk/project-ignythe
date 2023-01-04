package project.ignythe.shopservice.domain.order;

import project.ignythe.shopservice.domain.basket.Basket;
import project.ignythe.shopservice.domain.basket.BasketItem;
import project.ignythe.shopservice.domain.basket.BasketStorage;
import project.ignythe.shopservice.domain.payment.PaymentOperations;
import project.ignythe.shopservice.domain.payment.PaymentService;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import static project.ignythe.shopservice.domain.basket.BasketOperations.BasketGetDetails;
import static project.ignythe.shopservice.domain.order.OrderNotFoundException.orderNotFound;
import static project.ignythe.shopservice.domain.order.OrderOperations.*;
import static project.ignythe.shopservice.domain.order.OrderStatus.WAITING_FOR_PAYMENT;
import static project.ignythe.shopservice.domain.payment.PaymentOperations.PaymentCreateDetails;

public class OrderStorage {

    private final OrderRepository orderRepository;
    private final BasketStorage basketStorage;
    private final PaymentService paymentService;

    OrderStorage(OrderRepository orderRepository, BasketStorage basketStorage, PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.basketStorage = basketStorage;
        this.paymentService = paymentService;
    }

    public Order get(OrderGetDetails getDetails) {
        return orderRepository.findById(getDetails.orderId())
                .orElseThrow(orderNotFound(getDetails.orderId()));
    }

    public Order create(OrderCreateDetails createDetails) {
        Basket basket = basketStorage.getBasketById(new BasketGetDetails(createDetails.basketId()));

        var orderItems = basket.getBasketItems().stream()
                .map(OrderStorage::toOrderItem)
                .collect(Collectors.toSet());

        var amount = orderItems.stream()
                .reduce(
                        BigDecimal.ZERO,
                        (BigDecimal acc, OrderItem item) -> acc.add(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getAmount()))),
                        BigDecimal::add
                );

        var paymentCreateDetails = new PaymentCreateDetails(
                "Payment for order",
                amount
        );

        var payment = paymentService.createPayment(paymentCreateDetails);

        var order = Order.builder()
                .orderStatus(WAITING_FOR_PAYMENT)
                .orderItems(orderItems)
                .paymentId(payment.paymentId())
                .build();

        return orderRepository.save(order);
    }

    public Order update(OrderUpdateDetails updateDetails) {
        var order = get(new OrderGetDetails(updateDetails.orderId()));

        order.setOrderStatus(updateDetails.orderStatus());
        return orderRepository.save(order);
    }

    private static OrderItem toOrderItem(BasketItem basketItem) {
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

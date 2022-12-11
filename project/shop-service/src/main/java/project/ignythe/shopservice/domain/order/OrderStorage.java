package project.ignythe.shopservice.domain.order;

import project.ignythe.shopservice.domain.basket.Basket;
import project.ignythe.shopservice.domain.basket.BasketItem;
import project.ignythe.shopservice.domain.basket.BasketStorage;
import project.ignythe.shopservice.domain.payment.Payment;
import project.ignythe.shopservice.domain.payment.PaymentCreateRequest;
import project.ignythe.shopservice.domain.payment.PaymentFacade;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import static project.ignythe.shopservice.domain.basket.BasketOperations.BasketGetDetails;
import static project.ignythe.shopservice.domain.order.OrderNotFoundException.orderNotFound;
import static project.ignythe.shopservice.domain.order.OrderOperations.*;
import static project.ignythe.shopservice.domain.order.OrderStatus.WAITING_FOR_PAYMENT;

public class OrderStorage {

    private final OrderRepository orderRepository;
    private final BasketStorage basketStorage;
    private final PaymentFacade paymentFacade;

    OrderStorage(OrderRepository orderRepository, BasketStorage basketStorage, PaymentFacade paymentFacade) {
        this.orderRepository = orderRepository;
        this.basketStorage = basketStorage;
        this.paymentFacade = paymentFacade;
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

        var payment = createPayment(basket.getId(), orderItems);

        var order = Order.builder()
                .orderStatus(WAITING_FOR_PAYMENT)
                .paymentId(payment.paymentId())
                .orderItems(orderItems)
                .build();

        return orderRepository.save(order);
    }

    public Order update(OrderUpdateDetails updateDetails) {
        var order = get(new OrderGetDetails(updateDetails.orderId()));

        order.setOrderStatus(updateDetails.orderStatus());
        return orderRepository.save(order);
    }

    private Payment createPayment(Long basketId, Set<OrderItem> orderItems) {
        var createRequest = new PaymentCreateRequest(
                "Payment for basket id '%s'".formatted(basketId),
                calculateOrderPrice(orderItems)
        );
        return paymentFacade.createPayment(createRequest);
    }

    private BigDecimal calculateOrderPrice(Set<OrderItem> orderItems) {
        return orderItems.stream()
                .reduce(
                        BigDecimal.ZERO,
                        (BigDecimal acc, OrderItem item) -> acc.add(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getAmount()))),
                        BigDecimal::add
                );
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

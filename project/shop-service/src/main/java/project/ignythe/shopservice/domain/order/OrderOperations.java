package project.ignythe.shopservice.domain.order;

public interface OrderOperations {

    record OrderCreateDetails(Long basketId) {
    }

    record OrderGetDetails(Long orderId) {
    }

    record OrderUpdateDetails(Long orderId, OrderStatus orderStatus) {
    }

}

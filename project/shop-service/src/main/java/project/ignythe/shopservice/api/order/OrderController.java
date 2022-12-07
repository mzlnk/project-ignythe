package project.ignythe.shopservice.api.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ignythe.shopservice.domain.order.OrderStorage;

import static org.springframework.http.HttpStatus.CREATED;
import static project.ignythe.shopservice.domain.order.OrderOperations.OrderCreateDetails;
import static project.ignythe.shopservice.domain.order.OrderOperations.OrderGetDetails;

@RestController
@RequestMapping("/orders")
class OrderController {

    private final OrderStorage orderStorage;
    private final OrderMapper orderMapper;

    OrderController(OrderStorage orderStorage, OrderMapper orderMapper) {
        this.orderStorage = orderStorage;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/{orderId}")
    ResponseEntity<OrderResponse> getById(@PathVariable Long orderId) {
        var getDetails = new OrderGetDetails(orderId);
        var order = orderStorage.get(getDetails);

        return ResponseEntity.ok(orderMapper.toOrderResponse(order));
    }

    @PostMapping
    ResponseEntity<OrderResponse> create(@RequestBody OrderCreateRequest createRequest) {
        var createDetails = new OrderCreateDetails(createRequest.basketId());

        var createdOrder = orderStorage.create(createDetails);
        return new ResponseEntity<>(orderMapper.toOrderResponse(createdOrder), CREATED);
    }

}

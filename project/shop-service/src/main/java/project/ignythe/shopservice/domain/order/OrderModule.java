package project.ignythe.shopservice.domain.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.ignythe.shopservice.domain.basket.BasketStorage;
import project.ignythe.shopservice.domain.payment.PaymentService;

@Configuration
class OrderModule {

    @Bean
    OrderStorage orderStorage(OrderRepository orderRepository, BasketStorage basketStorage, PaymentService paymentService) {
        return new OrderStorage(orderRepository, basketStorage, paymentService);
    }

}

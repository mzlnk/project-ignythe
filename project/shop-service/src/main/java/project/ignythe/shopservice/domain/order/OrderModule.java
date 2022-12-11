package project.ignythe.shopservice.domain.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.ignythe.shopservice.domain.basket.BasketStorage;
import project.ignythe.shopservice.domain.payment.PaymentFacade;

@Configuration
class OrderModule {

    @Bean
    OrderStorage orderStorage(OrderRepository orderRepository, BasketStorage basketStorage, PaymentFacade paymentFacade) {
        return new OrderStorage(orderRepository, basketStorage, paymentFacade);
    }

}

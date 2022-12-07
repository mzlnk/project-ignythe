package project.ignythe.shopservice.api.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderControllerModule {

    @Bean
    OrderMapper orderMapper() {
        return new OrderMapper();
    }

}

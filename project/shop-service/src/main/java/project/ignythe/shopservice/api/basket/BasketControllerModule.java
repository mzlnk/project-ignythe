package project.ignythe.shopservice.api.basket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BasketControllerModule {

    @Bean
    BasketMapper basketMapper() {
        return new BasketMapper();
    }

    @Bean
    BasketItemMapper basketItemMapper() {
        return new BasketItemMapper();
    }

}

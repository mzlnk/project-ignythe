package project.ignythe.shopservice.api.basket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasketControllerModule {

    @Bean
    public BasketMapper basketMapper() {
        return new BasketMapper();
    }

    @Bean
    public BasketItemMappper basketItemMappper() {
        return new BasketItemMappper();
    }

}

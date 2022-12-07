package project.ignythe.shopservice.domain.basket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.ignythe.shopservice.domain.item.ItemStorage;

@Configuration
class BasketModule {

    @Bean
    BasketStorage basketStorage(BasketRepository basketRepository,
                                       BasketItemRepository basketItemRepository,
                                       ItemStorage itemStorage) {
        return new BasketStorage(basketRepository, basketItemRepository, itemStorage);
    }

}

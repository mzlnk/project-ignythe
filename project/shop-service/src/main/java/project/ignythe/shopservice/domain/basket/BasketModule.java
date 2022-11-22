package project.ignythe.shopservice.domain.basket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.ignythe.shopservice.domain.item.ItemService;

@Configuration
public class BasketModule {

    @Bean
    public BasketStorage basketStorage(BasketRepository basketRepository,
                                       BasketItemRepository basketItemRepository,
                                       ItemService itemService) {
        return new BasketStorage(basketRepository, basketItemRepository, itemService);
    }

}

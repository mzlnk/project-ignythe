package project.ignythe.shopservice.domain.item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ItemModule {

    @Bean
    ItemStorage itemService(ItemRepository itemRepository) {
        return new ItemStorage(itemRepository);
    }

}

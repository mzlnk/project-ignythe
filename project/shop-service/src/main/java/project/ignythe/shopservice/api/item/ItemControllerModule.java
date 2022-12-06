package project.ignythe.shopservice.api.item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ItemControllerModule {

    @Bean
    ItemMapper itemMapper() {
        return new ItemMapper();
    }

}

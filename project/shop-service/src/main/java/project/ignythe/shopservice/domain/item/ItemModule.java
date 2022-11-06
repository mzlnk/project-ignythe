package project.ignythe.shopservice.domain.item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemModule {

    @Bean
    public ItemService itemService() {
        return new ItemService();
    }

}

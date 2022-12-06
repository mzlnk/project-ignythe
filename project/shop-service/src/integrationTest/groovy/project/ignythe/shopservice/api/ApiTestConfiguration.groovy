package project.ignythe.shopservice.api

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import project.ignythe.shopservice.api.basket.BasketItemMappper
import project.ignythe.shopservice.api.basket.BasketMapper

@TestConfiguration
class ApiTestConfiguration {

    @Bean
    BasketMapper basketMapper() {
        return new BasketMapper()
    }

    @Bean
    BasketItemMappper basketItemMappper() {
        return new BasketItemMappper()
    }

}

package project.ignythe.paymentservice.api.payment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PaymentControllerModule {

    @Bean
    PaymentMapper paymentMapper() {
        return new PaymentMapper();
    }

}

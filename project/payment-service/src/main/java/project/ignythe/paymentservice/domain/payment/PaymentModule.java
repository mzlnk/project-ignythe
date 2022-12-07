package project.ignythe.paymentservice.domain.payment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PaymentModule {

    @Bean
    PaymentStorage paymentStorage(PaymentRepository paymentRepository) {
        return new PaymentStorage(paymentRepository);
    }

}

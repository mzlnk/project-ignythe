package project.ignythe.shopservice.domain.payment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PaymentModule {

    @Bean
    PaymentFacade paymentFacade(PaymentReader paymentReader, PaymentWriter paymentWriter) {
        return new PaymentFacade(paymentReader, paymentWriter);
    }

}

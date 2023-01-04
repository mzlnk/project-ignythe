package project.ignythe.shopservice.domain.payment.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.ignythe.shopservice.domain.payment.PaymentService;

@Configuration
@ConditionalOnProperty(prefix = "payment", name = "type", havingValue = "service")
class ServicePaymentModule {

    @Bean
    PaymentService servicePaymentService(ServicePaymentClient paymentClient) {
        return new ServicePaymentService(paymentClient);
    }

}

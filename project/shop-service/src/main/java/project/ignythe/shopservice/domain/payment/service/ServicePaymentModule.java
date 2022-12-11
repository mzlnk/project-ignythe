package project.ignythe.shopservice.domain.payment.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.ignythe.shopservice.domain.payment.PaymentReader;
import project.ignythe.shopservice.domain.payment.PaymentWriter;

@Configuration
@ConditionalOnProperty(prefix = "payment", name = "type", havingValue = "service")
class ServicePaymentModule {

    @Bean
    PaymentReader servicePaymentReader(ServicePaymentClient paymentClient) {
        return new ServicePaymentReader(paymentClient);
    }

    @Bean
    PaymentWriter servicePaymentWriter(ServicePaymentClient paymentClient) {
        return new ServicePaymentWriter(paymentClient);
    }

}

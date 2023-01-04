package project.ignythe.shopservice.domain.payment.stub;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.ignythe.shopservice.domain.payment.PaymentService;

@Configuration
@ConditionalOnProperty(prefix = "payment", name = "type", havingValue = "stub")
class StubPaymentModule {

    @Bean
    PaymentService stubPaymentService() {
        return new StubPaymentService();
    }

}

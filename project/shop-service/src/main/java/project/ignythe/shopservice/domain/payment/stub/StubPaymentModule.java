package project.ignythe.shopservice.domain.payment.stub;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.ignythe.shopservice.domain.payment.PaymentReader;
import project.ignythe.shopservice.domain.payment.PaymentWriter;

@Configuration
@ConditionalOnProperty(prefix = "payment", name = "type", havingValue = "stub")
class StubPaymentModule {

    @Bean
    StubPaymentStorage stubPaymentStorage() {
        return new StubPaymentStorage();
    }

    @Bean
    PaymentReader stubPaymentReader(StubPaymentStorage stubPaymentStorage) {
        return new StubPaymentReader(stubPaymentStorage);
    }

    @Bean
    PaymentWriter stubPaymentWriter(StubPaymentStorage stubPaymentStorage) {
        return new StubPaymentWriter(stubPaymentStorage);
    }

}

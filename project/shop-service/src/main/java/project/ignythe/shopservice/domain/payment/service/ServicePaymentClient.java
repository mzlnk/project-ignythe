package project.ignythe.shopservice.domain.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import feign.httpclient.ApacheHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import project.ignythe.shopservice.domain.payment.PaymentCreateRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@ConditionalOnProperty(prefix = "payment", name = "type", havingValue = "service")
@FeignClient(
        name = "payment-client",
        url = "${payment.host}",
        configuration = ServicePaymentClientConfiguration.class
)
interface ServicePaymentClient {

    @RequestMapping(method = GET, value = "/payments/{paymentId}")
    PaymentResponse readPaymentById(@PathVariable("paymentId") Long paymentId);

    @RequestMapping(method = POST, value = "/payments")
    PaymentResponse createPayment(@RequestBody PaymentCreateRequest createRequest);

}

class ServicePaymentClientConfiguration {

    @Bean
    ApacheHttpClient client() {
        return new ApacheHttpClient();
    }

    @Bean
    ErrorDecoder errorDecoder(ObjectMapper objectMapper) {
        return new ServicePaymentErrorDecoder(objectMapper);
    }

}

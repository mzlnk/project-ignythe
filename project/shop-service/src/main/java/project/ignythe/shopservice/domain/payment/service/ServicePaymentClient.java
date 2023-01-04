package project.ignythe.shopservice.domain.payment.service;

import feign.RequestInterceptor;
import feign.httpclient.ApacheHttpClient;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        name = "payment-client",
        url = "${payment.host}",
        configuration = ServicePaymentClientConfiguration.class
)
@ConditionalOnProperty(prefix = "payment", name = "type", havingValue = "service")
interface ServicePaymentClient {

    @RequestMapping(method = RequestMethod.POST, path = "/payments")
    PaymentResponse createPayment(PaymentCreateRequest createRequest);

}

class ServicePaymentClientConfiguration {

    @Bean
    ApacheHttpClient apacheHttpClient() {
        return new ApacheHttpClient();
    }

    @Bean
    RequestInterceptor traceIdRequestInterceptor() {
        return template -> {
            template.header("Trace-Id", MDC.get("traceId"));
        };
    }

}

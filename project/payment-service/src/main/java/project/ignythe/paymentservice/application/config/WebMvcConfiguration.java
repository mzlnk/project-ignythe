package project.ignythe.paymentservice.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.ignythe.paymentservice.application.logging.RequestIdInterceptor;
import project.ignythe.paymentservice.application.logging.TraceIdInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestIdInterceptor());
        registry.addInterceptor(new TraceIdInterceptor());
    }
}

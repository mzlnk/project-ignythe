package project.ignythe.shopservice.domain.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import io.vavr.control.Try;
import project.ignythe.shopservice.common.http.HttpClientException;

import java.io.IOException;

class ServicePaymentErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    ServicePaymentErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        return Try.of(() -> readErrorResponse(response.body()))
                .fold(this::onFail, this::onSuccess);
    }

    private ErrorResponse readErrorResponse(Response.Body responseBody) throws IOException {
        return objectMapper.readValue(responseBody.asInputStream(), ErrorResponse.class);
    }

    private HttpClientException onSuccess(ErrorResponse errorResponse) {
        return new HttpClientException(errorResponse.description());
    }

    private HttpClientException onFail(Throwable throwable) {
        return new HttpClientException(throwable.getMessage());
    }
}

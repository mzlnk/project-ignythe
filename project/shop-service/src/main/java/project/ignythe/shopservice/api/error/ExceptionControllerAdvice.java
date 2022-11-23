package project.ignythe.shopservice.api.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import project.ignythe.shopservice.domain.basket.BasketNotFoundException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var error = new ErrorResponse(
                "Validation failed",
                ex.getBindingResult().getFieldErrors().stream().map(err -> "%s %s".formatted(err.getField(), err.getDefaultMessage())).collect(Collectors.joining(","))
        );

        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);

        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(BasketNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(BasketNotFoundException ex) {
        var error = new ErrorResponse("Basket Not Found", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}

package project.ignythe.shopservice.domain.payment;

public class PaymentException extends RuntimeException {

    PaymentException(String message) {
        super(message);
    }

}

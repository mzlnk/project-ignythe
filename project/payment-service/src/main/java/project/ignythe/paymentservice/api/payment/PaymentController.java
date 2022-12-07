package project.ignythe.paymentservice.api.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ignythe.paymentservice.domain.payment.PaymentOperations;
import project.ignythe.paymentservice.domain.payment.PaymentStorage;

import static org.springframework.http.HttpStatus.CREATED;
import static project.ignythe.paymentservice.domain.payment.PaymentOperations.*;
import static project.ignythe.paymentservice.domain.payment.PaymentOperations.PaymentGetDetails;

@RestController
@RequestMapping("/payments")
class PaymentController {

    private final PaymentStorage paymentStorage;
    private final PaymentMapper paymentMapper;

    PaymentController(PaymentStorage paymentStorage, PaymentMapper paymentMapper) {
        this.paymentStorage = paymentStorage;
        this.paymentMapper = paymentMapper;
    }

    @GetMapping("/{paymentId}")
    ResponseEntity<PaymentResponse> getById(@PathVariable Long paymentId) {
        var getDetails = new PaymentGetDetails(paymentId);
        var payment = paymentStorage.get(getDetails);

        return ResponseEntity.ok(paymentMapper.toPaymentResponse(payment));
    }

    @PostMapping
    ResponseEntity<PaymentResponse> create(@RequestBody PaymentCreateRequest createRequest) {
        var createDetails = new PaymentCreateDetails(createRequest.description(), createRequest.amount());
        var createdPayment = paymentStorage.create(createDetails);

        return new ResponseEntity<>(paymentMapper.toPaymentResponse(createdPayment), CREATED);
    }

    @PutMapping("/{paymentId}")
    ResponseEntity<PaymentResponse> update(@PathVariable Long paymentId,
                                           @RequestBody PaymentUpdateRequest updateRequest) {
        var updateDetails = new PaymentUpdateDetails(paymentId, updateRequest.newStatus());
        var updatedPayment = paymentStorage.update(updateDetails);

        return ResponseEntity.ok(paymentMapper.toPaymentResponse(updatedPayment));
    }
}

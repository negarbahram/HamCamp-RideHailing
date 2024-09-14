package org.ridehailing.tripservice.Controller;

import lombok.AllArgsConstructor;
import org.ridehailing.tripservice.Service.PaymentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/trip/pay")
public class PaymentController {
    private final PaymentService paymentService;
    @GetMapping("")
    public ResponseEntity<String> tripPay(@RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok(paymentService.processPayment(headers));
    }
}

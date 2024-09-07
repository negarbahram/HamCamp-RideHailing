package org.ridehailing.tripservice.Controller;

import lombok.AllArgsConstructor;
import org.ridehailing.tripservice.DTO.CreatTripRequest;
import org.ridehailing.tripservice.Entity.Model.Trip;
import org.ridehailing.tripservice.Service.CreatTripService;
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
    public ResponseEntity<String> tripStatus(@RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok(paymentService.processPayment(headers));
    }
}

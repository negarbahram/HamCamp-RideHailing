package org.ridehailing.tripservice.Service;

import lombok.RequiredArgsConstructor;
import org.ridehailing.tripservice.Entity.Model.Trip;
import org.ridehailing.tripservice.Repository.TripRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final TripRepository tripRepository;
    private final PassengerService passengerService;

    @Transactional
    public String processPayment(HttpHeaders headers) {
        // Fetch the trip from the database
        Trip trip = passengerService.getActiveTrip(headers);


        if (trip.isPaid()) {
            return "Payment already processed for this trip.";
        }

        boolean paymentSuccessful = true;

        if (paymentSuccessful) {
            trip.setPaid(true);
            tripRepository.save(trip);
            return "Payment successful.";
        } else {
            return "Payment failed.";
        }
    }
}

package org.ridehailing.tripservice.Service;

import lombok.RequiredArgsConstructor;
import org.ridehailing.tripservice.Config.RabbitMQConfig;
import org.ridehailing.tripservice.Entity.Model.Passenger;
import org.ridehailing.tripservice.Entity.Model.Trip;
import org.ridehailing.tripservice.Repository.TripRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final TripRepository tripRepository;
    private final PassengerService passengerService;
    private final RabbitTemplate rabbitTemplate;

    public String processPayment(HttpHeaders headers) {

        Passenger passenger = passengerService.get(headers);
        Trip trip = tripRepository.findByPassenger(passenger);

        if (trip.isPaid()) {
            rabbitTemplate.convertAndSend(RabbitMQConfig.REPORTING_QUEUE, "repeated payment attempt-" + trip);
            return "Payment already processed for this trip.";
        }

        passenger = passengerService.getPassenger(headers);

        boolean paymentSuccessful = true;

        if (paymentSuccessful) {
            trip.setPaid(true);
            tripRepository.save(trip);
            passengerService.updateCache(headers, passenger);
            rabbitTemplate.convertAndSend(RabbitMQConfig.REPORTING_QUEUE, "successful payment attempt-" + trip);
            return "Payment successful.";
        } else {
            rabbitTemplate.convertAndSend(RabbitMQConfig.REPORTING_QUEUE, "failed payment attempt-" + trip);
            return "Payment failed.";
        }
    }
}

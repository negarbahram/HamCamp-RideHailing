package org.ridehailing.tripservice.Service;

import lombok.RequiredArgsConstructor;
import org.ridehailing.tripservice.Config.RabbitMQConfig;
import org.ridehailing.tripservice.Entity.Model.Passenger;
import org.ridehailing.tripservice.Entity.Model.Trip;
import org.ridehailing.tripservice.Repository.TripRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TripStatusService {

    private final PassengerService passengerService;
    private final TripRepository tripRepository;
    private final RabbitTemplate rabbitTemplate;


    public Trip getTripStatus(HttpHeaders headers) {
        Trip trip = tripRepository.findByPassenger(passengerService.get(headers));
        rabbitTemplate.convertAndSend(RabbitMQConfig.REPORTING_QUEUE, "trip status checked-" + trip);
        return trip;
    }
}

package org.ridehailing.tripservice.Service;

import lombok.AllArgsConstructor;
import org.ridehailing.tripservice.Config.RabbitMQConfig;
import org.ridehailing.tripservice.DTO.CreatTripRequest;
import org.ridehailing.tripservice.Entity.Model.Driver;
import org.ridehailing.tripservice.Entity.Model.Passenger;
import org.ridehailing.tripservice.Entity.Model.Trip;
import org.ridehailing.tripservice.Exception.DriverNotFoundException;
import org.ridehailing.tripservice.Repository.TripRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatTripService {

    private final AllocateDriverService allocateDriverService;
    private final PassengerService passengerService;
    private final TripRepository tripRepository;
    private final RabbitTemplate rabbitTemplate;
    public Trip creatTrip(HttpHeaders headers, CreatTripRequest creatTripRequest) {

        Passenger passenger = passengerService.getPassengerByToken(headers);

        Driver driver = allocateDriverService.findClosestDriver(creatTripRequest)
                .orElseThrow(DriverNotFoundException::new);

        Trip trip = new Trip(null, true, passenger, driver);

        rabbitTemplate.convertAndSend(RabbitMQConfig.REPORTING_QUEUE, trip.toString());

        return tripRepository.save(trip);
    }
}

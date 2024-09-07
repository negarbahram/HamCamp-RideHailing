package org.ridehailing.tripservice.Service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.ridehailing.tripservice.DTO.CreatTripRequest;
import org.ridehailing.tripservice.Entity.Model.Driver;
import org.ridehailing.tripservice.Entity.Model.Passenger;
import org.ridehailing.tripservice.Entity.Model.Trip;
import org.ridehailing.tripservice.Exception.DriverNotFoundException;
import org.ridehailing.tripservice.Repository.PassengerRepository;
import org.ridehailing.tripservice.Repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CreatTripService {

    private final AllocateDriverService allocateDriverService;
    private final PassengerRepository passengerRepository;
    private final TripRepository tripRepository;
    public Trip creatTrip(CreatTripRequest creatTripRequest, Integer passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId).get();
        System.out.println("**** " + passenger.getFirstname());
        Driver driver = allocateDriverService.findClosestDriver(creatTripRequest)
                .orElseThrow(DriverNotFoundException::new);
        Trip trip = new Trip(null, passenger, driver);

        return tripRepository.save(trip);
    }
}

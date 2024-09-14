package org.ridehailing.tripservice.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.ridehailing.tripservice.Entity.Model.Passenger;
import org.ridehailing.tripservice.Entity.Model.Trip;
import org.ridehailing.tripservice.Exception.TripNotFoundException;
import org.ridehailing.tripservice.Repository.PassengerRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final JwtService jwtService;
    private final PassengerRepository passengerRepository;
    private final RedisTemplate<String, Passenger> redisson;
    private final RedissonClient redissonClient;
    private RMap<String, Passenger> passengerCache;


    @PostConstruct
    public void init() {
        passengerCache = redissonClient.getMap("my_passenger_cache");
    }
    public Trip getActiveTrip(Passenger passenger) {
        List<Trip> trips = passenger.getTripHistory();
        Optional<Trip> trip = trips.stream().filter(Trip::getActive).toList().stream().findFirst();
        return trip.orElseThrow(TripNotFoundException::new);
    }

    public Passenger getPassenger(HttpHeaders headers) {
        return getPassenger(jwtService.getEmailByToken(headers));
    }

    public Passenger getPassenger(String email) {
        Optional<Passenger> passenger = passengerRepository.findByEmail(email);
        if (passenger.isEmpty())
            passenger = Optional.of(passengerRepository.save(new Passenger(email)));

        return passenger.get();
    }

    public Passenger get(String email) {
        Passenger passenger = redisson.opsForValue().get(email);
        if (passenger == null) {
            passenger = getPassenger(email);
            redisson.opsForValue().set(email, passenger);
        }
        return passenger;
    }

    public Passenger get(HttpHeaders headers) {
        return get(jwtService.getEmailByToken(headers));
    }

    public void updateCache(String email, Passenger passenger) {
        passengerRepository.save(passenger);
        redisson.opsForValue().set(email, passenger);
    }

    public void updateCache(HttpHeaders headers, Passenger passenger) {
        passengerRepository.save(passenger);
        updateCache(jwtService.getEmailByToken(headers), passenger);
    }
}

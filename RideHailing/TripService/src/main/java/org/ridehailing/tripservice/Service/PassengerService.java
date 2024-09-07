package org.ridehailing.tripservice.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.ridehailing.tripservice.Entity.Model.Passenger;
import org.ridehailing.tripservice.Entity.Model.Trip;
import org.ridehailing.tripservice.Exception.TripNotFoundException;
import org.ridehailing.tripservice.Repository.PassengerRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public Trip getActiveTrip(HttpHeaders headers) {
        Passenger passenger = getPassengerByToken(headers);
        List<Trip> trips = passenger.getTripHistory();
        Optional<Trip> trip = trips.stream().filter(Trip::getIsOver).toList().stream().findFirst();
        return trip.orElseThrow(TripNotFoundException::new);
    }

    public Passenger getPassengerByToken(HttpHeaders headers) {

        String authHeader = headers.get(org.springframework.http.HttpHeaders.AUTHORIZATION).get(0);
        System.out.println("***" + authHeader);

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("missing authorization header");
        }

        final String token = authHeader.substring(7);

        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        String subValue = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(payload);
            subValue = jsonNode.get("sub").asText();

            System.out.println("sub: " + subValue);  // Output: sub: 1234567890
        } catch (Exception e) {
            e.printStackTrace();
        }

        Optional<Passenger> passenger = passengerRepository.findByEmail(subValue);
        if (passenger.isEmpty())
            passenger = Optional.of(passengerRepository.save(new Passenger(subValue)));

        return passenger.get();
    }
}

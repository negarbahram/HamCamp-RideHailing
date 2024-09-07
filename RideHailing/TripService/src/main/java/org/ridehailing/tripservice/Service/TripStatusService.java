package org.ridehailing.tripservice.Service;

import lombok.RequiredArgsConstructor;
import org.ridehailing.tripservice.Entity.Model.Trip;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripStatusService {

    private final PassengerService passengerService;

    public Trip getTripStatus(HttpHeaders headers) {
        return passengerService.getActiveTrip(headers);
    }
}

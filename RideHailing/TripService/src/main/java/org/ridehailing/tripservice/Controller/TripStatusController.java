package org.ridehailing.tripservice.Controller;


import lombok.AllArgsConstructor;
import org.ridehailing.tripservice.DTO.CreatTripRequest;
import org.ridehailing.tripservice.Entity.Model.Trip;
import org.ridehailing.tripservice.Repository.TripRepository;
import org.ridehailing.tripservice.Service.TripStatusService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/trip/status")
public class TripStatusController {

    private final TripStatusService tripStatusService;
    @GetMapping("")
    public ResponseEntity<Trip> tripStatus(@RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok(tripStatusService.getTripStatus(headers));
    }
}

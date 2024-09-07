package org.ridehailing.tripservice.Controller;

import lombok.AllArgsConstructor;
import org.ridehailing.tripservice.DTO.CreatTripRequest;
import org.ridehailing.tripservice.Entity.Model.Trip;
import org.ridehailing.tripservice.Service.CreatTripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("trip/creat")
public class CreatTripController {

    private final CreatTripService creatTripService;
    @GetMapping("")
    public ResponseEntity<Trip> creatTrip(@RequestBody CreatTripRequest creatTripRequest, @RequestParam("userId") Integer userId) {
        return ResponseEntity.ok(creatTripService.creatTrip(creatTripRequest, userId));
    }
}

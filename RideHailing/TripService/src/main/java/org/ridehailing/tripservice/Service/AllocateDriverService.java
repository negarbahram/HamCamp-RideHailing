package org.ridehailing.tripservice.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.ridehailing.tripservice.DTO.CreatTripRequest;
import org.ridehailing.tripservice.Entity.Model.Driver;
import org.ridehailing.tripservice.Repository.DriverRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AllocateDriverService {

    private final DriverRepository driverRepository;

    public List<Driver> findDriversWithinDistanceSortedByDistance(CreatTripRequest creatTripRequest) {
        return driverRepository.findDriversWithinDistanceSortedByDistance(
                Double.parseDouble(creatTripRequest.getLatitude()),
                Double.parseDouble(creatTripRequest.getLongitude())
        );
    }

    public Optional<Driver> findClosestDriver(CreatTripRequest creatTripRequest) {
        return findDriversWithinDistanceSortedByDistance(creatTripRequest).stream().findFirst();
    }
}

package org.ridehailing.tripservice;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.geom.Point;
import org.ridehailing.tripservice.Entity.Model.Driver;
import org.ridehailing.tripservice.Entity.Model.Passenger;
import org.ridehailing.tripservice.Repository.DriverRepository;
import org.ridehailing.tripservice.Repository.PassengerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.*;

@SpringBootApplication
public class TripServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripServiceApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(
            DriverRepository driverRepository
    ) {
        return args -> {

            GeometryFactory geometryFactory = new GeometryFactory();

            driverRepository.save(new Driver(geometryFactory.createPoint(new Coordinate(3, 5)), "first@gmail.com"));
            driverRepository.save(new Driver(geometryFactory.createPoint(new Coordinate(3, 6)), "seconf@gmail.com"));
            driverRepository.save(new Driver(geometryFactory.createPoint(new Coordinate(3, 8)), "neg@gmail.com"));
            driverRepository.save(new Driver(geometryFactory.createPoint(new Coordinate(4, 9)), "bah@gmail.com"));
        };
    }

}

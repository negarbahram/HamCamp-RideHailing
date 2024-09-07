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
            DriverRepository driverRepository,
            PassengerRepository passengerRepository
    ) {
        return args -> {

            GeometryFactory geometryFactory = new GeometryFactory();

            driverRepository.save(new Driver(geometryFactory.createPoint(new Coordinate(3, 5))));
            driverRepository.save(new Driver(geometryFactory.createPoint(new Coordinate(3, 6))));
            driverRepository.save(new Driver(geometryFactory.createPoint(new Coordinate(3, 8))));
            driverRepository.save(new Driver(geometryFactory.createPoint(new Coordinate(4, 9))));
/*
            passengerRepository.save(new Passenger("neg"));
            passengerRepository.save(new Passenger("bah"));
            passengerRepository.save(new Passenger("sar"));
*/
            //    }, new PrecisionModel())));
          /*  Faker faker = new Faker();

            for(int i = 0; i < 10; i++) {

                var food = Food.builder()
                        .name(faker.food().dish())
                        .category(faker.food().ingredient())
                        .price(faker.number().numberBetween(0, 100))
                        .build();
                foodRepository.save(food);

                orderRepository.save(new Order());
                restaurantRepository.save(new Restaurant());

                var customer = Customer.builder()
                        .username(faker.name().username())
                        .password(Integer.toString(faker.number().numberBetween(100, 1000)))
                        .build();
                userRepository.save(customer);
            }*/

            //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);

            // userRepository.save(new User(null,"sar", encoder.encode("1234")));
        };
    }

}

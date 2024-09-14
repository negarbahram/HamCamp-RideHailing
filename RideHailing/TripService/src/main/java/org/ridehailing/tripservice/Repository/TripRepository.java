package org.ridehailing.tripservice.Repository;

import org.ridehailing.tripservice.Entity.Model.Passenger;
import org.ridehailing.tripservice.Entity.Model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    Trip findByPassenger(Passenger passenger);
}

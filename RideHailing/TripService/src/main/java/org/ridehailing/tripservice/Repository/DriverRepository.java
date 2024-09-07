package org.ridehailing.tripservice.Repository;

import org.ridehailing.tripservice.DTO.CreatTripRequest;
import org.ridehailing.tripservice.Entity.Model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

   @Query(value = "SELECT * FROM driver d WHERE ST_DWithin(d.location, ST_MakePoint(:x, :y), 50) " +
           "ORDER BY ST_Distance(d.location, ST_MakePoint(:x, :y)) ASC", nativeQuery = true)
   List<Driver> findDriversWithinDistanceSortedByDistance(@Param("x") double x, @Param("y") double y);
}

package org.ridehailing.tripservice.Entity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Driver {
    @Id
    @GeneratedValue
    Integer id;
    Point location;
    String email;

    public Driver(Point location, String email) {
        this.location = location;
        this.email = "e@gmail.com";
    }

    @OneToMany(mappedBy = "driver")
    @JsonIgnore
    List<Trip> tripHistory;

    public String toString() {
        return "driverId = " + id;
    }
}

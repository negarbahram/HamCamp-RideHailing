package org.ridehailing.tripservice.Entity.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Trip {

    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("tripHistory")
    Passenger passenger;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("tripHistory")
    Driver driver;
}

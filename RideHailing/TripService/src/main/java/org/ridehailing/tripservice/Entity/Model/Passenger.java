package org.ridehailing.tripservice.Entity.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Passenger {
    @Id
    @GeneratedValue
    Integer id;

    String email;

    @OneToMany(mappedBy = "passenger")
    @JsonIgnoreProperties("passenger")
    List<Trip> tripHistory;

    public Passenger(String email) {
        this.email = email;
    }
}

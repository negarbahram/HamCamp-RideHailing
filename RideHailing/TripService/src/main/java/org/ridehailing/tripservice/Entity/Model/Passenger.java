package org.ridehailing.tripservice.Entity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Passenger implements Serializable {
    @Id
    @GeneratedValue
    Integer id;

    String email;

    @OneToMany(mappedBy = "passenger")
    @JsonIgnore
    List<Trip> tripHistory;

    public Passenger(String email) {
        this.email = email;
    }

    public String toString() {
        return "passengerId = " + id;
    }
}

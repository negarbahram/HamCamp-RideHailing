package org.ridehailing.tripservice.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.locationtech.jts.geom.Point;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatTripRequest {
    String latitude;
    String longitude;
}

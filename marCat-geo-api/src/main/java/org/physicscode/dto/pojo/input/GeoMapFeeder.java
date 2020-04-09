package org.physicscode.dto.pojo.input;

import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
public class GeoMapFeeder {

    private GeoJsonPoint location;
    private String city;


}

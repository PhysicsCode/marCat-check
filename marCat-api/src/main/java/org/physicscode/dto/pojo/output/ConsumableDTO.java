package org.physicscode.dto.pojo.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ConsumableDTO {

    @JsonProperty("consumable_name")
    private String consumableName;

    @JsonProperty("last_report")
    private Instant lastReport;


}


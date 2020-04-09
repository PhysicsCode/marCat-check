package org.physicscode.dto.pojo.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SuperMarketFeedbackInputDTO {

    @JsonProperty("google_code")
    private String googleCode;

    @JsonProperty("waiting-minutes")
    private Integer waitingMinutes;


}

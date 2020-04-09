package org.physicscode.dto.pojo.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class FeedbackResponseDTO {

    private String user;
    private String message;
    @JsonProperty("feedback_time")
    private Instant feedbackTime;
}

package org.physicscode.dto.pojo.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SupermarketDataDTO {

    @JsonProperty("estimated_queue_minutes_outside")
    private Integer estimatedQueueMinutesOutside;

    @JsonProperty("estimated_queue_minutes_inside")
    private Integer estimatedQueueMinutesInside;

    @JsonProperty("maximum_capacity")
    private Integer maximumCapacity;

    @JsonProperty("lack_consumable_list")
    private List<ConsumableDTO> lackConsumableList;

    @JsonProperty("feedback_list")
    private List<FeedbackResponseDTO> feedbackList;
}

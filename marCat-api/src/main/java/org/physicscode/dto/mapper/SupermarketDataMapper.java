package org.physicscode.dto.mapper;

import org.mapstruct.ReportingPolicy;
import org.physicscode.domain.entity.FeedbackEntity;
import org.physicscode.domain.entity.SupermarketData;
import org.physicscode.dto.pojo.input.SuperMarketFeedbackInputDTO;
import org.physicscode.dto.pojo.output.SupermarketDataDTO;
import org.mapstruct.Mapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SupermarketDataMapper {
    //SupermarketDataDTO mapToOutput(SupermarketData supermarketData);

    FeedbackEntity mapToFeedbackEntity(SuperMarketFeedbackInputDTO superMarketFeedbackInputDTO);
}

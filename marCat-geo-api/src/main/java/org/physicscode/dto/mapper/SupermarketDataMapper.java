package org.physicscode.dto.mapper;

import org.mapstruct.Mapper;
import org.physicscode.domain.entity.FeedbackEntity;
import org.physicscode.domain.entity.SupermarketData;
import org.physicscode.dto.pojo.input.GeoMapFeeder;
import org.physicscode.dto.pojo.output.SupermarketDataDTO;

@Mapper
public interface SupermarketDataMapper {
    SupermarketDataDTO mapToOutput(SupermarketData supermarketData);

    FeedbackEntity mapToFeedbackEntity(GeoMapFeeder superMarketFeedbackInputDTO);
}

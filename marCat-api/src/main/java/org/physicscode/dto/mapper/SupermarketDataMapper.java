package org.physicscode.dto.mapper;

import org.physicscode.domain.entity.SupermarketData;
import org.physicscode.dto.pojo.output.SupermarketDataDTO;
import org.mapstruct.Mapper;

@Mapper
public interface SupermarketDataMapper {
    SupermarketDataDTO mapToOutput(SupermarketData supermarketData);
}

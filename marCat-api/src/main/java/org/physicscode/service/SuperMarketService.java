package org.physicscode.service;

import org.physicscode.domain.repository.SuperMarketRepository;
import org.physicscode.dto.mapper.SupermarketDataMapper;
import org.physicscode.dto.pojo.input.LocatorIdentifier;
import org.physicscode.dto.pojo.output.SupermarketDataDTO;
import org.physicscode.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static org.physicscode.exception.ErrorCode.EMPTY_SUPERMARKET_DATA;

@RequiredArgsConstructor
@Service
public class SuperMarketService {

    private final SuperMarketRepository superMarketRepository;
    private final SupermarketDataMapper superMarketDataMapper;

    public Mono<SupermarketDataDTO> retrieveSuperMarketData(LocatorIdentifier locationId) {

        return superMarketRepository.findByGoogleIdAndCity(locationId.getGoogleCode(), locationId.getCity())
            .map(superMarketDataMapper::mapToOutput)
            .switchIfEmpty(Mono.error(new ServiceException(EMPTY_SUPERMARKET_DATA)));
    }
}

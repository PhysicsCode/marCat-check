package org.physicscode.service;

import org.physicscode.domain.entity.FeedbackEntity;
import org.physicscode.domain.entity.SupermarketData;
import org.physicscode.domain.repository.SupermarketRepository;
import org.physicscode.dto.mapper.SupermarketDataMapper;
import org.physicscode.dto.pojo.input.LocatorIdentifier;
import org.physicscode.dto.pojo.input.SuperMarketFeedbackInputDTO;
import org.physicscode.dto.pojo.output.SupermarketDataDTO;
import org.physicscode.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.physicscode.exception.ErrorCode.EMPTY_SUPERMARKET_DATA;
import static org.physicscode.exception.ErrorCode.FAILED_TO_INSERT_FEEDBACK;

@RequiredArgsConstructor
@Service
public class SuperMarketService {

    private final SupermarketRepository supermarketRepository;
    private final SupermarketDataMapper superMarketDataMapper;

    public Mono<SupermarketDataDTO> retrieveSuperMarketData(LocatorIdentifier locationId) {

        return supermarketRepository.findByGoogleIdAndCity(locationId.getGoogleCode(), locationId.getCity())
            .map(superMarketDataMapper::mapToOutput)
            .switchIfEmpty(Mono.error(new ServiceException(EMPTY_SUPERMARKET_DATA)));
    }

    public Mono<String> publishFeedback(SuperMarketFeedbackInputDTO superMarketFeedbackInputDTO) {

        FeedbackEntity feedbackEntity = superMarketDataMapper.mapToFeedbackEntity(superMarketFeedbackInputDTO);

        return supermarketRepository.findByGoogleIdAndCity(superMarketFeedbackInputDTO.getGoogleCode(), superMarketFeedbackInputDTO.getCity())
                .switchIfEmpty(createSuperMarketEntity(superMarketFeedbackInputDTO.getGoogleCode(), superMarketFeedbackInputDTO.getCity()))
                .flatMap(supermarketData -> {
                    String feedbackId = UUID.randomUUID().toString();
                    feedbackEntity.setId(feedbackId);

                    return supermarketRepository.insertFeedbackToSuperMarket(superMarketFeedbackInputDTO.getGoogleCode(), superMarketFeedbackInputDTO.getCity(), feedbackEntity)
                            .onErrorMap(e -> new ServiceException(FAILED_TO_INSERT_FEEDBACK))
                            .thenReturn(feedbackId);
                });
    }

    private Mono<SupermarketData> createSuperMarketEntity(String googleCode, String city) {

        SupermarketData supermarketData = new SupermarketData();

        supermarketData.setGoogleId(googleCode);
        supermarketData.setCity(city);

        return Mono.just(supermarketData);
    }
}

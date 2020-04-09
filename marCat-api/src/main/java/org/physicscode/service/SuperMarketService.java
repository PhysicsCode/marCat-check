package org.physicscode.service;

import org.physicscode.domain.entity.FeedbackEntity;
import org.physicscode.domain.entity.SupermarketData;
import org.physicscode.domain.repository.SupermarketRepository;
import org.physicscode.dto.mapper.SupermarketDataMapper;
import org.physicscode.dto.pojo.input.LocatorIdentifier;
import org.physicscode.dto.pojo.input.SuperMarketFeedbackInputDTO;
import org.physicscode.dto.pojo.output.ConsumableDTO;
import org.physicscode.dto.pojo.output.FeedbackResponseDTO;
import org.physicscode.dto.pojo.output.SupermarketDataDTO;
import org.physicscode.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static org.physicscode.exception.ErrorCode.EMPTY_SUPERMARKET_DATA;
import static org.physicscode.exception.ErrorCode.FAILED_TO_INSERT_FEEDBACK;

@RequiredArgsConstructor
@Service
public class SuperMarketService {

    private final SupermarketRepository supermarketRepository;
    private final SupermarketDataMapper superMarketDataMapper;

    public Mono<SupermarketDataDTO> retrieveSuperMarketData(String locationId) {

        return mockedSuperMarketData();

     /*
        return supermarketRepository.findByGoogle(locationId.getGoogleCode())
            .map(superMarketDataMapper::mapToOutput)
            .switchIfEmpty(Mono.error(new ServiceException(EMPTY_SUPERMARKET_DATA)));
            */

    }

    public Mono<String> publishFeedback(SuperMarketFeedbackInputDTO superMarketFeedbackInputDTO) {

        FeedbackEntity feedbackEntity = superMarketDataMapper.mapToFeedbackEntity(superMarketFeedbackInputDTO);

        return supermarketRepository.findByGoogle(superMarketFeedbackInputDTO.getGoogleCode())
                .switchIfEmpty(createSuperMarketEntity(superMarketFeedbackInputDTO.getGoogleCode()))
                .flatMap(supermarketData -> {
                    String feedbackId = UUID.randomUUID().toString();
                    feedbackEntity.setId(feedbackId);

                    return supermarketRepository.insertFeedbackToSuperMarket(superMarketFeedbackInputDTO.getGoogleCode(), feedbackEntity)
                            .onErrorMap(e -> new ServiceException(FAILED_TO_INSERT_FEEDBACK))
                            .thenReturn(feedbackId);
                });
    }

    private Mono<SupermarketData> createSuperMarketEntity(String googleCode) {

        SupermarketData supermarketData = new SupermarketData();

        supermarketData.setGoogleId(googleCode);

        return Mono.just(supermarketData);
    }


    private Mono<SupermarketDataDTO> mockedSuperMarketData() {

        SupermarketDataDTO supermarketDataDTO = new SupermarketDataDTO();


        supermarketDataDTO.setEstimatedQueueMinutesInside(10);
        supermarketDataDTO.setEstimatedQueueMinutesOutside(5);
        supermarketDataDTO.setMaximumCapacity(60);

        supermarketDataDTO.setFeedbackList(List.of(
                new FeedbackResponseDTO("Manolo", "Tus muertos, te veo en el messenger y me desconecto", Instant.now().plusSeconds(2L)),
                new FeedbackResponseDTO("Ana", "No queda papel de culo", Instant.now().plusSeconds(1L)),
                new FeedbackResponseDTO("Albert Paris", "Queda bastante nespresso", Instant.now())
        ));

        supermarketDataDTO.setLackConsumableList(List.of(
                new ConsumableDTO("Papel de culo", Instant.now().minusSeconds(300L)),
                new ConsumableDTO("Papel de culo", Instant.now().minusSeconds(600L)),
                new ConsumableDTO("Papel de culo", Instant.now().minusSeconds(900L)),
                new ConsumableDTO("Papel de culo", Instant.now().minusSeconds(3000L)),
                new ConsumableDTO("Papel de culo", Instant.now().minusSeconds(30000L))
                ));

        return Mono.just(supermarketDataDTO);
    }
}

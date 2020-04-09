package org.physicscode.controller;

import lombok.RequiredArgsConstructor;
import org.physicscode.domain.repository.GeoDataRepository;
import org.physicscode.dto.pojo.input.GeoMapFeeder;
import org.physicscode.dto.pojo.input.LocatorIdentifier;
import org.physicscode.dto.pojo.output.SupermarketDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class CoreController {

    private final GeoDataRepository geoDataRepository;
/*
    @GetMapping("/supermarket")
    public Mono<ResponseEntity<SupermarketDataDTO>> retrieveSuperMarketData(@RequestParam("location-id") LocatorIdentifier locationId) {

        //return superMarketService.retrieveSuperMarketData(locationId)
        //        .map(ResponseEntity::ok);
    }

    @PostMapping("/supermarket")
    public Mono<ResponseEntity<Void>> submitSupermarketFeedback(@RequestBody GeoMapFeeder superMarketFeedbackInputDTO) {

        geoDataRepository.validateFeedbackInput(superMarketFeedbackInputDTO);

        return superMarketService.publishFeedback(superMarketFeedbackInputDTO)
                .map(ResponseEntity::ok);
    }

 */
}

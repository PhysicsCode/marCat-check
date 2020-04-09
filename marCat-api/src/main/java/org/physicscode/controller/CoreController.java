package org.physicscode.controller;

import org.physicscode.dto.pojo.input.LocatorIdentifier;
import org.physicscode.dto.pojo.input.SuperMarketFeedbackInputDTO;
import org.physicscode.dto.pojo.output.SupermarketDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import org.physicscode.service.SuperMarketService;
import org.physicscode.validation.SuperMarketInputConverter;

@RequiredArgsConstructor
@RestController
public class CoreController {

    private final SuperMarketInputConverter superMarketInputConverter;
    private final SuperMarketService superMarketService;

    @GetMapping("/supermarket")
    public Mono<ResponseEntity<SupermarketDataDTO>> retrieveSuperMarketData(@RequestParam("location-id") String locationId) {

        superMarketInputConverter.validateLocationInput(locationId);

        return superMarketService.retrieveSuperMarketData(locationId)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/supermarket")
    public Mono<ResponseEntity<String>> submitSupermarketFeedback(@RequestBody SuperMarketFeedbackInputDTO superMarketFeedbackInputDTO) {

        superMarketInputConverter.validateFeedbackInput(superMarketFeedbackInputDTO);
        return Mono.just(ResponseEntity.ok("Tus muertos, te veo en el messenger y me desconecto"));
        //return superMarketService.publishFeedback(superMarketFeedbackInputDTO)
        //        .map(ResponseEntity::ok);
    }
}

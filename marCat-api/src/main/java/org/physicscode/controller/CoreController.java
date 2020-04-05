package org.physicscode.controller;

import org.physicscode.dto.pojo.input.LocatorIdentifier;
import org.physicscode.dto.pojo.output.SupermarketDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import org.physicscode.service.SuperMarketService;
import org.physicscode.validation.SuperMarketInputConverter;

@RequiredArgsConstructor
@RestController
public class CoreController {

    private final SuperMarketInputConverter superMarketInputConverter;
    private final SuperMarketService superMarketService;

    @GetMapping("/supermarket")
    public Mono<ResponseEntity<SupermarketDataDTO>> retrieveSuperMarketData(@RequestParam("location-id") LocatorIdentifier locationId) {

        superMarketInputConverter.validateLocationInput(locationId);

        return superMarketService.retrieveSuperMarketData(locationId)
                .map(ResponseEntity::ok);
    }
}

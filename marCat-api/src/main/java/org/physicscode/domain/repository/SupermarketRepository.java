package org.physicscode.domain.repository;

import org.physicscode.domain.entity.SupermarketData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface SupermarketRepository extends ReactiveMongoRepository<SupermarketData, ObjectId>, SupermarketCustomRepository {

    Mono<SupermarketData> findByGoogleId(String googleId);
}

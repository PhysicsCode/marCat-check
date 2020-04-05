package org.physicscode.domain.repository;

import com.mongodb.client.result.UpdateResult;
import org.physicscode.domain.entity.FeedbackEntity;
import reactor.core.publisher.Mono;

public interface SupermarketCustomRepository {

    Mono<UpdateResult> insertFeedbackToSuperMarket(String googleCode, String city, FeedbackEntity feedback);
}

package org.physicscode.domain.repository;

import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.physicscode.domain.entity.FeedbackEntity;
import org.physicscode.domain.entity.SupermarketData;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RequiredArgsConstructor
public class SupermarketCustomRepositoryImpl implements SupermarketCustomRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public Mono<UpdateResult> insertFeedbackToSuperMarket(String googleCode, String city, FeedbackEntity feedback) {

        Query query = new Query();
        query.addCriteria(where("googleCode").is(googleCode));
        query.addCriteria(where("city").is(city));
        Update update = new Update();
        update.addToSet("feedbackList", feedback);

        return reactiveMongoTemplate.upsert(query, update, SupermarketData.class);
    }
}

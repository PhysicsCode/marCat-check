package org.physicscode.domain.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document
public class SupermarketData {

    @Id
    private ObjectId objectId;

    @Field
    private String googleId;

    @Field
    private String city;

    @Field
    private List<FeedbackEntity> feedbackList;
}

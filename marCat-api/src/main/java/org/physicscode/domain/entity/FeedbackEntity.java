package org.physicscode.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Data
public class FeedbackEntity {

    @Field
    private String id;

    @Field
    private String message;

    @Field
    private Crowd crowd;

    @Field
    private Map<String, Level> consumableLevel;

    public enum Level {

        HIGH,
        MEDIUM,
        LOW,
        UNDEFINED;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Crowd {

        NONE("0"),
        ONE_TO_TEN("1-10"),
        TEN_TO_THIRTY("10-30"),
        THIRTY_PLUS("30+");

        private final String textAlias;
    }

}

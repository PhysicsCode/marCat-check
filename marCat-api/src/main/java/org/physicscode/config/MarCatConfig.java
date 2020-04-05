package org.physicscode.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class MarCatConfig {

    @Bean
    public Clock clock(){
        return Clock.systemUTC();
    }

    @Bean
    public String mongoDBUri(@Value("${spring.data.mongodb.uri}") String uri) {

        System.out.println(uri);
        return uri;
    }
}

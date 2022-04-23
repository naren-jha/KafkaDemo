package com.njha.KafkaDemo.config;

import com.njha.KafkaDemo.common.KafkaConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    // this will create the topic (if it doesn't exist already) on spring context load
    @Bean
    public NewTopic createUserRegTopic() {
        return TopicBuilder.name(KafkaConstants.USER_REGISTRATION_TOPIC)
                .build();
    }
}

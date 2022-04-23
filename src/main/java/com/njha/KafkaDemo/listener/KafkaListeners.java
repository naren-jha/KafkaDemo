package com.njha.KafkaDemo.listener;

import com.njha.KafkaDemo.common.KafkaConstants;
import com.njha.KafkaDemo.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(
            topics = KafkaConstants.USER_REGISTRATION_TOPIC,
            groupId = "onboarding-consumer",
            containerFactory = "userConsumerListenerFactory"
    )
    public void userRegistrationTopicListener(@Payload User user) {
        System.out.println("New user registered..");
        System.out.println(user);
    }
}

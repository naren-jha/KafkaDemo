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
    public void userRegistrationTopicListener(User user) {
        System.out.println("New user registered..");
        System.out.println(user);
    }

    @KafkaListener(
            topics = KafkaConstants.USER_REGISTRATION_TOPIC,
            groupId = "onboarding-consumer2",
            containerFactory = "userConsumerListenerFactory"
    )
    public void userRegistrationTopicListener2(User user) {
        System.out.println("Second listener for user registration..");
        System.out.println(user);
    }

    @KafkaListener(
            topics = KafkaConstants.USER_REGISTRATION_TOPIC,
            groupId = "onboarding-consumer3",
            containerFactory = "adultUserConsumerListenerFactory"
    )
    public void adultUserRegistrationListener(User user) {
        System.out.println("A new adult user registered..");
        System.out.println(user);
    }
}

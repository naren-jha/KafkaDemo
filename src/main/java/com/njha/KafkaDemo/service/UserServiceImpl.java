package com.njha.KafkaDemo.service;

import com.njha.KafkaDemo.common.KafkaConstants;
import com.njha.KafkaDemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    @Override
    public void pushUserToKafka(User user) {
        kafkaTemplate.send(KafkaConstants.USER_REGISTRATION_TOPIC, "bengaluru", user);
    }
}

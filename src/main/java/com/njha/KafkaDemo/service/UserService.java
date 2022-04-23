package com.njha.KafkaDemo.service;

import com.njha.KafkaDemo.model.User;

public interface UserService {
    public void pushUserToKafka(User user);
}

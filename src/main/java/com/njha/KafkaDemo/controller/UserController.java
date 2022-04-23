package com.njha.KafkaDemo.controller;

import com.njha.KafkaDemo.model.User;
import com.njha.KafkaDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/pushToKafka")
    public ResponseEntity<String> pushUserToKafka(@RequestBody User user) {
        userService.pushUserToKafka(user);
        return ResponseEntity.ok("successfully pushed user object to kafka.");
    }
}

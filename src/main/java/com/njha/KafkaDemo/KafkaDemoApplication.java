package com.njha.KafkaDemo;

import com.njha.KafkaDemo.common.KafkaConstants;
import com.njha.KafkaDemo.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class KafkaDemoApplication {

	// https://youtu.be/SqVfCyfCJqw

	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(KafkaTemplate<String, User> kafkaTemplate) {
//		return args -> {
//			for (int i = 0; i < 10_000; ++i) {
//				kafkaTemplate.send(KafkaConstants.USER_REGISTRATION_TOPIC, "bengaluru",
//						new User(Long.valueOf(i), "Tom", 20+i));
//			}
//		};
//	}

}

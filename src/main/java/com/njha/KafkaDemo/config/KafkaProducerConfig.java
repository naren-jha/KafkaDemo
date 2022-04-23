package com.njha.KafkaDemo.config;

import com.njha.KafkaDemo.model.User;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    public String bootstrapServer;

    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // https://kafka.apache.org/documentation/#producerconfigs

        // props.put(ProducerConfig.RETRIES_CONFIG, 3); // retry sending if send failed
        // props.put(ProducerConfig.BATCH_SIZE_CONFIG, 100000); // Batch records together into fewer requests. Default:	16384

        // props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoundRobinPartitioner); // A class to use to determine which partition to be send to

        // set maximum amount of time the client will wait for the response of a request. If the response is not received before the timeout
        // elapses the client will resend the request if necessary or fail the request if retries are exhausted
        // props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 10000); // Default: 30000 (30 seconds)
        return props;
    }

    @Bean
    public ProducerFactory<String, User> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, User> kafkaTemplate(ProducerFactory<String, User> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}

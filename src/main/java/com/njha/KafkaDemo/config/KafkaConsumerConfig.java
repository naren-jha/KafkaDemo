package com.njha.KafkaDemo.config;

import com.njha.KafkaDemo.model.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    public String bootstrapServer;

    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        // https://kafka.apache.org/documentation/#consumerconfigs

        // we can set consumer group at config/factory level. then this can be ignored at the listener level.
        // props.put(ConsumerConfig.GROUP_ID_CONFIG, "onboarding-consumer");

        // what should happen when there is no initial offset in Kafka or if the current offset does not exist any more on the server
        // for example a new consumer groups - this will set consumption strategy for new consumer groups
        // props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest"); // values: earliest, latest. Default value: latest
        // earliest: When a new consumer group is registered for the very first time. All existing messages in the topic will be replayed to the consumer group.
        // latest: Default value. In this case, new consumer groups too will wait for new messages to be pushed in the topic. It'll not consume existing messages.
        // https://stackoverflow.com/a/63081826/4210068

        // Allow automatic topic creation on the broker when subscribing to or assigning a topic
        // props.put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, true);

        return props;
    }

    @Bean
    public ConsumerFactory<String, User> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(), new JsonDeserializer<>(User.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, User> userConsumerListenerFactory(ConsumerFactory<String, User> consumerFactory) {
        
        // https://docs.spring.io/spring-kafka/docs/current/reference/html/#message-listener-container
        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        // factory.setConcurrency(3); // https://stackoverflow.com/a/55033589/4210068
        // https://stackoverflow.com/a/68978435/4210068
        // https://docs.spring.io/spring-kafka/docs/current/reference/html/#message-listener-container

        // factory.setBatchListener(true);
        // factory.getContainerProperties().setPollTimeout(3000);
        // factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.BATCH);
        // factory.setAckDiscarded(true);

        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, User> adultUserConsumerListenerFactory(ConsumerFactory<String, User> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setRecordFilterStrategy(record -> record.value().getAge() < 18); // ignores users of age < 18

        /*factory.setRecordFilterStrategy(consumerRecord -> {
            // we can do filtering here based on 'key' or 'value' of the message
            System.out.println(consumerRecord.key());
            System.out.println(consumerRecord.value());
            User user = consumerRecord.value();
            return user.getAge() < 18;
        });*/

        return factory;
    }
}

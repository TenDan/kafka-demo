package pl.tendan.kafkademo.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic messagesTopic() {
        return TopicBuilder.name("messages")
                .build();
    }

    @Bean
    public NewTopic tensionAndIntensityTopic() {
        return TopicBuilder.name("tension_and_intensity")
                .build();
    }
}

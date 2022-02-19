package pl.tendan.kafkademo.config.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import pl.tendan.kafkademo.model.ElectricityData;
import pl.tendan.kafkademo.util.deserializer.ElectricityDataDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TensionAndIntensityConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    /* Tension and intensity consumer configuration */

    public Map<String, Object> tensionAndIntensityConsumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ElectricityDataDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<Integer, ElectricityData> tensionAndIntensityConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(tensionAndIntensityConsumerConfig());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, ElectricityData>>
    tensionAndIntensityFactory(
            ConsumerFactory<Integer, ElectricityData> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<Integer, ElectricityData> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}

package pl.tendan.kafkademo.config.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import pl.tendan.kafkademo.model.ElectricityData;
import pl.tendan.kafkademo.util.serializer.ElectricityDataSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TensionAndIntensityProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    /* Tension and intensity producer configuration */

    public Map<String, Object> tensionAndIntensityProducerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ElectricityDataSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<Integer, ElectricityData> tensionAndIntensityProducerFactory() {
        return new DefaultKafkaProducerFactory<>(tensionAndIntensityProducerConfig());
    }

    @Bean
    public KafkaTemplate<Integer, ElectricityData> tensionAndIntensity(
            ProducerFactory<Integer, ElectricityData> producerFactory
    ) {
        return new KafkaTemplate<>(producerFactory);
    }
}

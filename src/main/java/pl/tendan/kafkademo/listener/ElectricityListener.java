package pl.tendan.kafkademo.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.tendan.kafkademo.model.ElectricityData;
import pl.tendan.kafkademo.model.Message;

@Component
@Slf4j
@RequiredArgsConstructor
public class ElectricityListener {

    private final KafkaTemplate<String, Message> messageTemplate;

    @KafkaListener(
            topics = "tension_and_intensity",
            groupId = "resistanceListener",
            containerFactory = "tensionAndIntensityFactory"
    )
    void resistanceListener(ElectricityData data) {
        double resistance = data.getTension() / data.getIntensity();
        messageTemplate.send("messages", Message.builder().content("Calculated the resistance").build());
        log.info(String.format("Calculated resistance: %f", resistance));
    }

    @KafkaListener(
            topics = "tension_and_intensity",
            groupId = "powerListener",
            containerFactory = "tensionAndIntensityFactory"
    )
    void powerListener(ElectricityData data) {
        double power = data.getTension() * data.getIntensity();
        messageTemplate.send("messages", Message.builder().content("Calculated the power").build());
        log.info(String.format("Calculated power: %f", power));
    }
}

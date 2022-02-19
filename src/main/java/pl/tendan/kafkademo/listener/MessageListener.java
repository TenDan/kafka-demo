package pl.tendan.kafkademo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.tendan.kafkademo.model.Message;

@Component
@Slf4j
public class MessageListener {

    @KafkaListener(
            topics = "messages",
            groupId = "messageListener",
            containerFactory = "messageFactory"
    )
    void listener(Message data) {
        log.info(String.format("%s at %s", data.getContent(), data.getCreationDate()));
    }
}

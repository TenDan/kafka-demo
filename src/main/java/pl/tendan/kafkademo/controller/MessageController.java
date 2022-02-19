package pl.tendan.kafkademo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import pl.tendan.kafkademo.model.Message;
import pl.tendan.kafkademo.model.request.MessageRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/messages")
public class MessageController {

    private final KafkaTemplate<String, Message> messageTemplate;

    @GetMapping
    public ResponseEntity<String> sendExampleMessage() {
        var message = Message.builder()
                .content("Some example message")
                .build();
        messageTemplate.send("messages", message);
        return ResponseEntity.ok("Success");
    }

    @PostMapping
    public ResponseEntity<String> sendRequestedMessage(@RequestBody MessageRequest request) {
        var message = Message.builder()
                .content(request.getContent())
                .build();
        messageTemplate.send("messages", message);
        return ResponseEntity.noContent().build();
    }
}

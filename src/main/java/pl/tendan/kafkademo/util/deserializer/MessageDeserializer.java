package pl.tendan.kafkademo.util.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import pl.tendan.kafkademo.model.Message;

import java.io.IOException;

public class MessageDeserializer implements Deserializer<Message> {

    @Override
    public Message deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        Message returnable = null;

        try {
            returnable = mapper.readValue(data, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnable;
    }
}

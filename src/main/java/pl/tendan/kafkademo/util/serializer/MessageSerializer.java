package pl.tendan.kafkademo.util.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import pl.tendan.kafkademo.model.Message;

public class MessageSerializer implements Serializer<Message> {

    @Override
    public byte[] serialize(String topic, Message data) {
        byte[] returnable = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (data == null)
                return null;
            else
                returnable = mapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return returnable;
    }
}

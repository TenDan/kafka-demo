package pl.tendan.kafkademo.util.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import pl.tendan.kafkademo.model.ElectricityData;
import pl.tendan.kafkademo.model.Message;

import java.io.IOException;

public class ElectricityDataDeserializer implements Deserializer<ElectricityData> {
    @Override
    public ElectricityData deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        ElectricityData returnable = null;

        try {
            returnable = mapper.readValue(data, ElectricityData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnable;
    }
}

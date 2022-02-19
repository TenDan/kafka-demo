package pl.tendan.kafkademo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.tendan.kafkademo.model.ElectricityData;

@Service
@RequiredArgsConstructor
public class ElectricityService {

    private final KafkaTemplate<Integer, ElectricityData> tensionAndIntensityTemplate;

    public void passTensionIntensityValues(Double[]... values) {
        for (Double[] value : values) {
            var data = ElectricityData.builder()
                    .tension(value[0])
                    .intensity(value[1])
                    .build();
            tensionAndIntensityTemplate.send("tension_and_intensity", data);
        }
    }
}

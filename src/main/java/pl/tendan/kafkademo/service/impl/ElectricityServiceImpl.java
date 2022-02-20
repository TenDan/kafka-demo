package pl.tendan.kafkademo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import pl.tendan.kafkademo.model.ElectricityData;
import pl.tendan.kafkademo.service.ElectricityService;

@Service
@RequiredArgsConstructor
public class ElectricityServiceImpl implements ElectricityService {

    private final KafkaTemplate<Integer, ElectricityData> tensionAndIntensityTemplate;

    @Override
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

package pl.tendan.kafkademo.model.request;

import lombok.Data;

@Data
public class CircuitRequest {
    private Double[][] tensionIntensityTable;
}

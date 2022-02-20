package pl.tendan.kafkademo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ElectricityData {

    private Double tension;

    private Double intensity;

    @JsonIgnore
    public Double getResistance() {
        return this.tension / this.intensity;
    }

    @JsonIgnore
    public Double getPower() {
        return this.tension * this.intensity;
    }
}

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
        return Math.round(this.tension / this.intensity * 100) / 100.0;
    }

    @JsonIgnore
    public Double getPower() {
        return Math.round(this.tension * this.intensity * 100) / 100.0;
    }
}

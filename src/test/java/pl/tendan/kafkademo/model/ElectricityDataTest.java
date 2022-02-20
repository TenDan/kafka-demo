package pl.tendan.kafkademo.model;

import org.assertj.core.data.Offset;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ElectricityDataTest {

    @ParameterizedTest
    @CsvSource(value = {
            "50,30",
            "230,5",
            "25,25",
            "100,25"
    }, delimiter = ',')
    void shouldApplyValuesFromAllArgsConstructor(String tensionString, String intensityString) {
        // given
        double tension = Double.parseDouble(tensionString);
        double intensity = Double.parseDouble(intensityString);

        // when
        ElectricityData electricityData = new ElectricityData(tension, intensity);

        // then
        assertThat(electricityData.getTension())
                .isNotNull()
                .isEqualTo(tension);
        assertThat(electricityData.getIntensity())
                .isNotNull()
                .isEqualTo(intensity);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "50,30",
            "230,5",
            "25,25",
            "100,25"
    }, delimiter = ',')
    void shouldApplyValuesFromBuilder(String tensionString, String intensityString) {
        // given
        double tension = Double.parseDouble(tensionString);
        double intensity = Double.parseDouble(intensityString);

        // when
        ElectricityData electricityData = ElectricityData.builder()
                .tension(tension)
                .intensity(intensity)
                .build();

        // then
        assertThat(electricityData.getTension())
                .isNotNull()
                .isEqualTo(tension);
        assertThat(electricityData.getIntensity())
                .isNotNull()
                .isEqualTo(intensity);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "50,30",
            "230,5",
            "25,25",
            "100,25"
    }, delimiter = ',')
    void shouldApplyValuesFromSetters(String tensionString, String intensityString) {
        // given
        double tension = Double.parseDouble(tensionString);
        double intensity = Double.parseDouble(intensityString);
        ElectricityData electricityData = new ElectricityData();

        // when
        electricityData.setTension(tension);
        electricityData.setIntensity(intensity);

        // then
        assertThat(electricityData.getTension())
                .isNotNull()
                .isEqualTo(tension);
        assertThat(electricityData.getIntensity())
                .isNotNull()
                .isEqualTo(intensity);
    }

    @Test
    void shouldCompareTwoObjectsWithDifferentData() {
        // given
        ElectricityData electricityData1 = new ElectricityData(5.0, 5.0);
        ElectricityData electricityData2 = new ElectricityData(25.0, 5.0);

        // when
        var actual1 = electricityData1.equals(electricityData2);
        var actual2 = electricityData2.equals(electricityData1);

        // then
        assertThat(actual1)
                .isFalse();
        assertThat(actual2)
                .isFalse();
    }

    @Test
    void shouldCompareTwoObjectsWithSameData() {
        // given
        ElectricityData electricityData1 = new ElectricityData(5.0, 5.0);
        ElectricityData electricityData2 = new ElectricityData(5.0, 5.0);

        // when
        var actual1 = electricityData1.equals(electricityData2);
        var actual2 = electricityData2.equals(electricityData1);

        // then
        assertThat(actual1)
                .isTrue();
        assertThat(actual2)
                .isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "50,30",
            "230,5",
            "25,25",
            "100,25"
    }, delimiter = ',')
    void shouldProperlyCalculateResistance(String tensionString, String intensityString) {
        // given
        double tension = Double.parseDouble(tensionString);
        double intensity = Double.parseDouble(intensityString);

        // when
        ElectricityData electricityData = new ElectricityData(tension, intensity);

        // then
        assertThat(electricityData.getResistance())
                .isCloseTo(tension / intensity, Offset.offset(0.01));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "50,30",
            "230,5",
            "25,25",
            "100,25"
    }, delimiter = ',')
    void shouldProperlyCalculatePower(String tensionString, String intensityString) {
        // given
        double tension = Double.parseDouble(tensionString);
        double intensity = Double.parseDouble(intensityString);

        // when
        ElectricityData electricityData = new ElectricityData(tension, intensity);

        // then
        assertThat(electricityData.getPower())
                .isCloseTo(tension * intensity, Offset.offset(0.01));
    }
}
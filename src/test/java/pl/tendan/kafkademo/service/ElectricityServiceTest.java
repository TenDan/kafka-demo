package pl.tendan.kafkademo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import pl.tendan.kafkademo.model.ElectricityData;
import pl.tendan.kafkademo.service.impl.ElectricityServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ElectricityServiceTest {

    @InjectMocks
    private ElectricityServiceImpl underTest;

    @Mock
    KafkaTemplate<Integer, ElectricityData> mockedTemplate;

    @BeforeEach
    void setUp() {
        underTest = new ElectricityServiceImpl(mockedTemplate);
    }

    @Test
    void shouldInvokeSendingToKafka() {
        // given
        Double[][] data = {
                {25.0, 32.0},
                {58.0, 3.0},
                {230.0, 5.0},
                {140.0, 20.0}
        };
        given(mockedTemplate.send(eq("tension_and_intensity"), any(ElectricityData.class)))
                .willCallRealMethod();

        // when
        underTest.passTensionIntensityValues(data);

        // then
        then(mockedTemplate)
                .should(times(4))
                .send(eq("tension_and_intensity"), any(ElectricityData.class));
    }

    @Test
    void shouldRetrieveValidObjectToSend() {
        // given
        Double[][] data = {
                {25.0, 32.0}
        };
        given(mockedTemplate.send(eq("tension_and_intensity"), any(ElectricityData.class)))
                .willCallRealMethod();

        // when
        underTest.passTensionIntensityValues(data);

        // then
        ArgumentCaptor<ElectricityData> electricityDataArgumentCaptor = ArgumentCaptor.forClass(ElectricityData.class);
        then(mockedTemplate)
                .should(times(1))
                .send(eq("tension_and_intensity"), electricityDataArgumentCaptor.capture());
        assertThat(electricityDataArgumentCaptor.getValue().getTension())
                .isEqualTo(25.0);
        assertThat(electricityDataArgumentCaptor.getValue().getIntensity())
                .isEqualTo(32.0);
    }
}
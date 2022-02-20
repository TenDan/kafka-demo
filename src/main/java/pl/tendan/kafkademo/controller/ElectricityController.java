package pl.tendan.kafkademo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tendan.kafkademo.model.request.CircuitRequest;
import pl.tendan.kafkademo.service.ElectricityService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/electricity")
public class ElectricityController {

    private final ElectricityService electricityService;

    @PostMapping
    public ResponseEntity<?> sendCircuitData(@RequestBody CircuitRequest request) {
        electricityService.passTensionIntensityValues(request.getTensionIntensityTable());
        return ResponseEntity.ok().build();
    }
}

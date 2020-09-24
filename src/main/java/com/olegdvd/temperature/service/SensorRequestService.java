package com.olegdvd.temperature.service;

import com.olegdvd.temperature.domain.SensorData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class SensorRequestService {

    private final RestTemplate restTemplate;
    private static final String URL = "";

    public SensorRequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<SensorData> getDataFromSensor(String sensorId, String urlString) {
        return mapToObjWithTimestamp(restTemplate.getForEntity(urlString, SensorData.class));
    }

    private Optional<SensorData> mapToObjWithTimestamp(ResponseEntity<SensorData> responseEntity) {
        SensorData body = responseEntity.getBody();
        if (Objects.nonNull(body)) {
            body.setTimestamp(LocalDateTime.now());
            return Optional.of(body);
        }
        return Optional.empty();
    }
}

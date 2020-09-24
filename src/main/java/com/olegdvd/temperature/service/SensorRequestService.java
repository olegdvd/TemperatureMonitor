package com.olegdvd.temperature.service;

import com.olegdvd.temperature.domain.SensorData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SensorRequestService {

    private final RestTemplate restTemplate;
    private static final String URL = "";

    public SensorRequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<SensorData> getDataFromSensor(String sensorId){
        ResponseEntity<SensorData> sensorDataResponseEntity = restTemplate.getForEntity(URL, SensorData.class);
        System.out.println(sensorId);
        return sensorDataResponseEntity;
    }
}

package com.olegdvd.temperature.web;

import com.olegdvd.temperature.domain.SensorData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/temperature")
public class HomeController {

    private final RestTemplate restTemplate;
    private static final String URL = "";

    public HomeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/home")
    public ResponseEntity<SensorData> home() {
        ResponseEntity<SensorData> sensorDataResponseEntity = restTemplate.getForEntity(URL, SensorData.class);
        return sensorDataResponseEntity;
    }
}
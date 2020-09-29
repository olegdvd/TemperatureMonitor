package com.olegdvd.temperature.web;

import com.olegdvd.temperature.domain.GatheredSensorData;
import com.olegdvd.temperature.repository.GatheredSensorDateRepository;
import com.olegdvd.temperature.service.GatheredSensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/data/sensor")
public class SensorDataController {

    private final GatheredSensorDataService gatheredSensorDataService;

    @Autowired
    public SensorDataController(GatheredSensorDataService gatheredSensorDataService) {
        this.gatheredSensorDataService = gatheredSensorDataService;
    }


    @GetMapping
    public List<GatheredSensorData> getGatheredSensorData() {
        return gatheredSensorDataService.getAllSensorData();
    }

}

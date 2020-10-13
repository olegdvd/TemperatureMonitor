package com.olegdvd.temperature.web;

import com.olegdvd.temperature.domain.GatheredSensorData;
import com.olegdvd.temperature.service.GatheredSensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/data/sensor")
@CrossOrigin("*")
public class SensorDataController {

    private final GatheredSensorDataService gatheredSensorDataService;

    @Autowired
    public SensorDataController(GatheredSensorDataService gatheredSensorDataService) {
        this.gatheredSensorDataService = gatheredSensorDataService;
    }


    @GetMapping("")
    public List<GatheredSensorData> getGatheredSensorData() {
        return gatheredSensorDataService.getAllSensorData();
    }

    @GetMapping("/{sensorId}")
    public List<GatheredSensorData> getGatheredSensorDataById(@PathVariable("sensorId") String sensorId) {
        return gatheredSensorDataService.getSensorData(sensorId);
    }

    @GetMapping("/{sensorId}/{dateFrom}/{dateTo}")
    public List<GatheredSensorData> getGatheredSensorDataByTime(@PathVariable("sensorId") String sensorId,
                                                                @PathVariable("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
                                                                @PathVariable("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo) {
        List<GatheredSensorData> gatheredSensorDataList = gatheredSensorDataService.getSensorData(sensorId);
        return gatheredSensorDataList.stream()
                .filter(data -> data.getTimestamp().compareTo(dateFrom) > 0)
                .filter(data -> data.getTimestamp().compareTo(dateTo) < 0)
                .collect(Collectors.toList());
    }
}

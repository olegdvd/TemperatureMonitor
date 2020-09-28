package com.olegdvd.temperature.service;

import com.olegdvd.temperature.domain.GatheredSensorData;
import com.olegdvd.temperature.domain.SensorData;
import com.olegdvd.temperature.domain.SensorInfo;
import com.olegdvd.temperature.repository.GatheredSensorDateRepository;
import com.olegdvd.temperature.repository.InfoRepository;
import com.olegdvd.temperature.repository.SensorInfoLocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SensorRequestService {

    private final RestTemplate restTemplate;
    private final InfoRepository<SensorInfo> repository;
    private final GatheredSensorDateRepository gatheredSensorDateRepo;

    @Autowired
    public SensorRequestService(RestTemplate restTemplate, SensorInfoLocalRepository repository, GatheredSensorDateRepository gatheredSensorDateRepo) {
        this.restTemplate = restTemplate;
        this.repository = repository;
        this.gatheredSensorDateRepo = gatheredSensorDateRepo;
    }

    @Scheduled(fixedRate = 5000)
    public int processSensors(){
        final List<GatheredSensorData> gatheredList = repository.getAll().stream()
                .map(this::getDataFromSensor)
                .collect(Collectors.toList());

        gatheredSensorDateRepo.saveAll(gatheredList);

        return gatheredList.size();

    }

    public GatheredSensorData getDataFromSensor(SensorInfo sensorInfo) {
        Optional<SensorData> opSensorData = mapToObjWithTimestamp(restTemplate.getForEntity(sensorInfo.getUrl(), SensorData.class));
        return opSensorData
                .map(sensorData -> new GatheredSensorData(sensorData, sensorInfo))
                .orElse(new GatheredSensorData(sensorInfo));
    }

    private Optional<SensorData> mapToObjWithTimestamp(ResponseEntity<SensorData> responseEntity) {
        SensorData body = responseEntity.getBody();
        if (Objects.nonNull(body)) {
            return Optional.of(body);
        }
        return Optional.empty();
    }


}

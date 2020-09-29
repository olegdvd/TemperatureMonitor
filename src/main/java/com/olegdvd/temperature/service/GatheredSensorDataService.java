package com.olegdvd.temperature.service;

import com.olegdvd.temperature.domain.GatheredSensorData;
import com.olegdvd.temperature.repository.GatheredSensorDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatheredSensorDataService {

    private final GatheredSensorDateRepository gatheredSensorDateRepository;

    @Autowired
    public GatheredSensorDataService(GatheredSensorDateRepository gatheredSensorDateRepository) {
        this.gatheredSensorDateRepository = gatheredSensorDateRepository;
    }

    public List<GatheredSensorData> getAllSensorData() {
        return gatheredSensorDateRepository.findAll();
    }
}


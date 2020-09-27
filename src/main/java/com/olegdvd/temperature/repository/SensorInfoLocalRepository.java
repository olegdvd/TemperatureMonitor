package com.olegdvd.temperature.repository;

import com.olegdvd.temperature.domain.SensorInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SensorInfoLocalRepository implements InfoRepository<SensorInfo> {

    private final List<SensorInfo> list;

    public SensorInfoLocalRepository() {
        list = Arrays.asList(
                new SensorInfo("1","http://192.168.0.77/value")
        );
    }

    @Override
    public List<SensorInfo> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public SensorInfo findOne(SensorInfo entity) {
        return null;
    }

    @Override
    public SensorInfo findById(String id) {
        return null;
    }

    @Override
    public SensorInfo save(SensorInfo entity) {
        return null;
    }


}


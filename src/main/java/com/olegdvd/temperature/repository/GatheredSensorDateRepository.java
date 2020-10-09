package com.olegdvd.temperature.repository;

import com.olegdvd.temperature.domain.GatheredSensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GatheredSensorDateRepository extends JpaRepository <GatheredSensorData, String> {

    List<GatheredSensorData> findAllBySensorId(String sensorId);

}

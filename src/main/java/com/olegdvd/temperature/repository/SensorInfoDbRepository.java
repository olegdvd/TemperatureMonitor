package com.olegdvd.temperature.repository;

import com.olegdvd.temperature.domain.SensorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorInfoDbRepository extends JpaRepository<SensorInfo, String> {

}

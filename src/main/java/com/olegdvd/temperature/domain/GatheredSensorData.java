package com.olegdvd.temperature.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class GatheredSensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sensorId;
    private double value1;
    private double value2;
    private double value3;
    private double value4;
    private LocalDateTime timestamp;

    public GatheredSensorData() {
        //empty
    }

    public GatheredSensorData(SensorData sensorData, SensorInfo sensorInfo) {
        this.sensorId = sensorInfo.getId();
        this.value1 = sensorData.getValue1();
        this.value2 = sensorData.getValue2();
        this.value3 = sensorData.getValue3();
        this.value4 = sensorData.getValue4();
        timestamp = LocalDateTime.now();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GatheredSensorData(SensorInfo sensorInfo) {
        this.sensorId = sensorInfo.getId();
        this.value1 = 0;
        this.value2 = 0;
        this.value3 = 0;
        this.value4 = 0;
        timestamp = LocalDateTime.now();
    }

    public void setSensorId(String id) {
        this.sensorId = id;
    }

    public String getSensorId() {
        return sensorId;
    }

    public double getValue1() {
        return value1;
    }

    public void setValue1(double value1) {
        this.value1 = value1;
    }

    public double getValue2() {
        return value2;
    }

    public void setValue2(double value2) {
        this.value2 = value2;
    }

    public double getValue3() {
        return value3;
    }

    public void setValue3(double value3) {
        this.value3 = value3;
    }

    public double getValue4() {
        return value4;
    }

    public void setValue4(double value4) {
        this.value4 = value4;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

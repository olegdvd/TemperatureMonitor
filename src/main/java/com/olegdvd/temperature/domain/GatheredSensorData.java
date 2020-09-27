package com.olegdvd.temperature.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class GatheredSensorData {

    @EmbeddedId
    private GatheredId gatheredId;
    private String id;
    private double value1;
    private double value2;
    private double value3;
    private double value4;
    private LocalDateTime timestamp;

    public GatheredSensorData() {
        //empty
    }

    public GatheredSensorData(SensorData sensorData, SensorInfo sensorInfo) {
        this.id = sensorInfo.getId();
        this.value1 = sensorData.getValue1();
        this.value2 = sensorData.getValue2();
        this.value3 = sensorData.getValue3();
        this.value4 = sensorData.getValue4();
        timestamp = LocalDateTime.now();
    }

    public GatheredSensorData(SensorInfo sensorInfo) {
        this.id = sensorInfo.getId();
        this.value1 = 0;
        this.value2 = 0;
        this.value3 = 0;
        this.value4 = 0;
        timestamp = LocalDateTime.now();
    }

    public GatheredId getGatheredId() {
        return gatheredId;
    }

    public void setGatheredId(GatheredId gatheredId) {
        this.gatheredId = gatheredId;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
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

@Embeddable
 class GatheredId{

    private String id;
    private LocalDateTime timestamp;

    public GatheredId() {
        //empty
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}